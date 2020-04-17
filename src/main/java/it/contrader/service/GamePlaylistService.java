package it.contrader.service;

import it.contrader.converter.GamePlaylistConverter;
import it.contrader.dao.GamePlaylistDAO;
import it.contrader.dto.GamePlaylistDTO;
import it.contrader.model.GamePlaylist;

public class GamePlaylistService extends AbstractService<GamePlaylist,GamePlaylistDTO> {	
	public GamePlaylistService(){
		this.dao = new GamePlaylistDAO();
		this.converter = new GamePlaylistConverter();
	}
	
}
