package org.campus.web;

import org.campus.domain.Attendance;
import org.campus.domain.Student;
import org.campus.repository.AttendanceRepository;
import org.campus.repository.StudentRepository;
import org.campus.web.helper.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.campus.domain.BaseEntity.toDate;
import static org.campus.web.helper.Preconditions.checkNotNull;

/**
 * Created by amey on 3/8/16.
 */
@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    @Autowired private AttendanceRepository attendanceRepository;
    @Autowired private StudentRepository studentRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Attendance create(@RequestBody Attendance attendance) {
        checkNotNull(attendance.getDate(), "Date");
        checkNotNull(attendance.getStandard(), "Standard");
        checkNotNull(attendance.getBranch(), "Branch");
        checkNotNull(attendance.getSubject(), "Subject");
        return attendanceRepository.save(attendance);
    }

    @RequestMapping(value = "{date}/{standard}/{branch}/{subject}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable("date") String date, @PathVariable("standard") String standard, @PathVariable("branch") String branch, @PathVariable("subject") String subject, @RequestBody Student student) {
        Attendance attendance = attendanceRepository.findByDateAndStandardAndBranchAndSubject(toDate(date), standard, branch, subject);
        Preconditions.checkFound(attendance, "Attendance");
        student = studentRepository.findByRollAndStandardAndBranch(student.getRoll(), student.getStandard(), student.getBranch());
        Preconditions.checkFound(student, "Student");
        attendance.addStudent(student);
        attendanceRepository.save(attendance);
    }

    @RequestMapping(value = "{date}/{standard}/{branch}/{subject}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Attendance find(@PathVariable("date") String date, @PathVariable("standard") String standard, @PathVariable("branch") String branch, @PathVariable("subject") String subject) {
        Attendance attendance = attendanceRepository.findByDateAndStandardAndBranchAndSubject(toDate(date), standard, branch, subject);
        Preconditions.checkFound(attendance, "Attendance");
        return attendance;
    }

}