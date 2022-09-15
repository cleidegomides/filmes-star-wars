package com.nttdata.desafiostarwars.controllers.film;

import com.nttdata.desafiostarwars.dto.requests.PutCommentFilmeRequest;
import com.nttdata.desafiostarwars.dto.responses.PutCommentFilmeResponse;
import com.nttdata.desafiostarwars.exception.FilmNotFoundException;
import com.nttdata.desafiostarwars.services.film.PutCommentFilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/filmes/")
@RequiredArgsConstructor
public class PutCommentFilmController {

    private final PutCommentFilmService putCommentFilmService;

    @PutMapping("{id}/comentario")
    public ResponseEntity<PutCommentFilmeResponse> update(@RequestBody PutCommentFilmeRequest putCommentFilmeRequest, @PathVariable Long id) throws FilmNotFoundException {
        PutCommentFilmeResponse response = putCommentFilmService.addComment(id, putCommentFilmeRequest);
        return ResponseEntity.ok(response);
    }
}