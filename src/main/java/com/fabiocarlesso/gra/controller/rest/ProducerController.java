package com.fabiocarlesso.gra.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabiocarlesso.gra.domain.Producer;
import com.fabiocarlesso.gra.domain.dto.ProducerDTO;
import com.fabiocarlesso.gra.service.ProducerService;

@RestController
@RequestMapping(value="/v1/producers")
public class ProducerController {
    
    @Autowired
    private ProducerService service;

    @GetMapping(value="/{id}")
    public ResponseEntity<ProducerDTO> find(@PathVariable Integer id){
        Producer obj = service.find(id);
        return ResponseEntity.ok().body(new ProducerDTO(obj));
    }

    @GetMapping()
    public ResponseEntity<List<ProducerDTO>> findAll(){
        List<Producer> list = service.findAll();
        List<ProducerDTO> listDto = list.stream().map(ProducerDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

}
