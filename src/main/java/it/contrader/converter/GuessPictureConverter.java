package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.GuessPictureDTO;
import it.contrader.model.GuessPicture;

public class GuessPictureConverter {
	
	public GuessPictureDTO toDTO(GuessPicture guessPicture) {
		GuessPictureDTO guessPictureDTO = new GuessPictureDTO(guessPicture.getId(), guessPicture.getIdCategory(), guessPicture.getScore(), guessPicture.getSolution(), guessPicture.getImage());
		return guessPictureDTO;
	}
		
	public GuessPicture toEntity(GuessPictureDTO guessPictureDTO) {
		GuessPicture guessPicture = new GuessPicture(guessPictureDTO.getId(), guessPictureDTO.getIdCategory(), guessPictureDTO.getScore(), guessPictureDTO.getSolution(), guessPictureDTO.getImage());
		return guessPicture;
	}
		
	public List<GuessPictureDTO> toDTOList(List<GuessPicture> guessPictureList){
			
		List<GuessPictureDTO> guessPictureDTOList = new ArrayList<GuessPictureDTO>();
			
		for (GuessPicture guessPicture : guessPictureList) {
			guessPictureDTOList.add(toDTO(guessPicture));
		}
		return guessPictureDTOList;

	}

}
