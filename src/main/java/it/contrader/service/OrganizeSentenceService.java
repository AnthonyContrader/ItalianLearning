package it.contrader.service;

//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

//import it.contrader.converter.OrganizeSentenceConverter;
//import it.contrader.dao.OrganizeSentenceRepository;
import it.contrader.dto.OrganizeSentenceDTO;
import it.contrader.model.OrganizeSentence;

/*
**created by Torquato Di Maio
*/

@Service
public class OrganizeSentenceService extends AbstractService<OrganizeSentence, OrganizeSentenceDTO> {
	
	//non ci va niente xke passo tutto nell'AbstractService e non devo fare niente specifico per OrganizeSentence
	//@Autowired
	//private OrganizeSentenceConverter converter;
	//@Autowired
	//private OrganizeSentenceRepository repository;
	
}
