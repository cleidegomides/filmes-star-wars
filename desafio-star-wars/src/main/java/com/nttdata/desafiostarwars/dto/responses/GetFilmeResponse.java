package com.nttdata.desafiostarwars.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFilmeResponse {

    private Long id;
    private String title;
    private Integer episodeId;
    private String openingCrawl;
    private String director;
    private String producer;
    private Integer version;
    private String comment;
}
