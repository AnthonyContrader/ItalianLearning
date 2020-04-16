package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.LevelDTO;
import it.contrader.model.Level;

public class LevelConverter implements Converter<Level, LevelDTO> {

	public LevelDTO toDTO(Level level) {
		LevelDTO levelDTO = new LevelDTO(level.getId(),level.getScore(), level.getName() ,level.getDescription());
		return levelDTO;
	}
	
	public Level toEntity(LevelDTO levelDto) {
		Level level = new Level(levelDto.getId(),levelDto.getScore(), levelDto.getName(), levelDto.getDescription());
		return level;
	}
	
	public List<LevelDTO> toDTOList(List<Level> levelList){
		
		List<LevelDTO> levelDTOList = new ArrayList<LevelDTO>();
		
		for (Level level : levelList) {
			levelDTOList.add(toDTO(level));
		}
		return levelDTOList;

	}
}
