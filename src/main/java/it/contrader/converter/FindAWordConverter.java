package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.FindAWordDTO;
import it.contrader.model.FindAWord;
import it.contrader.dao.CategoryDAO;

public class FindAWordConverter {
	
	private CategoryDAO categoryDAO;
	
	public FindAWordConverter() {
		this.categoryDAO = new CategoryDAO();
		
	}
	

	// converte Category Model in CategoryDTO
		public FindAWordDTO toDTO(FindAWord findAWord) {
			String categoryString = categoryDAO.read(findAWord.getIdCategory()).getTitle();
			FindAWordDTO findAWordDTO = new FindAWordDTO(findAWord.getId(),findAWord.getIdCategory(),findAWord.getScore(),findAWord.getSolution(),findAWord.getDefinition(),findAWord.getSentence(), categoryString);
			return findAWordDTO;
		}                                 
		
		//converte category DTO in category model (Entity)
		public FindAWord toEntity(FindAWordDTO findAWordDTO) {
			FindAWord findAWord = new FindAWord(findAWordDTO.getId(),findAWordDTO.getIdCategory(),findAWordDTO.getScore(), findAWordDTO.getSolution(),findAWordDTO.getDefinition(),findAWordDTO.getSentence());
			return findAWord;
		}
		
		// converte lista di category model (Entity) in lista di categoryDTO
		public List<FindAWordDTO> toDTOList(List<FindAWord> findAWordList){
			
			//lista vuota
			List<FindAWordDTO> findAWordDTOList = new ArrayList<FindAWordDTO>();
			
			// ciclo for che itera per ogni elemento di category in categoryList
			for (FindAWord findAWord : findAWordList) {
				findAWordDTOList.add(toDTO(findAWord));
			}
			return findAWordDTOList;

		}

}
