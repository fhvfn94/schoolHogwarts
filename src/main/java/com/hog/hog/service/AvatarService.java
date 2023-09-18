package com.hog.hog.service;

import com.hog.hog.model.Avatar;
import com.hog.hog.model.Student;
import com.hog.hog.repository.AvatarRepository;
import com.hog.hog.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static io.swagger.v3.core.util.AnnotationsUtils.getExtensions;

@Service
public class AvatarService {
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public void uploadImage(Long studentId, MultipartFile multipartFile) throws IOException {
        File dir = new File(avatarsDir); // Создаем файл с помощью этой строки
        if (!dir.exists()) { /* exists проверка на существование файла*/
            dir.mkdirs();
        }
        Student student = studentRepository.findById(studentId).get();
        String filePath = dir.getPath() + "/" + student.getId().toString() + "." + getExtensions(multipartFile.getOriginalFilename()); /* создаем путь к файлу*/
        File image = new File(filePath); /*объявляем переменную что есть файл с путем*/
        image.createNewFile(); /*создаем сам файл но он пуст*/
        multipartFile.transferTo(image.toPath()); /*мы берем картинку и перенаправляем в image */
        Avatar avatar = new Avatar();
        avatar.setData(multipartFile.getBytes());
        avatar.setFilePath(filePath);
        avatar.setFileSize(multipartFile.getSize());
        avatar.setMediaType(getExtensions(multipartFile.getOriginalFilename()));
        avatar.setStudent(student);
        avatarRepository.save(avatar);
    }


    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public byte[] getImageFromDB(Long id) {
        return avatarRepository.getAvatarByStudentId(id).getData();


    }

    public byte[] getImageFromDirectory(Long id) throws IOException {
        File dir = new File(avatarsDir);
        File file = Arrays.stream(dir.listFiles()).filter(f -> f.getName().substring(0, f.getName().lastIndexOf(".")).equals(id.toString())).findFirst().get();
        return Files.readAllBytes(file.toPath());
    }

    public Page<Avatar> findAll(PageRequest pageRequest) {
        return avatarRepository.findAll(pageRequest);
    }
}
