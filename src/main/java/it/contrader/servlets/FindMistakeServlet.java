package it.contrader.servlets;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.contrader.dto.FindMistakeDTO;
import it.contrader.dto.LevelDTO;
import it.contrader.dto.CategoryDTO;
import it.contrader.service.Service;
import it.contrader.service.FindMistakeService;
import it.contrader.service.LevelService;
import it.contrader.service.CategoryService;

public class FindMistakeServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	public FindMistakeServlet() {}
	
	public void updateList(HttpServletRequest request) {
		Service<FindMistakeDTO> service = new FindMistakeService();
		List<FindMistakeDTO> listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}
	
	public void categoryList(HttpServletRequest request) {
		Service<CategoryDTO> service = new CategoryService();
		List<CategoryDTO> listDTO = service.getAll();
		request.setAttribute("categoryList", listDTO);
	}
	
	public void levelList(HttpServletRequest request) {
		Service<LevelDTO> service = new LevelService();
		List<LevelDTO> listDTO = service.getAll();
		request.setAttribute("levelList", listDTO);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<FindMistakeDTO> service = new FindMistakeService();
		String mode = request.getParameter("mode");
		FindMistakeDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "GAMELIST":
			updateList(request);
			categoryList(request);
			levelList(request);
			getServletContext().getRequestDispatcher("/findmistake/findmistakemanager.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null && request.getParameter("delete") == null) {
				 getServletContext().getRequestDispatcher("/findmistake/readfindmistake.jsp").forward(request, response);
				
			}
			
			else if (request.getParameter("update") != null){
				categoryList(request);
				levelList(request);
				getServletContext().getRequestDispatcher("/findmistake/updatefindmistake.jsp").forward(request, response);
			}
			else getServletContext().getRequestDispatcher("/findmistake/deletefindmistake.jsp").forward(request, response);
			
			break;

		case "INSERT":
			String solution = request.getParameter("solution").toString();
			String definition = request.getParameter("definition").toString();
			String sentence = request.getParameter("sentence").toString();
			String optionA = request.getParameter("optionA").toString();
			String optionB = request.getParameter("optionB").toString();
			String optionC = request.getParameter("optionC").toString();
			Integer idCategory = Integer.parseInt(request.getParameter("idCategory").toString());
			Integer idLevel = Integer.parseInt(request.getParameter("idLevel").toString());
			dto = new FindMistakeDTO (solution,definition,sentence, optionA, optionB, optionC, idCategory, idLevel);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			categoryList(request);
			levelList(request);
			getServletContext().getRequestDispatcher("/findmistake/findmistakemanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			solution = request.getParameter("solution").toString();
			definition = request.getParameter("definition").toString();
			sentence = request.getParameter("sentence").toString();
			optionA = request.getParameter("optionA").toString();
			optionB = request.getParameter("optionB").toString();
			optionC = request.getParameter("optionC").toString();
			idCategory = Integer.parseInt(request.getParameter("idCategory").toString());
			idLevel = Integer.parseInt(request.getParameter("idLevel").toString());
			id = Integer.parseInt(request.getParameter("id"));
			dto = new FindMistakeDTO (id,solution,definition,sentence, optionA, optionB, optionC, idCategory, idLevel);
			ans = service.update(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			categoryList(request);
			levelList(request);
			getServletContext().getRequestDispatcher("/findmistake/findmistakemanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			categoryList(request);
			levelList(request);
			getServletContext().getRequestDispatcher("/findmistake/findmistakemanager.jsp").forward(request, response);
			break;
		}
	}
}
