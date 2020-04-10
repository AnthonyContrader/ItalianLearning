package it.contrader.view.findMistake;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.dto.FindMistakeDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.CategoryService;
import it.contrader.service.FindMistakeService;

public class FindMistakeInsertView extends AbstractView {
	private Request request;
	private String solution;
	private String definition;
	private String sentence;
	private String optionA;
	private String optionB;
	private String optionC;
	private Integer score;
	private Integer idCategory;
	private final String mode = "INSERT";
	private FindMistakeService findMistakeService;
	private CategoryService categoryService;
	
	public FindMistakeInsertView() {
		findMistakeService = new FindMistakeService();
		categoryService = new CategoryService();
	}

	@Override
	public void showResults(Request request) {
		if (request!=null) {
			request = new Request();
			System.out.println("Inserimento andato a buon fine.\n");
			//ritorniamo alla vista principale della categoria
			
			List<FindMistakeDTO> findMistakeDTO = findMistakeService.getAll();
			request.put("findMistakes", findMistakeDTO);
			MainDispatcher.getInstance().callView("FindMistake", request);
		}
	}

	@Override
	public void showOptions() {
		System.out.println("Inserisci la soluzione del gioco:");
		solution = getInput();
		System.out.println("Inserisci la definizione del gioco:");
		definition = getInput();
		System.out.println("Inserisci il suggerimento del gioco:");
		sentence = getInput();
		System.out.println("Inserisci l'opzione A del gioco:");
		optionA = getInput();
		System.out.println("Inserisci l'opzione B del gioco:");
		optionB = getInput();
		System.out.println("Inserisci l'opzione C del gioco:");
		optionC = getInput();
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
		request = new Request();
		request.put("solution", solution);
		request.put("definition", definition);
		request.put("sentence", sentence);
		request.put("optionA", optionA);
		request.put("optionB", optionB);
		request.put("optionC", optionC);
		request.put("score", score);
		request.put("idCategory", idCategory);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("FindMistake", "doControl", request);
	}

}
