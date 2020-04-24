//created by Gabriella Brunetto

package it.contrader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.contrader.dto.FindAWordDTO;
import it.contrader.model.FindAWord;


@Component //legge e comprende le annotazioni di altre classi per restituirle al converter se ne ha necessità

public class FindAWordConverter extends AbstractConverter<FindAWord, FindAWordDTO> {
@Autowired
	CategoryConverter categoryConverter = new CategoryConverter();
@Autowired
			LevelConverter levelConverter = new LevelConverter();
	
	@Override // funzione di classe ereditata chiamata per riscriverla a modo mio
	//passiamo un oggetto dal dto e lo convertiamo ad oggetto del modello
	public FindAWord toEntity(FindAWordDTO findAWordDTO) {
		FindAWord findAWord = null; // in caso di errore
		if(findAWordDTO != null) {
			findAWord = new FindAWord(findAWordDTO.getId(),categoryConverter.toEntity(findAWordDTO.getCategory()),levelConverter.toEntity(findAWordDTO.getLevel()),findAWordDTO.getSolution(),findAWordDTO.getDefinition(),findAWordDTO.getSentence());
			// seguiamo l'ordie del model
		}
		return findAWord; // xke non è void questo è quello ke torna
		
	}

	@Override
	public FindAWordDTO toDTO(FindAWord findAWord) {
		FindAWordDTO findAWordDTO = null; // in caso di errore
		if(findAWord != null) {
			findAWordDTO = new FindAWordDTO(findAWord.getId(),categoryConverter.toDTO(findAWord.getCategory()),levelConverter.toDTO(findAWord.getLevel()),findAWord.getSolution(),findAWord.getDefinition(),findAWord.getSentence());
			
		}
		return findAWordDTO;
		


	}

}
