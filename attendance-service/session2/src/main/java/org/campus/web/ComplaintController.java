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

    /*
    URL:http://localhost:9988/api/complaint
    Payload : {"title":"no staff","description":"there is no staff for blah blah","status":"OPEN"}
    */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Complaint create(@RequestBody Complaint complaint){
        return complaintRepository.save(complaint);
    }

    //URL: http://localhost:9988/api/complaint/2/3 [NOTE: 3 is Id of Compliment]
    @RequestMapping(value = "/{roll}/{id}",method = RequestMethod.PUT)
    public void addComplaint(@PathVariable("roll") Integer roll, @PathVariable("id") Long id){
        Student student=studentRepository.findByRoll(roll);
        Complaint com=complaintRepository.findOne(id);
        student.addComplaint(com);
        studentRepository.save(student);
    }
}
