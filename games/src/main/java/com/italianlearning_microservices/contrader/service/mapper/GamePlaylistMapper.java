package com.italianlearning_microservices.contrader.service.mapper;

import com.italianlearning_microservices.contrader.domain.*;
import com.italianlearning_microservices.contrader.service.dto.GamePlaylistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GamePlaylist and its DTO GamePlaylistDTO.
 */
@Mapper(componentModel = "spring", uses = {PlaylistMapper.class})
public interface GamePlaylistMapper extends EntityMapper<GamePlaylistDTO, GamePlaylist> {

    @Mapping(source = "playlist.id", target = "playlistId")
    @Mapping(source = "playlist.name", target = "playlistName")
    GamePlaylistDTO toDto(GamePlaylist gamePlaylist);

    @Mapping(source = "playlistId", target = "playlist")
    GamePlaylist toEntity(GamePlaylistDTO gamePlaylistDTO);

    default GamePlaylist fromId(Long id) {
        if (id == null) {
            return null;
        }
        GamePlaylist gamePlaylist = new GamePlaylist();
        gamePlaylist.setId(id);
        return gamePlaylist;
    }
}
