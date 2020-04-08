package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.main.MainDispatcher;

//eredita da AbstractView ereditando il metodo getInput e l'interfaccia View
public class CategoryView extends AbstractView {
	
	private Request request;
	private String choice;
	
	public CategoryView() {
		
	}
	
	/*
	 * Mostra la lista delle categorie
	 */
	@Override
	public void showResults(Request request) {
		if (request != null) {
			//stampo la testata
			System.out.println("\n--------- Gestione Categorie ---------\n");
			System.out.println("ID\tTitolo");
			System.out.println("----------------------------------------\n");
			
			@SuppressWarnings("unchecked")
			//popolo una lista di tipo CategoryDTO
			List<CategoryDTO> categories = (List<CategoryDTO>) request.get("categories");
			//Itero la lista e stampo ogni elemento della lista
			for (CategoryDTO c: categories)
				System.out.println(c);
			System.out.println();
		}
	}
	
	//Mostra le opzioni disponibili
	@Override
	public void showOptions() {
		System.out.println("          Scegli l'operazione da effettuare:");
		System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");

		this.choice = getInput();
		
	}
	
	//Registra la scelta nella request e la manda al controller
	@Override
	public void submit() {
		request = new Request();
		request.put("choice", choice);
		request.put("mode", "GETCHOICE");
		MainDispatcher.getInstance().callAction("Category", "doControl", this.request);	
	}

}
