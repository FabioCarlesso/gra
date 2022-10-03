package com.fabiocarlesso.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabiocarlesso.gra.domain.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer> {

}
