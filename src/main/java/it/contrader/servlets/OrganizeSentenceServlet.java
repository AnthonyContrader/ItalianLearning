package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.OrganizeSentenceDTO;
import it.contrader.dto.CategoryDTO;
import it.contrader.service.CategoryService;
import it.contrader.service.Service;

import it.contrader.service.OrganizeSentenceService;

public class OrganizeSentenceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public OrganizeSentenceServlet() {
		// TODO Auto-generated constructor stub
	}
	
	public void updateList(HttpServletRequest request) {
		Service<OrganizeSentenceDTO> service = new OrganizeSentenceService();
		List<OrganizeSentenceDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}
	
	public void categoryList(HttpServletRequest request) {
		Service<CategoryDTO> service = new CategoryService();
		List<CategoryDTO>listDTO = service.getAll();
		request.setAttribute("categoryList", listDTO);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//stiamo creando un service per le crud
		Service<OrganizeSentenceDTO> service = new OrganizeSentenceService();
		String mode = request.getParameter("mode");
		OrganizeSentenceDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "GAMELIST":
			updateList(request);
			categoryList(request);
			getServletContext().getRequestDispatcher("/organizesentence/organizesentencemanager.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/organizesentence/readorganizesentence.jsp").forward(request, response);
				
			}
			
			else getServletContext().getRequestDispatcher("/organizesentence/updateorganizesentence.jsp").forward(request, response);
			
			break;

		case "INSERT":
			String solution = request.getParameter("solution").toString();
			String sentence = request.getParameter("password").toString();
			Integer score = Integer.parseInt(request.getParameter("score").toString());			
			String definition = request.getParameter("definition").toString();
			Integer idCategory = Integer.parseInt(request.getParameter("idCategory").toString());
			
			dto = new OrganizeSentenceDTO (solution, sentence,  score, definition, idCategory);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			categoryList(request);
			getServletContext().getRequestDispatcher("/organizesentence/organizesentencemanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			solution = request.getParameter("solution");
			sentence = request.getParameter("sentence");
			score = Integer.parseInt(request.getParameter("score"));
			definition = request.getParameter("definition");
			idCategory = Integer.parseInt(request.getParameter("idCategory"));
			id = Integer.parseInt(request.getParameter("id"));
			
			dto = new OrganizeSentenceDTO (id,solution, sentence, score,definition,idCategory);
			ans = service.update(dto);
			updateList(request);
			categoryList(request);
			getServletContext().getRequestDispatcher("/organizesentence/organizesentencemanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			categoryList(request);
			getServletContext().getRequestDispatcher("/organizesentence/organizesentencemanager.jsp").forward(request, response);
			break;
		}
	}
}
