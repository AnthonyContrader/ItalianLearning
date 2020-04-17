package it.contrader.service;

import it.contrader.converter.PlaylistConverter;
import it.contrader.dao.PlaylistDAO;
import it.contrader.dto.PlaylistDTO;
import it.contrader.model.Playlist;

public class PlaylistService extends AbstractService<Playlist, PlaylistDTO>{

	public PlaylistService() {
		this.dao = new PlaylistDAO();
		this.converter = new PlaylistConverter();
	}

	@Override
	public String find(String parameter, Integer i, String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
