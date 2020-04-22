package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.GuessPictureDTO;
import it.contrader.service.GuessPictureService;
import it.contrader.service.LevelService;
import it.contrader.service.CategoryService;


/*
 * Created by Enzo Tasca
 */

@Controller
@RequestMapping("/guesspicture")
public class GuessPictureController {
	
	@Autowired
	private GuessPictureService service;
	
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "guesspictur/guesspicturs";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		setAll(request);
		return "guesspictur/guesspicturs";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "guesspictur/updateguesspicture";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("image") String image, 
			@RequestParam("solution") String solution, @RequestParam("idCategory") Long idCategory, @RequestParam("idLevel") Long idLevel) {
		GuessPictureDTO guessPictureDTO = new GuessPictureDTO();
		guessPictureDTO.setId(id);
		guessPictureDTO.setImage(image);
		guessPictureDTO.setSolution(solution);
		guessPictureDTO.setCategory(new CategoryService().read(idCategory));
		guessPictureDTO.setLevel(new LevelService().read(idLevel));
		service.update(guessPictureDTO);
		setAll(request);
		return "guesspictur/guesspictures";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("image") String image, @RequestParam("solution") String solution,
			@RequestParam("idCategory") Long idCategory, @RequestParam("idLevel") Long idLevel) {
		GuessPictureDTO guessPictureDTO = new GuessPictureDTO();
		guessPictureDTO.setImage(image);
		guessPictureDTO.setSolution(solution);
		guessPictureDTO.setCategory(new CategoryService().read(idCategory));
		guessPictureDTO.setLevel(new LevelService().read(idLevel));
		service.insert(guessPictureDTO);
		setAll(request);
		return "guesspictur/guesspictures";
	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "guesspictur/guesspicture";
	}

}
