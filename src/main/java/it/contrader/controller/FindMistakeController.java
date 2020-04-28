//Created By @Alessandro Alfieri
package it.contrader.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.FindMistakeDTO;

@RestController
@RequestMapping("/findmistake")
@CrossOrigin(origins = "http://localhost:4200")
public class FindMistakeController extends AbstractController<FindMistakeDTO>{

}
