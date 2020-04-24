package it.contrader.dao;
/*
 * created by Anna Cecere & Alessandro Alfieri
 */

import java.math.BigInteger;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.contrader.model.GamePlaylist;
import it.contrader.model.Playlist;

@Repository
@Transactional
public interface GamePlaylistRepository extends CrudRepository<GamePlaylist, Long>{
	
	@Query(value = "select (case when count(*) > 0 then true else false end) from game_playlist WHERE id_playlist = :idPlaylist AND id_game = :idGame AND type_game = :typeGame", nativeQuery = true)
	BigInteger findGameInPlaylist(@Param("idPlaylist") Long idPlaylist, @Param("idGame") Long idGame,@Param("typeGame") String typeGame);
	
	void deleteAllByPlaylist(Playlist playlist);
	
}
 
