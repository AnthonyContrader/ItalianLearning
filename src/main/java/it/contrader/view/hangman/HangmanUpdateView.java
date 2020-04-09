package it.contrader.view.hangman;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.dto.HangmanDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.CategoryService;
import it.contrader.service.HangmanService;

public class HangmanUpdateView extends AbstractView {
	private Request request;
		
	private int id;
	private String solution;
	private String definition;
	private String sentence;
	private String score;
	private String idCategory;
	private final String mode = "UPDATE";
	private HangmanService hangmanService;
	private CategoryService categoryService;
	
	public HangmanUpdateView() {
		hangmanService = new HangmanService();
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
			List<HangmanDTO> hangmenDTO = hangmanService.getAll();
			request.put("hangmen", hangmenDTO);
			MainDispatcher.getInstance().callView("Hangman", request);
		}
	}

	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci id del gioco:");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci la soluzione del gioco:");
			solution = getInput();
			System.out.println("Inserisci la definizione del gioco:");
			definition = getInput();
			System.out.println("Inserisci il suggerimento del gioco:");
			sentence = getInput();
			System.out.println("Inserisci il punteggio del gioco:");
			score = getInput();
			if ("".equals(score)) {
				score = "0";
			}
			System.out.println("\n----------------------------- Categorie -----------------------------\n");
			List<CategoryDTO> categories = (List<CategoryDTO>) categoryService.getAll();
			//Itero la lista e stampo ogni elemento della lista
			for (CategoryDTO c: categories)
				System.out.println(c);
			System.out.println("---------------------------------------------------------------------\n");
			System.out.println();
			System.out.println("Inserisci la categoria del gioco:");
			idCategory = getInput();
			if ("".equals(idCategory)) {
				idCategory = "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("solution", solution);
		request.put("definition", definition);
		request.put("sentence", sentence);
		request.put("score", score);
		request.put("idCategory", idCategory);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Hangman", "doControl", request);
	}
}
