package it.contrader.controller;
/* created by Anna Cecere */

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.QuizDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.User.Usertype;
import it.contrader.service.CategoryService;
import it.contrader.service.LevelService;
import it.contrader.service.QuizService;




@Controller
@RequestMapping("/quiz") // indica in quale percorso verrà effettuata le operazioni che andremo a scrivere.


public class QuizController {
	@Autowired
	private QuizService service;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private LevelService levelService;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "quizzes";
	}
	
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		setAll(request);
		return "quizzes";
	}
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "updatequiz";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id,@RequestParam("solution") String solution,
			@RequestParam("definition") String definition,@RequestParam("sentence") String sentence,
	        @RequestParam("idCategory") Long idCategory, @RequestParam("idLevel") Long idLevel)
		 {

		QuizDTO dto = new QuizDTO();
			
		dto.setId(id);
		dto.setSolution(solution);
		dto.setDefinition(definition);
		dto.setSentence(sentence);
		dto.setCategory(categoryService.read(idCategory));
		dto.setLevel(levelService.read(idLevel));
		
		service.update(dto);
		setAll(request);
		return "quizzes";
		

	}
	
	
	private void setAll(HttpServletRequest request) {
		request.getSession() . setAttribute("list", service.getAll());
	}
	
	
	
}
