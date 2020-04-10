package it.contrader.service;

import java.util.List;
import it.contrader.converter.OrganizeSentenceConverter;
import it.contrader.dao.OrganizeSentenceDAO;
import it.contrader.dto.OrganizeSentenceDTO;
import it.contrader.model.OrganizeSentence;


public class OrganizeSentenceService {
	
	private OrganizeSentenceDAO organizeSentenceDAO;
	private OrganizeSentenceConverter organizeSentenceConverter;
	
	
	public OrganizeSentenceService() {
		this.organizeSentenceDAO = new OrganizeSentenceDAO();
		this.organizeSentenceConverter = new OrganizeSentenceConverter();
	}
	
	
	// Ottiene una lista di entita e le restituisce convertendole in DTO 
	public List<OrganizeSentenceDTO> getAll() {
		return organizeSentenceConverter.toDTOList(organizeSentenceDAO.getAll());
	}
	
	// Ottiene un'entita e la restituisce convertendola in DTO
	public OrganizeSentenceDTO read(int id) {
		OrganizeSentence os = organizeSentenceDAO.read(id);
		if (os != null)
			return organizeSentenceConverter.toDTO(os);
		else
			return null;
		//return organizeSentenceConverter.toDTO(organizeSentenceDAO.read(id));
	}
	
	// Converte un DTO in entita e lo passa al DAO per l'inserimento
	public boolean insert(OrganizeSentenceDTO dto) {
		
		return organizeSentenceDAO.insert(organizeSentenceConverter.toEntity(dto));
	}
	
	// Converte un categoryDTO in entita e lo passa allo categoryDAO per la modifica
	public boolean update(OrganizeSentenceDTO dto) {
		return organizeSentenceDAO.update(organizeSentenceConverter.toEntity(dto));
	}
	
	// Questo metodo chiama direttamente il DAO
	public boolean delete(int id) {
		return organizeSentenceDAO.delete(id);
	}


}
