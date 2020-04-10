package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.GuessPictureDTO;
import it.contrader.main.MainDispatcher;

public class GuessPictureView extends AbstractView{
	
	private Request request;
	private String choice;
	
	public GuessPictureView() {}

	@Override
	public void showResults(Request request) {
		
		if (request != null) {
			// idCategory,  score,  solution,  image
			System.out.println("\n--------------------------- Indovina l'immagine --------------------------\n");
			System.out.println("ID\tSoluzione\tImmagine\t\tPunteggio\tCategoria");
			System.out.println("---------------------------------------------------------------------------\n");
																																																
			@SuppressWarnings("unchecked")
			List<GuessPictureDTO> guessPicture = (List<GuessPictureDTO>) request.get("guessPictures");
			for (GuessPictureDTO x: guessPicture)
				System.out.println(x);
			System.out.println();
		}
		
	}

	@Override
	public void showOptions() {
		System.out.println("          Scegli l'operazione da effettuare:");
		System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");

		this.choice = getInput();
	}

	@Override
	public void submit() {
		request = new Request();
		request.put("choice", choice);
		request.put("mode", "GETCHOICE");
		MainDispatcher.getInstance().callAction("GuessPicture", "doControl", this.request);
	}

}
