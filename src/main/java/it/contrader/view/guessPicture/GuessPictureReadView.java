package it.contrader.view.guessPicture;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.GuessPictureDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class GuessPictureReadView extends AbstractView{
	
	private Request request;
	private int id;
	private final String mode = "READ";
	
	public GuessPictureReadView() {
	}

	@Override
	public void showResults(Request request) {
		
		if (request != null) {
			GuessPictureDTO guessPicture = (GuessPictureDTO) request.get("guessPicture");
			System.out.println(guessPicture);
			
			String[] arrayString = guessPicture.getImage().split("\n");
			for (String s: arrayString ) 
				System.out.println(s);

			MainDispatcher.getInstance().callView("GuessPicture", null);
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
