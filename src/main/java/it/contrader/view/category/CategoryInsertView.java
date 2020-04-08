package it.contrader.view.category;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class CategoryInsertView extends AbstractView{
	
	private Request request;

	private String title;
	private String description;
	private final String mode = "INSERT";

	public CategoryInsertView() {
	}

	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Inserimento andato a buon fine.\n");
			MainDispatcher.getInstance().callView("Category", null);
		}
	}

	@Override
	public void showOptions() {
		System.out.println("Inserisci titolo della categoria:");
		title = getInput();
		System.out.println("Inserisci descrizione della categoria:");
		description = getInput();

	}

	@Override
	public void submit() {
		request = new Request();
		request.put("title", title);
		request.put("description", description);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Category", "doControl", request);
	}
	

}
