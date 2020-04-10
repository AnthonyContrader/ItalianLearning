package it.contrader.controller.view.quiz;

import java.util.List;
import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.dto.QuizDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.CategoryService;
import it.contrader.service.QuizService;

public class QuizInsertView extends AbstractView {
	private Request request;
	private String solution;
	private String definition;
	private Integer score;
	private Integer idCategory;
	private final String mode = "INSERT";
	private QuizService quizService;
	private CategoryService categoryService;
	
	public QuizInsertView() {
		quizService = new QuizService();
		categoryService = new CategoryService();
	}
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			request = new Request();
			System.out.println("Inserimento andato a buon fine.\n");
			List<QuizDTO> quizzesDTO = quizService.getAll();
			request.put("quizzes", quizzesDTO);
			MainDispatcher.getInstance().callView("quiz", request);
		}
	}

	@Override
	public void showOptions() {
		System.out.println("Inserisci la soluzione del gioco:");
		solution = getInput();
		System.out.println("Inserisci la definizione del gioco:");
		definition = getInput();
		System.out.println("Inserisci il suggerimento del gioco:");
		score = Integer.parseInt(getInput());
		System.out.println("\n----------------------------- Categorie -----------------------------\n");
		List<CategoryDTO> categories = (List<CategoryDTO>) categoryService.getAll();
		
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
		request.put("score", score);
		request.put("idCategory", idCategory);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Quiz", "doControl", request);
		
	}

}
