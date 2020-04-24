//Created By @Alessandro Alfieri
package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.HangmanDTO;
import it.contrader.service.HangmanService;
import it.contrader.service.CategoryService;
import it.contrader.service.GamePlaylistService;
import it.contrader.service.LevelService;

@Controller
@RequestMapping("/hangman")
public class HangmanController {
	
	@Autowired
	private HangmanService service;
	@Autowired
	private GamePlaylistService gpService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private LevelService levelService;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "hangman/hangmen";
	}
	
	@GetMapping("/predelete")
	public String preDelete(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "hangman/deletehangman";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id) {
		try {
			gpService.deleteAllByIdGameAndTypeGame(id, HangmanDTO.getTypeGame());
			service.delete(id);
			request.getSession().setAttribute("ans", true);
		}catch(Exception e) {
			request.getSession().setAttribute("ans", false);
		}
		setAll(request);
		return "hangman/hangmen";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		setAll(request);
		return "hangman/updatehangman";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id, @RequestParam(value = "solution", required = true) String solution,
						 @RequestParam("definition") String definition, @RequestParam(value = "sentence", required = true) String sentence,
						 @RequestParam(value = "idCategory", required = true) Long idCategory, @RequestParam(value = "idLevel", required = true) Long idLevel) {
		try {
			HangmanDTO dto =  new HangmanDTO();
			dto.setId(id);
			dto.setSolution(solution);
			dto.setDefinition(definition);
			dto.setSentence(sentence);
			dto.setCategory(categoryService.read(idCategory));
			dto.setLevel(levelService.read(idLevel));
			service.update(dto);
			request.getSession().setAttribute("ans", true);
		}catch(Exception e) {
			request.getSession().setAttribute("ans", false);
		}
		setAll(request);
		return "hangman/hangmen";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam(value = "solution", required = true) String solution,
			 @RequestParam("definition") String definition, @RequestParam(value = "sentence", required = true) String sentence,
			 @RequestParam(value = "idCategory", required = true) Long idCategory, @RequestParam(value = "idLevel", required = true) Long idLevel) {
		try {
			HangmanDTO dto =  new HangmanDTO();
			dto.setSolution(solution);
			dto.setDefinition(definition);
			dto.setSentence(sentence);
			dto.setCategory(categoryService.read(idCategory));
			dto.setLevel(levelService.read(idLevel));
			service.insert(dto);
			request.getSession().setAttribute("ans", true);
		}catch(Exception e) {
			request.getSession().setAttribute("ans", false);
		}
		setAll(request);
		return "hangman/hangmen";
	}
	
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "hangman/readhangman";
	}
	
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
		request.getSession().setAttribute("categoryList", categoryService.getAll());
		request.getSession().setAttribute("levelList", levelService.getAll());
	}
}
