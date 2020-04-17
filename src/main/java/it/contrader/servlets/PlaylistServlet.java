package it.contrader.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.JsonArray;

import it.contrader.dto.*;
import it.contrader.service.*;

public class PlaylistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static Map<String, Map<String,String>> gameList = new HashMap<>();
	public static Map<String, String> l;
	
	public PlaylistServlet() {}
	
	public void updateList(HttpServletRequest request) {
		Service<PlaylistDTO> service = new PlaylistService();
		List<PlaylistDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}
	
	public void gameList(HttpServletRequest request) {
		Service<GamePlaylistDTO> service = new GamePlaylistService();
		List<List> list = new ArrayList<>();
		
		Service<FindAWordDTO> findAWord = new FindAWordService();
		List<FindAWordDTO> listFindAWordDTO = findAWord.getAll();
		l = new HashMap<>();
		for (FindAWordDTO faw : listFindAWordDTO) {
			l.put("id", "" + faw.getId());
			l.put("solution", faw.getSolution());
			l.put("typeGame",faw.getTypeGame());
			l.put("name", "Find A Word");
			l.put("checked", "" + service.find(request.getParameter("id"), faw.getId(), faw.getTypeGame()));
			gameList.put(faw.getTypeGame(), l);
		}
		
		Service<FindMistakeDTO> findMistake= new FindMistakeService();
		l = new HashMap<>();
		List<FindMistakeDTO> listFindMistakeDTO = findMistake.getAll();
		for (FindMistakeDTO fm : listFindMistakeDTO) {
			l.put("id", "" + fm.getId());
			l.put("solution", fm.getSolution());
			l.put("typeGame", fm.getTypeGame());
			l.put("name", "Find Mistake");
			l.put("checked", "" + service.find(request.getParameter("id"), fm.getId(), fm.getTypeGame()));
			gameList.put(fm.getTypeGame(), l);
		}
		
		Service<GuessPictureDTO> guessPicture = new GuessPictureService();
		l = new HashMap<>();
		List<GuessPictureDTO> listGuessPictureDTO = guessPicture.getAll();
		for (GuessPictureDTO gp : listGuessPictureDTO) {
			l.put("id", "" + gp.getId());
			l.put("solution", gp.getSolution());
			l.put("typeGame", gp.getTypeGame());
			l.put("name", "Guess Picture");
			l.put("checked", "" + service.find(request.getParameter("id"), gp.getId(), gp.getTypeGame()));
			gameList.put(gp.getTypeGame(), l);
		}
		
		Service<HangmanDTO> hangman = new HangmanService();
		l = new HashMap<>();
		List<HangmanDTO> listHangmanDTO = hangman.getAll();
		for (HangmanDTO h : listHangmanDTO) {
			l.put("id", "" + h.getId());
			l.put("solution", h.getSolution());
			l.put("typeGame", h.getTypeGame());
			l.put("name",  h.getTypeGame());
			l.put("checked", "" + service.find(request.getParameter("id"), h.getId(), h.getTypeGame()));
			gameList.put(h.getTypeGame(), l);
		}
		
		Service<OrganizeSentenceDTO> organizeSentence = new OrganizeSentenceService();
		l = new HashMap<>();
		List<OrganizeSentenceDTO> listOrganizeSentenceDTO = organizeSentence.getAll();
		for (OrganizeSentenceDTO os : listOrganizeSentenceDTO) {
			l.put("id", "" + os.getId());
			l.put("solution", os.getSolution());
			l.put("typeGame", os.getTypeGame());
			l.put("name", "Organize Sentence");
			l.put("checked", "" + service.find(request.getParameter("id"), os.getId(), os.getTypeGame()));
			gameList.put(os.getTypeGame(), l);
		}
		
		Service<QuizDTO> quiz = new QuizService();
		l = new HashMap<>();
		List<QuizDTO> listQuizDTO = quiz.getAll();
		for (QuizDTO q : listQuizDTO) {
			l.put("id", "" + q.getId());
			l.put("solution", q.getSolution());
			l.put("typeGame", q.getTypeGame());
			l.put("name", q.getTypeGame());
			l.put("checked", "" + service.find(request.getParameter("id"), q.getId(), q.getTypeGame()));
			gameList.put(q.getTypeGame(), l);
		}
		request.setAttribute("gameList", gameList);
		
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Service<PlaylistDTO> service = new PlaylistService();
		Service<GamePlaylistDTO> gamePlaylistService = new GamePlaylistService();
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
			
			if (request.getParameter("update") == null && request.getParameter("delete") == null) {
				gameList(request);
				getServletContext().getRequestDispatcher("/playlist/readplaylist.jsp").forward(request, response);
			}
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
				
		case "EDITPLAYLIST":
			String[] list = request.getParameter("gameList").split(",");
			ArrayList<ArrayList<String>> games = new ArrayList<ArrayList<String>>();
			for (int i = 0; i < list.length; i++) {
				ArrayList<String> arr = new ArrayList<String>();
				if (i % 2 == 0) {
					arr.add(list[i]);
				}
				else {
					arr.add(list[i]);
					games.add(arr);
				}		
			}
			gamePlaylistService.insert(games); //Da implementare
			id = Integer.parseInt(request.getParameter("id"));
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
