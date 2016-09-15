package org.campus.web;

import org.campus.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by amey on 3/8/16.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest extends ControllerBaseTest {

    @Test
    public void testCreateStudent() throws Exception {
        //given
        Student student = new Student("Amey", "BE", "CS", 1);
        given(this.studentRepository.save(student))
                .willReturn(student);
        //when then
        this.mvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Amey"))
                .andExpect(jsonPath("$.standard").value("BE"))
                .andExpect(jsonPath("$.branch").value("CS"))

                .andExpect(jsonPath("$.roll").value(1))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateStudentShouldFailForEmptyStandard() throws Exception {
        //given
        Student student = new Student("Amey", null, "CS", 1);
        given(this.studentRepository.save(student))
                .willReturn(student);
        //when then
        this.mvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateStudentShouldFailForEmptyBranch() throws Exception {
        //given
        Student student = new Student("Amey", "BE", null, 1);
        given(this.studentRepository.save(student))
                .willReturn(student);
        //when then
        this.mvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateStudentShouldFailForEmptyRoll() throws Exception {
        //given
        Student student = new Student("Amey", "BE", "CS", null);
        given(this.studentRepository.save(student))
                .willReturn(student);
        //when then
        this.mvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateStudentShouldFailForEmptyName() throws Exception {
        //given
        Student student = new Student(null, "BE", "CS", 1);
        given(this.studentRepository.save(student))
                .willReturn(student);
        //when then
        this.mvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testFindStudent() throws Exception {
        //given
        Student student = new Student("Amey", "BE", "CS", 1);
        given(this.studentRepository.findByRollAndStandardAndBranch(1, "BE", "CS"))
                .willReturn(student);
        //when then
        this.mvc.perform(MockMvcRequestBuilders.get("/api/students/BE/CS/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.roll").value(1))
                .andExpect(jsonPath("$.name").value("Amey"))
                .andExpect(jsonPath("$.standard").value("BE"))
                .andExpect(jsonPath("$.branch").value("CS"));
    }

    @Test
    public void testFindStudentShouldThrowNotFoundIfDoesntExist() throws Exception {
        //given
        given(this.studentRepository.findByRollAndStandardAndBranch(1, "BE", "CS"))
                .willReturn(null);
        //when then
        this.mvc.perform(MockMvcRequestBuilders.get("/api/students/BE/CS/1"))
                .andExpect(status().isNotFound());
    }
}