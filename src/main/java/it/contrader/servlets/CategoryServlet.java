package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.CategoryDTO;
import it.contrader.service.CategoryService;
import it.contrader.service.Service;

public class CategoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public CategoryServlet() {}
	
	// ritorna la lista delle categorie e viene richiamato nello switch del service
	public void updateList(HttpServletRequest request) {
		Service<CategoryDTO> service = new CategoryService();
		List<CategoryDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// creiamo il service che ci servira per fare le crud
		Service<CategoryDTO> service = new CategoryService();
		String mode = request.getParameter("mode");
		CategoryDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "CATEGORYLIST":
			//inserisce nella request la lista delle categorie
			updateList(request);
			// chiamata alla pagina jsp
			getServletContext().getRequestDispatcher("/category/categorymanager.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null && request.getParameter("delete") == null) 
				 getServletContext().getRequestDispatcher("/category/readcategory.jsp").forward(request, response);
			else if  (request.getParameter("update") != null) 
				getServletContext().getRequestDispatcher("/category/updatecategory.jsp").forward(request, response);
			else
				getServletContext().getRequestDispatcher("/category/deletecategory.jsp").forward(request, response);
			
			break;

		case "INSERT":
			String title = request.getParameter("title").toString();
			String description = request.getParameter("description").toString();
			dto = new CategoryDTO (title, description);
			//salva il risultato in ans che manderemo nella pagina jsp
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			// mando i dati alla pagina jsp
			getServletContext().getRequestDispatcher("/category/categorymanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			title = request.getParameter("title");
			description = request.getParameter("description");
			id = Integer.parseInt(request.getParameter("id"));
			dto = new CategoryDTO (id,title, description);
			ans = service.update(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/category/categorymanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/category/categorymanager.jsp").forward(request, response);
			break;
		}
	}

}
