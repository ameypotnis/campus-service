package org.campus;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.campus.domain.Attendance;
import org.campus.domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by amey on 9/8/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= RANDOM_PORT)
public class AttendanceServiceApplicationIntegrationTest {
    @Autowired private WebApplicationContext wac;
    @Autowired private ObjectMapper objectMapper;
    private MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testHappyPathScenario() throws Exception {
        //create student
        Student student = new Student("Amey", "BE", "CS", 1);
        this.mvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Amey"))
                .andExpect(jsonPath("$.standard").value("BE"))
                .andExpect(jsonPath("$.branch").value("CS"))
                .andExpect(jsonPath("$.roll").value(1))
                .andExpect(status().isCreated());

        //get student
        this.mvc.perform(MockMvcRequestBuilders.get("/api/students/BE/CS/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.roll").value(1))
                .andExpect(jsonPath("$.name").value("Amey"))
                .andExpect(jsonPath("$.standard").value("BE"))
                .andExpect(jsonPath("$.branch").value("CS"));

        //create attendance
        Attendance attendance = new Attendance("20160726", "BE", "CS");
        this.mvc.perform(post("/api/attendances")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attendance))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value("20160726"))
                .andExpect(jsonPath("$.standard").value("BE"))
                .andExpect(jsonPath("$.branch").value("CS"))
                .andExpect(status().isCreated());

        //add student
        this.mvc.perform(put("/api/attendances/20160726/BE/CS")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        //find attendance
        this.mvc.perform(MockMvcRequestBuilders.get("/api/attendances/20160726/BE/CS"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.date").value("20160726"))
                .andExpect(jsonPath("$.standard").value("BE"))
                .andExpect(jsonPath("$.branch").value("CS"));
                //decide student structure on demand
//                .andExpect(jsonPath("$.students[0].roll").value(1))
//                .andExpect(jsonPath("$.students[0].name").value("Amey"));
    }

}