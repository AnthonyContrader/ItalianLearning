package it.contrader.service;

import java.util.List;

import it.contrader.converter.HangmanConverter;
import it.contrader.dao.HangmanDAO;
import it.contrader.dto.HangmanDTO;

public class HangmanService {
	
	private HangmanDAO hangmanDAO;
	private HangmanConverter hangmanConverter;
	
	
	public HangmanService() {
		this.hangmanDAO = new HangmanDAO();
		this.hangmanConverter = new HangmanConverter();
	}
	
	
	// Ottiene una lista di entita e le restituisce convertendole in DTO 
	public List<HangmanDTO> getAll() {
		return hangmanConverter.toDTOList(hangmanDAO.getAll());
	}
	
	// Ottiene un'entita e la restituisce convertendola in DTO
	public HangmanDTO read(int id) {
		return hangmanConverter.toDTO(hangmanDAO.read(id));
	}
	
	// Converte un DTO in entita e lo passa al DAO per l'inserimento
	public boolean insert(HangmanDTO dto) {
		return hangmanDAO.insert(hangmanConverter.toEntity(dto));
	}
	
	// Converte un categoryDTO in entita e lo passa allo categoryDAO per la modifica
	public boolean update(HangmanDTO dto) {
		return hangmanDAO.update(hangmanConverter.toEntity(dto));
	}
	
	// Questo metodo chiama direttamente il DAO
	public boolean delete(int id) {
		return hangmanDAO.delete(id);
	}

}
