package it.contrader.view.category;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.CategoryService;

public class CategoryInsertView extends AbstractView{
	
	private Request request;

	private String title;
	private String description;
	private final String mode = "INSERT";
	private CategoryService categoryService;

	public CategoryInsertView() {
		categoryService = new CategoryService();
	}
	
	// mostra se l'inserimento e andato a buon fine
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			request = new Request();
			System.out.println("Inserimento andato a buon fine.\n");
			//ritorniamo alla vista principale della categoria
			
			List<CategoryDTO> categoriesDTO = categoryService.getAll();
			request.put("categories", categoriesDTO);
			MainDispatcher.getInstance().callView("Category", request);
		}
	}

	// chiede di inserire i dati per il nuovo inserimento
	@Override
	public void showOptions() {
		System.out.println("Inserisci titolo della categoria:");
		// prende il valore inserito da tastiera e lo salva nella variabile title
		title = getInput();
		System.out.println("Inserisci descrizione della categoria:");
		description = getInput();

	}

	// invia i dati al controller
	@Override
	public void submit() {
		request = new Request();
		request.put("title", title);
		request.put("description", description);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Category", "doControl", request);
	}
	

}
