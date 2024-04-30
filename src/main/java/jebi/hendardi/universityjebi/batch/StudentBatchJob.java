package jebi.hendardi.universityjebi.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class StudentBatchJob {

    @Autowired
    private StudentBatchStep studentBatchStep;

    @Bean
    public Job studentBatchJob() {
        return jobBuilder("studentBatchJob")
                .incrementer(new RunIdIncrementer())
                .start(studentBatchStep.studentBatchStep())
                .build();
    }
}