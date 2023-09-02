package com.hog.hog.controller;

import com.hog.hog.dto.FacultyDto;
import com.hog.hog.model.Faculty;
import com.hog.hog.model.Student;
import com.hog.hog.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @PostMapping
    public void createFaculty(@RequestBody FacultyDto faculty) {
        facultyService.createFaculty(faculty);

    }

    @PutMapping(path = "/{id}")
    public void updateFaculty(@PathVariable long id, @RequestBody FacultyDto faculty) {
        facultyService.updateFaculty(id, faculty);
    }
    @GetMapping(path = "/{id}")
    public Faculty getFacultyById(@PathVariable Long id) {
        return facultyService.getFacultyById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteFacultyById(@PathVariable Long id) {
        facultyService.deleteFacultyById(id);
    }

    @GetMapping(path = "/students")
    public List<Faculty> getAllFaculties(@RequestParam(required = false) String name, @RequestParam(required = false) String color) {
        if (color != null && !color.isEmpty() && !color.isBlank()) {
            return facultyService.getFacultyByColor(color);
        }
        if (name != null && !name.isBlank() && !name.isEmpty()) {
            return facultyService.getFacultyByName(name);
        }
        return facultyService.getAllFaculty();
    }


}
