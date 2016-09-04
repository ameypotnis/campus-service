package org.campus.web;

import org.campus.domain.Complaint;
import org.campus.domain.Student;
import org.campus.repository.ComplaintRepository;
import org.campus.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by indrajeet on 4/9/16.
 */
@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {

    @Autowired private ComplaintRepository complaintRepository;
    @Autowired private StudentRepository studentRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Complaint create(@RequestBody Complaint complaint){
        return complaintRepository.save(complaint);
    }

    @RequestMapping(value = "/{roll}/{id}",method = RequestMethod.PUT)
    public void addComplaint(@PathVariable("roll") Integer roll, @PathVariable("id") Long id){
        Student student=studentRepository.findByRoll(roll);
        Complaint com=complaintRepository.findOne(id);
        student.addComplaint(com);
        studentRepository.save(student);
    }
}
