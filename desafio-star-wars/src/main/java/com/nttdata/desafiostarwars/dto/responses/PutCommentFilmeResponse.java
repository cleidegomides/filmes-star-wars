package com.nttdata.desafiostarwars.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PutCommentFilmeResponse {

    private Long id;
    private String title;
    private String comment;
}
