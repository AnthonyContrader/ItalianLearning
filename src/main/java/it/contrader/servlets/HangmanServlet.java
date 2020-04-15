package it.contrader.servlets;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.contrader.dto.HangmanDTO;
import it.contrader.dto.CategoryDTO;
import it.contrader.service.Service;
import it.contrader.service.HangmanService;
import it.contrader.service.CategoryService;

public class HangmanServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public HangmanServlet() {	
	}
	
	public void updateList(HttpServletRequest request) {
		Service<HangmanDTO> service = new HangmanService();
		List<HangmanDTO> listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}
	
	public void categoryList(HttpServletRequest request) {
		Service<CategoryDTO> service = new CategoryService();
		List<CategoryDTO> listDTO = service.getAll();
		request.setAttribute("categoryList", listDTO);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<HangmanDTO> service = new HangmanService();
		String mode = request.getParameter("mode");
		HangmanDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "GAMELIST":
			updateList(request);
			categoryList(request);
			getServletContext().getRequestDispatcher("/hangman/hangmanmanager.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/hangman/readhangman.jsp").forward(request, response);
				
			}
			
			else getServletContext().getRequestDispatcher("/hangman/updatehangman.jsp").forward(request, response);
			
			break;

		case "INSERT":
			String solution = request.getParameter("solution").toString();
			String definition = request.getParameter("definition").toString();
			String sentence = request.getParameter("sentence").toString();
			Integer score = Integer.parseInt(request.getParameter("score").toString());
			Integer idCategory = Integer.parseInt(request.getParameter("idCategory").toString());
			dto = new HangmanDTO (solution,definition,sentence, score, idCategory);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			categoryList(request);
			getServletContext().getRequestDispatcher("/hangman/hangmanmanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			solution = request.getParameter("solution").toString();
			definition = request.getParameter("definition").toString();
			sentence = request.getParameter("sentence").toString();
			score = Integer.parseInt(request.getParameter("score").toString());
			idCategory = Integer.parseInt(request.getParameter("idCategory").toString());
			id = Integer.parseInt(request.getParameter("id"));
			dto = new HangmanDTO (id,solution,definition,sentence, score, idCategory);
			ans = service.update(dto);
			updateList(request);
			categoryList(request);
			getServletContext().getRequestDispatcher("/hangman/hangmanmanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			categoryList(request);
			getServletContext().getRequestDispatcher("/hangman/hangmanmanager.jsp").forward(request, response);
			break;
		}
	}

}
