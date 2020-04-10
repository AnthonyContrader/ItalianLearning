package it.contrader.service;

import java.util.List;

import it.contrader.converter.GuessPictureConverter;
import it.contrader.dao.GuessPictureDAO;
import it.contrader.dto.GuessPictureDTO;
import it.contrader.model.GuessPicture;

public class GuessPictureService {
	
	private GuessPictureDAO guessPictureDAO;
	private GuessPictureConverter guessPictureConverter;
	
	public GuessPictureService() {
		this.guessPictureDAO = new GuessPictureDAO();
		this.guessPictureConverter = new GuessPictureConverter();
	}
	
	// Ottiene una lista di entita e le restituisce convertendole in DTO 
	public List<GuessPictureDTO> getAll() {
		return guessPictureConverter.toDTOList(guessPictureDAO.getAll());
	}
		
	// Ottiene un'entita e la restituisce convertendola in DTO
	public GuessPictureDTO read(int id) {
		
		GuessPicture game = guessPictureDAO.read(id);
		if (game != null)
			return guessPictureConverter.toDTO(game);
		else
			return null;
	}
		
	// Converte un DTO in entita e lo passa al DAO per l'inserimento
	public boolean insert(GuessPictureDTO dto) {
		return guessPictureDAO.insert(guessPictureConverter.toEntity(dto));
	}
		
	// Converte un categoryDTO in entita e lo passa allo categoryDAO per la modifica
	public boolean update(GuessPictureDTO dto) {
		return guessPictureDAO.update(guessPictureConverter.toEntity(dto));
	}
		
	// Questo metodo chiama direttamente il DAO
	public boolean delete(int id) {
		return guessPictureDAO.delete(id);
	}
	
}
