package it.contrader.controller.view.quiz;

import java.util.List;
import it.contrader.controller.Request;
import it.contrader.dto.QuizDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.QuizService;

public class QuizDeleteView extends AbstractView {

private Request request;
	
	private int id;
	private final String mode = "DELETE";
	private QuizService quizService;
	
	public QuizDeleteView() {
		quizService = new QuizService();
	}
	
	@Override
	public void showResults(Request request) {
if (request!=null) {
			
			Boolean result = (Boolean) request.get("result");
			
			if (result) {
				System.out.println("Cancellazione andata a buon fine.\n");
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
