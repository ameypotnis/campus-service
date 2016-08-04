package org.campus.web;

import org.campus.domain.Attendance;
import org.campus.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by amey on 3/8/16.
 */
@RestController(value = "/api/attendance")
public class AttendanceController {

    @Autowired private AttendanceRepository attendanceRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Attendance create(@RequestBody Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

}
