package it.contrader.view.quiz;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CategoryDTO;
import it.contrader.dto.QuizDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.CategoryService;

import it.contrader.service.QuizService;


public class QuizUpdateView extends AbstractView {

	private Request request;
	
	private int id;
	private String solution;
	private String definition;
	private String score;
	private String idCategory;
	private final String mode = "UPDATE";
	private QuizService quizService;
	private CategoryService categoryService;
	private String sentence;
	
	public QuizUpdateView() {
		quizService = new QuizService();
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
				List<QuizDTO> quizzesDTO = quizService.getAll();
				request.put("quizzes", quizzesDTO);
				MainDispatcher.getInstance().callView("Quiz", request);
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
			
			System.out.println("\n----------------------------- Categorie -----------------------------\n");
			List<CategoryDTO> categories = (List<CategoryDTO>) categoryService.getAll();
			
			for (CategoryDTO c: categories)
				System.out.println(c);
			System.out.println("---------------------------------------------------------------------\n");
			System.out.println();
			System.out.println("Inserisci la categoria del gioco:");
			idCategory = getInput();
			if ("".equals(idCategory)) {
				idCategory = "0";
				}
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
		request.put("score", score);
		request.put("idCategory", idCategory);
		request.put("mode", mode);
		request.put("sentence", sentence);
		MainDispatcher.getInstance().callAction("Quiz", "doControl", request);
		
	}

}
