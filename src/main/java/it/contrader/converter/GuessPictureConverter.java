package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.GuessPictureDTO;
import it.contrader.model.GuessPicture;
import it.contrader.dao.CategoryDAO;

public class GuessPictureConverter implements Converter<GuessPicture, GuessPictureDTO>{
	
	private CategoryDAO categoryDAO;
	
	public GuessPictureConverter(){
		this.categoryDAO = new CategoryDAO();
	}
	
	public GuessPictureDTO toDTO(GuessPicture guessPicture) {
		String categoryString = categoryDAO.read(guessPicture.getIdCategory()).getTitle();
		GuessPictureDTO guessPictureDTO = new GuessPictureDTO(guessPicture.getId(), guessPicture.getIdCategory(), guessPicture.getScore(), guessPicture.getSolution(), guessPicture.getImage(), categoryString);
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
