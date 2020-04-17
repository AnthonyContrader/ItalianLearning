package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.contrader.dto.CategoryDTO;
import it.contrader.dto.FindAWordDTO;
import it.contrader.dto.LevelDTO;
import it.contrader.service.Service;
import it.contrader.service.FindAWordService;
import it.contrader.service.LevelService;
import it.contrader.service.CategoryService;

public class FindAWordServlet extends HttpServlet {
	
		private static final long serialVersionUID = 1L;

		public FindAWordServlet() {
		}
		
		//ritorna la lista del gioco e viene richiamato nello switch del service
		public void updateList(HttpServletRequest request) {
			Service<FindAWordDTO> service = new FindAWordService();
			List<FindAWordDTO>listDTO = service.getAll();
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
			Service<FindAWordDTO> service = new FindAWordService(); //creiamo un service per fare le crud
			String mode = request.getParameter("mode");
			FindAWordDTO dto;
			int id;
			boolean ans;

			switch (mode.toUpperCase()) {

			case "GAMELIST": //Torna la lista di tutto il mio gioco e la inserisce nella request
				updateList(request);
				categoryList(request);
				levelList(request);
				//chiamata alla pagina jsp
				getServletContext().getRequestDispatcher("/findaword/findawordmanager.jsp").forward(request, response); 
				break;

			case "READ":
				id = Integer.parseInt(request.getParameter("id"));
				dto = service.read(id);
				request.setAttribute("dto", dto);
				
				if (request.getParameter("update") == null && request.getParameter("delete")== null) {
					 getServletContext().getRequestDispatcher("/findaword/readfindaword.jsp").forward(request, response);
					}
				
				else if (request.getParameter("update") != null) {
					categoryList(request);
					levelList(request);
					getServletContext().getRequestDispatcher("/findaword/updatefindaword.jsp").forward(request, response);
				}
				else getServletContext().getRequestDispatcher("/findaword/deletefindaword.jsp").forward(request, response);
				
				break;

			case "INSERT": 
				Integer idCategory = Integer.parseInt(request.getParameter("idCategory"));
				Integer idLevel = Integer.parseInt(request.getParameter("idLevel"));
				String solution = request.getParameter("solution").toString();
				String definition= request.getParameter("definition").toString();
				String sentence = request.getParameter("sentence").toString();
				dto = new FindAWordDTO (idCategory,solution,definition,sentence, idLevel);
				//salva il risultato in ans che manderemo nella pagina jsp
				ans = service.insert(dto);
				request.setAttribute("ans", ans); //manda il risultato alla pagina jsp
				updateList(request);
				categoryList(request);
				levelList(request);
				//mando i dati alla pagina jsp
				getServletContext().getRequestDispatcher("/findaword/findawordmanager.jsp").forward(request, response);
				break;
				
			case "UPDATE":
				idCategory = Integer.parseInt(request.getParameter("idCategory"));
				idLevel = Integer.parseInt(request.getParameter("idLevel"));
				solution = request.getParameter("solution").toString();
				definition= request.getParameter("definition").toString();
				sentence = request.getParameter("sentence").toString();
				id = Integer.parseInt(request.getParameter("id"));
				dto = new FindAWordDTO (id,idCategory,solution,definition,sentence,idLevel);
				ans = service.update(dto);
				request.setAttribute("ans", ans);
				updateList(request);
				categoryList(request);
				levelList(request);
				getServletContext().getRequestDispatcher("/findaword/findawordmanager.jsp").forward(request, response);
				break;

			case "DELETE":
				id = Integer.parseInt(request.getParameter("id"));
				ans = service.delete(id);
				request.setAttribute("ans", ans);
				updateList(request);
				categoryList(request);
				levelList(request);
				getServletContext().getRequestDispatcher("/findaword/findawordmanager.jsp").forward(request, response);
				break;
			}
		}

}
