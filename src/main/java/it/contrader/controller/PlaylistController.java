// Created By @Alessandro Alfieri, @Gabriella Brunetto & @Enzo Tasca 
package it.contrader.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.converter.PlaylistConverter;
import it.contrader.dto.*;
import it.contrader.service.*;

@Controller
@RequestMapping("/playlist")	
public class PlaylistController {
	
	@Autowired
	private PlaylistService service;
	@Autowired
	private GamePlaylistService gpService;
	@Autowired
	private FindAWordService findAWord;
	@Autowired
	private FindMistakeService findMistake;
	@Autowired
	private GuessPictureService guessPicture;
	@Autowired
	private HangmanService hangman;
	@Autowired
	private OrganizeSentenceService organizeSentence;
	@Autowired
	private QuizService quiz;
	@Autowired
	private PlaylistConverter playlistConverter;
	
	private boolean ans;
	
	public void gameList(HttpServletRequest request, Long id) {
		Map<String, String> l;
		List<Map<String,String>> arr;
		List<List<Map<String,String>>> gameList = new ArrayList<>();
		
		List<FindAWordDTO> listFindAWordDTO = findAWord.getAll();
		arr = new ArrayList<>();
		for (FindAWordDTO faw : listFindAWordDTO) {
			l = new HashMap<>();
			l.put("id", "" + faw.getId());
			l.put("solution", faw.getSolution());
			l.put("typeGame",FindAWordDTO.getTypeGame());
			l.put("name", "Find A Word");
			l.put("checked", "" + gpService.findGameInPlaylist(id, faw.getId(), FindAWordDTO.getTypeGame()));
			arr.add(l);
		}
		gameList.add(arr);
		
		arr = new ArrayList<>();
		List<FindMistakeDTO> listFindMistakeDTO = findMistake.getAll();
		for (FindMistakeDTO fm : listFindMistakeDTO) {
			l = new HashMap<>();
			l.put("id", "" + fm.getId());
			l.put("solution", fm.getSolution());
			l.put("typeGame", FindMistakeDTO.getTypeGame());
			l.put("name", "Find Mistake");
			l.put("checked", "" + gpService.findGameInPlaylist(id, fm.getId(), FindMistakeDTO.getTypeGame()));
			arr.add(l);
		}
		gameList.add(arr);
			
		arr = new ArrayList<>();
		List<GuessPictureDTO> listGuessPictureDTO = guessPicture.getAll();
		for (GuessPictureDTO gp : listGuessPictureDTO) {
			l = new HashMap<>();
			l.put("id", "" + gp.getId());
			l.put("solution", gp.getSolution());
			l.put("typeGame", GuessPictureDTO.getTypeGame());
			l.put("name", "Guess Picture");
			l.put("checked", "" + gpService.findGameInPlaylist(id, gp.getId(), GuessPictureDTO.getTypeGame()));
			arr.add(l);
		}
		gameList.add(arr);
		
		arr = new ArrayList<>();
		List<HangmanDTO> listHangmanDTO = hangman.getAll();
		for (HangmanDTO h : listHangmanDTO) {
			l = new HashMap<>();
			l.put("id", "" + h.getId());
			l.put("solution", h.getSolution());
			l.put("typeGame", HangmanDTO.getTypeGame());
			l.put("name", HangmanDTO.getTypeGame());
			l.put("checked", "" + gpService.findGameInPlaylist(id, h.getId(), HangmanDTO.getTypeGame()));
			arr.add(l);
		}
		gameList.add(arr);
		
		arr = new ArrayList<>();
		List<OrganizeSentenceDTO> listOrganizeSentenceDTO = organizeSentence.getAll();
		for (OrganizeSentenceDTO os : listOrganizeSentenceDTO) {
			l = new HashMap<>();
			l.put("id", "" + os.getId());
			l.put("solution", os.getSolution());
			l.put("typeGame", OrganizeSentenceDTO.getTypeGame());
			l.put("name", "Organize Sentence");
			l.put("checked", "" + gpService.findGameInPlaylist(id, os.getId(), OrganizeSentenceDTO.getTypeGame()));
			arr.add(l);
		}
		gameList.add(arr);
		
		arr = new ArrayList<>();
		List<QuizDTO> listQuizDTO = quiz.getAll();
		for (QuizDTO q : listQuizDTO) {
			l = new HashMap<>();
			l.put("id", "" + q.getId());
			l.put("solution", q.getSolution());
			l.put("typeGame", QuizDTO.getTypeGame());
			l.put("name", QuizDTO.getTypeGame());
			l.put("checked", "" + gpService.findGameInPlaylist(id, q.getId(), QuizDTO.getTypeGame()));
			arr.add(l);
		}
		gameList.add(arr);
		request.getSession().setAttribute("gameList", gameList);
		
	}
		
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
		
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "playlist/playlist";
	}
	
	@GetMapping("/predelete")
	public String preDelete(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "playlist/deleteplaylist";
	}
		
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {
		
		try {
			service.delete(id);
			ans = true;
		}catch(Exception e) {ans = false;}
		
		setAll(request);
		request.getSession().setAttribute("ans", ans);

		return "playlist/playlist";
	}
		
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "playlist/updateplaylist";
	}
	
	@GetMapping("/editplaylist")
	public String editplaylist(HttpServletRequest request, @RequestParam(value="id", required = true) Long id, @RequestParam(value="gameList", required = true) String gameList) {
		PlaylistDTO dto;
		try {
			gpService.deleteAllByPlaylist(playlistConverter.toEntity(service.read(id)));
			String[] list = request.getParameter("gameList").split(",");
			if (list.length > 1 && list.length % 2 == 0) {
				GamePlaylistDTO gamePlayListDTO = new GamePlaylistDTO();
				for (int i = 0; i < list.length; i++) {
					if (i % 2 == 0) {
						gamePlayListDTO.setIdGame(Long.parseLong(list[i]));
					}
					else {
						gamePlayListDTO.setTypeGame(list[i]);
						gamePlayListDTO.setPlaylist(service.read(id));
						gpService.insert(gamePlayListDTO);
						gamePlayListDTO = new GamePlaylistDTO();
					}		
				}	
			}
			ans = true;
		}catch(Exception e) {
			ans = false;
		}
		
		dto = service.read(id);
		request.getSession().setAttribute("dto", dto);
		gameList(request, id);	
		setAll(request);
		request.getSession().setAttribute("ans", ans);
		return "playlist/playlist";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam(value="id", required = true) Long id, @RequestParam(value="name", required = true) String name, @RequestParam("description") String description) {
		
		try {
			PlaylistDTO playlistDTO = new PlaylistDTO();
			playlistDTO.setId(id);
			playlistDTO.setDescription(description);
			playlistDTO.setName(name);
			service.update(playlistDTO);
			ans = true;
		}catch(Exception e) { ans=false; }
		
		setAll(request);
		request.getSession().setAttribute("ans", ans);
		return "playlist/playlist";
	}
		
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam(value = "name", required = true) String name, @RequestParam("description") String description) {
		
		try {
			PlaylistDTO playlistDTO = new PlaylistDTO();
			playlistDTO.setName(name);
			playlistDTO.setDescription(description);
			service.insert(playlistDTO);
			ans = true;

		}catch(Exception e) { ans=false; }
		
		setAll(request);
		request.getSession().setAttribute("ans", ans);
		return "playlist/playlist";
	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
	request.getSession().setAttribute("dto", service.read(id));
	gameList(request, id);
	return "playlist/readplaylist";
	}
}
