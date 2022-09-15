package com.nttdata.desafiostarwars.services.film;

import com.nttdata.desafiostarwars.dto.requests.PutFilmeRequest;
import com.nttdata.desafiostarwars.dto.responses.PutFilmeResponse;
import com.nttdata.desafiostarwars.exception.FilmNotFoundException;
import com.nttdata.desafiostarwars.mapper.FilmeEntityToDtoMapper;
import com.nttdata.desafiostarwars.repositories.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PutFilmService {

    private final FilmeRepository filmeRepository;
    private final FilmeEntityToDtoMapper filmeEntityToDtoMapper;

    public PutFilmeResponse update(Long id, PutFilmeRequest filmeRequest) throws FilmNotFoundException {
        return filmeRepository.findById(id)
                .map(filme -> {
                    filmeEntityToDtoMapper.toEntityUpdate(filmeRequest, filme);
                    filme.setVersion(filme.getVersion() + 1);
                    return filme;
                })
                .map(filmeRepository::save)
                .map(filmeEntityToDtoMapper::toPutResponse)
                .orElseThrow(() -> new FilmNotFoundException(id));
    }
}
