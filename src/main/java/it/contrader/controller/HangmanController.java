package it.contrader.controller;

import java.util.List;

import it.contrader.dto.HangmanDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.HangmanService;

public class HangmanController implements Controller {
	
	private static String sub_package = "hangman.";
	
	private HangmanService hangmanService;
	
	public HangmanController() {
		this.hangmanService = new HangmanService();
	}

	@Override
	public void doControl(Request request) {
		
		String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");
		
		int id;
		String solution;
		String definition;
		String sentence;
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
			HangmanDTO hangmanDTO = hangmanService.read(id);
			//Mando il risultato alla vista
			request.put("hangman", hangmanDTO);
			//Chiamo la vista HangmanReadView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "HangmanRead", request);
			break;
		case "INSERT":
			solution = request.get("solution").toString();
			definition = request.get("definition").toString();
			sentence = request.get("sentence").toString();
			score = Integer.parseInt(request.get("score").toString());
			idCategory = Integer.parseInt(request.get("idCategory").toString());
						
			/*
			 *  Costruisco l'oggetto DTO hangmantoinsert per mandarlo al service
			 *  che proverà ad inserirlo tramite la funzione insert del DAO 
			 */
			HangmanDTO hangmantoinsert = new HangmanDTO(solution, definition, sentence, score, idCategory);
			result = hangmanService.insert(hangmantoinsert);
			
			//Definisco una nuova request per mandare il risultato alla view
			request = new Request();
			request.put("mode", mode); // controllare se è corretto rimandare la stessa mode
			request.put("result", result); // verificare se si necessita il risultato
			//Chiamo la vista HangmanInsertView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "HangmanInsert", request);
			break;
		case "DELETE":
			//prendo il valore chiamato 'id', lo converto in stringa e poi lo converto in intero
			id = Integer.parseInt(request.get("id").toString());
			//chiamo solamente la delete del DAO in quanto non necessita il DTO
			result = hangmanService.delete(id);
			//Definisco una nuova request per mandare il risultato alla view
			request = new Request();
			request.put("mode", mode); // controllare se è corretto rimandare la stessa mode
			request.put("result", result); // verificare se si necessita il risultato
			//Chiamo la vista HangmanDeleteView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "HangmanDelete", request);
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
		case "GAMELIST":
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
