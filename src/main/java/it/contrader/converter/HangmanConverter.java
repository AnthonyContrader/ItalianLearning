//Created By @Alessandro Alfieri
package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.HangmanDTO;
import it.contrader.model.Hangman;

@Component
public class HangmanConverter extends AbstractConverter<Hangman, HangmanDTO> {

	@Override
	public Hangman toEntity(HangmanDTO hangmanDTO) {
		Hangman hangman = null;
		if (hangmanDTO != null) {
			hangman = new Hangman(hangmanDTO.getId(), hangmanDTO.getSolution(), hangmanDTO.getDefinition(), hangmanDTO.getSentence(), hangmanDTO.getCategory(), hangmanDTO.getLevel());
		}
		return hangman;
	}

	@Override
	public HangmanDTO toDTO(Hangman hangman) {
		HangmanDTO hangmanDTO = null;
		if (hangman != null) {
			hangmanDTO = new HangmanDTO(hangman.getId(), hangman.getSolution(), hangman.getDefinition(), hangman.getSentence(), hangman.getCategory(), hangman.getLevel());
		}
		return hangmanDTO;
	}

}