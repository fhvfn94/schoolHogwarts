package com.hog.hog.repository;

import com.hog.hog.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> getFacultyByColor(String color);
    @Query(value = "select* from faculty as f where f.name like lower('f')", nativeQuery = true)
    List<Faculty> getFacultyByName(String name);


}
