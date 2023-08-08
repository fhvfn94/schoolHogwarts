package com.hogwarts.school.service;

import com.hogwarts.school.model.Faculty;
import com.hogwarts.school.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }


    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).get();
    }


    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }


    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }


    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
}
