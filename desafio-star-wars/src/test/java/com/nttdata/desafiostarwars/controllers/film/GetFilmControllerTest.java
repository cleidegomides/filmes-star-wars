package com.nttdata.desafiostarwars.controllers.film;

import com.nttdata.desafiostarwars.dto.responses.GetFilmeResponse;
import com.nttdata.desafiostarwars.services.film.GetFilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
class GetFilmControllerTest {

    private static final String FILM_API_URL = "/v1/filmes";

    @Mock
    private GetFilmService getFilmService;

    @InjectMocks
    private GetFilmController filmController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(filmController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void deveRetornarFilmePeloId() throws Exception {

        GetFilmeResponse filmeResponse = mockGetFilmeResponse();

        when(getFilmService.getOneFilme(Mockito.anyLong())).thenReturn(filmeResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(FILM_API_URL + "/" + filmeResponse.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(filmeResponse.getTitle())))
                .andExpect(jsonPath("$.episodeId", is(filmeResponse.getEpisodeId())))
                .andExpect(jsonPath("$.openingCrawl", is(filmeResponse.getOpeningCrawl())))
                .andExpect(jsonPath("$.director", is(filmeResponse.getDirector())))
                .andExpect(jsonPath("$.producer", is(filmeResponse.getProducer())))
                .andExpect(jsonPath("$.version", is(filmeResponse.getVersion())));

    }

    private GetFilmeResponse mockGetFilmeResponse() {
        return GetFilmeResponse.builder()
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