package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.GamePlaylistConverter;
import it.contrader.dao.GamePlaylistRepository;
import it.contrader.dto.GamePlaylistDTO;
import it.contrader.model.GamePlaylist;

/*
 * created by Anna Cecere & Alessandro Alfieri
 */
 
@Service
public class GamePlaylistService extends AbstractService <GamePlaylist, GamePlaylistDTO> {
	
	@Autowired
	private GamePlaylistRepository repository;

	public boolean findGameInPlaylist(Long idPlaylist, Long idGame, String typeGame) {
		return repository.existsByIdPlaylistAndIdGameAndTypeGame(idPlaylist, idGame, typeGame);
	}
	
}