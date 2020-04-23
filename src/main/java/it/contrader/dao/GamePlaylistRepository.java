package it.contrader.dao;
/*
 * createt by Anna Cecere
 */

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.GamePlaylist;



@Repository
@Transactional

public interface GamePlaylistRepository extends CrudRepository<GamePlaylist, Long>{}
 
