package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.FindAWordDTO;
import it.contrader.main.MainDispatcher;

public class FindAWordView extends AbstractView {

	private Request request;
	
	private String choice;
	
	public FindAWordView() {
		
	}
	


@Override
public void showResults(Request request) {
	// TODO Auto-generated method stub
	if (request != null) {
		//stampo la testata
		System.out.println("\n-------------------------------- Trova la parola --------------------------------\n");
		System.out.println("ID\tSoluzione\tSuggerimento\t\tPunteggio\tCategoria");
		System.out.println("-----------------------------------------------------------------------------------\n");
																																															
		@SuppressWarnings("unchecked")
		//popolo una lista di tipo CategoryDTO
		List<FindAWordDTO> findAWord = (List<FindAWordDTO>) request.get("findAWords");
		//Itero la lista e stampo ogni elemento della lista
		for (FindAWordDTO h: findAWord)
			System.out.println(h);
		System.out.println();
	}
	
}


@Override
public void showOptions() {
	// TODO Auto-generated method stub
	System.out.println("          Scegli l'operazione da effettuare:");
	System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");

	this.choice = getInput();
	
}


@Override
public void submit() {
	// TODO Auto-generated method stub
	request = new Request();
	request.put("choice", choice);
	request.put("mode", "GETCHOICE");
	MainDispatcher.getInstance().callAction("FindAWord", "doControl", this.request);
 }
}
