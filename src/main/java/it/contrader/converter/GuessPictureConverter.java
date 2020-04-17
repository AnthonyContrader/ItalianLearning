package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.GuessPictureDTO;
import it.contrader.model.GuessPicture;
import it.contrader.dao.CategoryDAO;
import it.contrader.dao.LevelDAO;

public class GuessPictureConverter implements Converter<GuessPicture, GuessPictureDTO>{
	
	private CategoryDAO categoryDAO;
	private LevelDAO levelDAO;
	
	public GuessPictureConverter(){
		this.categoryDAO = new CategoryDAO();
		this.levelDAO = new LevelDAO();
	}
	
	public GuessPictureDTO toDTO(GuessPicture guessPicture) {
		String categoryString = categoryDAO.read(guessPicture.getIdCategory()).getTitle();
		String levelString = levelDAO.read(guessPicture.getIdLevel()).getName();
		GuessPictureDTO guessPictureDTO = new GuessPictureDTO(guessPicture.getId(), guessPicture.getIdCategory(), guessPicture.getSolution(), guessPicture.getImage(), categoryString, guessPicture.getIdLevel(), levelString);
		return guessPictureDTO;
	}
		
	public GuessPicture toEntity(GuessPictureDTO guessPictureDTO) {
		GuessPicture guessPicture = new GuessPicture(guessPictureDTO.getId(), guessPictureDTO.getIdCategory(), guessPictureDTO.getSolution(), guessPictureDTO.getImage(), guessPictureDTO.getIdLevel());
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
