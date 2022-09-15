package com.nttdata.desafiostarwars.services.film;

import com.nttdata.desafiostarwars.externals.starwars.StarWarsFeign;
import com.nttdata.desafiostarwars.externals.starwars.dtos.responses.GetFilmsResponse;
import com.nttdata.desafiostarwars.mapper.FilmeEntityToDtoMapper;
import com.nttdata.desafiostarwars.repositories.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class StartDataFilmService {

    private final StarWarsFeign starWarsFeign;
    private final FilmeRepository filmeRepository;
    private final FilmeEntityToDtoMapper filmeEntityToDtoMapper;

    @PostConstruct
    public void initializeData() {
        GetFilmsResponse getFilmsResponse = starWarsFeign.getFilmes();

        getFilmsResponse
                .getFilms()
                .stream()
                .map( filmeEntityToDtoMapper::toEntity )
                .map(  filme -> {
                    filme.setVersion(1);
                    return filme;
                })
                .forEach(filmeRepository::save);
    }
}
