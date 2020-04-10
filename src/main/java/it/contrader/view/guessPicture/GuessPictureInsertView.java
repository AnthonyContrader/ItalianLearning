package it.contrader.view.guessPicture;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.dto.GuessPictureDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.CategoryService;
import it.contrader.service.GuessPictureService;
import it.contrader.view.AbstractView;

public class GuessPictureInsertView extends AbstractView{
	// idCategory,  score,  solution,  image

	private Request request;
	
	private String solution;
	private String image;
	private Integer score;
	private Integer idCategory;
	private final String mode = "INSERT";
	
	private GuessPictureService guessPictureService;
	private CategoryService categoryService;
	
	public GuessPictureInsertView() {
		this.guessPictureService = new GuessPictureService();
		this.categoryService = new CategoryService();
	}
	
	@Override
	public void showResults(Request request) {
		
		if (request!=null) {
			request = new Request();
			System.out.println("Inserimento andato a buon fine.\n");
			
			List<GuessPictureDTO> guessPictureDTO = guessPictureService.getAll();
			request.put("guessPictures", guessPictureDTO);
			MainDispatcher.getInstance().callView("GuessPicture", request);
		}
	}
	
	@Override
	public void showOptions() {
		// idCategory,  score,  solution,  image
		
		System.out.println("Inserisci la soluzione del gioco:");
		solution = getInput();
		
		System.out.println("Inserisci l'immagine del gioco in formato ascii ( Termina l'inserimento con end_ascii ):");
		image = getInputAscii();

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
		request.put("image", image);
		request.put("score", score);
		request.put("idCategory", idCategory);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("GuessPicture", "doControl", request);
	}

}
