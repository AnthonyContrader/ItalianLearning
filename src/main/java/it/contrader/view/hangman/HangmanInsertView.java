package it.contrader.view.hangman;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.dto.HangmanDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.CategoryService;
import it.contrader.service.HangmanService;

public class HangmanInsertView extends AbstractView{
	private Request request;
	private String solution;
	private String definition;
	private String sentence;
	private Integer score;
	private Integer idCategory;
	private final String mode = "INSERT";
	private HangmanService hangmanService;
	private CategoryService categoryService;
	
	public HangmanInsertView() {
		hangmanService = new HangmanService();
		categoryService = new CategoryService();
	}

	@Override
	public void showResults(Request request) {
		if (request!=null) {
			request = new Request();
			System.out.println("Inserimento andato a buon fine.\n");
			//ritorniamo alla vista principale della categoria
			
			List<HangmanDTO> hangmenDTO = hangmanService.getAll();
			request.put("hangmen", hangmenDTO);
			MainDispatcher.getInstance().callView("Hangman", request);
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
		request.put("score", score);
		request.put("idCategory", idCategory);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Hangman", "doControl", request);
	}
}
