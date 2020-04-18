package it.contrader.service;

import it.contrader.converter.FindMistakeConverter;
import it.contrader.dao.FindMistakeDAO;
import it.contrader.dto.FindMistakeDTO;
import it.contrader.model.FindMistake;

public class FindMistakeService extends AbstractService<FindMistake, FindMistakeDTO> {

	public FindMistakeService() {
		this.dao = new FindMistakeDAO();
		this.converter = new FindMistakeConverter();
	}

	@Override
	public boolean find(Integer parameter, Integer i, String string) {
		// TODO Auto-generated method stub
		return false;
	}
}
