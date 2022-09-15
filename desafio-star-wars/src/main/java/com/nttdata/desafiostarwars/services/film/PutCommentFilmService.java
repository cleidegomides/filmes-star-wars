package com.nttdata.desafiostarwars.services.film;

import com.nttdata.desafiostarwars.dto.requests.PutCommentFilmeRequest;
import com.nttdata.desafiostarwars.dto.responses.PutCommentFilmeResponse;
import com.nttdata.desafiostarwars.exception.FilmNotFoundException;
import com.nttdata.desafiostarwars.mapper.FilmeEntityToDtoMapper;
import com.nttdata.desafiostarwars.repositories.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PutCommentFilmService {

    private final FilmeRepository filmeRepository;
    private final FilmeEntityToDtoMapper filmeEntityToDtoMapper;

    public PutCommentFilmeResponse addComment(Long id, PutCommentFilmeRequest filmeRequest) throws FilmNotFoundException {
        return filmeRepository.findById(id)
                .map(filme -> {
                    filmeEntityToDtoMapper.toEntityAddComment(filmeRequest, filme);
                    return filme;
                })
                .map(filmeRepository::save)
                .map(filmeEntityToDtoMapper::toPutCommentResponse)
                .orElseThrow(() -> new FilmNotFoundException(id));
    }
}
