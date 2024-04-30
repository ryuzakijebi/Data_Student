package jebi.hendardi.universityjebi.batch;

import jebi.hendardi.universityjebi.dto.StudentDto;
import jebi.hendardi.universityjebi.service.impl.StudentServiceImpl;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class StudentBatchStep {

    @Autowired
    private StudentServiceImpl studentService;

    @Bean
    public Step studentBatchStep() {
        return stepBuilder("studentBatchStep")
                .<StudentDto, StudentDto>chunk(500)
                .reader(new StudentBatchReader())
                .writer(studentBatchWriter())
                .build();
    }

    @Bean
    public ItemWriter<StudentDto> studentBatchWriter() {
        return new ListItemWriter<StudentDto>() {
            @Override
            public void write(List<? extends StudentDto> items) throws Exception {
                studentService.createStudents(items);
            }
        };
    }
}