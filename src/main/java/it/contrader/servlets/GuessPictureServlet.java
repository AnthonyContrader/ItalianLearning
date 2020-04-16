package it.contrader.servlets;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.CategoryDTO;
import it.contrader.dto.GuessPictureDTO;
import it.contrader.service.CategoryService;
import it.contrader.service.GuessPictureService;
import it.contrader.service.Service;

public class GuessPictureServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public GuessPictureServlet() {}
	
	public void updateList(HttpServletRequest request) {
		Service<GuessPictureDTO> service = new GuessPictureService();
		List<GuessPictureDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}
		
	public void categoryList(HttpServletRequest request) {
		Service<CategoryDTO> service = new CategoryService();
		List<CategoryDTO> listDTO = service.getAll();
		request.setAttribute("categoryList", listDTO);
	}
		
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		Service<GuessPictureDTO> service = new GuessPictureService();
		String mode = request.getParameter("mode");
		GuessPictureDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "GAMELIST":
			updateList(request);
			categoryList(request);
			getServletContext().getRequestDispatcher("/guesspicture/guesspicturemanager.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
				
			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/guesspicture/readguesspicture.jsp").forward(request, response);
			}
				
			else {
				categoryList(request);
				getServletContext().getRequestDispatcher("/guesspicture/updateguesspicture.jsp").forward(request, response);
			}	
			break;

		case "INSERT":				
			String solution = request.getParameter("solution").toString();
			String image = request.getParameter("image").toString();
			Integer score = new Integer(request.getParameter("score"));
			Integer idCategory = new Integer(request.getParameter("score"));

			dto = new GuessPictureDTO (idCategory, score, solution, image);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			categoryList(request);
			getServletContext().getRequestDispatcher("/guesspicture/guesspicturemanager.jsp").forward(request, response);
			break;
				
		case "UPDATE":
			solution = request.getParameter("solution").toString();
			String imageString = request.getParameter("image").toString();
			score = new Integer(request.getParameter("score"));
			idCategory = new Integer(request.getParameter("idCategory"));
			id = Integer.parseInt(request.getParameter("id"));
			
			System.out.println(imageString);
			/*
			File file = new File(imageString);
			byte[] encoded = Base64.encodeBase64(bytes);
			String encodedString = new String(encoded);
		    */
			
			dto = new GuessPictureDTO (id, idCategory, score, solution, null);
			
			ans = service.update(dto);
			updateList(request);
			categoryList(request);			
			getServletContext().getRequestDispatcher("/guesspicture/guesspicturemanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			categoryList(request);
			getServletContext().getRequestDispatcher("/guesspicture/guesspicturemanager.jsp").forward(request, response);
			break;
		}
	}

}
