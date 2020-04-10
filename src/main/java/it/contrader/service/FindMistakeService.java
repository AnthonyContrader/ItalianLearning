package it.contrader.service;

import java.util.List;

import it.contrader.converter.FindMistakeConverter;
import it.contrader.dao.FindMistakeDAO;
import it.contrader.dto.FindMistakeDTO;

public class FindMistakeService {
	
	private FindMistakeDAO findMistakeDAO;
	private FindMistakeConverter findMistakeConverter;
	
	public FindMistakeService() {
		this.findMistakeDAO = new FindMistakeDAO();
		this.findMistakeConverter = new FindMistakeConverter();
	}
	 
	public List<FindMistakeDTO> getAll() {
		return findMistakeConverter.toDTOList(findMistakeDAO.getAll());
	}
	
	public FindMistakeDTO read(int id) {
		return findMistakeConverter.toDTO(findMistakeDAO.read(id));
	}
	
	public boolean insert(FindMistakeDTO dto) {
		return findMistakeDAO.insert(findMistakeConverter.toEntity(dto));
	}
	
	public boolean update(FindMistakeDTO dto) {
		return findMistakeDAO.update(findMistakeConverter.toEntity(dto));
	}
	
	public boolean delete(int id) {
		return findMistakeDAO.delete(id);
	}


}
