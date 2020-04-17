package it.contrader.service;

import it.contrader.converter.HangmanConverter;
import it.contrader.dao.HangmanDAO;
import it.contrader.dto.HangmanDTO;
import it.contrader.model.Hangman;

public class HangmanService extends AbstractService<Hangman,HangmanDTO> {	
	public HangmanService(){
		this.dao = new HangmanDAO();
		this.converter = new HangmanConverter();
	}

	@Override
	public String find(String parameter, Integer i, String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
