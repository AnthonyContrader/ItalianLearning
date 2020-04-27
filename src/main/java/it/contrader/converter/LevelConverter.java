//Created By @Alessandro Alfieri
package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.model.Level;
import it.contrader.dto.LevelDTO;

@Component
public class LevelConverter extends AbstractConverter<Level, LevelDTO> {

	@Override
	public Level toEntity(LevelDTO levelDTO) {
		Level level = null;
		level = (levelDTO != null) ? new Level(levelDTO.getId(), levelDTO.getName(), levelDTO.getDescription(), levelDTO.getScore()) : null ;
		return level;
	}

	@Override
	public LevelDTO toDTO(Level level) {
		LevelDTO levelDTO = null;
		levelDTO = (level != null) ? new LevelDTO(level.getId(), level.getName(), level.getDescription(), level.getScore()) : null;
		return levelDTO;
	}
	

}