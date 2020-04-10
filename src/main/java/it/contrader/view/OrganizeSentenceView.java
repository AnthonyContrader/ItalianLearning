package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.HangmanDTO;
import it.contrader.dto.OrganizeSentenceDTO;
import it.contrader.main.MainDispatcher;

public class OrganizeSentenceView extends AbstractView {
	
	private Request request;
	
	private String choice;
	
	public OrganizeSentenceView() {
		
	}
	
	
	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
		if (request != null) {
			//stampo la testata
			System.out.println("\n---------------------- Riordina la frase --------------------------------\n");
			System.out.println("ID\tSoluzione\tFrase Disordinata\t\tPunteggio\tCategoria");
			System.out.println("---------------------------------------------------------------------------\n");
																																																
			@SuppressWarnings("unchecked")
			//popolo una lista di tipo CategoryDTO
			List<OrganizeSentenceDTO> organizeSentence = (List<OrganizeSentenceDTO>) request.get("organizeSentences");
			//Itero la lista e stampo ogni elemento della lista
			for (OrganizeSentenceDTO o: organizeSentence)
				System.out.println(o);
			System.out.println();
		}
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("          Scegli l'operazione da effettuare:");
		System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");

		this.choice = getInput();
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		request = new Request();
		request.put("choice", choice);
		request.put("mode", "GETCHOICE");
		MainDispatcher.getInstance().callAction("OrganizeSentence", "doControl", this.request);
	}
	
	

}
