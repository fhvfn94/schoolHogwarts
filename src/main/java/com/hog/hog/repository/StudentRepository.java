package com.hog.hog.repository;

import com.hog.hog.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT count(id) from student", nativeQuery = true)
    Integer getAllNumberOfStudents();

    @Query(value = "select avg(age) from student", nativeQuery = true)
    Double getAvgAge();

    @Query(value = "select * from student order by id desc limit 5", nativeQuery = true)
    List<Student> getLastFiveStudents();
}
