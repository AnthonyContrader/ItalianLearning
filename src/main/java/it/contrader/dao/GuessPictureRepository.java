package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.GuessPicture;

/*
 * Created by Enzo Tasca
 */

@Repository
@Transactional
public interface GuessPictureRepository  extends CrudRepository<GuessPicture, Long>{}
