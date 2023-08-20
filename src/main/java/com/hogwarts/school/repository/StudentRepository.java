package com.hogwarts.school.repository;

import com.hogwarts.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);
    @Query(value = "SELECT count(*) from student", nativeQuery = true)
    Integer getAllNumberOfStudents();

    @Query(value = "select avg(age) from student", nativeQuery = true)
    Double getAvgAge();
}
