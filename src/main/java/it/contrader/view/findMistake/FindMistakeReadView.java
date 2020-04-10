package it.contrader.view.findMistake;

import it.contrader.controller.Request;
import it.contrader.dto.FindMistakeDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class FindMistakeReadView extends AbstractView {
	
	private Request request;
	
	private int id;
	private final String mode = "READ";
	
	public FindMistakeReadView() {
	}

	@Override
	public void showResults(Request request) {
		if (request != null) {
			FindMistakeDTO findMistake = (FindMistakeDTO) request.get("findMistake");
			if (findMistake != null) {
				System.out.println(findMistake);
				System.out.println(findMistake.getDefinition());
				MainDispatcher.getInstance().callView("FindMistake", null);
			}
			else
				System.out.println("Elemento non trovato");
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
		MainDispatcher.getInstance().callAction("FindMistake", "doControl", request);
	}
	
	
}
