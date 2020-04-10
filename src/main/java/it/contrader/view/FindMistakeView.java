package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.FindMistakeDTO;
import it.contrader.main.MainDispatcher;

public class FindMistakeView extends AbstractView {
	
	private Request request;
	
	private String choice;
	
	public FindMistakeView() {
		
	}

	@Override
	public void showResults(Request request) {
		if (request != null) {
			//stampo la testata
			System.out.println("\n-------------------------------- Trova l'errore --------------------------------\n");
			System.out.println("ID\tSoluzione\tSuggerimento\t\tPunteggio\tCategoria");
			System.out.println("---------------------------------------------------------------------------\n");
																																																
			@SuppressWarnings("unchecked")
			//popolo una lista di tipo CategoryDTO
			List<FindMistakeDTO> findMistakes = (List<FindMistakeDTO>) request.get("findMistakes");
			//Itero la lista e stampo ogni elemento della lista
			for (FindMistakeDTO f: findMistakes)
				System.out.println(f);
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
		MainDispatcher.getInstance().callAction("FindMistake", "doControl", this.request);
	}

}
