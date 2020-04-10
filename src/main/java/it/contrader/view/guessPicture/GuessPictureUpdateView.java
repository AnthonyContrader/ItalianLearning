package it.contrader.view.guessPicture;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.dto.GuessPictureDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.CategoryService;
import it.contrader.service.GuessPictureService;
import it.contrader.view.AbstractView;

public class GuessPictureUpdateView extends AbstractView{
	// idCategory,  score,  solution,  image
	
	private Request request;
	
	private int id;
	private String solution;
	private String image;
	private String score;
	private String idCategory;
	
	private final String mode = "UPDATE";
	private GuessPictureService guessPictureService;
	private CategoryService categoryService;
	
	public GuessPictureUpdateView() {
		this.guessPictureService = new GuessPictureService();
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
			List<GuessPictureDTO> guessPictureDTO = guessPictureService.getAll();
			request.put("guessPictures", guessPictureDTO);
			MainDispatcher.getInstance().callView("GuessPicture", request);
		}
	}

	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci id del gioco:");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci la soluzione del gioco:");
			solution = getInput();
			System.out.println("Inserisci l'immagine del gioco:");
			image = getInput();

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
		request.put("image", image);
		request.put("score", score);
		request.put("idCategory", idCategory);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("GuessPicture", "doControl", request);
	}

}
