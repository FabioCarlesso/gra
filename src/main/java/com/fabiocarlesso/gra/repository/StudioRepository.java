package com.fabiocarlesso.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabiocarlesso.gra.domain.Studio;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Integer> {

}
