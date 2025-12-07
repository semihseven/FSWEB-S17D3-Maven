package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
    }

    @GetMapping("")
    public List<Kangaroo> findAll(){
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo findById(@PathVariable Integer id) {
        if(id<=0) {
            throw new ZooException("ID should be greater than 0",HttpStatus.BAD_REQUEST);
        }

        if(kangaroos.containsKey(id)) {
            return kangaroos.get(id);
        } else {
            throw new ZooException("can not find ID", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public Kangaroo insertKangaroo(@RequestBody Kangaroo kangaroo) {

        if(kangaroo.getId()<=0){
            throw new ZooException("ID should be greater than 0",HttpStatus.BAD_REQUEST);
        }

        kangaroos.put(kangaroo.getId(),kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo editKangaroo(@PathVariable Integer id, @RequestBody Kangaroo kangaroo) {

        if(id<=0) {
            throw new ZooException("ID should be greater than 0",HttpStatus.BAD_REQUEST);
        }

        if(kangaroos.containsKey(id)) {
            kangaroos.put(id,kangaroo);
            return kangaroo;
        } else {
            throw new ZooException("can not find ID", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable Integer id) {
        if(id<=0) {
            throw new ZooException("ID should be greater than 0",HttpStatus.BAD_REQUEST);
        }


        if(kangaroos.containsKey(id)){
            return kangaroos.remove(id);
        } else {
            throw new ZooException("can not find ID", HttpStatus.NOT_FOUND);
        }
    }

}
