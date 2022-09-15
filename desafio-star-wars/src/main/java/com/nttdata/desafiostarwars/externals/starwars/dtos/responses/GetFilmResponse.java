package com.nttdata.desafiostarwars.externals.starwars.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetFilmResponse {

    @JsonProperty("title")
    private String title;

    @JsonProperty("episode_id")
    private Integer episodeId;

    @JsonProperty("opening_crawl")
    private String openingCrawl;

    @JsonProperty("director")
    private String director;

    @JsonProperty("producer")
    private String producer;

    @JsonProperty("release_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate releaseDate;

}
