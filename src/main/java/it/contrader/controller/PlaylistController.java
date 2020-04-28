//Created By @Alessandro Alfieri
package it.contrader.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.PlaylistDTO;

@RestController
@RequestMapping("/playlist")
@CrossOrigin(origins = "http://localhost:4200")
public class PlaylistController extends AbstractController<PlaylistDTO>{

}
