package com.nttdata.desafiostarwars.services.film;

import com.nttdata.desafiostarwars.dto.responses.GetListItemFilmeResponse;
import com.nttdata.desafiostarwars.entities.Filme;
import com.nttdata.desafiostarwars.mapper.FilmeEntityToDtoMapper;
import com.nttdata.desafiostarwars.repositories.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListFilmService {

    private final FilmeRepository filmeRepository;
    private final FilmeEntityToDtoMapper filmeEntityToDtoMapper;

    public List<GetListItemFilmeResponse> listAll(){
        List<Filme> filmeList = filmeRepository.findAll();

        return filmeList
                .stream()
                .map(filmeEntityToDtoMapper::toGetListItemResponse)
                .collect(Collectors.toList());
    }
}
