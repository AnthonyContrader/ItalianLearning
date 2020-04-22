package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.OrganizeSentenceDTO;
import it.contrader.model.OrganizeSentence;
import it.contrader.service.OrganizeSentenceService;
import it.contrader.service.CategoryService;
import it.contrader.service.LevelService;
/*
**created by Torquato Di Maio
*/

@Controller
@RequestMapping("/organizeSentence")
public class OrganizeSentenceController {
	
	@Autowired
	private OrganizeSentenceService service;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private LevelService levelService;
	
	//chiamata organizeSentece/getall
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		//dove organizeSentences è il nome della pagina jsp
		return"organizeSentences";
	}
	
	//RequestParam("id") Long id   salva nella variabile id il valore di "id" della RequestParam 
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		setAll(request); //mette nella richiesta tutte le righe dei nostri giochi
		return "organizeSentences";
	}
	
	//salvo il parametro id della richiesta nella variabile Long id questo è il secondo parametro
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "updateorganizeSentence";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("solution") String solution,
			@RequestParam("sentence") String sentence, @RequestParam("definition") String definition,
			@RequestParam("idCategory") Long idCategory, @RequestParam("idLevel") Long idLevel ) {
		
		
		OrganizeSentenceDTO dto = new OrganizeSentenceDTO();
		dto.setId(id);
		dto.setSolution(solution);
		dto.setSentence(sentence);
		dto.setDefinition(definition);
		
		dto.setCategory(categoryService.read(idCategory));
		dto.setLevel(levelService.read(idLevel));
		
		service.update(dto);
		setAll(request);
		return "organizeSentences";

	}
	
	
	
	
	private void setAll(HttpServletRequest request) {
		//prende la sessione aperta(pagina chrome aperta)e gli passi un attributo di chiave lista e come valore service.getAll() 
		request.getSession().setAttribute("list", service.getAll());
	}
	
}
