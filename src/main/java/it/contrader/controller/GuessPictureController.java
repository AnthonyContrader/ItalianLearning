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
		return "guesspicturs";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		setAll(request);
		return "guesspicturs";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "updateguesspicture";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("image") String image, @RequestParam("solution") String solution) {
		GuessPictureDTO guessPictureDTO = new GuessPictureDTO();
		guessPictureDTO.setId(id);
		guessPictureDTO.setImage(image);
		guessPictureDTO.setSolution(solution);
		service.update(guessPictureDTO);
		setAll(request);
		return "guesspictures";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("image") String image, @RequestParam("solution") String solution) {
		GuessPictureDTO guessPictureDTO = new GuessPictureDTO();
		guessPictureDTO.setImage(image);
		guessPictureDTO.setSolution(solution);
		service.insert(guessPictureDTO);
		setAll(request);
		return "guesspictures";
	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "guesspicture";
	}

}
