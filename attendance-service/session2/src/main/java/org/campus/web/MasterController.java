package org.campus.web;

import org.campus.domain.Master;
import org.campus.repository.MasterRepository;
import org.campus.web.helper.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    List<String> findAll(@PathVariable("type") String type) {
        List<Master> typeList=masterRepository.findByType(type);
        List<String> arrayList=new ArrayList<>();
        return typeList.stream().map(Master::getValue).collect(Collectors.toList());

    }

    @RequestMapping(value = "/{type}/{value}",method = RequestMethod.PUT)
    void update(@PathVariable("type") String type, @PathVariable("value") String value, @RequestBody Master master) {
        Master master1=masterRepository.findByTypeAndValue(type,value);
        Preconditions.checkFound(master1, "Master");
        master1.setType(master.getType());
        master1.setValue(master.getValue());
        masterRepository.save(master1);
    }

    @RequestMapping(value = "/{type}/{value}", method = RequestMethod.DELETE)
    void delete(@PathVariable("type") String type, @PathVariable("value") String value) {
        
    }
}
