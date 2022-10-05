package com.fabiocarlesso.gra.domain.dto;

import com.fabiocarlesso.gra.domain.Producer;

import lombok.Data;

@Data
public class ProducerDTO {

    private Integer id;
    private String producerName;

    public ProducerDTO(Producer obj){
        this.id = obj.getId();
        this.producerName = obj.getProducerName();
    }

}
