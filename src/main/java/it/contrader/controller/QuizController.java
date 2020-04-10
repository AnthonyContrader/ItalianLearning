package it.contrader.controller;

import java.util.List;
import it.contrader.dto.QuizDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.QuizService;

public class QuizController implements Controller {
	
private static String sub_package = "quiz.";
	
	private QuizService quizService;
	
	public QuizController() {
		this.quizService = new QuizService();
	}
	@Override
	public void doControl(Request request) {
		
		String mode = (String) request.get("mode");
		
		String choice = (String)  request.get("choice");
		
		int id;
		String solution;
		String definition;
		String sentence;
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
			sentence = request.get("sentence").toString();
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
			
			QuizDTO quiztoupdate = new QuizDTO(id,idCategory,score, solution, definition);
			result = quizService.update(quiztoupdate);
			request = new Request();
			request.put("mode", mode);
			request.put("result", result); 
			
			MainDispatcher.getInstance().callView(sub_package + "QuizUpdate", request);
			break;
			
		case "GAMELIST":
			
			List<QuizDTO> quizsDTO = quizService.getAll();
			request.put("quiz", quizsDTO);
			MainDispatcher.getInstance().callView("Quiz", request);
			break;
			
		case "GETCHOICE":
			
			
			switch (choice.toUpperCase()) {
			
			case "L":
				
				MainDispatcher.getInstance().callView(sub_package + "QuizRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "QuizInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "QuizUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "QuizDelete", null);
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
	

