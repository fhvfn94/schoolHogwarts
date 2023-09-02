package com.hog.hog.controller;

import com.hog.hog.service.AvatarService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(path = "/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadImage(@RequestParam Long studentId, @RequestParam MultipartFile file) throws IOException {
        avatarService.uploadImage(studentId, file);
    }

    @GetMapping(path = "/fromDB/{id}")
    public byte[] getImageFromDB(@PathVariable Long id) {
        return avatarService.getImageFromDB(id);
    }

    @GetMapping(path = "/getFromDirectory/{id}")
    public byte[] getImageFromDirectory(@PathVariable Long id) throws IOException {
        return avatarService.getImageFromDirectory(id);
    }
}
