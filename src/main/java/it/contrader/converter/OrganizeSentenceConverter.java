package it.contrader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.contrader.dto.OrganizeSentenceDTO;
import it.contrader.model.OrganizeSentence;


/*
**created by Torquato Di Maio
*/

@Component //legge e comprende le annotazioni da altre classi grazie a Component
public class OrganizeSentenceConverter extends AbstractConverter<OrganizeSentence, OrganizeSentenceDTO> {

	@Autowired
	CategoryConverter categoryConverter = new CategoryConverter();
	@Autowired
	LevelConverter levelConverter = new LevelConverter();
	
	@Override
	public OrganizeSentence toEntity(OrganizeSentenceDTO organizeSentenceDTO) {
		OrganizeSentence organizeSentence = null;
		if (organizeSentenceDTO != null) {
			//il costruttore di Model e in ordine a come ho definito le variabili in OrganizeSentence
			organizeSentence = new OrganizeSentence(organizeSentenceDTO.getId(), organizeSentenceDTO.getSolution(), 
					organizeSentenceDTO.getSentence(), organizeSentenceDTO.getDefinition(), categoryConverter.toEntity(organizeSentenceDTO.getCategory()),
					levelConverter.toEntity(organizeSentenceDTO.getLevel()));
		}
		return organizeSentence;
	}

	@Override
	public OrganizeSentenceDTO toDTO(OrganizeSentence organizeSentence) {
		OrganizeSentenceDTO organizeSentenceDTO = null;
		if (organizeSentence != null) {
			organizeSentenceDTO = new OrganizeSentenceDTO(organizeSentence.getId(), organizeSentence.getSolution(), 
					organizeSentence.getSentence(), organizeSentence.getDefinition(), categoryConverter.toDTO(organizeSentence.getCategory()),
					levelConverter.toDTO(organizeSentence.getLevel()));

		}
		return organizeSentenceDTO;
	}
	
	

}
