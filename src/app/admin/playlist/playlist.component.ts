import { Component, OnInit } from '@angular/core';
import { PlaylistDTO } from 'src/dto/playlistdto';
import { PlaylistService } from 'src/service/playlist.service';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit {

  playlistDTO: PlaylistDTO[];
  playlisttoinsert: PlaylistDTO = new PlaylistDTO();

  constructor(private service: PlaylistService) { }

  ngOnInit() {
    this.getPlaylist();
  }

  getPlaylist() {
    this.service.getAll().subscribe(playlist => this.playlistDTO = playlist)
  }

  delete(playlist: PlaylistDTO){
    this.service.delete(playlist.id).subscribe(() => this.getPlaylist());
  }

  update(playlist: PlaylistDTO){
    this.service.update(playlist).subscribe(() => this.getPlaylist());
  }

  insert(playlist: PlaylistDTO){
    this.service.insert(playlist).subscribe(() => this.getPlaylist());
  }

  clear(){
    this.playlisttoinsert = new PlaylistDTO;
  }

}
