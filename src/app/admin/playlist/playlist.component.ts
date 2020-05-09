//Created By Alessandro Alfieri
import { Component, OnInit, ViewChild} from '@angular/core';
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
  _gamesArray: Array<any>;
  playlisttoinsert: PlaylistDTO = new PlaylistDTO();
  @ViewChild('gameList') gamelist;
  @ViewChild('newPlaylistForm') playlistForm;
  @ViewChild('modalTitle') modalTitle;
  @ViewChild('closeModal') closeModal;

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
    this._gamesArray = [];
    this.gpService.findByPlaylist(playlist.id).subscribe(obj =>{
      obj.forEach(rk => {
        let map = new Map<string, any>();
        map.set("id", parseInt(rk.id));
        map.set("solution", rk.solution);
        map.set("typeGame", rk.typeGame);
        map.set("name", rk.name);
        map.set("checked", rk.checked);
        map.set("idPlaylist", playlist.id )
        this._gamesArray.push(map);
      });
    })
  console.log(this._gamesArray)
  }

  updatePlaylist(playlist_id: number){
    console.log(playlist_id)
    let gameList: Array<any> = new Array<any>();
    let checkboxes: Array<any> = Array.from(this.gamelist.nativeElement.querySelectorAll('.selection_cb'))
    for (let checkbox of checkboxes){
      let list = {}
      if (checkbox.checked){
        list["id"] = checkbox.dataset.id.toString()
        list["typeGame"] = checkbox.dataset.typeGame
        gameList.push(list)
      }
    }
    console.log(gameList)
    this.gpService.updatePlaylist(playlist_id, gameList).subscribe(() => this.getPlaylist());
  }

  clear(){
    this.playlisttoinsert = new PlaylistDTO;
  }
  submitted = false;

  editPlaylist(playlist: PlaylistDTO){
    this.playlistForm.reset()
    if(playlist != null){
      this.service.read(playlist.id).subscribe(playlist => this.playlisttoinsert = playlist);
      this.modalTitle.nativeElement.textContent = 'Edit Playlist ' + playlist.id
    }
    else
      this.modalTitle.nativeElement.textContent = 'New Playlist'
  }

  onSubmit(playlist: PlaylistDTO) {
    if (playlist.id != null)
      this.update(playlist)
    else
      this.insert(playlist)
      
      this.closeModal.nativeElement.click()
  }

}
