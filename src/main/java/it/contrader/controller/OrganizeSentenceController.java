package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.OrganizeSentenceDTO;
//import it.contrader.model.OrganizeSentence;
import it.contrader.service.OrganizeSentenceService;
import it.contrader.service.CategoryService;
import it.contrader.service.LevelService;

/*
**created by Torquato Di Maio
*/

@Controller
@RequestMapping("/organizesentence")
public class OrganizeSentenceController {
	
	@Autowired
	private OrganizeSentenceService service;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private LevelService levelService;
	
	private boolean ans;
	
	//chiamata organizeSentece/getall
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		//dove organizeSentences è il nome della pagina jsp
		return"organizesentence/organizesentences";
	}
	
	//RequestParam("id") Long id   salva nella variabile id il valore di "id" della RequestParam 
	//inoltre metto value e required=true per dire che e necessario inserirlo
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam(value="id", required=true) Long id) {
		try {
			service.delete(id);
			ans=true;
		}catch(Exception e) {
			ans= false;
		}
		
		setAll(request); //mette nella richiesta tutte le righe dei nostri giochi
		
		request.getSession().setAttribute("ans",ans);
		
		return "organizesentence/organizesentences";
	}
	
	//salvo il parametro id della richiesta nella variabile Long id questo è il secondo parametro!
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam(value="id",required=true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "organizesentence/updateorganizesentence";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam(value="id", required=true) Long id, @RequestParam(value="solution", required=true) String solution,
			@RequestParam(value="sentence", required=true) String sentence, @RequestParam("definition") String definition,
			@RequestParam(value="idCategory", required=true) Long idCategory, @RequestParam(value="idLevel",required=true) Long idLevel ) {
		
		try {
			OrganizeSentenceDTO dto = new OrganizeSentenceDTO();
			dto.setId(id);
			dto.setSolution(solution);
			dto.setSentence(sentence);
			dto.setDefinition(definition);
			
			dto.setCategory(categoryService.read(idCategory));
			dto.setLevel(levelService.read(idLevel));
			
			service.update(dto);
			ans=true;
		}catch(Exception e) {
			ans=false;
		}
		
		setAll(request);
		
		request.getSession().setAttribute("ans",ans);
		
		return "organizesentence/organizesentences";

	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam(value="solution", required=true) String solution,
			@RequestParam(value="sentence", required=true) String sentence, @RequestParam("definition") String definition,
			@RequestParam(value="idCategory", required=true) Long idCategory, @RequestParam(value="idLevel",required=true) Long idLevel ) {
		try {
			
			OrganizeSentenceDTO dto = new OrganizeSentenceDTO();
			dto.setSolution(solution);
			dto.setSentence(sentence);
			dto.setDefinition(definition);
			
			dto.setCategory(categoryService.read(idCategory));
			dto.setLevel(levelService.read(idLevel));
			service.insert(dto);
			ans=true;
		}catch(Exception e) {
			ans=false;
		}
		
		setAll(request);
		request.getSession().setAttribute("ans",ans);
		
		return "organizesentence/organizesentences";
	}
	
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam(value = "id", required = true) Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "organizesentence/readorganizesentences";
	}
	
	private void setAll(HttpServletRequest request) {
		
		//prende la sessione aperta(pagina chrome aperta)e gli passi un attributo di chiave lista e come valore service.getAll() 
		request.getSession().setAttribute("list", service.getAll());
		//levelList e categoryList devono essere uguali a come scritto nella jsp del nostro gioco
		request.getSession().setAttribute("levelList", levelService.getAll());
		request.getSession().setAttribute("categoryList", categoryService.getAll());
	}
	
}
