package it.contrader.controller;

import java.util.List;

import it.contrader.dto.HangmanDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.HangmanService;

public class GameController implements Controller {
	
	private HangmanService hangmanService;
	
	public GameController() {
		this.hangmanService = new HangmanService();
	}

	@Override
	public void doControl(Request request) {
		String mode = (String) request.get("mode");
		String choice = (String) request.get("choice");
		
		switch (mode) {
		
			case "GAMELIST":
				MainDispatcher.getInstance().callView("Game", null);
				break;
			
			case "GETCHOICE":
			
				//toUpperCase() mette in maiuscolo la scelta
				switch (choice.toUpperCase()) {
				
				case "C":
					MainDispatcher.getInstance().callView("FindAWord", null);
					break;
					
				case "I":
					List<HangmanDTO> hangmenDTO = hangmanService.getAll();
					request.put("hangmen", hangmenDTO);
					MainDispatcher.getInstance().callView("Hangman", request);
					break;
					
				case "N":
					MainDispatcher.getInstance().callView("GuessPicture", null);
					break;
					
				case "Q":
					MainDispatcher.getInstance().callView("Quiz", null);
					break;
					
				case "R":
					MainDispatcher.getInstance().callView("OrganizeSentence", null);
					break;
			
				case "T":
					MainDispatcher.getInstance().callView("FindAMistake", null);
					break;
					
				case "B":
					MainDispatcher.getInstance().callView("HomeAdmin", null);
					break;
					
				case "E":
					MainDispatcher.getInstance().callView("Login", null);
					break;
					
				default:
					MainDispatcher.getInstance().callView("Login", null);
				}
			default:
				MainDispatcher.getInstance().callView("Login", null);
			}		
	}	
}
