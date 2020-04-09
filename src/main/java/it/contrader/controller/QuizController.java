package it.contrader.controller;

import java.util.List;

import it.contrader.dto.HangmanDTO;
import it.contrader.dto.QuizDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.HangmanService;
import it.contrader.service.QuizService;

public class QuizController implements Controller {
	
private static String sub_package = "quiz.";
	
	private QuizService quizService;
	
	public QuizController() {
		this.quizService = new QuizService();
	}

	public void doControl(Request request) {
		
		String mode = (String) request.get("mode");
		
		String choice = (String)  request.get("choice");
		
		int id;
		String solution;
		String definition;
		Integer score;
		Integer idCategory;
		boolean result;
		
		switch (mode) {
		case "READ":
			
			id = Integer.parseInt(request.get("id").toString());
			
			QuizDTO quizDTO = quizService.read(id);
			
			request.put("quiz", quizDTO);
			
			MainDispatcher.getInstance().callView(sub_package + "QuizRead", request);
			break;
		case "INSERT":
			solution = request.get("solution").toString();
			definition = request.get("definition").toString();
			score = Integer.parseInt(request.get("score").toString());
			idCategory = Integer.parseInt(request.get("idCategory").toString());
						
			
			QuizDTO quiztoinsert = new QuizDTO(idCategory, score, solution, definition);
			result = quizService.insert(quiztoinsert);
			
			
			request = new Request();
			request.put("mode", mode); 
			request.put("result", result); 
			MainDispatcher.getInstance().callView(sub_package + "QuizInsert", request);
			break;
		case "DELETE":
			
			id = Integer.parseInt(request.get("id").toString());
			
			result = quizService.delete(id);
			
			request = new Request();
			request.put("mode", mode); 
			request.put("result", result); 			
			MainDispatcher.getInstance().callView(sub_package + "QuizDelete", request);
			break;
		case "UPDATE":
			id = Integer.parseInt(request.get("id").toString());
			solution = request.get("solution").toString();
			definition = request.get("definition").toString();
			sentence = request.get("sentence").toString();
			score = Integer.parseInt(request.get("score").toString());
			idCategory = Integer.parseInt(request.get("idCategory").toString());
			HangmanDTO hangmantoupdate = new HangmanDTO(id, solution, definition, sentence, score, idCategory);
			result = hangmanService.update(hangmantoupdate);
			request = new Request();
			request.put("mode", mode); // controllare se è corretto rimandare la stessa mode
			request.put("result", result); // verificare se si necessita il risultato
			//Chiamo la vista HangmanUpdateView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "HangmanUpdate", request);
			break;
		case "CATEGORYLIST":
			List<HangmanDTO> hangmenDTO = hangmanService.getAll();
			request.put("hangmen", hangmenDTO);
			MainDispatcher.getInstance().callView("Hangman", request);
			break;
		case "GETCHOICE":
			
			//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "HangmanRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "HangmanInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "HangmanUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "HangmanDelete", null);
				break;
				
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;
		
			case "B":
				MainDispatcher.getInstance().callView("Game", null);
				break;
				
			default:
				MainDispatcher.getInstance().callView("Login", null);
			}
			
		default:
			MainDispatcher.getInstance().callView("Login", null);
		}	
		
		}
	}
	
}
