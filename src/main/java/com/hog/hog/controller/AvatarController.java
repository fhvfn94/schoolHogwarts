package com.hog.hog.controller;

import com.hog.hog.model.Avatar;
import com.hog.hog.service.AvatarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @GetMapping(path = "/allAvatars")
    public Page<Avatar> getAllAvatars(
            @RequestParam("page") Integer pageNumber,
            @RequestParam("size") Integer pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return avatarService.findAll(pageRequest);
    }
}
