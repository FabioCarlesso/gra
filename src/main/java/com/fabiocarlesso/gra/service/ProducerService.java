package com.fabiocarlesso.gra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabiocarlesso.gra.domain.Producer;
import com.fabiocarlesso.gra.repository.ProducerRepository;
import com.fabiocarlesso.gra.service.exception.ObjectNotFoundException;

@Service
public class ProducerService {
    
    @Autowired
    private ProducerRepository repo;

    public Producer find(Integer id) {
        Optional<Producer> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + Producer.class.getName()));
    }

    public List<Producer> findAll(){
        return repo.findAll();
    }

}
