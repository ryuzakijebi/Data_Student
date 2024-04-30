package jebi.hendardi.universityjebi.config;

import jebi.hendardi.universityjebi.dto.StudentDto;
import jebi.hendardi.universityjebi.service.DepartmentService;
import jebi.hendardi.universityjebi.service.StudentService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class StudentBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private StudentService studentService;

    @Bean
    public Job studentBatchJob() {
        return jobBuilderFactory.get("studentBatchJob")
                .start(studentBatchStep())
                .build();
    }

    @Bean
    public Step studentBatchStep() {
        return stepBuilderFactory.get("studentBatchStep")
                .partitioner(studentStep().getName(), new DepartmentPartitioner())
                .step(studentStep())
                .build();
    }

    @Bean
    public Step studentStep() {
        return stepBuilderFactory.get("studentStep")
                .<StudentDto, StudentDto>chunk(500)
                .reader(studentItemReader())
                .processor(studentItemProcessor())
                .writer(studentItemWriter())
                .build();
    }

    @Bean
    public StudentItemReader studentItemReader() {
        return new StudentItemReader(studentService, departmentService);
    }

    @Bean
    public StudentItemProcessor studentItemProcessor() {
        return new StudentItemProcessor();
    }

    @Bean
    public StudentItemWriter studentItemWriter() {
        return new StudentItemWriter(studentService);
    }
}