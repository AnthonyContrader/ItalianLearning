package it.contrader.servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GameServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public GameServlet() {	
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		getServletContext().getRequestDispatcher("/game/gamemanager.jsp").forward(request, response);
	}
}
