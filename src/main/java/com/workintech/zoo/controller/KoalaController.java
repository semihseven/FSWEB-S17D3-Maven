package com.workintech.zoo.controller;


import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
        koalas=new HashMap<>();
    }

    @GetMapping("")
    public List<Koala> findAll(){
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala findById(@PathVariable Integer id) {
        if(id<=0) {
            throw new ZooException("ID should be greater than 0",HttpStatus.BAD_REQUEST);
        }


        if(koalas.containsKey(id)) {
            return koalas.get(id);
        } else {
            throw new ZooException("can not find ID", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public Koala insertKoala(@RequestBody Koala koala) {
        koalas.put(koala.getId(),koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala editKoala(@PathVariable Integer id, @RequestBody Koala koala) {
        if(koalas.containsKey(id)) {
            koalas.put(id,koala);
            return koala;
        } else {
            System.out.println("Could not found koala");
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable Integer id) {
        if(koalas.containsKey(id)){
            return koalas.remove(id);
        } else {
            System.out.println("Could not found koala");
            return null;
        }
    }
}
