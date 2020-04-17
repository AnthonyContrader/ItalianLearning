package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dao.CategoryDAO;
import it.contrader.dao.LevelDAO;
import it.contrader.dto.FindMistakeDTO;
import it.contrader.model.FindMistake;

public class FindMistakeConverter implements Converter<FindMistake, FindMistakeDTO>{
	
	private CategoryDAO categoryDAO;
	private LevelDAO levelDAO;
	
	public FindMistakeConverter(){
		this.categoryDAO = new CategoryDAO();
		this.levelDAO = new LevelDAO();
	}
	
	public FindMistakeDTO toDTO(FindMistake findMistake) {
		String categoryString = categoryDAO.read(findMistake.getIdCategory()).getTitle();
		String levelString = levelDAO.read(findMistake.getIdLevel()).getName();
		FindMistakeDTO findMistakeDTO = new FindMistakeDTO(findMistake.getId(),findMistake.getSolution(),findMistake.getDefinition(),findMistake.getSentence(),findMistake.getOptionA(),findMistake.getOptionB(),findMistake.getOptionC(), findMistake.getIdCategory(), categoryString, findMistake.getIdLevel(), levelString);
		return findMistakeDTO;
	}
	
	//converte category DTO in category model (Entity)
	public FindMistake toEntity(FindMistakeDTO findMistakeDTO) {
		FindMistake findMistake = new FindMistake(findMistakeDTO.getId(),findMistakeDTO.getSolution(),findMistakeDTO.getDefinition(),findMistakeDTO.getSentence(),findMistakeDTO.getOptionA(),findMistakeDTO.getOptionB(),findMistakeDTO.getOptionC(), findMistakeDTO.getIdCategory(), findMistakeDTO.getIdLevel());
		return findMistake;
	}
	
	// converte lista di category model (Entity) in lista di categoryDTO
	public List<FindMistakeDTO> toDTOList(List<FindMistake> findMistakeList){
		
		//lista vuota
		List<FindMistakeDTO> findMistakeDTOList = new ArrayList<FindMistakeDTO>();
		
		// ciclo for che itera per ogni elemento di category in categoryList
		for (FindMistake findMistake : findMistakeList) {
			findMistakeDTOList.add(toDTO(findMistake));
		}
		return findMistakeDTOList;

	}
}
