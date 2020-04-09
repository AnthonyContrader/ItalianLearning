package it.contrader.view.hangman;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.HangmanDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.HangmanService;

public class HangmanDeleteView extends AbstractView {

	private Request request;
	
	private int id;
	private final String mode = "DELETE";
	private HangmanService hangmanService;
	
	public HangmanDeleteView() {
		hangmanService = new HangmanService();
	}
	
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			
			Boolean result = (Boolean) request.get("result");
			
			if (result) {
				System.out.println("Cancellazione andata a buon fine.\n");
			}
			else {
				System.out.println("Si e' verificato un' errore.\n");
			}
			
			request = new Request();
			List<HangmanDTO> hangmenDTO = hangmanService.getAll();
			request.put("hangmen", hangmenDTO);
			MainDispatcher.getInstance().callView("Hangman", request);
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
