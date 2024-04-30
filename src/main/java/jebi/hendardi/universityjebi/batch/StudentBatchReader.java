package jebi.hendardi.universityjebi.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jebi.hendardi.universityjebi.dto.StudentDto;

import org.springframework.batch.item.support.AbstractItemStreamItemReader;


public class StudentBatchReader extends AbstractItemStreamItemReader<StudentDto> {

    private List<StudentDto> students = new ArrayList<>();

    @Override
    public StudentDto read() throws Exception {
        if (students.isEmpty()) {
            generateStudents();
        }
        return students.remove(0);
    }

    private void generateStudents() {
        String[] departments = {"Information Technology", "Computer Engineering", "Information Systems", "Software Engineering", "Digital Business"};
        Random random = new Random();

        for (int i = 0; i < 500; i++) {
            StudentDto studentDto = new StudentDto();
            studentDto.setFirstName("Student " + i);
            studentDto.setLastName("Lastname " + i);
            studentDto.setEmail("student" + i + "@example.com");
            studentDto.setDepartment(departments[random.nextInt(departments.length)]);
            students.add(studentDto);
        }
    }
}