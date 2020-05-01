//Created By @Alessandro Alfieri
package it.contrader.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.converter.PlaylistConverter;
import it.contrader.dto.FindAWordDTO;
import it.contrader.dto.FindMistakeDTO;
import it.contrader.dto.GamePlaylistDTO;
import it.contrader.dto.GuessPictureDTO;
import it.contrader.dto.HangmanDTO;
import it.contrader.dto.OrganizeSentenceDTO;
import it.contrader.dto.QuizDTO;
import it.contrader.service.FindAWordService;
import it.contrader.service.FindMistakeService;
import it.contrader.service.GamePlaylistService;
import it.contrader.service.GuessPictureService;
import it.contrader.service.HangmanService;
import it.contrader.service.OrganizeSentenceService;
import it.contrader.service.PlaylistService;
import it.contrader.service.QuizService;

@RestController
@RequestMapping("/gameplaylist")
@CrossOrigin(origins = "http://localhost:4200")
public class GamePlaylistController extends AbstractController<GamePlaylistDTO>{
	
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
	
	@GetMapping("/getbyplaylist")
	public Iterable<Map<String, String>> getByPlaylist(@RequestParam("id") long id){
		
		Map<String, String> l;
		List<Map<String,String>> gameList = new ArrayList<>();
		
		Iterable<FindAWordDTO> listFindAWordDTO = findAWord.getAll();

		for (FindAWordDTO faw : listFindAWordDTO) {
			l = new HashMap<>();
			l.put("id", "" + faw.getId());
			l.put("solution", faw.getSolution());
			l.put("typeGame",FindAWordDTO.getTypeGame());
			l.put("name", "Find A Word");
			l.put("checked", "" + gpService.findGameInPlaylist(id, faw.getId(), FindAWordDTO.getTypeGame()));
			gameList.add(l);
		}

		Iterable<FindMistakeDTO> listFindMistakeDTO = findMistake.getAll();
		for (FindMistakeDTO fm : listFindMistakeDTO) {
			l = new HashMap<>();
			l.put("id", "" + fm.getId());
			l.put("solution", fm.getSolution());
			l.put("typeGame", FindMistakeDTO.getTypeGame());
			l.put("name", "Find Mistake");
			l.put("checked", "" + gpService.findGameInPlaylist(id, fm.getId(), FindMistakeDTO.getTypeGame()));
			gameList.add(l);
		}

		Iterable<GuessPictureDTO> listGuessPictureDTO = guessPicture.getAll();
		for (GuessPictureDTO gp : listGuessPictureDTO) {
			l = new HashMap<>();
			l.put("id", "" + gp.getId());
			l.put("solution", gp.getSolution());
			l.put("typeGame", GuessPictureDTO.getTypeGame());
			l.put("name", "Guess Picture");
			l.put("checked", "" + gpService.findGameInPlaylist(id, gp.getId(), GuessPictureDTO.getTypeGame()));
			gameList.add(l);
		}
;
		Iterable<HangmanDTO> listHangmanDTO = hangman.getAll();
		for (HangmanDTO h : listHangmanDTO) {
			l = new HashMap<>();
			l.put("id", "" + h.getId());
			l.put("solution", h.getSolution());
			l.put("typeGame", HangmanDTO.getTypeGame());
			l.put("name", HangmanDTO.getTypeGame());
			l.put("checked", "" + gpService.findGameInPlaylist(id, h.getId(), HangmanDTO.getTypeGame()));
			gameList.add(l);
		}

		Iterable<OrganizeSentenceDTO> listOrganizeSentenceDTO = organizeSentence.getAll();
		for (OrganizeSentenceDTO os : listOrganizeSentenceDTO) {
			l = new HashMap<>();
			l.put("id", "" + os.getId());
			l.put("solution", os.getSolution());
			l.put("typeGame", OrganizeSentenceDTO.getTypeGame());
			l.put("name", "Organize Sentence");
			l.put("checked", "" + gpService.findGameInPlaylist(id, os.getId(), OrganizeSentenceDTO.getTypeGame()));
			gameList.add(l);
		}

		Iterable<QuizDTO> listQuizDTO = quiz.getAll();
		for (QuizDTO q : listQuizDTO) {
			l = new HashMap<>();
			l.put("id", "" + q.getId());
			l.put("solution", q.getSolution());
			l.put("typeGame", QuizDTO.getTypeGame());
			l.put("name", QuizDTO.getTypeGame());
			l.put("checked", "" + gpService.findGameInPlaylist(id, q.getId(), QuizDTO.getTypeGame()));
			gameList.add(l);
		}

		Iterable<Map<String, String>> gpl = gameList;
		
		return gpl;
	}
	
	@PostMapping("/updateplaylist")
	public void updatePlaylist(@RequestParam("id") Long id, @RequestBody Iterable<Map<String, String>> gameList) {
		gpService.deleteAllByPlaylist(playlistConverter.toEntity(service.read(id)));
		GamePlaylistDTO gamePlayListDTO = new GamePlaylistDTO();
		for(Map<String, String> game : gameList) {
			gamePlayListDTO.setIdGame(Long.parseLong(game.get("id")));
			gamePlayListDTO.setTypeGame(game.get("typeGame"));
			gamePlayListDTO.setPlaylist(service.read(id));
			gpService.insert(gamePlayListDTO);
			gamePlayListDTO = new GamePlaylistDTO();
		}
	}
}
