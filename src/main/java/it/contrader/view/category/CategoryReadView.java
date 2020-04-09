package it.contrader.view.category;

import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class CategoryReadView extends AbstractView{
	
	private Request request;
	
	private int id;
	private final String mode = "READ";
	
	
	public CategoryReadView() {
	}
	
	// mostra il dato letto se esiste
	@Override
	public void showResults(Request request) {
		if (request != null) {
			CategoryDTO category = (CategoryDTO) request.get("category");
			System.out.println(category);
			MainDispatcher.getInstance().callView("Category", null);
		}
	}
	
	@Override
	public void showOptions() {
		System.out.println("Inserisci l'ID della categoria:");
		id = Integer.parseInt(getInput());
	}
	
	// invia i dati al controller
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Category", "doControl", request);
	}

}
