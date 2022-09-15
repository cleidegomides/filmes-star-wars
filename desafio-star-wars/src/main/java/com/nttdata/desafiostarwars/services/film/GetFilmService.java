package com.nttdata.desafiostarwars.services.film;

import com.nttdata.desafiostarwars.dto.responses.GetFilmeResponse;
import com.nttdata.desafiostarwars.entities.Filme;
import com.nttdata.desafiostarwars.exception.FilmNotFoundException;
import com.nttdata.desafiostarwars.mapper.FilmeEntityToDtoMapper;
import com.nttdata.desafiostarwars.repositories.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetFilmService {

    private final FilmeRepository filmeRepository;
    private final FilmeEntityToDtoMapper filmeEntityToDtoMapper;

    public GetFilmeResponse getOneFilme(Long id) throws FilmNotFoundException {
        Filme filme = filmeRepository.findById(id).get();

        if (filme == null) {
            throw new FilmNotFoundException(id);
        }

        GetFilmeResponse responseOneFilme = filmeEntityToDtoMapper.toGetResponse(filme);

        return responseOneFilme;
    }
}
