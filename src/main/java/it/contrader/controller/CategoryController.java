package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.CategoryDTO;
import it.contrader.service.CategoryService;

/*
 * Created by Enzo Tasca
 */

@Controller
@RequestMapping("/category")		
public class CategoryController {

	@Autowired
	private CategoryService service;
		
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
		
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "category/categories";
	}
		
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		setAll(request);
		return "category/categories";
	}
		
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "category/updatecategory";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("descriptio") String description) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(id);
		categoryDTO.setDescription(description);
		categoryDTO.setTitle(title);
		service.update(categoryDTO);
		setAll(request);
		return "category/categories";
	}
		
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("title") String title, @RequestParam("description") String description) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setTitle(title);
		categoryDTO.setDescription(description);
		service.insert(categoryDTO);
		setAll(request);
		return "category/categories";
	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
	request.getSession().setAttribute("dto", service.read(id));
	return "category/categories";
	}
}
