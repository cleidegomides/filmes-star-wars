package com.nttdata.desafiostarwars.mapper;

import com.nttdata.desafiostarwars.dto.requests.PutCommentFilmeRequest;
import com.nttdata.desafiostarwars.dto.requests.PutFilmeRequest;
import com.nttdata.desafiostarwars.dto.responses.GetFilmeResponse;
import com.nttdata.desafiostarwars.dto.responses.GetListItemFilmeResponse;
import com.nttdata.desafiostarwars.dto.responses.PutCommentFilmeResponse;
import com.nttdata.desafiostarwars.dto.responses.PutFilmeResponse;
import com.nttdata.desafiostarwars.entities.Filme;
import com.nttdata.desafiostarwars.externals.starwars.dtos.responses.GetFilmResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FilmeEntityToDtoMapper {

    Filme toEntity(GetFilmResponse getFilmResponse);
    GetFilmeResponse toGetResponse(Filme filme);
    PutFilmeResponse toPutResponse(Filme filme);
    PutCommentFilmeResponse toPutCommentResponse(Filme filme);
    GetListItemFilmeResponse toGetListItemResponse(Filme filme);
    void toEntityUpdate(PutFilmeRequest putFilmeRequest, @MappingTarget Filme filme);
    void toEntityAddComment(PutCommentFilmeRequest putCommentFilmeRequest, @MappingTarget Filme filme);
}
