package org.campus.repository;

import org.campus.domain.Complaint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


/**
 * Created by indrajeet on 5/9/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ComplaintRespositoryTest {
    @Autowired private ComplaintRepository complaintRepository;
    @Autowired private StudentRepository studentRepository;


    @Test
    public void testCreateAndSave() {
        Complaint complaint=new Complaint("No staff for teaching XYZ","No staff","OPEN","Managment");
        complaintRepository.save(complaint);

        Complaint actual=complaintRepository.findOne(1L);
        assertThat(actual.getDescription().equals("No staff for teaching XYZ"));
        assertThat(actual.getTitle().equals("No staff"));
        assertThat(complaint.getStatus("OPEN") == actual.getStatus());
        assertThat(actual.getResponsible().equals("Managment"));
    }

}
