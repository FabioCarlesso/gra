package com.fabiocarlesso.gra.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieProducerIntervalDTO {
    private String producerName;
    private Integer previousWin;
    private Integer followingWin;
    private Integer interval;
}
