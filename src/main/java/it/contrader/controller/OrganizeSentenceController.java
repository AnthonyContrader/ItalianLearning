package it.contrader.controller;

import java.util.List;

import it.contrader.dto.OrganizeSentenceDTO;

import it.contrader.main.MainDispatcher;
import it.contrader.service.OrganizeSentenceService;


public class OrganizeSentenceController implements Controller {
	
	private static String sub_package = "organizeSentence.";
	private OrganizeSentenceService organizeSentenceService;

	public OrganizeSentenceController() {
		
		this.organizeSentenceService = new OrganizeSentenceService();
	}

	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		
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
			/* Chiamo organizeSentenceService per eseguire il metodo read di OrganizeSentenceDTO per leggere dal DB
			 * il record con id equivalente alla variabile 'id' e salvarlo all'interno
			 * dell'oggetto categoryDTO
			 */
			OrganizeSentenceDTO organizeSentenceDTO = organizeSentenceService.read(id);
			//Mando il risultato alla vista
			request.put("organizeSentence", organizeSentenceDTO);
			//Chiamo la vista OrganizeSentenceReadView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "OrganizeSentenceRead", request);
			break;
		case "INSERT":
			solution = request.get("solution").toString();
			definition = request.get("definition").toString();
			sentence = request.get("sentence").toString();
			score = Integer.parseInt(request.get("score").toString());
			idCategory = Integer.parseInt(request.get("idCategory").toString());
						
			/*
			 *  Costruisco l'oggetto DTO organizeSentencetoinsert per mandarlo al service
			 *  che proverà ad inserirlo tramite la funzione insert del DAO 
			 *///(String solution, String sentence, Integer score, String definition, Integer idCategory)
			OrganizeSentenceDTO organizeSentencetoinsert = new OrganizeSentenceDTO(solution,sentence, score, definition, idCategory);
			result = organizeSentenceService.insert(organizeSentencetoinsert);
			
			//Definisco una nuova request per mandare il risultato alla view
			request = new Request();
			request.put("mode", mode); // controllare se è corretto rimandare la stessa mode
			request.put("result", result); // verificare se si necessita il risultato
			//Chiamo la vista OrganizeSentenceInsertView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "OrganizeSentenceInsert", request);
			break;
		case "DELETE":
			//prendo il valore chiamato 'id', lo converto in stringa e poi lo converto in intero
			id = Integer.parseInt(request.get("id").toString());
			//chiamo solamente la delete del DAO in quanto non necessita il DTO
			result = organizeSentenceService.delete(id);
			//Definisco una nuova request per mandare il risultato alla view
			request = new Request();
			request.put("mode", mode); // controllare se è corretto rimandare la stessa mode
			request.put("result", result); // verificare se si necessita il risultato
			//Chiamo la vista OrganizeSentenceDeleteView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "OrganizeSentenceDelete", request);
			break;
		case "UPDATE":
			id = Integer.parseInt(request.get("id").toString());
			solution = request.get("solution").toString();
			definition = request.get("definition").toString();
			sentence = request.get("sentence").toString();
			score = Integer.parseInt(request.get("score").toString());
			idCategory = Integer.parseInt(request.get("idCategory").toString());
			
			OrganizeSentenceDTO organizeSentencetoupdate = new OrganizeSentenceDTO(id,solution,sentence, score, definition, idCategory);
			result = organizeSentenceService.update(organizeSentencetoupdate);
			request = new Request();
			request.put("mode", mode); // controllare se è corretto rimandare la stessa mode
			request.put("result", result); // verificare se si necessita il risultato
			//Chiamo la vista OrganizeSentenceUpdateView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "OrganizeSentenceUpdate", request);
			break;
		case "GAMELIST":
			List<OrganizeSentenceDTO> organizeSentencesDTO = organizeSentenceService.getAll();
			request.put("organizeSentences", organizeSentencesDTO);
			MainDispatcher.getInstance().callView("OrganizeSentence", request);
			break;
		case "GETCHOICE":
			
			//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "OrganizeSentenceRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "OrganizeSentenceInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "OrganizeSentenceUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "OrganizeSentenceDelete", null);
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
