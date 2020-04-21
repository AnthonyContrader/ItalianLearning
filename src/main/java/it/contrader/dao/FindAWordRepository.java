package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.FindAWord;

//created by Gabriella Brunetto

@Repository //indichiamo ke la classe è un repository e abilitiamo le operatività base del crud
@Transactional // quando deve parlare al db esegue transazione se va bene esegue se va male non fa nulla

public interface FindAWordRepository extends CrudRepository <FindAWord, Long> {

}
