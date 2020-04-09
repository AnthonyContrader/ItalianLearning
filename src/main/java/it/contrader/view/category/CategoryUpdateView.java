package it.contrader.view.category;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.CategoryService;


public class CategoryUpdateView extends AbstractView{
	
	private Request request;
	
	private int id;
	private String title;
	private String description;
	private final String mode = "UPDATE";
	private CategoryService categoryService;

	public CategoryUpdateView() {
		categoryService = new CategoryService();
	}

	@Override
	public void showResults(Request request) {
		
		if (request!=null) {
			Boolean result = (Boolean) request.get("result");
			
			if (result) {
				System.out.println("Modifica andata a buon fine.\n");
			}
			else {
				System.out.println("Si e' verificato un' errore.\n");
			}
			
			request = new Request();
			List<CategoryDTO> categoriesDTO = categoryService.getAll();
			request.put("categories", categoriesDTO);
			MainDispatcher.getInstance().callView("Category", request);
		}
		
		/*if (request!=null) {
			System.out.println("Modifica andata a buon fine.\n");
			MainDispatcher.getInstance().callView("Category", null);
		}*/
		
	}

	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci id della categoria:");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci titolo della categoria:");
			title = getInput();
			System.out.println("Inserisci descrizione della categoria:");
			description = getInput();
		} catch (Exception e) {

		}
	}

	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("title", title);
		request.put("description", description);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Category", "doControl", request);
	}
}
