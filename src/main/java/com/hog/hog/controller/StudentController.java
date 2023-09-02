package com.hog.hog.controller;

import com.hog.hog.dto.StudentDto;
import com.hog.hog.model.Student;
import com.hog.hog.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public void createStudent(@RequestBody StudentDto student) {
        studentService.createStudent(student);
    }

    @PutMapping(path = "/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody StudentDto student) {
        studentService.updateStudent(id, student);
    }

    @GetMapping(path = "/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping(path = "/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

    @GetMapping(path = "/{min}/{max}")
    public List<Student> findByAgeBetween(@PathVariable int min, @PathVariable int max) {
        return studentService.findByAgeBetween(min, max);
    }

    @GetMapping(path = "count")
    public Integer getAllNumberOfStudents() {
        return studentService.getAllNumberOfStudents();
    }
    @GetMapping(path = "avgAge")
    public Double getAvgAge() {
        return studentService.getAvgAge();
    }
    @GetMapping(path = "last")
    public List<Student> getLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }
}
