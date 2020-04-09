package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.FindAWordDTO;
import it.contrader.model.FindAWord;

public class FindAWordConverter {

	// converte Category Model in CategoryDTO
		public FindAWordDTO toDTO(FindAWord findAWord) {
			FindAWordDTO findAWordDTO = new FindAWordDTO(findAWord.getId(),findAWord.getIdCategory(),findAWord.getScore(),findAWord.getSolution(),findAWord.getDefinition(),findAWord.getSentence());
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
