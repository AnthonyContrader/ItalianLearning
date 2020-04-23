package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.PlaylistDTO;
import it.contrader.service.PlaylistService;

@Controller
@RequestMapping("/playlist")	
public class PlaylistController {
	@Autowired
	private PlaylistService service;
	
	private boolean ans;
		
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
		
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "playlist/playlist";
	}
	
	@GetMapping("/predelete")
	public String preDelete(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "playlist/deleteplaylist";
	}
		
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {
		
		try {
			service.delete(id);
			ans = true;
		}catch(Exception e) {ans = false;}
		
		setAll(request);
		request.getSession().setAttribute("ans", ans);

		return "playlist/playlist";
	}
		
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "playlist/updateplaylist";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam(value="id", required = true) Long id, @RequestParam(value="name", required = true) String name, @RequestParam("description") String description) {
		
		try {
			PlaylistDTO playlistDTO = new PlaylistDTO();
			playlistDTO.setId(id);
			playlistDTO.setDescription(description);
			playlistDTO.setName(name);
			service.update(playlistDTO);
			ans = true;
		}catch(Exception e) { ans=false; }
		
		setAll(request);
		request.getSession().setAttribute("ans", ans);
		return "playlist/playlist";
	}
		
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam(value = "name", required = true) String name, @RequestParam("description") String description) {
		
		try {
			PlaylistDTO playlistDTO = new PlaylistDTO();
			playlistDTO.setName(name);
			playlistDTO.setDescription(description);
			service.insert(playlistDTO);
			ans = true;

		}catch(Exception e) { ans=false; }
		
		setAll(request);
		request.getSession().setAttribute("ans", ans);
		return "playlist/playlist";
	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
	request.getSession().setAttribute("dto", service.read(id));
	return "playlist/readplaylist";
	}
}
