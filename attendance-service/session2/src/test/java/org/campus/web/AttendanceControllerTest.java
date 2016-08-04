package org.campus.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.campus.domain.Attendance;
import org.campus.domain.Student;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.campus.domain.BaseEntity.toDate;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        //given
        Attendance attendance = new Attendance("20160726", "BE", "CS");
        given(this.attendanceRepository.save(attendance))
                .willReturn(attendance);
        //when then
        this.mvc.perform(post("/api/attendance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attendance))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value("20160726"))
                .andExpect(jsonPath("$.standard").value("BE"))
                .andExpect(jsonPath("$.branch").value("CS"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateAttendanceShouldFailForInvalidDateRequest() throws Exception {
        //given
        Attendance attendance = new Attendance(null, "BE", "CS");
        given(this.attendanceRepository.save(attendance))
                .willReturn(attendance);

        //when then
        this.mvc.perform(post("/api/attendance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attendance))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateAttendanceShouldFailForInvalidStandardRequest() throws Exception {
        //given
        Attendance attendance = new Attendance("20160726", null, "CS");

        //when then
        this.mvc.perform(post("/api/attendance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attendance))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateAttendanceShouldFailForInvalidBranchRequest() throws Exception {
        //given
        Attendance attendance = new Attendance("20160726", "BE", null);

        //when then
        this.mvc.perform(post("/api/attendance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attendance))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddStudentToAttendance() throws Exception {
        //given
        Attendance attendance = new Attendance("20160726", "BE", "CS");
        Student student = new Student("BE", "CS", 1);
        given(this.attendanceRepository.findByDateAndStandardAndBranch(toDate("20160726"), "BE", "CS"))
                .willReturn(attendance);
        given(this.studentRepository.findByRollAndStandardAndBranch(1, "BE", "CS"))
                .willReturn(student);
        //when then
        this.mvc.perform(put("/api/attendance/20160726/BE/CS")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testAddStudentToAttendanceShouldFailForInvalidAttendance() throws Exception {
        //given
        Student student = new Student("BE", "CS", 1);
        given(this.attendanceRepository.findByDateAndStandardAndBranch(toDate("20160726"), "BE", "CS"))
                .willReturn(null);
        //when then
        this.mvc.perform(put("/api/attendance/20160726/BE/CS")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddStudentToAttendanceShouldFailForInvalidStudent() throws Exception {
        //given
        Attendance attendance = new Attendance("20160726", "BE", "CS");
        Student student = new Student("BE", "CS", 1);
        given(this.attendanceRepository.findByDateAndStandardAndBranch(toDate("20160726"), "BE", "CS"))
                .willReturn(attendance);
        given(this.studentRepository.findByRollAndStandardAndBranch(1, "BE", "CS"))
                .willReturn(null);
        //when then
        this.mvc.perform(put("/api/attendance/20160726/BE/CS")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testFindAttendance() throws Exception {
        //given
        Attendance attendance = new Attendance("20160726", "BE", "CS");
        attendance.setId(1L);
        given(this.attendanceRepository.findByDateAndStandardAndBranch(toDate("20160726"), "BE", "CS"))
                .willReturn(attendance);
        //when then
        this.mvc.perform(MockMvcRequestBuilders.get("/api/attendance/20160726/BE/CS"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.date").value("20160726"))
                .andExpect(jsonPath("$.standard").value("BE"))
                .andExpect(jsonPath("$.branch").value("CS"));
    }
}