package com.hogwarts.school;

import com.hogwarts.school.controller.StudentController;

import com.hogwarts.school.model.Student;
import com.hogwarts.school.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.NoSuchElementException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HogwartsApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void testGetStudent() throws Exception  {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotEmpty();
    }
    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Bob");
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);

        this.restTemplate.delete("http://localhost:" + port + "/student/" + student.getId());

        // После удаления, вы можете добавить проверку, что студент больше не существует.
        Assertions.assertThatThrownBy(() -> studentService.getStudentById(student.getId()))
                .isInstanceOf(NoSuchElementException.class); // Подставьте правильное исключение
    }

}
