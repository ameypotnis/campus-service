package org.campus.web;

import org.campus.domain.Complaint;
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
 * Created by indrajeet on 6/9/16.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ComplaintController.class)
public class ComplaintControllerTest extends ControllerBaseTest {

    @Test
    public void testCreateComplaint()throws Exception {
        //given
        Complaint complaint=new Complaint("No staff for xyz","No Staff","OPEN","Management");
        given(this.complaintRepository.save(complaint)).willReturn(complaint);

        //when then
        this.mvc.perform(post("/api/complaint")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(complaint))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value("No staff for xyz"))
                .andExpect(jsonPath("$.title").value("No Staff"))
                .andExpect(jsonPath("$.status").value("OPEN"))
                .andExpect(jsonPath("$.responsible").value("Management"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateComplaintShouldFailForEmptyDescription() throws Exception {
        //given
        Complaint complaint=new Complaint(null, "No Staff", "OPEN", "Management");
        given(this.complaintRepository.save(complaint)).willReturn(complaint);

        //when then
        this.mvc.perform(post("/api/complaint")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(complaint))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateComplaintShouldFailForEmptyTitle() throws Exception {
        //given
        Complaint complaint=new Complaint("No staff for xyz", null, "OPEN", "Management");
        given(this.complaintRepository.save(complaint)).willReturn(complaint);

        //when then
        this.mvc.perform(post("/api/complaint")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(complaint))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * Not Working
    @Test
    public void testCreateComplaintShouldFailForEmptyStatus() throws Exception {
        //given
        Complaint complaint=new Complaint("No staff for xyz", "No Staff", null, "Management");
        given(this.complaintRepository.save(complaint)).willReturn(complaint);

        //when then
        this.mvc.perform(post("/api/complaint")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(complaint))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    */

    @Test
    public void testCreateComplaintShouldFailForEmptyResponsible() throws Exception {
        //given
        Complaint complaint=new Complaint("No staff for xyz", "No Staff", "OPEN", null);
        given(this.complaintRepository.save(complaint)).willReturn(complaint);

        //when then
        this.mvc.perform(post("/api/complaint")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(complaint))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
