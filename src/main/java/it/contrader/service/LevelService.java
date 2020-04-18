package it.contrader.service;

import it.contrader.converter.LevelConverter;
import it.contrader.dao.LevelDAO;
import it.contrader.dto.LevelDTO;
import it.contrader.model.Level;

public class LevelService extends AbstractService<Level, LevelDTO>{

	public LevelService() {
		this.dao = new LevelDAO();
		this.converter = new LevelConverter();
	}

	@Override
	public boolean find(Integer parameter, Integer i, String string) {
		// TODO Auto-generated method stub
		return false;
	}

}
