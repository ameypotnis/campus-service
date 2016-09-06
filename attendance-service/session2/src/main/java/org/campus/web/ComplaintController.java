package org.campus.web;

import org.campus.domain.Complaint;
import org.campus.domain.Student;
import org.campus.repository.ComplaintRepository;
import org.campus.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.campus.web.helper.Preconditions.checkNotNull;

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
    Payload : {"title":"no staff","description":"there is no staff for blah blah","status":"OPEN", "responsible":"Managment"}
    */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Complaint create(@RequestBody Complaint complaint){
        checkNotNull(complaint.getDescription(), "Description");
        checkNotNull(complaint.getTitle(), "Title");
        checkNotNull(complaint.getStatus(), "Status");
        checkNotNull(complaint.getResponsible(), "Responsible");
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

    //URL:http://localhost:9988/api/complaint/2/1/CLOSED
    @RequestMapping(value = "/{roll}/{id}/{status}",method = RequestMethod.PUT)
    public void updateStatus(@PathVariable("roll") Integer roll, @PathVariable("id") Long id, @PathVariable String status){
        Complaint com=complaintRepository.findOne(id);
        com.setStatus(status);
        complaintRepository.save(com);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody public List<Complaint> find(){
        List<Complaint> complaints=complaintRepository.findAll();
        return complaints;
    }
}
