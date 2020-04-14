package it.contrader.service;

import it.contrader.converter.FindAWordConverter;
import it.contrader.dao.FindAWordDAO;
import it.contrader.dto.FindAWordDTO;
import it.contrader.model.FindAWord;


public class FindAWordService extends AbstractService<FindAWord, FindAWordDTO> {
	
	
	public FindAWordService() {
		this.dao = new FindAWordDAO();
		this.converter = new FindAWordConverter();
		
	}

}
