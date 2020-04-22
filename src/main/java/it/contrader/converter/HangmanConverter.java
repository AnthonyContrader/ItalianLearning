//Created By @Alessandro Alfieri
package it.contrader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.contrader.dto.HangmanDTO;
import it.contrader.model.Hangman;

@Component
public class HangmanConverter extends AbstractConverter<Hangman, HangmanDTO> {
	
	@Autowired
	CategoryConverter categoryConverter = new CategoryConverter();
	@Autowired
	LevelConverter levelConverter = new LevelConverter();
	
	@Override
	public Hangman toEntity(HangmanDTO hangmanDTO) {
		Hangman hangman = null;
		if (hangmanDTO != null) {
			hangman = new Hangman(hangmanDTO.getId(), hangmanDTO.getSolution(), hangmanDTO.getDefinition(), hangmanDTO.getSentence(), categoryConverter.toEntity(hangmanDTO.getCategory()), levelConverter.toEntity(hangmanDTO.getLevel()));
		}
		return hangman;
	}

	@Override
	public HangmanDTO toDTO(Hangman hangman) {
		HangmanDTO hangmanDTO = null;
		if (hangman != null) {
			hangmanDTO = new HangmanDTO(hangman.getId(), hangman.getSolution(), hangman.getDefinition(), hangman.getSentence(), categoryConverter.toDTO(hangman.getCategory()), levelConverter.toDTO(hangman.getLevel()));
		}
		return hangmanDTO;
	}

}