package com.nttdata.desafiostarwars.controllers.film;

import com.nttdata.desafiostarwars.dto.requests.PutCommentFilmeRequest;
import com.nttdata.desafiostarwars.dto.responses.PutCommentFilmeResponse;
import com.nttdata.desafiostarwars.services.film.PutCommentFilmService;
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
class PutCommentFilmControllerTest {

    private static final String FILM_API_URL = "/v1/filmes";
    private static final String FILM_PUT_COMMENT_URL = "/comentario";
    private static final Long VALID_ID_FILM = 1L;

    @Mock
    private PutCommentFilmService putCommentFilmService;

    @InjectMocks
    private PutCommentFilmController putCommentFilmController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(putCommentFilmController).build();
    }

    @Test
    public void deveAdicionarComentarioComSucesso() throws Exception {

        PutCommentFilmeRequest putCommentFilmeRequest = mockPutFilmesRequest();
        PutCommentFilmeResponse putCommentFilmeResponse = mockPutFilmesResponse();

        Mockito.when(putCommentFilmService.addComment(VALID_ID_FILM, putCommentFilmeRequest)).thenReturn(putCommentFilmeResponse);

        mockMvc.perform(MockMvcRequestBuilders.put(FILM_API_URL + "/1" + FILM_PUT_COMMENT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(putCommentFilmeRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(putCommentFilmeResponse.getTitle())))
                .andExpect(jsonPath("$.comment", is(putCommentFilmeResponse.getComment())));

    }

    @Test
    public void naoDeveAdicionarComentarioComSucesso() throws Exception {

        PutCommentFilmeRequest putCommentFilmeRequest = mockPutFilmesRequest();
        PutCommentFilmeResponse putCommentFilmeResponse = mockPutFilmesResponse();

        Mockito.when(putCommentFilmService.addComment(VALID_ID_FILM, putCommentFilmeRequest)).thenReturn(putCommentFilmeResponse);

        mockMvc.perform(MockMvcRequestBuilders.put(FILM_API_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(putCommentFilmeRequest)))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    private PutCommentFilmeResponse mockPutFilmesResponse() {
        return PutCommentFilmeResponse.builder()
                .title("The Empire Strikes Back")
                .comment("Adicionando comentário com sucesso!")
                .build();
    }

    private PutCommentFilmeRequest mockPutFilmesRequest() {
        return PutCommentFilmeRequest.builder()
                .comment("Adicionando comentário com sucesso!")
                .build();
    }

}