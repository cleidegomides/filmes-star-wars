package com.nttdata.desafiostarwars.services.film;

import com.nttdata.desafiostarwars.dto.responses.GetListItemFilmeResponse;
import com.nttdata.desafiostarwars.entities.Filme;
import com.nttdata.desafiostarwars.mapper.FilmeEntityToDtoMapperImpl;
import com.nttdata.desafiostarwars.repositories.FilmeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class ListFilmServiceTest {

    @Mock
    private FilmeRepository filmeRepository;

    @Spy
    private FilmeEntityToDtoMapperImpl filmeEntityToDtoMapper;

    @InjectMocks
    private ListFilmService filmService;

    @Test
    public void deveRetornarUmaListaDeFilmes() {
        Filme filmeEsperado = mockFilm();

        Mockito.when(filmeRepository.findAll()).thenReturn(Collections.singletonList(filmeEsperado));

        GetListItemFilmeResponse listItemFilmeResponse = mockGetListFilmesResponse();

        List<GetListItemFilmeResponse> foundListFilms = filmService.listAll();

        assertNotNull(foundListFilms);
        assertEquals(listItemFilmeResponse, foundListFilms.get(0));
    }

    private Filme mockFilm() {
        return Filme.builder()
                .id(1L)
                .title("The Empire Strikes Back")
                .episodeId(5)
                .openingCrawl("It is a dark time for the\r\nRebellion. Although the Death\r\nStar has been destroyed," +
                        "\r\nImperial troops have driven the\r\nRebel forces from their hidden\r\nbase and pursued" +
                        " them across\r\nthe galaxy.\r\n\r\nEvading the dreaded Imperial\r\nStarfleet, a group of " +
                        "freedom\r\nfighters led by Luke Skywalker\r\nhas established a new secret\r\nbase on the " +
                        "remote ice world\r\nof Hoth.\r\n\r\nThe evil lord Darth Vader,\r\nobsessed with " +
                        "finding young\r\nSkywalker, has dispatched\r\nthousands of remote probes into\r\nthe " +
                        "far reaches of space....")
                .director("Irvin Kershner")
                .producer("Gary Kurtz, Rick McCallum")
                .version(1)
                .build();
    }

    private GetListItemFilmeResponse mockGetListFilmesResponse() {
        return GetListItemFilmeResponse.builder()
                .id(1L)
                .title("The Empire Strikes Back")
                .version(1)
                .build();
    }

}