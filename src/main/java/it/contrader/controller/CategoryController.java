package it.contrader.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.CategoryDTO;

/*
 * @author Enzo Tasca
 */

@RestController
@RequestMapping("/category")
//ci permette di accettare richiesta da angular
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController extends AbstractController<CategoryDTO>{
	
}
