package it.contrader.controller;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.FindAWordDTO;

//Credated By Gabriella Brunetto


@RestController
@RequestMapping("/findaword")
@CrossOrigin(origins = "http://localhost:4200")
public class FindAWordController extends AbstractController<FindAWordDTO>{
	
}