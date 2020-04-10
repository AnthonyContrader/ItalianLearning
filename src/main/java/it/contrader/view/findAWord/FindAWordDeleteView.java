package it.contrader.view.findAWord;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.FindAWordDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.FindAWordService;

public class FindAWordDeleteView extends AbstractView {

	private Request request;
	
	private int id;
	private final String mode = "DELETE";
	private FindAWordService findAWordService;
	
	public FindAWordDeleteView() {
		findAWordService = new FindAWordService();
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
			List<FindAWordDTO> findAWordDTO = findAWordService.getAll();
			request.put("findAWords", findAWordDTO);
			MainDispatcher.getInstance().callView("FindAWord", request);
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
