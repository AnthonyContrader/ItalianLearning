package it.contrader.view.guessPicture;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.GuessPictureDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.GuessPictureService;
import it.contrader.view.AbstractView;

public class GuessPictureDeleteView extends AbstractView {
	
private Request request;
	
	private int id;
	private final String mode = "DELETE";
	private GuessPictureService guessPictureService;
	
	public GuessPictureDeleteView(){
		this.guessPictureService = new GuessPictureService();
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
			List<GuessPictureDTO> guessPictureDTO = guessPictureService.getAll();
			request.put("guessPictures", guessPictureDTO);
			MainDispatcher.getInstance().callView("GuessPicture", request);
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
		MainDispatcher.getInstance().callAction("GuessPicture", "doControl", request);
	}

}
