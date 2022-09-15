package com.nttdata.desafiostarwars.dto.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetListItemFilmeResponse {

    private Long id;
    private String title;
    private Integer version;
}
