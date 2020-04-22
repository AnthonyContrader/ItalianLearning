package it.contrader.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.FindAWordDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.FindAWord;
import it.contrader.model.User.Usertype;
import it.contrader.service.CategoryService;
import it.contrader.service.FindAWordService;
import it.contrader.service.LevelService;

@Controller
@RequestMapping("/findAWord") //le sottopagine di findaword


public class FindAWordController {
	
	@Autowired
	private FindAWordService service;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private LevelService levelService;
	
	@GetMapping("/getall")  // si avrà la kiamata alla pagina findaword/get all
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "findAWord";//torna a questa pagina
	}
	
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
		//gli passi la request come parametro ti prendi la sessione ovvero il proprio collegamento tra l'utente al browser, e gli passi come attributo di chiave lista e come valore service.getall ke prende tutte le righe
	}
	
	@GetMapping("/delete")//si avrà la kiamata alla pagina findaword/delete
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
	service.delete(id);
	setAll(request);// mette nella richiesta tutte le righe dei nostri giochi
	return "findAWord";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {// il secondo parametro dice ke salvi il parametro id della richiesta nella variabile long id {
	request.getSession().setAttribute("dto", service.read(id));
	return "updateuser";
	
	}

@PostMapping("/update")
public String update(HttpServletRequest request, @RequestParam("id") Long id, 
		@RequestParam("solution") String solution, @RequestParam("definition") String definition,
		@RequestParam("sentence") String sentence, @RequestParam("idCategory") Long idCategory, 
		@RequestParam("idLevel") Long idLevel) {

	FindAWordDTO dto = new FindAWordDTO();
	
	dto.setId(id);
	dto.setSolution(solution);
	dto.setDefinition(definition);
	dto.setSentence(sentence);
	dto.setCategory(categoryService.read(idCategory));
	dto.setLevel(levelService.read(idLevel));
	service.update(dto);
	setAll(request);
	return "findAWord";

}


	
}
