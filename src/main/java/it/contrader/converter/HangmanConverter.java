package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dao.CategoryDAO;
import it.contrader.dao.LevelDAO;
import it.contrader.dto.HangmanDTO;
import it.contrader.model.Hangman;

public class HangmanConverter implements Converter<Hangman, HangmanDTO> {
	
	private CategoryDAO categoryDAO;
	private LevelDAO levelDAO;
	
	public HangmanConverter(){
		this.categoryDAO = new CategoryDAO();
		this.levelDAO = new LevelDAO();
	}

	// converte Category Model in CategoryDTO
		public HangmanDTO toDTO(Hangman hangman) {
			String categoryString = categoryDAO.read(hangman.getIdCategory()).getTitle();
			String levelString = levelDAO.read(hangman.getIdLevel()).getName();
			HangmanDTO hangmanDTO = new HangmanDTO(hangman.getId(),hangman.getSolution(),hangman.getDefinition(),hangman.getSentence(), hangman.getIdCategory(), categoryString, hangman.getIdLevel(), levelString);
			return hangmanDTO;
		}
		
		//converte category DTO in category model (Entity)
		public Hangman toEntity(HangmanDTO hangmanDto) {
			Hangman hangman = new Hangman(hangmanDto.getId(),hangmanDto.getSolution(),hangmanDto.getDefinition(),hangmanDto.getSentence(), hangmanDto.getIdCategory(), hangmanDto.getIdLevel());
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
