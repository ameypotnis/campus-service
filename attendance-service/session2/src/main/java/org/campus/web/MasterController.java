package org.campus.web;

import org.campus.domain.Master;
import org.campus.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by indrajeet on 17/8/16.
 */
@RestController
@RequestMapping("/api/master")
public class MasterController {

    @Autowired
    private MasterRepository masterRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public List<Master> create(@RequestBody List<Master> masters) {
        return (List<Master>) masterRepository.save(masters);
    }

    @RequestMapping(value = "all/{type}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<String> findAllByType(@PathVariable("type") String type) {
        List<Master> masters = masterRepository.findByType(type);
        return masters.stream().map(Master::getValue).collect(Collectors.toList());
    }
}
