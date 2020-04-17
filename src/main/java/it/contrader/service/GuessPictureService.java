package it.contrader.service;

import it.contrader.converter.GuessPictureConverter;
import it.contrader.dao.GuessPictureDAO;
import it.contrader.dto.GuessPictureDTO;
import it.contrader.model.GuessPicture;

public class GuessPictureService extends AbstractService<GuessPicture, GuessPictureDTO>{
	
	//Istanzio DAO  e Converter specifici.
	public GuessPictureService(){
		this.dao = new GuessPictureDAO();
		this.converter = new GuessPictureConverter();
	}

	@Override
	public String find(String parameter, Integer i, String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
