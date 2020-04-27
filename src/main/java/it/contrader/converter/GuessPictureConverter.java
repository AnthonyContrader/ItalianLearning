package it.contrader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.contrader.dto.CategoryDTO;
import it.contrader.dto.LevelDTO;
import it.contrader.dto.GuessPictureDTO;
import it.contrader.model.GuessPicture;
import it.contrader.model.Category;
import it.contrader.model.Level;

/*
 * Created by Enzo Tasca
 */

@Component
public class GuessPictureConverter extends AbstractConverter<GuessPicture, GuessPictureDTO>{
	
	@Autowired
	CategoryConverter categoryConverter = new CategoryConverter();
	
	@Autowired
	LevelConverter levelConverter = new LevelConverter();

	@Override
	public GuessPicture toEntity(GuessPictureDTO guessPictureDTO) {
		if(guessPictureDTO == null)
			return null;
		
		Category category = categoryConverter.toEntity(guessPictureDTO.getCategory());
		Level level = levelConverter.toEntity(guessPictureDTO.getLevel());
		return new GuessPicture(guessPictureDTO.getId(), guessPictureDTO.getImage(), guessPictureDTO.getSolution(), category, level);
	}

	@Override
	public GuessPictureDTO toDTO(GuessPicture guessPicture) {
		if(guessPicture == null)
			return null;
		
		CategoryDTO category = categoryConverter.toDTO(guessPicture.getCategory());
		LevelDTO level = levelConverter.toDTO(guessPicture.getLevel());
		return new GuessPictureDTO(guessPicture.getId(), guessPicture.getSolution(), guessPicture.getImage(), category, level);
	}

}
