package it.contrader.controller;

import it.contrader.main.MainDispatcher;

public class GameController implements Controller {
	
	
	public GameController() {
	}

	@Override
	public void doControl(Request request) {
		String mode = (String) request.get("mode");
		String choice = (String) request.get("choice");
		request.put("mode", "GAMELIST");
		switch (mode) {
		
			case "GAMELIST":
				MainDispatcher.getInstance().callView("Game", null);
				break;
			
			case "GETCHOICE":
			
				//toUpperCase() mette in maiuscolo la scelta
				switch (choice.toUpperCase()) {
				
				case "C":
					MainDispatcher.getInstance().callAction("FindAWord", "doControl", request);
					break;
					
				case "I":
					MainDispatcher.getInstance().callAction("Hangman", "doControl", request);
					break;
					
				case "N":
					MainDispatcher.getInstance().callAction("GuessPicture", "doControl", request);
					break;
					
				case "Q":
					MainDispatcher.getInstance().callAction("Quiz", "doControl", request);
					break;
					
				case "R":
					MainDispatcher.getInstance().callAction("OrganizeSentence", "doControl", request);
					break;
			
				case "T":
					MainDispatcher.getInstance().callAction("FindAMistake", "doControl", request);
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
