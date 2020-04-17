package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.FindAWordDTO;
import it.contrader.model.FindAWord;
import it.contrader.dao.CategoryDAO;
import it.contrader.dao.LevelDAO;

public class FindAWordConverter  implements Converter<FindAWord, FindAWordDTO> {
	
	private CategoryDAO categoryDAO;
	private LevelDAO levelDAO;
	
	public FindAWordConverter() {
		this.categoryDAO = new CategoryDAO();
		this.levelDAO = new LevelDAO();
		
	}
	

	// converte Category Model in CategoryDTO
		public FindAWordDTO toDTO(FindAWord findAWord) {
			String categoryString = categoryDAO.read(findAWord.getIdCategory()).getTitle();
			String levelString = levelDAO.read(findAWord.getIdLevel()).getName();
			FindAWordDTO findAWordDTO = new FindAWordDTO(findAWord.getId(),findAWord.getIdCategory(),findAWord.getSolution(),findAWord.getDefinition(),findAWord.getSentence(), categoryString, findAWord.getIdLevel(),levelString);
			return findAWordDTO;
		}                                 
		
		//converte category DTO in category model (Entity)
		public FindAWord toEntity(FindAWordDTO findAWordDTO) {
			FindAWord findAWord = new FindAWord(findAWordDTO.getId(),findAWordDTO.getIdCategory(),findAWordDTO.getSolution(),findAWordDTO.getDefinition(),findAWordDTO.getSentence(), findAWordDTO.getIdLevel());
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
