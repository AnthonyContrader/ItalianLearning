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
import it.contrader.dto.*;
import it.contrader.model.FindAWord;
import it.contrader.model.FindMistake;
import it.contrader.service.*;

public class PlaylistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static Map<String, List<Map<String,String>>> gameList = new HashMap<>();
	public static Map<String, String> l;
	
	public PlaylistServlet() {}
	
	public void updateList(HttpServletRequest request) {
		Service<PlaylistDTO> service = new PlaylistService();
		List<PlaylistDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}
	
	public void gameList(HttpServletRequest request) {
		Service<GamePlaylistDTO> service = new GamePlaylistService();
		
		Service<FindAWordDTO> findAWord = new FindAWordService();
		List<FindAWordDTO> listFindAWordDTO = findAWord.getAll();
		List<Map<String,String>> arr = new ArrayList<>();
		for (FindAWordDTO faw : listFindAWordDTO) {
			l = new HashMap<>();
			l.put("id", "" + faw.getId());
			l.put("solution", faw.getSolution());
			l.put("typeGame",FindAWordDTO.getTypeGame());
			l.put("name", "Find A Word");
			l.put("checked", "" + service.find(Integer.parseInt(request.getParameter("id")), faw.getId(), FindAWordDTO.getTypeGame()));
			arr.add(l);
			System.out.println("L:" + l);
		}
		System.out.println(arr);
		gameList.put(FindAWordDTO.getTypeGame(), arr);
		
		Service<FindMistakeDTO> findMistake= new FindMistakeService();
		System.out.println(gameList);
		arr = new ArrayList<>();
		List<FindMistakeDTO> listFindMistakeDTO = findMistake.getAll();
		for (FindMistakeDTO fm : listFindMistakeDTO) {
			l = new HashMap<>();
			l.put("id", "" + fm.getId());
			l.put("solution", fm.getSolution());
			l.put("typeGame", FindMistakeDTO.getTypeGame());
			l.put("name", "Find Mistake");
			l.put("checked", "" + service.find(Integer.parseInt(request.getParameter("id")), fm.getId(), FindMistakeDTO.getTypeGame()));
			arr.add(l);
		}
		gameList.put(FindMistakeDTO.getTypeGame(), arr);
		System.out.println(gameList.get("FindAWord"));
		
		Service<GuessPictureDTO> guessPicture = new GuessPictureService();
		arr = new ArrayList<>();
		List<GuessPictureDTO> listGuessPictureDTO = guessPicture.getAll();
		for (GuessPictureDTO gp : listGuessPictureDTO) {
			l = new HashMap<>();
			l.put("id", "" + gp.getId());
			l.put("solution", gp.getSolution());
			l.put("typeGame", GuessPictureDTO.getTypeGame());
			l.put("name", "Guess Picture");
			l.put("checked", "" + service.find(Integer.parseInt(request.getParameter("id")), gp.getId(), GuessPictureDTO.getTypeGame()));
			arr.add(l);
		}
		gameList.put(GuessPictureDTO.getTypeGame(), arr);
		
		Service<HangmanDTO> hangman = new HangmanService();
		arr = new ArrayList<>();
		List<HangmanDTO> listHangmanDTO = hangman.getAll();
		for (HangmanDTO h : listHangmanDTO) {
			l = new HashMap<>();
			l.put("id", "" + h.getId());
			l.put("solution", h.getSolution());
			l.put("typeGame", HangmanDTO.getTypeGame());
			l.put("name", HangmanDTO.getTypeGame());
			l.put("checked", "" + service.find(Integer.parseInt(request.getParameter("id")), h.getId(), HangmanDTO.getTypeGame()));
			arr.add(l);
		}
		gameList.put(HangmanDTO.getTypeGame(), arr);
		
		Service<OrganizeSentenceDTO> organizeSentence = new OrganizeSentenceService();
		arr = new ArrayList<>();
		List<OrganizeSentenceDTO> listOrganizeSentenceDTO = organizeSentence.getAll();
		for (OrganizeSentenceDTO os : listOrganizeSentenceDTO) {
			l = new HashMap<>();
			l.put("id", "" + os.getId());
			l.put("solution", os.getSolution());
			l.put("typeGame", OrganizeSentenceDTO.getTypeGame());
			l.put("name", "Organize Sentence");
			l.put("checked", "" + service.find(Integer.parseInt(request.getParameter("id")), os.getId(), OrganizeSentenceDTO.getTypeGame()));
			arr.add(l);
		}
		gameList.put(OrganizeSentenceDTO.getTypeGame(), arr);
		
		Service<QuizDTO> quiz = new QuizService();
		arr = new ArrayList<>();
		List<QuizDTO> listQuizDTO = quiz.getAll();
		for (QuizDTO q : listQuizDTO) {
			l = new HashMap<>();
			l.put("id", "" + q.getId());
			l.put("solution", q.getSolution());
			l.put("typeGame", QuizDTO.getTypeGame());
			l.put("name", QuizDTO.getTypeGame());
			l.put("checked", "" + service.find(Integer.parseInt(request.getParameter("id")), q.getId(), QuizDTO.getTypeGame()));
			arr.add(l);
		}
		gameList.put(QuizDTO.getTypeGame(), arr);
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
			id = Integer.parseInt(request.getParameter("id"));
			ans = gamePlaylistService.delete(id);
			if (ans) {
				String[] list = request.getParameter("gameList").split(",");
				GamePlaylistDTO gamePlayListDTO = new GamePlaylistDTO();
				for (int i = 0; i < list.length; i++) {
					if (i % 2 == 0) {
						gamePlayListDTO.setIdGame(Integer.parseInt(list[i]));
					}
					else {
						gamePlayListDTO.setTypeGame(list[i]);
						gamePlayListDTO.setIdPlaylist(id);
						gamePlaylistService.insert(gamePlayListDTO);
						gamePlayListDTO = new GamePlaylistDTO();
					}		
				}
			}		
			request.setAttribute("ans", ans);
			dto = service.read(id);
			request.setAttribute("dto", dto);
			gameList(request);
			getServletContext().getRequestDispatcher("/playlist/readplaylist.jsp").forward(request, response);
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
