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
	
	private boolean ans;
		
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
		
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "category/categories";
	}
	
	@GetMapping("/predelete")
	public String preDelete(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "category/deletecategory";
	}
		
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {
		
		try {
			service.delete(id);
			ans = true;
		}catch(Exception e) {ans = false;}
		
		setAll(request);
		request.getSession().setAttribute("ans", ans);

		return "category/categories";
	}
		
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "category/updatecategory";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam(value="id", required = true) Long id, @RequestParam(value="title", required = true) String title, @RequestParam("description") String description) {
		
		try {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(id);
			categoryDTO.setDescription(description);
			categoryDTO.setTitle(title);
			service.update(categoryDTO);
			ans = true;
		}catch(Exception e) { ans=false; }
		
		setAll(request);
		request.getSession().setAttribute("ans", ans);
		return "category/categories";
	}
		
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam(value = "title", required = true) String title, @RequestParam("description") String description) {
		
		try {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setTitle(title);
			categoryDTO.setDescription(description);
			service.insert(categoryDTO);
			ans = true;

		}catch(Exception e) { ans=false; }
		
		setAll(request);
		request.getSession().setAttribute("ans", ans);
		return "category/categories";
	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
	request.getSession().setAttribute("dto", service.read(id));
	return "category/readcategory";
	}
}
