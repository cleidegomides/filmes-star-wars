package com.nttdata.desafiostarwars.externals.starwars;

import com.nttdata.desafiostarwars.externals.starwars.dtos.responses.GetFilmsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "StarWars-filmes", url = "https://swapi.dev/api")
public interface StarWarsFeign {

    @GetMapping(value = "/films/", consumes = MediaType.APPLICATION_JSON_VALUE)
    GetFilmsResponse getFilmes();
}
