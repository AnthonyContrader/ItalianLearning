package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Playlist;

/*
 * created by Enzo, Gabriella
 */

@Repository
@Transactional
public interface PlaylistRepository  extends CrudRepository<Playlist, Long> { }
