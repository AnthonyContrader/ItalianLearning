package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.PlaylistDTO;
import it.contrader.service.PlaylistService;
import it.contrader.service.Service;

public class PlaylistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public PlaylistServlet() {}
	
	public void updateList(HttpServletRequest request) {
		Service<PlaylistDTO> service = new PlaylistService();
		List<PlaylistDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Service<PlaylistDTO> service = new PlaylistService();
		String mode = request.getParameter("mode");
		PlaylistDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "PLAYLISTLIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/playlist/playlistmanager.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null && request.getParameter("delete") == null) 
				 getServletContext().getRequestDispatcher("/playlist/readplaylist.jsp").forward(request, response);
			else if  (request.getParameter("update") != null) 
				getServletContext().getRequestDispatcher("/playlist/updateplaylist.jsp").forward(request, response);
			else
				getServletContext().getRequestDispatcher("/playlist/deleteplaylist.jsp").forward(request, response);
			
			break;

		case "INSERT":
			String name = request.getParameter("name").toString();
			String description = request.getParameter("description").toString();
			dto = new PlaylistDTO (name, description);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/playlist/playlistmanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			name = request.getParameter("name");
			description = request.getParameter("description");
			id = Integer.parseInt(request.getParameter("id"));
			dto = new PlaylistDTO (id,name, description);
			ans = service.update(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/playlist/playlistmanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/playlist/playlistmanager.jsp").forward(request, response);
			break;
		}
	}

}
