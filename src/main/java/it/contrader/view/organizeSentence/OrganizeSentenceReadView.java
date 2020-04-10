package it.contrader.view.organizeSentence;

import it.contrader.controller.Request;
import it.contrader.dto.OrganizeSentenceDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;


public class OrganizeSentenceReadView extends AbstractView{

	private Request request;
	
	private int id;
	private final String mode = "READ";
	public OrganizeSentenceReadView() {
		
	}
	
	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		if (request != null) {
			OrganizeSentenceDTO organizeSentence = (OrganizeSentenceDTO) request.get("organizeSentence");
			System.out.println(organizeSentence);
			MainDispatcher.getInstance().callView("OrganizeSentence", null);
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
		MainDispatcher.getInstance().callAction("OrganizeSentence", "doControl", request);
	}
	
	
}
