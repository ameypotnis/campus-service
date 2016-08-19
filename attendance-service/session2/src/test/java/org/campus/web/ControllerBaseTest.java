package org.campus.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.campus.repository.AttendanceRepository;
import org.campus.repository.MasterRepository;
import org.campus.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by amey on 19/8/16.
 */
public class ControllerBaseTest {
    @Autowired
    protected MockMvc mvc;
    @MockBean
    protected AttendanceRepository attendanceRepository;
    @MockBean
    protected MasterRepository masterRepository;
    @MockBean
    protected StudentRepository studentRepository;
    @Autowired
    protected ObjectMapper objectMapper;
}
