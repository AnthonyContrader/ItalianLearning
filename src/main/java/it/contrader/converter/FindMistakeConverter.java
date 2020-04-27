//Created By @Alessandro Alfieri
package it.contrader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.contrader.dto.FindMistakeDTO;
import it.contrader.model.FindMistake;

@Component
public class FindMistakeConverter extends AbstractConverter<FindMistake, FindMistakeDTO> {
	
	@Autowired
	CategoryConverter categoryConverter = new CategoryConverter();
	@Autowired
	LevelConverter levelConverter = new LevelConverter();
		
	@Override
	public FindMistake toEntity(FindMistakeDTO findMistakeDTO) {
		FindMistake findMistake = null;
		if (findMistakeDTO != null) {
			findMistake = new FindMistake(findMistakeDTO.getId(), findMistakeDTO.getSolution(), findMistakeDTO.getDefinition(), findMistakeDTO.getSentence(), findMistakeDTO.getOptionA(), findMistakeDTO.getOptionB(), findMistakeDTO.getOptionC(), categoryConverter.toEntity(findMistakeDTO.getCategory()), levelConverter.toEntity(findMistakeDTO.getLevel()));
		}
		return findMistake;
	}

	@Override
	public FindMistakeDTO toDTO(FindMistake findMistake) {
		FindMistakeDTO findMistakeDTO = null;
		if (findMistake != null) {
			findMistakeDTO = new FindMistakeDTO(findMistake.getId(), findMistake.getSolution(), findMistake.getDefinition(), findMistake.getSentence(), findMistake.getOptionA(), findMistake.getOptionB(), findMistake.getOptionC(), categoryConverter.toDTO(findMistake.getCategory()), levelConverter.toDTO(findMistake.getLevel()));
		}
		return findMistakeDTO;
	}
}