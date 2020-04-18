package it.contrader.service;

import it.contrader.dao.OrganizeSentenceDAO;
import it.contrader.converter.OrganizeSentenceConverter;
import it.contrader.dto.OrganizeSentenceDTO;
import it.contrader.model.OrganizeSentence;


public class OrganizeSentenceService extends AbstractService<OrganizeSentence, OrganizeSentenceDTO>{

	public OrganizeSentenceService() {
		// TODO Auto-generated constructor stub
		this.dao =new OrganizeSentenceDAO();
		this.converter = new OrganizeSentenceConverter();
		
	}

	@Override
	public boolean find(Integer parameter, Integer i, String string) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
