package com.fabiocarlesso.gra.domain.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieProducerMinMaxDTO {
    private List<MovieProducerIntervalDTO> min = new ArrayList<>();
    private List<MovieProducerIntervalDTO> max = new ArrayList<>();
}
