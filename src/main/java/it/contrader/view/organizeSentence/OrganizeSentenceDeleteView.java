package it.contrader.view.organizeSentence;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.OrganizeSentenceDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.OrganizeSentenceService;

public class OrganizeSentenceDeleteView extends AbstractView{
	
	private Request request;
	
	private int id;
	private final String mode = "DELETE";
	private OrganizeSentenceService organizeSentenceService;
	
	
	
	public OrganizeSentenceDeleteView() {
		
		this.organizeSentenceService = new OrganizeSentenceService();
		
	}
	
	
	
	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		if (request!=null) {
			
			Boolean result = (Boolean) request.get("result");
			
			if (result) {
				System.out.println("Cancellazione andata a buon fine.\n");
			}
			else {
				System.out.println("Si e' verificato un' errore.\n");
			}
			
			request = new Request();
			List<OrganizeSentenceDTO> organizeSentenceDTO = organizeSentenceService.getAll();
			request.put("organizeSentences", organizeSentenceDTO);
			MainDispatcher.getInstance().callView("OrganizeSentence", request);
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
