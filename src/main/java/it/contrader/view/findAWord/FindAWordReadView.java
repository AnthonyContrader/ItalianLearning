package it.contrader.view.findAWord;

import it.contrader.controller.Request;
import it.contrader.dto.FindAWordDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class FindAWordReadView extends AbstractView{
	
	private Request request;
	
	private int id;
	private final String mode = "READ";
	
	public FindAWordReadView() {
	}

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		if (request != null) {
			FindAWordDTO findAWord = (FindAWordDTO) request.get("findAWord");
			System.out.println(findAWord);
			MainDispatcher.getInstance().callView("FindAWord", null);
		}
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("Inserisci l'ID del gioco:");
		id = Integer.parseInt(getInput());
		
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("FindAWord", "doControl", request);
	}
		
	}
