package it.contrader.controller;

import java.util.List;

import it.contrader.dto.GuessPictureDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.GuessPictureService;


public class GuessPictureController implements Controller{
	
private static String sub_package = "guessPicture.";
	
	private GuessPictureService guessPictureService;
	
	public GuessPictureController() {
		this.guessPictureService = new GuessPictureService();
	}

	@Override
	public void doControl(Request request) {
		
		String mode = (String) request.get("mode");
		String choice = (String) request.get("choice");
		
		int id;
		String solution;
		String image;
		Integer score;
		Integer idCategory;
		
		boolean result;
		
		switch (mode) {
		case "READ":
			id = Integer.parseInt(request.get("id").toString());
			GuessPictureDTO guessPictureDTO = guessPictureService.read(id);
			request.put("guessPicture", guessPictureDTO);
			MainDispatcher.getInstance().callView(sub_package + "GuessPictureRead", request);
			break;
			
		case "INSERT":
			solution = request.get("solution").toString();
			image = request.get("image").toString();
			score = Integer.parseInt(request.get("score").toString());
			idCategory = Integer.parseInt(request.get("idCategory").toString());

			GuessPictureDTO guessPicturetoinsert = new GuessPictureDTO( idCategory,  score,  solution,  image);
			result = guessPictureService.insert(guessPicturetoinsert);
			
			request = new Request();
			request.put("mode", mode); 
			request.put("result", result); 
			MainDispatcher.getInstance().callView(sub_package + "GuessPictureInsert", request);
			break;
			
		case "DELETE":
			id = Integer.parseInt(request.get("id").toString());
			result = guessPictureService.delete(id);
			request = new Request();
			request.put("mode", mode); 
			request.put("result", result);
			MainDispatcher.getInstance().callView(sub_package + "GuessPictureDelete", request);
			break;
			
		case "UPDATE":
			id = Integer.parseInt(request.get("id").toString());
			solution = request.get("solution").toString();
			image = request.get("image").toString();
			score = Integer.parseInt(request.get("score").toString());
			idCategory = Integer.parseInt(request.get("idCategory").toString());
			
			GuessPictureDTO guessPicturetoupdate = new GuessPictureDTO( id,  idCategory,  score,  solution,  image);
			
			result = guessPictureService.update(guessPicturetoupdate);
			request = new Request();
			request.put("mode", mode); 
			request.put("result", result);

			MainDispatcher.getInstance().callView(sub_package + "GuessPictureUpdate", request);
			break;
			
		case "GAMELIST":
			List<GuessPictureDTO> guessPicturesDTO = guessPictureService.getAll();
			request.put("guessPictures", guessPicturesDTO);
			MainDispatcher.getInstance().callView("GuessPicture", request);
			break;
			
		case "GETCHOICE":
			
			//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "GuessPictureRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "GuessPictureInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "GuessPictureUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "GuessPictureDelete", null);
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
