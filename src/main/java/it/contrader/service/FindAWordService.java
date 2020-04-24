//created by Gabriella Brunetto

package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.contrader.converter.FindAWordConverter;
import it.contrader.dao.FindAWordRepository;
import it.contrader.dto.FindAWordDTO;
import it.contrader.model.FindAWord;




@Service

public class FindAWordService extends AbstractService<FindAWord, FindAWordDTO> {
//abbiamo gà tutto quello ke ci serve nell'abstractService non abbiamo bisogno di autowired
	
	/*@Autowired
	private FindAWordConverter converter;
	
	@Autowired
	private FindAWordRepository repository;*/
}
