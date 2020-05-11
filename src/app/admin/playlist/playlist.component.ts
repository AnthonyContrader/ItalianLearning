//Created By Alessandro Alfieri
import { Component, OnInit, ViewChild } from '@angular/core';
import { PlaylistDTO } from 'src/dto/playlistdto';
import { PlaylistService } from 'src/service/playlist.service';
import { GamePlaylistService } from 'src/service/gameplaylist.service';
import { FindAWordService } from 'src/service/findaword.service';
import { FindMistakeService } from 'src/service/findmistake.service';
import { GuessPictureService } from 'src/service/guesspicture.service';
import { HangmanService } from 'src/service/hangman.service';
import { OrganizeSentenceService } from 'src/service/organizesentence.service';
import { QuizService } from 'src/service/quiz.service';
import { GamePlaylistDTO } from 'src/dto/gameplaylistdto';
import { FindMistakeDTO } from 'src/dto/findmistakedto';
import { FindAWordDTO } from 'src/dto/findaworddto';
import { GuessPictureDTO } from 'src/dto/guesspicturedto';
import { HangmanDTO } from 'src/dto/hangmandto';
import { OrganizeSentenceDTO } from 'src/dto/organizesentencedto';
import { QuizDTO } from 'src/dto/quizdto';
import { map, delay } from 'rxjs/operators';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit {

  playlistDTO: PlaylistDTO[];
  gamePlaylistDTO: GamePlaylistDTO[];
  playListDTO: PlaylistDTO;
  gamePlaylisttoinsert: GamePlaylistDTO;
  _gamesArray: Array<any>;
  playlisttoinsert: PlaylistDTO = new PlaylistDTO();
  @ViewChild('gameList') gamelist;
  @ViewChild('newPlaylistForm') playlistForm;
  @ViewChild('modalTitle') modalTitle;
  @ViewChild('closeModal') closeModal;

  constructor(private service: PlaylistService, private gpService: GamePlaylistService, private fawService: FindAWordService, private fmService: FindMistakeService, private gupService: GuessPictureService, private hService: HangmanService, private osService: OrganizeSentenceService, private qService: QuizService) { }

  ngOnInit() {
    this.getPlaylist();
  }

  getPlaylist() {
    this.service.getAll().subscribe(playlist => this.playlistDTO = playlist)
    this.gpService.getAll().subscribe(gamePlaylist => this.gamePlaylistDTO = gamePlaylist);
  }

  delete(playlist: PlaylistDTO) {
    this.service.delete(playlist.id).subscribe(() => this.getPlaylist());
  }

  update(playlist: PlaylistDTO) {
    this.service.update(playlist).subscribe(() => this.getPlaylist());
  }

  insert(playlist: PlaylistDTO) {
    console.log(playlist);
    this.service.insert(playlist).subscribe(() => this.getPlaylist());
  }

  gamePlaylist(playlist: PlaylistDTO) {
    this.playListDTO = playlist;
    let list = new Map<String, any>();
    this._gamesArray = new Array();
    this.fawService.getAll().subscribe(obj => {
      obj.forEach(rk => {
        list = new Map<String, any>();
        list.set("id", rk.id);
        list.set("solution", rk.solution);
        list.set("typeGame", FindAWordDTO.typeGame);
        list.set("name", 'Find A Word');
        list.set("checked", this.getCheckedGame(playlist.id, rk.id, FindAWordDTO.typeGame));
        list.set("idPlaylist", playlist.id);
        this._gamesArray.push(list);
      });
    });
    this.fmService.getAll().subscribe(obj => {
      obj.forEach(rk => {
        list = new Map<String, any>();
        list.set("id", rk.id);
        list.set("solution", rk.solution);
        list.set("typeGame", FindMistakeDTO.typeGame);
        list.set("name", 'Find Mistake');
        list.set("idPlaylist", playlist.id);
        this._gamesArray.push(list);
      });
    });
    this.gupService.getAll().subscribe(obj => {
      obj.forEach(rk => {
        list = new Map<String, any>();
        list.set("id", rk.id);
        list.set("solution", rk.solution);
        list.set("typeGame", GuessPictureDTO.typeGame);
        list.set("name", 'Guess Picture');
        list.set("idPlaylist", playlist.id);
        this._gamesArray.push(list);
      });
    });
    this.hService.getAll().subscribe(obj => {
      obj.forEach(rk => {
        list = new Map<String, any>();
        list.set("id", rk.id);
        list.set("solution", rk.solution);
        list.set("typeGame", HangmanDTO.typeGame);
        list.set("name", 'Hangman');
        list.set("idPlaylist", playlist.id);
        this._gamesArray.push(list);
      });
    });
    this.osService.getAll().subscribe(obj => {
      obj.forEach(rk => {
        list = new Map<String, any>();
        list.set("id", rk.id);
        list.set("solution", rk.solution);
        list.set("typeGame", OrganizeSentenceDTO.typeGame);
        list.set("name", 'Organize Sentence');
        list.set("idPlaylist", playlist.id);
        this._gamesArray.push(list);
      });
    });
    this.qService.getAll().subscribe(obj => {
      obj.forEach(rk => {
        list = new Map<String, any>();
        list.set("id", rk.id);
        list.set("solution", rk.solution);
        list.set("typeGame", QuizDTO.typeGame);
        list.set("name", 'Quiz');
        list.set("idPlaylist", playlist.id);
        this._gamesArray.push(list);
      });
    });



    // console.log(this._gamesArray)
    return this._gamesArray
  }

  getCheckedGame(playlist_id: number, game_id: number, typeGame: string){
    return this.gamePlaylistDTO.filter(value => value.idGame == game_id && value.playlistId == playlist_id && value.typeGame == typeGame).length > 0
  }

  updatePlaylist(playlist_id: number) {
    
    this.gamePlaylisttoinsert = new GamePlaylistDTO();
    let gameList: Array<any> = new Array<any>();
    let checkboxes: Array<any> = Array.from(this.gamelist.nativeElement.querySelectorAll('.selection_cb'))
    console.log(checkboxes)
    this.gpService.deleteByPlaylist(playlist_id).subscribe(() => this.getPlaylist());
    for (let checkbox of checkboxes) {
      // let list = {}
      if (checkbox.checked) {
        this.gamePlaylisttoinsert.playlistId = JSON.parse(checkbox.dataset.playlist).id;
        // this.gamePlaylisttoinsert.playlistName = JSON.parse(checkbox.dataset.playlist).name;
        this.gamePlaylisttoinsert.idGame = checkbox.dataset.id ;
        this.gamePlaylisttoinsert.typeGame = checkbox.dataset.typeGame;
        this.gpService.insert(this.gamePlaylisttoinsert).subscribe(() => null);;
        console.log(this.gamePlaylisttoinsert);
      }
    }
    
    
  }

  clear() {
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
