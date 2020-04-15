package it.contrader.servlets;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.HangmanDTO;
import it.contrader.service.HangmanService;
import it.contrader.service.Service;

public class GameServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public GameServlet() {	
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		getServletContext().getRequestDispatcher("/findaword/findawordmanager.jsp").forward(request, response);
	}
}
