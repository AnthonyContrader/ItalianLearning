package it.contrader.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.GuessPictureDTO;

/*
 * @author Enzo Tasca
 */

@RestController
@RequestMapping("/guesspicture")
@CrossOrigin(origins = "http://localhost:4200")
public class GuessPictureController extends AbstractController<GuessPictureDTO>{

}
