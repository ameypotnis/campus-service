package org.campus.web;

import org.campus.domain.Master;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by amey on 19/8/16.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MasterController.class)
public class MasterControllerTest extends ControllerBaseTest {

    @Test
    public void testFindAllMasterValuesForGivenType() throws Exception {
        //given
        List<Master> masterList = new ArrayList<Master>() {{
            add(new Master("standard", "FE"));
            add(new Master("standard", "SE"));
            add(new Master("standard", "TE"));
            add(new Master("standard", "BE"));
        }};
        given(this.masterRepository.findByType("standard"))
                .willReturn(masterList);
        //when then
        this.mvc.perform(MockMvcRequestBuilders.get("/api/master/all/standard"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0]").value("FE"))
                .andExpect(jsonPath("$[1]").value("SE"))
                .andExpect(jsonPath("$[2]").value("TE"))
                .andExpect(jsonPath("$[3]").value("BE"));
    }

}