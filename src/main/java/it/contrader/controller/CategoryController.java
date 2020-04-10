package it.contrader.controller;

import java.util.List;

import it.contrader.dto.CategoryDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.CategoryService;

public class CategoryController implements Controller {
	
	//prefisso per chiamare le viste specifiche di Category
	private static String sub_package = "category.";
	
	private CategoryService categoryService;
	
	//Istanzio un oggetto CategoryService per utilizzarne le funzioni
	public CategoryController() {
		this.categoryService = new CategoryService();
	}
	
	
	@Override
	public void doControl(Request request) {
		
		//Mi salvo i parametri che arrivano dalla view
		String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");
		
		int id;
		String title;
		String description;
		boolean result;
		
		switch (mode) {
		case "READ":
			//prendo il valore chiamato 'id', lo converto in stringa e poi lo converto in intero
			id = Integer.parseInt(request.get("id").toString());
			/* Chiamo categoryService per eseguire il metodo read di CategoryDAO per leggere dal DB
			 * il record con id equivalente alla variabile 'id' e salvarlo all'interno
			 * dell'oggetto categoryDTO
			 */
			
			CategoryDTO categoryDTO = categoryService.read(id);
			//Mando il risultato alla vista
			request.put("category", categoryDTO);
			//Chiamo la vista CategoryReadView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "CategoryRead", request);
			break;
		case "INSERT":
			title = request.get("title").toString();
			description = request.get("description").toString();
			
			/*
			 *  Costruisco l'oggetto DTO categorytoinsert per mandarlo al service
			 *  che prover� ad inserirlo tramite la funzione insert del DAO 
			 */
			CategoryDTO categorytoinsert = new CategoryDTO(title, description);
			result = categoryService.insert(categorytoinsert);
			
			//Definisco una nuova request per mandare il risultato alla view
			request = new Request();
			request.put("mode", mode); // controllare se � corretto rimandare la stessa mode
			request.put("result", result); // verificare se si necessita il risultato
			//Chiamo la vista CategoryInsertView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "CategoryInsert", request);
			break;
		case "DELETE":
			//prendo il valore chiamato 'id', lo converto in stringa e poi lo converto in intero
			id = Integer.parseInt(request.get("id").toString());
			//chiamo solamente la delete del DAO in quanto non necessita il DTO
			result = categoryService.delete(id);
			//Definisco una nuova request per mandare il risultato alla view
			request = new Request();
			request.put("mode", mode); // controllare se � corretto rimandare la stessa mode
			request.put("result", result); // verificare se si necessita il risultato
			//Chiamo la vista CategoryDeleteView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "CategoryDelete", request);
			break;
		case "UPDATE":
			id = Integer.parseInt(request.get("id").toString());
			title = request.get("title").toString();
			description = request.get("description").toString();
			CategoryDTO categorytoupdate = new CategoryDTO(id, title, description);
			result = categoryService.update(categorytoupdate);
			request = new Request();
			request.put("mode", mode); // controllare se � corretto rimandare la stessa mode
			request.put("result", result); // verificare se si necessita il risultato
			//Chiamo la vista CategoryUpdateView con il risultato nei parametri
			MainDispatcher.getInstance().callView(sub_package + "CategoryUpdate", request);
			break;
		case "CATEGORYLIST":
			List<CategoryDTO> categoriesDTO = categoryService.getAll();
			request.put("categories", categoriesDTO);
			MainDispatcher.getInstance().callView("Category", request);
			break;
		case "GETCHOICE":
			
			//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "CategoryRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "CategoryInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "CategoryUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "CategoryDelete", null);
				break;
				
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;
		
			case "B":
				MainDispatcher.getInstance().callView("HomeAdmin", null);
				break;
				
			default:
				MainDispatcher.getInstance().callView("Login", null);
			}
			
		default:
			MainDispatcher.getInstance().callView("Login", null);
		}
	}
}
