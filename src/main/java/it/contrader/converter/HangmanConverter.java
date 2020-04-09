package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.HangmanDTO;
import it.contrader.model.Hangman;

public class HangmanConverter {

	// converte Category Model in CategoryDTO
		public HangmanDTO toDTO(Hangman hangman) {
			HangmanDTO hangmanDTO = new HangmanDTO(hangman.getId(),hangman.getSolution(),hangman.getDefinition(),hangman.getSentence(), hangman.getScore(), hangman.getIdCategory());
			return hangmanDTO;
		}
		
		//converte category DTO in category model (Entity)
		public Hangman toEntity(HangmanDTO hangmanDto) {
			Hangman hangman = new Hangman(hangmanDto.getId(),hangmanDto.getSolution(),hangmanDto.getDefinition(),hangmanDto.getSentence(), hangmanDto.getScore(), hangmanDto.getIdCategory());
			return hangman;
		}
		
		// converte lista di category model (Entity) in lista di categoryDTO
		public List<HangmanDTO> toDTOList(List<Hangman> hangmanList){
			
			//lista vuota
			List<HangmanDTO> hangmanDTOList = new ArrayList<HangmanDTO>();
			
			// ciclo for che itera per ogni elemento di category in categoryList
			for (Hangman hangman : hangmanList) {
				hangmanDTOList.add(toDTO(hangman));
			}
			return hangmanDTOList;

		}
}
