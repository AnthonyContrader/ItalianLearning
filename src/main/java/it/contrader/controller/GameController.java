package it.contrader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * created by enzo, anna, gabriella, alessandro, torquato
 */

@Controller
@RequestMapping("/game")
public class GameController {
	
	@GetMapping("/getall")
	public String getAll() {
		return "games";
	}
}
