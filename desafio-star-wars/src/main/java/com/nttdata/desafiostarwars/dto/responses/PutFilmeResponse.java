package com.nttdata.desafiostarwars.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PutFilmeResponse {

    private Long id;
    private String title;
    private String openingCrawl;
}
