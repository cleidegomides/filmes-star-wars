package com.nttdata.desafiostarwars.controllers.film;

import com.nttdata.desafiostarwars.dto.requests.PutFilmeRequest;
import com.nttdata.desafiostarwars.dto.responses.PutFilmeResponse;
import com.nttdata.desafiostarwars.exception.FilmNotFoundException;
import com.nttdata.desafiostarwars.services.film.PutFilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/filmes/")
@RequiredArgsConstructor
public class PutFilmController {

    private final PutFilmService putFilmService;

    @PutMapping("{id}")
    public ResponseEntity<PutFilmeResponse> update(@RequestBody PutFilmeRequest putFilmeRequest, @PathVariable Long id) throws FilmNotFoundException {
        PutFilmeResponse response = putFilmService.update(id, putFilmeRequest);
        return ResponseEntity.ok(response);
    }
}