package com.nttdata.desafiostarwars.services.film;

import com.nttdata.desafiostarwars.dto.requests.PutCommentFilmeRequest;
import com.nttdata.desafiostarwars.dto.responses.PutCommentFilmeResponse;
import com.nttdata.desafiostarwars.entities.Filme;
import com.nttdata.desafiostarwars.exception.FilmNotFoundException;
import com.nttdata.desafiostarwars.mapper.FilmeEntityToDtoMapperImpl;
import com.nttdata.desafiostarwars.repositories.FilmeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PutCommentFilmServiceTest {

    private static final Long VALID_ID = 1L;

    @Mock
    private FilmeRepository filmeRepository;

    @Spy
    private FilmeEntityToDtoMapperImpl filmeEntityToDtoMapperImpl;

    @InjectMocks
    private PutCommentFilmService putCommentFilmService;

    @Test
    public void deveAdicionarComentarioComSucesso() throws FilmNotFoundException {


        PutCommentFilmeRequest request = mockPutFilmesRequest();

        Filme filmeFindBy = mockFilmeValid();
        Filme filmeSave = mockFilmeValid();
        Optional<Filme> optionalFilme = Optional.of(filmeFindBy);

        when(filmeRepository.findById(VALID_ID)).thenReturn(optionalFilme);

        filmeSave.setComment( request.getComment() );
        when(filmeRepository.save(filmeSave)).thenReturn(filmeSave);

        PutCommentFilmeResponse response = putCommentFilmService.addComment(VALID_ID, request);


        assertEquals(request.getComment(), response.getComment());
    }

    private PutCommentFilmeRequest mockPutFilmesRequest() {
        return PutCommentFilmeRequest.builder()
                .comment("Adicionando comentário com sucesso!")
                .build();
    }

    private Filme mockFilmeValid() {
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

}