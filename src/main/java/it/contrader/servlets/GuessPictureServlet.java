package it.contrader.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.CategoryDTO;
import it.contrader.dto.GuessPictureDTO;
import it.contrader.service.CategoryService;
import it.contrader.service.GuessPictureService;
import it.contrader.service.Service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class GuessPictureServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
    private final String UPLOAD_DIRECTORY = "/Users/enzo/Desktop/";

	
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

			if(ServletFileUpload.isMultipartContent(request)){
	            try {
	                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	               
	                for(FileItem item : multiparts){
	                    if(!item.isFormField()){
	                        String name = new File(item.getName()).getName();
	                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
	                    }
	                }
	            
	               //File uploaded successfully
	               System.out.println("ok");
	            } catch (Exception ex) {
	               request.setAttribute("message", "File Upload Failed due to " + ex);
	            }          
	          
	        }else{
	            request.setAttribute("message",
	                                 "Sorry this Servlet only handles file upload request");
	        }
			
			solution = request.getParameter("solution").toString();
			//byte[] imageBinary = request.getParameter("image").getBytes();
			score = new Integer(request.getParameter("score"));
			idCategory = new Integer(request.getParameter("idCategory"));
			id = Integer.parseInt(request.getParameter("id"));
			

			
			//String base64DataString = new String(imageBinary , "UTF-8");
			
			//System.out.println(base64DataString);

		    
			
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
