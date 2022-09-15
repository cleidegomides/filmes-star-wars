package com.nttdata.desafiostarwars.controllers.film;

import com.nttdata.desafiostarwars.dto.requests.PutFilmeRequest;
import com.nttdata.desafiostarwars.dto.responses.PutFilmeResponse;
import com.nttdata.desafiostarwars.services.film.PutFilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.nttdata.desafiostarwars.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class PutFilmControllerTest {

    private static final String FILM_API_URL = "/v1/filmes";
    private static final Long VALID_ID_FILM = 1L;

    @Mock
    private PutFilmService putFilmService;

    @InjectMocks
    private PutFilmController putFilmController;

    private MockMvc mockMvc;


    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(putFilmController).build();
    }

    @Test
    public void deveAtualizarADescricaoComSucesso() throws Exception {

        PutFilmeRequest putFilmeRequest = mockPutFilmesRequest();
        PutFilmeResponse putFilmeResponse = mockPutFilmesResponse();

        Mockito.when(putFilmService.update(VALID_ID_FILM, putFilmeRequest)).thenReturn(putFilmeResponse);

        mockMvc.perform(MockMvcRequestBuilders.put(FILM_API_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(putFilmeRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(putFilmeResponse.getTitle())))
                .andExpect(jsonPath("$.openingCrawl", is(putFilmeResponse.getOpeningCrawl())));

    }

    private PutFilmeResponse mockPutFilmesResponse() {
        return PutFilmeResponse.builder()
                .openingCrawl("Fazendo atualização com sucesso!")
                .build();
    }

    private PutFilmeRequest mockPutFilmesRequest() {
        return PutFilmeRequest.builder()
                .openingCrawl("Fazendo atualização com sucesso!")
                .build();
    }

}