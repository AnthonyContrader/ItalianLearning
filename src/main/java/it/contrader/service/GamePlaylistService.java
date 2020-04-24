package it.contrader.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.dao.GamePlaylistRepository;
import it.contrader.dto.GamePlaylistDTO;
import it.contrader.model.GamePlaylist;
import it.contrader.model.Playlist;

/*
 * created by Anna Cecere & Alessandro Alfieri
 */
 
@Service
public class GamePlaylistService extends AbstractService <GamePlaylist, GamePlaylistDTO> {
	
	@Autowired
	private GamePlaylistRepository repository;

	public boolean findGameInPlaylist(Long idPlaylist, Long idGame, String typeGame) {
		return repository.findGameInPlaylist(idPlaylist, idGame, typeGame).toString().equals("1");
	}
	public void deleteAllByPlaylist(Playlist playlist) {
		repository.deleteAllByPlaylist(playlist);
	}
	
}