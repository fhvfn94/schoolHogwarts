package com.hogwarts.school;

import com.hogwarts.school.controller.StudentController;
import com.hogwarts.school.model.Student;
import com.hogwarts.school.repository.StudentRepository;
import com.hogwarts.school.service.StudentService;
import net.minidev.json.JSONObject;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static com.jayway.jsonpath.JsonPath.read;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TestStudentController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void saveStudentTest() throws Exception {
        Long id = 1L;
        String name = "Bob";

        JSONObject userObject = new JSONObject();
        userObject.put("name", name);

        Student student = new Student();
        student.setId(id);
        student.setName(name);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student") //send
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect((ResultMatcher) jsonPath("$.id").value(id.intValue()))
                .andExpect((ResultMatcher) jsonPath("$.name").value(name));
    }
    @Test
    public void getStudentByIdTest() throws Exception {
        Long id = 1L;
        String name = "Alice";

        Student student = new Student();
        student.setId(id);
        student.setName(name);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/{id}", id) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect((ResultMatcher) jsonPath("$.id").value(id.intValue()))
                .andExpect((ResultMatcher) jsonPath("$.name").value(name));
    }
    @Test
    public void updateStudentTest() throws Exception {
        Long id = 1L;
        String updatedName = "UpdatedName";

        JSONObject updatedUserObject = new JSONObject();
        updatedUserObject.put("id", id);
        updatedUserObject.put("name", updatedName);

        Student updatedStudent = new Student();
        updatedStudent.setId(id);
        updatedStudent.setName(updatedName);

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(updatedStudent));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student") //send
                        .content(updatedUserObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect((ResultMatcher) jsonPath("$.id").value(id.intValue()))
                .andExpect((ResultMatcher) jsonPath("$.name").value(updatedName));
    }
    @Test
    public void deleteStudentTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/{id}", id)) //send
                .andExpect(status().isOk()); //receive
    }
    @Test
    public void getAllStudentTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student") //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect((ResultMatcher) jsonPath("$", Matchers.hasSize(9)));
    }
}
