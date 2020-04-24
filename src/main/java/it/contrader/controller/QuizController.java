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
import it.contrader.service.CategoryService;
import it.contrader.service.GamePlaylistService;
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
	private boolean ans;
	@Autowired
	GamePlaylistService gpService;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "quiz/quizzes";
	}
	
	@GetMapping("/predelete")
	public String predelete(HttpServletRequest request, @RequestParam(value = "id", required = true ) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "quiz/deletequiz";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam(value = "id", required = true ) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		try {
			gpService.deleteAllByIdGameAndTypeGame(id, QuizDTO.getTypeGame());
			service.delete(id);
			ans = true;
			
		}catch(Exception e) {
			ans = false;
		}
		
		setAll (request);
		request.getSession().setAttribute ("ans" , ans);
		return "quiz/quizzes";
			
	}
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam(value = "id", required = true ) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "quiz/updatequiz";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id, @RequestParam(value = "solution", required = true) String solution,
			 @RequestParam("definition") String definition, @RequestParam(value = "sentence", required = true) String sentence,
			 @RequestParam(value = "idCategory", required = true) Long idCategory, @RequestParam(value = "idLevel", required = true) Long idLevel) {
		
		try {

		QuizDTO dto = new QuizDTO();
			
		dto.setId(id);
		dto.setSolution(solution);
		dto.setDefinition(definition);
		dto.setSentence(sentence);
		dto.setCategory(categoryService.read(idCategory));//inportiamo i service per prenderci l'oggetto category in base all'id
		dto.setLevel(levelService.read(idLevel));
		
		service.update(dto);
		
		ans = true;
		
		}catch(Exception e) {
			ans = false;
		}
		setAll(request);
		request.getSession().setAttribute("ans", ans);
		return "quiz/quizzes";
	}
		
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam(value = "solution", required = true) String solution,
			 @RequestParam("definition") String definition, @RequestParam(value = "sentence", required = true) String sentence,
			 @RequestParam(value = "idCategory", required = true) Long idCategory, @RequestParam(value = "idLevel", required = true) Long idLevel) {
		try {
			QuizDTO dto = new QuizDTO();		
		
			dto.setSolution(solution);
			dto.setDefinition(definition);
			dto.setSentence(sentence);
			dto.setCategory(categoryService.read(idCategory));//inportiamo i service per prenderci l'oggetto category in base all'id
			dto.setLevel(levelService.read(idLevel));
			
			service.update(dto);
			setAll(request);
			ans = true;
		}catch(Exception e) {
			ans = false;
		}
		request.getSession().setAttribute("ans", ans);
		return "quiz/quizzes";
	}
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam (value = "id", required = true ) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "quiz/readquiz";
	}
	
	private void setAll(HttpServletRequest request) {
		request.getSession() . setAttribute("list", service.getAll());
		request.getSession() . setAttribute("categoryList", categoryService.getAll());
		request.getSession() . setAttribute("levelList", levelService.getAll());
		
	}
	
	
	
}
