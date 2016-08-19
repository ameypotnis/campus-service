package org.campus.web;

import org.campus.domain.Master;
import org.campus.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indrajeet on 17/8/16.
 */
@RestController
@RequestMapping("/api/master")
public class MasterController {

    @Autowired private MasterRepository masterRepository;

    @RequestMapping(value = "",method = RequestMethod.POST)
    List<Master> create(@RequestBody List<Master> masters) {
        return (List<Master>)masterRepository.save(masters) ;
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    ArrayList<String> findAll(@PathVariable("type") String type) {
        List<Master> typeList=masterRepository.findByType(type);
        ArrayList<String> arrayList=new ArrayList<>();
        for(int i=0;i<typeList.size();i++) {
            arrayList.add(typeList.get(i).getValue());
        }

        return arrayList;
    }

}