package jebi.hendardi.universityjebi.controller;

import jebi.hendardi.universityjebi.dto.StudentDto;
import jebi.hendardi.universityjebi.entity.Student;
import jebi.hendardi.universityjebi.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    //REST API Create Student
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        StudentDto savedStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    //REST API Get Student
    @GetMapping("{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id")Long studentID){
        StudentDto studentDto = studentService.getStudentById(studentID);
        return ResponseEntity.ok(studentDto);
    }

    //REST API Get All Students
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        List<StudentDto> students = studentService.getAllStudents();
        return  ResponseEntity.ok(students);
    }

    //REST API Update Student
    @PutMapping("{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long studendId,
                                                    @RequestBody StudentDto updateStudent){
        StudentDto studentDto = studentService.updateStudent(studendId, updateStudent);
        return ResponseEntity.ok(studentDto);
    }

    //REST API Delete Student
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Student deleted successfully.");
    }

}

