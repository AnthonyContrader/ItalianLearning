package com.italianlearning_microservices.contrader.service.mapper;

import com.italianlearning_microservices.contrader.domain.*;
import com.italianlearning_microservices.contrader.service.dto.PlaylistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Playlist and its DTO PlaylistDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlaylistMapper extends EntityMapper<PlaylistDTO, Playlist> {


    @Mapping(target = "gamePlaylists", ignore = true)
    Playlist toEntity(PlaylistDTO playlistDTO);

    default Playlist fromId(Long id) {
        if (id == null) {
            return null;
        }
        Playlist playlist = new Playlist();
        playlist.setId(id);
        return playlist;
    }
}
