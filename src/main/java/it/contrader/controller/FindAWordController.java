package it.contrader.controller;
import java.util.List;

import it.contrader.dto.FindAWordDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.FindAWordService;


public class FindAWordController implements Controller {
	  
		
		private static String sub_package = "findAWord.";
		
		private FindAWordService findAWordService;
		
		public FindAWordController() {
			this.findAWordService = new FindAWordService();
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
					/* Chiamo findAWordService per eseguire il metodo read di FindAWordDTO per leggere dal DB
					 * il record con id equivalente alla variabile 'id' e salvarlo all'interno
					 * dell'oggetto categoryDTO
					 */
					FindAWordDTO findAWordDTO = findAWordService.read(id);
					//Mando il risultato alla vista
					request.put("findAWord", findAWordDTO);
					//Chiamo la vista FindAWordReadView con il risultato nei parametri
					MainDispatcher.getInstance().callView(sub_package + "FindAWordRead", request);
					break;
				case "INSERT":
					solution = request.get("solution").toString();
					definition = request.get("definition").toString();
					sentence = request.get("sentence").toString();
					score = Integer.parseInt(request.get("score").toString());
					idCategory = Integer.parseInt(request.get("idCategory").toString());
								
					/*
					 *  Costruisco l'oggetto DTO findAWordtoinsert per mandarlo al service
					 *  che proverà ad inserirlo tramite la funzione insert del DAO 
					 */
					FindAWordDTO findAWordtoinsert = new FindAWordDTO(idCategory,score,solution, definition, sentence);
					result = findAWordService.insert(findAWordtoinsert);
					
					//Definisco una nuova request per mandare il risultato alla view
					request = new Request();
					request.put("mode", mode); // controllare se è corretto rimandare la stessa mode
					request.put("result", result); // verificare se si necessita il risultato
					//Chiamo la vista FindAWordInsertView con il risultato nei parametri
					MainDispatcher.getInstance().callView(sub_package + "FindAWordInsert", request);
					break;
				case "DELETE":
					//prendo il valore chiamato 'id', lo converto in stringa e poi lo converto in intero
					id = Integer.parseInt(request.get("id").toString());
					//chiamo solamente la delete del DAO in quanto non necessita il DTO
					result = findAWordService.delete(id);
					//Definisco una nuova request per mandare il risultato alla view
					request = new Request();
					request.put("mode", mode); // controllare se è corretto rimandare la stessa mode
					request.put("result", result); // verificare se si necessita il risultato
					//Chiamo la vista FindAWordDeleteView con il risultato nei parametri
					MainDispatcher.getInstance().callView(sub_package + "FindAWordDelete", request);
					break;
				case "UPDATE":
					id = Integer.parseInt(request.get("id").toString());
					solution = request.get("solution").toString();
					definition = request.get("definition").toString();
					sentence = request.get("sentence").toString();
					score = Integer.parseInt(request.get("score").toString());
					idCategory = Integer.parseInt(request.get("idCategory").toString());
					FindAWordDTO findAWordtoupdate = new FindAWordDTO(id, idCategory,score,solution, definition, sentence);
					result = findAWordService.update(findAWordtoupdate);
					request = new Request();
					request.put("mode", mode); // controllare se è corretto rimandare la stessa mode
					request.put("result", result); // verificare se si necessita il risultato
					//Chiamo la vista FindAWordUpdateView con il risultato nei parametri
					MainDispatcher.getInstance().callView(sub_package + "FindAWordUpdate", request);
					break;
				case "CATEGORYLIST":
					List<FindAWordDTO> hangmenDTO = findAWordService.getAll();
					request.put("hangmen", hangmenDTO);
					MainDispatcher.getInstance().callView("FindAWord", request);
					break;
				case "GETCHOICE":
					
					//toUpperCase() mette in maiuscolo la scelta
					switch (choice.toUpperCase()) {
					
					case "L":
						MainDispatcher.getInstance().callView(sub_package + "FindAWordRead", null);
						break;
						
					case "I":
						MainDispatcher.getInstance().callView(sub_package + "FindAWordInsert", null);
						break;
						
					case "M":
						MainDispatcher.getInstance().callView(sub_package + "FindAWordUpdate", null);
						break;
						
					case "C":
						MainDispatcher.getInstance().callView(sub_package + "FindAWordDelete", null);
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

