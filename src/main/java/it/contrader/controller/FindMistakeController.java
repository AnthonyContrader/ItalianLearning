//Created By @Alessandro Alfieri
package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.FindMistakeDTO;
import it.contrader.service.CategoryService;
import it.contrader.service.FindMistakeService;
import it.contrader.service.LevelService;

@Controller
@RequestMapping("/findmistake")
public class FindMistakeController {

	@Autowired
	private FindMistakeService service;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private LevelService levelService;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "findmistake/findmistakes";
	}
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id) {
		service.delete(id);
		setAll(request);
		return "findmistake/findmistakes";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "findmistake/updatefindmistake";
	}
	
	@GetMapping("/update")
	public String update(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id, @RequestParam(value = "solution", required = true) String solution,
						 @RequestParam("definition") String definition, @RequestParam(value = "sentence", required = true) String sentence,
						 @RequestParam(value = "optionA", required = true) String optionA, @RequestParam(value = "optionB", required = true) String optionB,
						 @RequestParam(value = "optionC", required = true) String optionC, @RequestParam(value = "idCategory", required = true) Integer idCategory,
				 		 @RequestParam(value = "idLevel", required = true) Integer idLevel) {
		FindMistakeDTO dto = new FindMistakeDTO();
		dto.setId(id);
		dto.setSolution(solution);
		dto.setDefinition(definition);
		dto.setSentence(sentence);
		dto.setOptionA(optionA);
		dto.setOptionB(optionB);
		dto.setOptionC(optionC);
		dto.setCategory(categoryService.read(idCategory));
		dto.setLevel(levelService.read(idLevel));
		service.update(dto);
		setAll(request);
		return "findmistake/findmistakes";
	}
	
	@GetMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam(value = "solution", required = true) String solution,
						 @RequestParam("definition") String definition, @RequestParam(value = "sentence", required = true) String sentence,
						 @RequestParam(value = "optionA", required = true) String optionA, @RequestParam(value = "optionB", required = true) String optionB,
						 @RequestParam(value = "optionC", required = true) String optionC, @RequestParam(value = "idCategory", required = true) Integer idCategory,
				 		 @RequestParam(value = "idLevel", required = true) Integer idLevel) {
		FindMistakeDTO dto = new FindMistakeDTO();
		dto.setSolution(solution);
		dto.setDefinition(definition);
		dto.setSentence(sentence);
		dto.setOptionA(optionA);
		dto.setOptionB(optionB);
		dto.setOptionC(optionC);
		dto.setCategory(categoryService.read(idCategory));
		dto.setLevel(levelService.read(idLevel));
		service.insert(dto);
		setAll(request);
		return "findmistake/findmistakes";
	}
	
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "findmistake/readfindmistake";
	}
	
	public void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
}
