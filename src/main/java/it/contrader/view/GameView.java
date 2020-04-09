package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class GameView extends AbstractView {
	
	private Request request;
	
	private String choice;
	
	public GameView() {
		
	}

	@Override
	public void showResults(Request request) {
		System.out.println("\n---------------------------- Lista Giochi ----------------------------\n");
		System.out.println("[C]ompleta la frase");
		System.out.println("[I]mpiccato");
		System.out.println("I[n]dovina l'immagine");
		System.out.println("[Q]uiz");
		System.out.println("[R]iordina la frase");
		System.out.println("[T]rova l'errore");
		System.out.println("[B]ack");
		System.out.println("[E]xit");
		System.out.println("------------------------------------------------------------------------\n");
	}

	@Override
	public void showOptions() {
		System.out.print("Quale gioco vuoi gestire?:  ");
		this.choice = getInput();
	}

	@Override
	public void submit() {
		request = new Request();
		request.put("choice", choice);
		request.put("mode", "GETCHOICE");
		MainDispatcher.getInstance().callAction("Game", "doControl", this.request);
	}

}
