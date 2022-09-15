package com.nttdata.desafiostarwars.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Data
@Entity
@Table(name = "tb_filmes")
@NoArgsConstructor
@AllArgsConstructor
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer episodeId;

    @Column(name = "opening_crawl", length = 5000)
    private String openingCrawl;

    private String director;
    private String producer;
    private LocalDate releaseDate;

    private Integer version;
    private String comment;
}
