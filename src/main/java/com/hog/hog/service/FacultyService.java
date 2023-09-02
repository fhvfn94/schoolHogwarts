package com.hog.hog.service;

import com.hog.hog.dto.FacultyDto;
import com.hog.hog.model.Faculty;
import com.hog.hog.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public void createFaculty(FacultyDto faculty) {
        Faculty faculty1 = new Faculty();
        faculty1.setName(faculty.getName());
        faculty1.setColor(faculty.getColor());
        facultyRepository.save(faculty1);
    }

    public void updateFaculty(Long id, FacultyDto faculty) {
        if (id != null) {
            Faculty faculty1 = new Faculty();
            faculty1.setId(id);
            faculty1.setName(faculty.getName());
            faculty1.setColor(faculty.getColor());
            facultyRepository.save(faculty1);
        } else {
            throw new RuntimeException("id = null");
        }
    }

    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).get();
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public void deleteFacultyById(Long id) {
        facultyRepository.deleteById(id);
    }

/**
 * Добавить эндпоинт для поиска факультета по имени или цвету, игнорируя регистр
 * в GET-запросе будет передана строка, по которой будет происходить фильтрация.
 */
public List<Faculty> getFacultyByColor(String color) {
        return facultyRepository.getFacultyByColor(color);
    }
    public List<Faculty> getFacultyByName(String name) {
        return facultyRepository.getFacultyByName(name);
    }
}
