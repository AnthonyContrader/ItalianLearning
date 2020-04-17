package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.QuizDTO;
import it.contrader.service.CategoryService;
import it.contrader.service.LevelService;
import it.contrader.service.QuizService;
import it.contrader.service.Service;

import it.contrader.dto.CategoryDTO;
import it.contrader.dto.LevelDTO;





public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public QuizServlet() {
		
	}

	public void updateList(HttpServletRequest request) {
		Service<QuizDTO> service = new QuizService();
		List<QuizDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}
		
	public void categoryList(HttpServletRequest request) {
		Service<CategoryDTO> service = new CategoryService();
		List<CategoryDTO>listDTO = service.getAll();
		request.setAttribute("categoryList", listDTO);
	}
	
	public void levelList(HttpServletRequest request) {
		Service<LevelDTO> service = new LevelService();
		List<LevelDTO>listDTO = service.getAll();
		request.setAttribute("levelList", listDTO);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<QuizDTO> service = new QuizService();
		String mode = request.getParameter("mode");
		QuizDTO dto;
		int id;
		boolean ans;
		
		switch (mode.toUpperCase()) {

		case "GAMELIST":
			categoryList(request);
			levelList(request);
			updateList(request);
			getServletContext().getRequestDispatcher("/quiz/quizmanager.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null && request.getParameter("delete") == null) {
				 getServletContext().getRequestDispatcher("/quiz/readquiz.jsp").forward(request, response);
				
			}
			else if (request.getParameter("update") != null) {
				categoryList(request);
				levelList(request);
				getServletContext().getRequestDispatcher("/quiz/updatequiz.jsp").forward(request, response);
			}
else getServletContext().getRequestDispatcher("/quiz/deletequiz.jsp").forward(request, response);
			
			break;
			
		case "INSERT":
			Integer idCategory = Integer.parseInt(request.getParameter("idCategory"));
			String solution = request.getParameter("solution").toString();
			String definition = request.getParameter("definition").toString();
			String sentence = request.getParameter("sentence").toString();
			Integer idLevel = Integer.parseInt(request.getParameter("idLevel"));
			dto = new QuizDTO(idCategory, solution, definition, sentence, idLevel );
			
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			
			updateList(request);
			categoryList(request);
			levelList(request);
			getServletContext().getRequestDispatcher("/quiz/quizmanager.jsp").forward(request, response);
			break;	
			
		case "UPDATE":
			idCategory = Integer.parseInt(request.getParameter("idCategory"));
			solution = request.getParameter("solution").toString();
			definition = request.getParameter("definition").toString();
			sentence = request.getParameter("sentence").toString();
			id = Integer.parseInt(request.getParameter("id"));
			idLevel = Integer.parseInt(request.getParameter("idLevel"));
			dto = new QuizDTO (id, idCategory, solution, definition, sentence, idLevel );
			ans = service.update(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			categoryList(request);
			levelList(request);
			getServletContext().getRequestDispatcher("/quiz/quizmanager.jsp").forward(request, response);
			break;
			
			
		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			categoryList(request);
			levelList(request);
			getServletContext().getRequestDispatcher("/quiz/quizmanager.jsp").forward(request, response);
			break;
		}
	}
}
