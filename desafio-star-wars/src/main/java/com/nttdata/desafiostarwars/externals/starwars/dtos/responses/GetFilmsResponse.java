package com.nttdata.desafiostarwars.externals.starwars.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetFilmsResponse {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("results")
    private List<GetFilmResponse> films;
}
