package it.contrader.view.findAWord;
import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.dto.FindAWordDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.CategoryService;
import it.contrader.service.FindAWordService;

public class FindAWordInsertView extends AbstractView{
	private Request request;
	private String solution;
	private String definition;
	private String sentence;
	private Integer score;
	private Integer idCategory;
	private final String mode = "INSERT";
	private FindAWordService findAWordService;
	private CategoryService categoryService;
	
	public FindAWordInsertView() {
		findAWordService = new FindAWordService();
		categoryService = new CategoryService();
	}

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		if (request!=null) {
			request = new Request();
			System.out.println("Inserimento andato a buon fine.\n");
			//ritorniamo alla vista principale della categoria
			
			List<FindAWordDTO> findAWordDTO = findAWordService.getAll();
			request.put("findAWords", findAWordDTO);
			MainDispatcher.getInstance().callView("FindAWord", request);
		}
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("Inserisci la soluzione del gioco:");
		solution = getInput();
		System.out.println("Inserisci la definizione del gioco:");
		definition = getInput();
		System.out.println("Inserisci il suggerimento del gioco:");
		sentence = getInput();
		System.out.println("Inserisci il punteggio del gioco:");
		score = Integer.parseInt(getInput());
		System.out.println("\n----------------------------- Categorie -----------------------------\n");
		List<CategoryDTO> categories = (List<CategoryDTO>) categoryService.getAll();
		//Itero la lista e stampo ogni elemento della lista
		for (CategoryDTO c: categories)
			System.out.println(c);
		System.out.println("---------------------------------------------------------------------\n");
		System.out.println();
		System.out.println("Inserisci la categoria del gioco:");
		idCategory = Integer.parseInt(getInput());
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		request = new Request();
		request.put("solution", solution);
		request.put("definition", definition);
		request.put("sentence", sentence);
		request.put("score", score);
		request.put("idCategory", idCategory);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("FindAWord", "doControl", request);
	}
}
		
	