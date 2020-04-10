package it.contrader.view.findMistake;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.FindMistakeDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.service.FindMistakeService;

public class FindMistakeDeleteView extends AbstractView {
	private Request request;
	
	private int id;
	private final String mode = "DELETE";
	private FindMistakeService findMistakeService;
	
	public FindMistakeDeleteView() {
		findMistakeService = new FindMistakeService();
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
			List<FindMistakeDTO> findMistakesDTO = findMistakeService.getAll();
			request.put("findMistakes", findMistakesDTO);
			MainDispatcher.getInstance().callView("FindMistake", request);
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
		MainDispatcher.getInstance().callAction("FindMistake", "doControl", request);
	}

}
