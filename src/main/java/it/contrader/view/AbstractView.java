package it.contrader.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public abstract class AbstractView implements View {

	private Scanner scanner;

	/**
	*Questo metodo � usato da tutti i controller che lo ereditano (non devono definirlo) estendendo questa classe
	*/
	
	public String getInput() {
		scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	
	public String getInputAscii() {

		BufferedReader in = new BufferedReader(
		new InputStreamReader(System.in));	        
		String finalString = new String();
			 
		try {
			for (String s = ""; !s.equals("end_ascii"); s = in.readLine()) {
				finalString += s;
				finalString +='\n';
			}
				
		} catch (IOException e) {return null;}
		
		return finalString;
	}
}
