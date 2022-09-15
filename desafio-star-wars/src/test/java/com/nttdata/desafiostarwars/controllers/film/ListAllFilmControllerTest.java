package com.nttdata.desafiostarwars.controllers.film;

import com.nttdata.desafiostarwars.dto.responses.GetListItemFilmeResponse;
import com.nttdata.desafiostarwars.services.film.ListFilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class ListAllFilmControllerTest {

    private static final String FILM_API_URL = "/v1/filmes";

    @Mock
    private ListFilmService listFilmService;

    @InjectMocks
    private ListAllFilmController listAllFilmController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(listAllFilmController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void deveRetornarUmaListaDeFilmes() throws Exception {

        GetListItemFilmeResponse listItemFilmeResponse = mockGetListFilmesResponse();

        when(listFilmService.listAll()).thenReturn(Collections.singletonList(listItemFilmeResponse));

        mockMvc.perform(MockMvcRequestBuilders.get(FILM_API_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is(listItemFilmeResponse.getTitle())))
                .andExpect(jsonPath("$[0].id", is(listItemFilmeResponse.getId())))
                .andExpect(jsonPath("$[0].version", is(listItemFilmeResponse.getVersion())));
    }

    private GetListItemFilmeResponse mockGetListFilmesResponse() {
        return GetListItemFilmeResponse.builder()
                .title("The Empire Strikes Back")
                .version(1)
                .build();
    }

}