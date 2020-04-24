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
import it.contrader.service.GamePlaylistService;

/*
 * Created by Enzo Tasca
 */

@Controller
@RequestMapping("/guesspicture")
public class GuessPictureController {
	
	@Autowired
	private GuessPictureService service;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LevelService levelService;
	
	@Autowired
	private GamePlaylistService gpService;
	
	private boolean ans;
	
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
		request.getSession().setAttribute("levelList", levelService.getAll());
		request.getSession().setAttribute("categoryList", categoryService.getAll());
	}
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "guesspicture/guesspictures";
	}
	
	@GetMapping("/predelete")
	public String preDelete(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "guesspicture/deleteguesspicture";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {
		
		try {
			gpService.deleteAllByIdGameAndTypeGame(id, GuessPictureDTO.getTypeGame());
			service.delete(id);
			ans = true;
		}catch(Exception e) {
			ans = false;
		}
		
		setAll(request);
		request.getSession().setAttribute("ans", ans);
		return "guesspicture/guesspictures";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "guesspicture/updateguesspicture";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam(value="id", required = true) Long id, @RequestParam(value="image", required = true) String image, 
			@RequestParam(value="solution", required = true) String solution, @RequestParam(value="idCategory", required = true) Long idCategory, @RequestParam(value="idLevel", required = true) Long idLevel) {
		try{
			GuessPictureDTO guessPictureDTO = new GuessPictureDTO();
			guessPictureDTO.setId(id);
			guessPictureDTO.setImage(image);
			guessPictureDTO.setSolution(solution);
			guessPictureDTO.setCategory(categoryService.read(idCategory));
			guessPictureDTO.setLevel(levelService.read(idLevel));
			service.update(guessPictureDTO);
			ans = true;
		}
		catch(Exception e) {ans = false;}
		
		setAll(request);
		request.getSession().setAttribute("ans", ans);
		return "guesspicture/guesspictures";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam(value = "image", required = true) String image, @RequestParam(value = "solution", required = true) String solution,
			@RequestParam(value = "idCategory", required = true) Long idCategory, @RequestParam(value = "idLevel", required = true) Long idLevel) {
		try {
			GuessPictureDTO guessPictureDTO = new GuessPictureDTO();
			guessPictureDTO.setImage(image);
			guessPictureDTO.setSolution(solution);
			guessPictureDTO.setCategory(categoryService.read(idCategory));
			guessPictureDTO.setLevel(levelService.read(idLevel));

			service.insert(guessPictureDTO);
			ans = true;
		}catch(Exception e) {ans = false;}
		
		setAll(request);
		request.getSession().setAttribute("ans", ans);
		return "guesspicture/guesspictures";
	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "guesspicture/readguesspicture";
	}

}
