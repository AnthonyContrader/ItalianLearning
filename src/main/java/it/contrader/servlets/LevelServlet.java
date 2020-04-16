package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.LevelDTO;
import it.contrader.service.LevelService;
import it.contrader.service.Service;

public class LevelServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public LevelServlet() {}
	
		public void updateList(HttpServletRequest request) {
			Service<LevelDTO> service = new LevelService();
			List<LevelDTO>listDTO = service.getAll();
			request.setAttribute("list", listDTO);
		}
		
		@Override
		public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			Service<LevelDTO> service = new LevelService();
			String mode = request.getParameter("mode");
			LevelDTO dto;
			int id;
			boolean ans;

			switch (mode.toUpperCase()) {

			case "LEVELLIST":
				updateList(request);
				getServletContext().getRequestDispatcher("/level/levelmanager.jsp").forward(request, response);
				break;

			case "READ":
				id = Integer.parseInt(request.getParameter("id"));
				dto = service.read(id);
				request.setAttribute("dto", dto);
				
				if (request.getParameter("update") == null && request.getParameter("delete") == null) 
					 getServletContext().getRequestDispatcher("/level/readlevel.jsp").forward(request, response);
				else if  (request.getParameter("update") != null) 
					getServletContext().getRequestDispatcher("/level/updatelevel.jsp").forward(request, response);
				else
					getServletContext().getRequestDispatcher("/level/deletelevel.jsp").forward(request, response);
				
				break;

			case "INSERT":
				String name = request.getParameter("name").toString();
				String description = request.getParameter("description").toString();
				Integer score = Integer.parseInt(request.getParameter("score").toString());
				dto = new LevelDTO (score, name, description);
				ans = service.insert(dto);
				request.setAttribute("ans", ans);
				updateList(request);
				getServletContext().getRequestDispatcher("/level/levelmanager.jsp").forward(request, response);
				break;
				
			case "UPDATE":
				name = request.getParameter("title");
				description = request.getParameter("description");
				id = Integer.parseInt(request.getParameter("id"));
				score = Integer.parseInt(request.getParameter("score").toString());

				dto = new LevelDTO (id, score, name, description);
				ans = service.update(dto);
				request.setAttribute("ans", ans);
				updateList(request);
				getServletContext().getRequestDispatcher("/level/levelmanager.jsp").forward(request, response);
				break;

			case "DELETE":
				id = Integer.parseInt(request.getParameter("id"));
				ans = service.delete(id);
				request.setAttribute("ans", ans);
				updateList(request);
				getServletContext().getRequestDispatcher("/level/levelmanager.jsp").forward(request, response);
				break;
			}
		}

}
