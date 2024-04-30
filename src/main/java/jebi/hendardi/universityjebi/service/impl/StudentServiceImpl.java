package jebi.hendardi.universityjebi.service.impl;

import jebi.hendardi.universityjebi.dto.StudentDto;
import jebi.hendardi.universityjebi.entity.Student;
import jebi.hendardi.universityjebi.exception.ResourceNotFoundException;
import jebi.hendardi.universityjebi.mapper.StudentMapper;
import jebi.hendardi.universityjebi.repository.StudentRepository;
import jebi.hendardi.universityjebi.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        // Set department
        student.setDepartment(studentDto.getDepartment());
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentID) {
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new ResourceNotFoundException("Student is not exist with given id : " + studentID));

        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map((student) -> StudentMapper.mapToStudentDto(student))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updateStudent) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student is not exists with given id : " + studentId));

        student.setFirstName(updateStudent.getFirstName());
        student.setLastName(updateStudent.getLastName());
        student.setEmail(updateStudent.getEmail());

        Student updateStudentObj = studentRepository.save(student);

        return StudentMapper.mapToStudentDto(updateStudentObj);
    }

    @Override
    public void deleteStudent(Long studentId) {

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student is not exists with given id : " + studentId));
        studentRepository.deleteById(studentId);
    }
}
