//created by Gabriella Brunetto

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
@RequestMapping("/findaword") //le sottopagine di findaword



public class FindAWordController {
	
	@Autowired
	private FindAWordService service;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private LevelService levelService;
	
	private boolean ans;
	
	
	//percorso html
	@GetMapping("/getall")  // si avrà la kiamata alla pagina findaword/get all
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "findaword/findawords";//torna a questa pagina
	}
	
	//per la pagina di quando decidiamo se confermare o cancellare 
	@GetMapping("/predelete")
	public String preDelete(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {// il secondo parametro dice ke salvi il parametro id della richiesta nella variabile long id {
	request.getSession().setAttribute("dto", service.read(id));
	return "findaword/deletefindaword";
	
	}
	
	//al campo list metti tutta la lista di tutti i livelli giochi e categorie
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
		request.getSession().setAttribute("levelList", levelService.getAll());
		request.getSession().setAttribute("categoryList", categoryService.getAll());
		//gli passi la request come parametro ti prendi la sessione ovvero il proprio collegamento tra l'utente al browser, e gli passi come attributo di chiave lista e come valore service.getall ke prende tutte le righe
	}
	
	@GetMapping("/delete")//si avrà la kiamata alla pagina findaword/delete
	
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		try {
			service.delete(id);
			ans=true;
	
		}catch(Exception e) {
			ans=false;
			}//se si verifica un errore  se non si verifica vai avanti
		
		
	setAll(request);// mette nella richiesta tutte le righe dei nostri giochi
	request.getSession().setAttribute("ans", ans);//metti nella request quello ke c'è nella casella di ans mettiamo il valore di ans ke può essere vero o falso 
	return "findaword/findawords";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam(value="id", required = true) Long id) {// il secondo parametro dice ke salvi il parametro id della richiesta nella variabile long id {
	request.getSession().setAttribute("dto", service.read(id));
	return "findaword/updatefindaword";
	
	}

@PostMapping("/update")
public String update(HttpServletRequest request, 
		@RequestParam(value = "id", required = true) Long id, 
		@RequestParam(value = "solution", required = true) String solution, @RequestParam("definition") String definition,
		@RequestParam(value = "sentence", required = true) String sentence, @RequestParam(value = "idCategory", required = true) Long idCategory, 
		@RequestParam(value = "idLevel", required = true) Long idLevel) {

	try {
	FindAWordDTO dto = new FindAWordDTO();
	
	dto.setId(id);
	dto.setSolution(solution);
	dto.setDefinition(definition);
	dto.setSentence(sentence);
	dto.setCategory(categoryService.read(idCategory));
	dto.setLevel(levelService.read(idLevel));
	service.update(dto);
	ans=true;
	
	}catch (Exception e) {ans=false;}
	
	setAll(request);
	request.getSession().setAttribute("ans", ans);
	return "findaword/findawords";

}

@PostMapping("/insert")
public String insert(HttpServletRequest request, 
		@RequestParam(value = "solution", required = true) String solution, @RequestParam("definition") String definition,
		@RequestParam(value = "sentence", required = true) String sentence, @RequestParam(value = "idCategory", required = true) Long idCategory, 
		@RequestParam(value = "idLevel", required = true) Long idLevel){
try {
	FindAWordDTO dto = new FindAWordDTO();
	
	dto.setSolution(solution);
	dto.setDefinition(definition);
	dto.setSentence(sentence);
	dto.setCategory(categoryService.read(idCategory));
	dto.setLevel(levelService.read(idLevel));
	service.insert(dto);
	ans=true;
	
}catch(Exception e) {ans=false;}

	setAll(request);
	request.getSession().setAttribute("ans", ans);
	return "findaword/findawords";
}

@GetMapping("/read")
public String read(HttpServletRequest request, @RequestParam("id") Long id) {
	request.getSession().setAttribute("dto", service.read(id));
	return "findaword/readfindaword";
}


	
}
