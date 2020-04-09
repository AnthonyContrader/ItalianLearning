package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.HangmanDTO;
import it.contrader.main.MainDispatcher;

public class HangmanView extends AbstractView {

	private Request request;
	
	private String choice;
	
	public HangmanView() {
		
	}
	
	@Override
	public void showResults(Request request) {
		if (request != null) {
			//stampo la testata
			System.out.println("\n---------------------------------- Impiccato ----------------------------------\n");
			System.out.println("ID\tSoluzione\tDefinizione\tSuggerimento\tPunteggio\tCategoria");
			System.out.println("-------------------------------------------------------------------------------\n");
																																																
			@SuppressWarnings("unchecked")
			//popolo una lista di tipo CategoryDTO
			List<HangmanDTO> hangmen = (List<HangmanDTO>) request.get("hangmen");
			//Itero la lista e stampo ogni elemento della lista
			for (HangmanDTO h: hangmen)
				System.out.println(h);
			System.out.println();
		}
	}

	@Override
	public void showOptions() {
		System.out.println("          Scegli l'operazione da effettuare:");
		System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");

		this.choice = getInput();
	}

	@Override
	public void submit() {
		request = new Request();
		request.put("choice", choice);
		request.put("mode", "GETCHOICE");
		MainDispatcher.getInstance().callAction("Hangman", "doControl", this.request);
	}
	
}
