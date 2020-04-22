//Created By @Alessandro Alfieri
package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.LevelDTO;
import it.contrader.service.LevelService;

@Controller
@RequestMapping("/level")
public class LevelController {
	
	Boolean ans;
	
	@Autowired
	private LevelService service;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "level/levels";
	}
	
	@GetMapping("/predelete")
	public String preDelete(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "level/deletelevel";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id) {
		try {
			service.delete(id);
			request.getSession().setAttribute("ans", true);
		}
		catch(Exception e) {
			request.getSession().setAttribute("ans", false);
		}
		setAll(request);
		return "level/levels";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "level/updatelevel";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id, @RequestParam(value = "name", required = true) String name,
						 @RequestParam("description") String description, @RequestParam(value = "score", required = true) Integer score) {
		try {
			LevelDTO dto =  new LevelDTO();
			dto.setId(id);
			dto.setName(name);
			dto.setDescription(description);
			dto.setScore(score);
			service.update(dto);
			request.getSession().setAttribute("ans", true);
		}catch(Exception e) {
			request.getSession().setAttribute("ans", false);
		}
		setAll(request);
		return "level/levels";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam(value = "name", required = true) String name,
						 @RequestParam("description") String description, @RequestParam(value = "score", required = true) Integer score) {
		try {
			LevelDTO dto =  new LevelDTO();
			dto.setName(name);
			dto.setDescription(description);
			dto.setScore(score);
			service.insert(dto);
			request.getSession().setAttribute("ans", true);
		}catch(Exception e) {
			request.getSession().setAttribute("ans", false);
		}
		setAll(request);
		return "level/levels";
	}
	
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "level/readlevel";
	}
	
	public void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
}
