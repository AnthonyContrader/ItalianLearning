package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;


import it.contrader.dto.OrganizeSentenceDTO;
import it.contrader.model.OrganizeSentence;



public class OrganizeSentenceConverter {
	
	// converte OrganizeSentence Model in OrganizeSentenceDTO
	//int id, String solution, String sentence, Integer score, String definition,
	//Integer idOrganizeSentence
		public OrganizeSentenceDTO toDTO(OrganizeSentence organizeSentence) {
			OrganizeSentenceDTO organizeSentenceDTO = new OrganizeSentenceDTO(organizeSentence.getId(), organizeSentence.getSolution() ,organizeSentence.getSentence(),
					organizeSentence.getScore(), organizeSentence.getDefinition(), organizeSentence.getIdCategory() );
			
			return organizeSentenceDTO;
		}
		
		//converte organizeSentence DTO in organizeSentence model (Entity)
		public OrganizeSentence toEntity(OrganizeSentenceDTO organizeSentenceDTO) {
			
			OrganizeSentence organizeSentence = new OrganizeSentence(organizeSentenceDTO.getId(), organizeSentenceDTO.getSolution() ,organizeSentenceDTO.getSentence(),
					organizeSentenceDTO.getScore(), organizeSentenceDTO.getDefinition(), organizeSentenceDTO.getIdCategory() );
			
			return organizeSentence;
		}
		
		
		// converte lista di category model (Entity) in lista di categoryDTO
		public List<OrganizeSentenceDTO> toDTOList(List<OrganizeSentence> organizeSentenceList){
			
			//lista vuota
			List<OrganizeSentenceDTO> organizeSentenceDTOList = new ArrayList<OrganizeSentenceDTO>();
			
			// ciclo for che itera per ogni elemento di category in categoryList
			for (OrganizeSentence organizeSentence : organizeSentenceList) {
				organizeSentenceDTOList.add(toDTO(organizeSentence));
			}
			return organizeSentenceDTOList;

		}
}
