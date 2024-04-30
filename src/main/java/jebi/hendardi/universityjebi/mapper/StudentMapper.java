package jebi.hendardi.universityjebi.mapper;

import jebi.hendardi.universityjebi.dto.StudentDto;
import jebi.hendardi.universityjebi.entity.Student;

public class StudentMapper {
    public static StudentDto mapToStudentDto(Student student){
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getDepartment()
        );
    }

    public static Student mapToStudent(StudentDto studentDto){
        return new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getEmail(),
                studentDto.getDepartment()
        );
    }
}
