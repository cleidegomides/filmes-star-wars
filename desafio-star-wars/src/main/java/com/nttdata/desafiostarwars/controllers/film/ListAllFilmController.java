package com.nttdata.desafiostarwars.controllers.film;

import com.nttdata.desafiostarwars.dto.responses.GetListItemFilmeResponse;
import com.nttdata.desafiostarwars.services.film.ListFilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/filmes")
@RequiredArgsConstructor
public class ListAllFilmController {

    private final ListFilmService listFilmService;

    @GetMapping
    public ResponseEntity<List<GetListItemFilmeResponse>> listAll() {
        List<GetListItemFilmeResponse> filmeResponses = listFilmService.listAll();

        return ResponseEntity.ok(filmeResponses);
    }
}
