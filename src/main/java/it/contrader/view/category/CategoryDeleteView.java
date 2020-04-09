package it.contrader.view.category;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.CategoryService;


public class CategoryDeleteView extends AbstractView{
	private Request request;
	
	private int id;
	private final String mode = "DELETE";
	private CategoryService categoryService;
	
	public CategoryDeleteView (){
		categoryService = new CategoryService();

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
			List<CategoryDTO> categoriesDTO = categoryService.getAll();
			request.put("categories", categoriesDTO);
			MainDispatcher.getInstance().callView("Category", request);

		}
	}

	@Override
	public void showOptions() {
		System.out.println("Inserisci id della categoria:");
		id = Integer.parseInt(getInput());
	}

	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Category", "doControl", request);
	}
	
}
