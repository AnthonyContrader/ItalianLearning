package it.contrader.view.organizeSentence;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.dto.OrganizeSentenceDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.CategoryService;
import it.contrader.service.OrganizeSentenceService;

public class OrganizeSentenceUpdateView extends AbstractView {
	
	private Request request;
	
	private int id;
	private String solution;
	private String definition;
	private String sentence;
	private String score;
	private String idCategory;
	private final String mode = "UPDATE";
	private OrganizeSentenceService organizeSentenceService;
	private CategoryService categoryService;
	
	public OrganizeSentenceUpdateView() {
		organizeSentenceService = new OrganizeSentenceService();
		categoryService = new CategoryService();
	}

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		if (request!=null) {
			Boolean result = (Boolean) request.get("result");
			
			if (result) {
				System.out.println("Modifica andata a buon fine.\n");
			}
			else {
				System.out.println("Si e' verificato un' errore.\n");
			}
			
			request = new Request();
			List<OrganizeSentenceDTO> organizeSentencesDTO = organizeSentenceService.getAll();
			request.put("organizeSentences", organizeSentencesDTO);
			MainDispatcher.getInstance().callView("OrganizeSentence", request);
		}
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		request = new Request();
		request.put("id", id);
		request.put("solution", solution);
		request.put("definition", definition);
		request.put("sentence", sentence);
		request.put("score", score);
		request.put("idCategory", idCategory);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("OrganizeSentence", "doControl", request);
	}
	
	

}
