package com.nttdata.desafiostarwars.controllers.film;

import com.nttdata.desafiostarwars.dto.responses.GetFilmeResponse;
import com.nttdata.desafiostarwars.exception.FilmNotFoundException;
import com.nttdata.desafiostarwars.services.film.GetFilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/filmes")
@RequiredArgsConstructor
public class GetFilmController {

    private final GetFilmService getFilmService;

    @GetMapping("/{id}")
    public ResponseEntity<GetFilmeResponse> getOneFilme(@PathVariable Long id) throws FilmNotFoundException {
        GetFilmeResponse oneFilme = getFilmService.getOneFilme(id);

        return ResponseEntity.ok(oneFilme);
    }
}
