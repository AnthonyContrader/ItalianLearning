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
	
	public boolean find(Integer idPlaylist, Integer idGame, String typeGame) {
		// Questo mtodo chiama direttamente il DAO
		return dao.find(idPlaylist, idGame, typeGame);
	}

	@Override
	public String find(String parameter, Integer i, String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
