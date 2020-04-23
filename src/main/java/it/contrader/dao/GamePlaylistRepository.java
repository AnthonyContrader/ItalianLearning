package it.contrader.dao;
/*
 * created by Anna Cecere & Alessandro Alfieri
 */

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.GamePlaylist;



@Repository
@Transactional
public interface GamePlaylistRepository extends CrudRepository<GamePlaylist, Long>{
	
	boolean existsByIdPlaylistAndIdGameAndTypeGame(Long idPlaylist, Long idGame, String typeGame);
	
}
 
