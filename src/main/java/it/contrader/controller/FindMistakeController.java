package it.contrader.controller;

import java.util.List;

import it.contrader.dto.FindMistakeDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.FindMistakeService;

public class FindMistakeController implements Controller {

private static String sub_package = "findMistake.";
	
	private FindMistakeService findMistakeService;
	
	public FindMistakeController() {
		this.findMistakeService = new FindMistakeService();
	}
	
	@Override
	public void doControl(Request request) {
		
		String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");
		
		int id;
		String solution;
		String definition;
		String sentence;
		String optionA;
		String optionB;
		String optionC;
		Integer score;
		Integer idCategory;
		boolean result;
		
		switch (mode) {
		case "READ":
			//prendo il valore chiamato 'id', lo converto in stringa e poi lo converto in intero
			id = Integer.parseInt(request.get("id").toString());
			/* Chiamo hangmanService per eseguire il metodo read di HangmanDTO per leggere dal DB
			 * il record con id equivalente alla variabile 'id' e salvarlo all'interno
			 * dell'oggetto categoryDTO
			 */
			FindMistakeDTO findMistakeDTO = findMistakeService.read(id);
			//Mando il risultato alla vista
			request.put("findMistake", findMistakeDTO);
			//Chiamo la vista HangmanReadView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "FindMistakeRead", request);
			break;
		case "INSERT":
			solution = request.get("solution").toString();
			definition = request.get("definition").toString();
			sentence = request.get("sentence").toString();
			optionA = request.get("optionA").toString();
			optionB = request.get("optionB").toString();
			optionC = request.get("optionC").toString();
			score = Integer.parseInt(request.get("score").toString());
			idCategory = Integer.parseInt(request.get("idCategory").toString());
						
			/*
			 *  Costruisco l'oggetto DTO hangmantoinsert per mandarlo al service
			 *  che proverà ad inserirlo tramite la funzione insert del DAO 
			 */
			FindMistakeDTO findMistaketoinsert = new FindMistakeDTO(solution, definition, sentence, optionA, optionB, optionC, score, idCategory);
			result = findMistakeService.insert(findMistaketoinsert);
			
			//Definisco una nuova request per mandare il risultato alla view
			request = new Request();
			request.put("mode", mode); // controllare se è corretto rimandare la stessa mode
			request.put("result", result); // verificare se si necessita il risultato
			//Chiamo la vista HangmanInsertView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "FindMistakeInsert", request);
			break;
		case "DELETE":
			//prendo il valore chiamato 'id', lo converto in stringa e poi lo converto in intero
			id = Integer.parseInt(request.get("id").toString());
			//chiamo solamente la delete del DAO in quanto non necessita il DTO
			result = findMistakeService.delete(id);
			//Definisco una nuova request per mandare il risultato alla view
			request = new Request();
			request.put("mode", mode); // controllare se è corretto rimandare la stessa mode
			request.put("result", result); // verificare se si necessita il risultato
			//Chiamo la vista HangmanDeleteView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "FindMistakeDelete", request);
			break;
		case "UPDATE":
			id = Integer.parseInt(request.get("id").toString());
			solution = request.get("solution").toString();
			definition = request.get("definition").toString();
			sentence = request.get("sentence").toString();
			optionA = request.get("optionA").toString();
			optionB = request.get("optionB").toString();
			optionC = request.get("optionC").toString();
			score = Integer.parseInt(request.get("score").toString());
			idCategory = Integer.parseInt(request.get("idCategory").toString());
			FindMistakeDTO findMistaketoupdate = new FindMistakeDTO(id, solution, definition, sentence, optionA, optionB, optionC, score, idCategory);
			result = findMistakeService.update(findMistaketoupdate);
			request = new Request();
			request.put("mode", mode); // controllare se è corretto rimandare la stessa mode
			request.put("result", result); // verificare se si necessita il risultato
			//Chiamo la vista HangmanUpdateView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "FindMistakeUpdate", request);
			break;
		case "GAMELIST":
			List<FindMistakeDTO> findMistakesDTO = findMistakeService.getAll();
			request.put("findMistakes", findMistakesDTO);
			MainDispatcher.getInstance().callView("FindMistake", request);
			break;
		case "GETCHOICE":
			
			//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "FindMistakeRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "FindMistakeInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "FindMistakeUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "FindMistakeDelete", null);
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
