package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.GuessPictureDTO;
import it.contrader.model.GuessPicture;

/*
 * Created by Enzo Tasca
 */

@Component
public class GuessPictureConverter extends AbstractConverter<GuessPicture, GuessPictureDTO>{

	@Override
	public GuessPicture toEntity(GuessPictureDTO guessPictureDTO) {
		if(guessPictureDTO == null)
			return null;
		
		return new GuessPicture(guessPictureDTO.getId(), guessPictureDTO.getImage(), guessPictureDTO.getSolution(), guessPictureDTO.getCategory(), guessPictureDTO.getLevel());
	}

	@Override
	public GuessPictureDTO toDTO(GuessPicture guessPicture) {
		if(guessPicture == null)
			return null;
		
		return new GuessPictureDTO(guessPicture.getId(), guessPicture.getSolution(), guessPicture.getImage(), guessPicture.getCategory(), guessPicture.getLevel());
	}

}
