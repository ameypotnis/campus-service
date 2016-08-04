package org.campus.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.campus.domain.Attendance;
import org.campus.repository.AttendanceRepository;
import org.campus.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by amey on 3/8/16.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AttendanceController.class)
public class AttendanceControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AttendanceRepository attendanceRepository;
    @MockBean
    private StudentRepository studentRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAttendance() throws Exception {
        Attendance attendance = new Attendance("20160726", "BE", "CS");
        given(this.attendanceRepository.save(attendance))
                .willReturn(attendance);

        this.mvc.perform(post("/api/attendance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attendance))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value("20160726"))
                .andExpect(jsonPath("$.standard").value("BE"))
                .andExpect(jsonPath("$.branch").value("CS"))
                .andExpect(status().isCreated());
    }

}