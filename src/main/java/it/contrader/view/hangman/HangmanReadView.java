package it.contrader.view.hangman;

import it.contrader.controller.Request;
import it.contrader.dto.HangmanDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class HangmanReadView extends AbstractView{
	
	private Request request;
	
	private int id;
	private final String mode = "READ";
	
	public HangmanReadView() {
	}
	
	@Override
	public void showResults(Request request) {
		if (request != null) {
			HangmanDTO hangman = (HangmanDTO) request.get("hangman");
			System.out.println(hangman);
			MainDispatcher.getInstance().callView("Hangman", null);
		}
	}

	@Override
	public void showOptions() {
		System.out.println("Inserisci l'ID del gioco:");
		id = Integer.parseInt(getInput());
	}

	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Hangman", "doControl", request);
	}
}
