package it.contrader.view.quiz;

import it.contrader.controller.Request;
import it.contrader.dto.QuizDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class QuizReadView extends AbstractView {
	
	private Request request;
	
	private int id;
	private final String mode = "READ";
	
	public QuizReadView() {
	}

	@Override
	public void showResults(Request request) {
		if (request != null) {
			QuizDTO quiz = (QuizDTO) request.get("quiz");
			
			if (quiz != null) {
			System.out.println(quiz);
			System.out.println(quiz.getDefinition());
			MainDispatcher.getInstance().callView("Quiz", null);
			}
		
		
			
			else {
				System.out.println("Elemento non trovato");
		}
		
		}
	}	
		
		
	

	@Override
	public void showOptions() {
		System.out.println("Inserisci l'ID del gioco:");
		id = Integer.parseInt(getInput());
	}
	
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Quiz", "doControl", request);
		
	}

}



