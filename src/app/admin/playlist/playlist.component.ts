import { Component, OnInit } from '@angular/core';
import { PlaylistDTO } from 'src/dto/playlistdto';
import { PlaylistService } from 'src/service/playlist.service';
import { GamePlaylistService } from 'src/service/gameplaylist.service';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit {

  playlistDTO: PlaylistDTO[];
  _gamesArray: Array<any> = [];
  playlisttoinsert: PlaylistDTO = new PlaylistDTO();

  constructor(private service: PlaylistService, private gpService: GamePlaylistService) { }

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

  gamePlaylist(playlist: PlaylistDTO){
    // ("#gamePlaylistModal").modal('show')
    this.gpService.findByPlaylist(playlist.id).subscribe(obj =>{
      for (var rk in obj){
        let map = new Map<string, any>();
        map.set("id", Number(obj.id));
        map.set("solution", obj.solution);
        map.set("typeGame", obj.typeGame);
        map.set("name", obj.name);
        map.set("checked", Boolean(obj.checked));
        this._gamesArray.push(map);
      }
    })
  }

  clear(){
    this.playlisttoinsert = new PlaylistDTO;
  }

}
