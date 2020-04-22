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
import it.contrader.service.LevelService;

@Controller
@RequestMapping("/hangman")
public class HangmanController {
	
	@Autowired
	private HangmanService service;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private LevelService levelService;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "hangmen";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		setAll(request);
		return "hangmen";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "updatehangman";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("solution") String solution,
						 @RequestParam("definition") String definition, @RequestParam("sentence") String sentence,
						 @RequestParam("idCategory") Long idCategory, @RequestParam("idLevel") Long idLevel) {
		HangmanDTO dto =  new HangmanDTO();
		dto.setId(id);
		dto.setSolution(solution);
		dto.setDefinition(definition);
		dto.setSentence(sentence);
		dto.setCategory(categoryService.read(idCategory));
		dto.setLevel(levelService.read(idLevel));
		service.update(dto);
		setAll(request);
		return "hangmen";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("solution") String solution,
			 @RequestParam("definition") String definition, @RequestParam("sentence") String sentence,
			 @RequestParam("idCategory") Long idCategory, @RequestParam("idLevel") Long idLevel) {
		
		HangmanDTO dto =  new HangmanDTO();
		dto.setSolution(solution);
		dto.setDefinition(definition);
		dto.setSentence(sentence);
		dto.setCategory(categoryService.read(idCategory));
		dto.setLevel(levelService.read(idLevel));
		service.insert(dto);
		setAll(request);
		return "hangmen";
	}
	
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "readhangman";
	}
	
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
}
