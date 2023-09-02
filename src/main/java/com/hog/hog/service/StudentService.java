package com.hog.hog.service;


import com.hog.hog.dto.StudentDto;
import com.hog.hog.model.Student;
import com.hog.hog.repository.FacultyRepository;
import com.hog.hog.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public void createStudent(StudentDto student) {
        Student student1 = new Student();
        student1.setName(student.getName());
        student1.setAge(student.getAge());
        student1.setFaculty(facultyRepository.findById(student.getFacultyId()).get());
        studentRepository.save(student1);
    }

    public void updateStudent(Long id, StudentDto student) {
        if (id != null) {
            Student student1 = new Student();
            student1.setId(id);
            student1.setName(student.getName());
            student1.setAge(student.getAge());
            student1.setFaculty(facultyRepository.findById(student.getFacultyId()).get());
            studentRepository.save(student1);
        } else {
            throw new RuntimeException("id = null");
        }
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Integer getAllNumberOfStudents() {
        return studentRepository.getAllNumberOfStudents();
    }
    public Double getAvgAge() {
        return studentRepository.getAvgAge();
    }

    public List<Student> getLastFiveStudents() {
        return studentRepository.getLastFiveStudents();
    }
}
