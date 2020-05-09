//Created By Alessandro Alfieri
import { Component, OnInit, ViewChild } from '@angular/core';
import { PlaylistDTO } from 'src/dto/playlistdto';
import { PlaylistService } from 'src/service/playlist.service';
import { GamePlaylistService } from 'src/service/gameplaylist.service';
import { Utils } from '../util/utils';
import { FindAWordService } from 'src/service/findaword.service';
import { FindMistakeService } from 'src/service/findmistake.service';
import { GuessPictureService } from 'src/service/guesspicture.service';
import { HangmanService } from 'src/service/hangman.service';
import { OrganizeSentenceService } from 'src/service/organizesentence.service';
import { QuizService } from 'src/service/quiz.service';

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

  constructor(private service: PlaylistService, private gpService: GamePlaylistService, private fawService: FindAWordService, private fmService: FindMistakeService, private gupService: GuessPictureService, private hService: HangmanService, private osService: OrganizeSentenceService, private qService: QuizService) { }

  ngOnInit() {
    this.getPlaylist();
  }

  getPlaylist() {
    this.service.getAll().subscribe(playlist => this.playlistDTO = playlist)
  }

  delete(playlist: PlaylistDTO) {
    this.service.delete(playlist.id).subscribe(() => this.getPlaylist());
  }

  update(playlist: PlaylistDTO) {
    this.service.update(playlist).subscribe(() => this.getPlaylist());
  }

  insert(playlist: PlaylistDTO) {
    this.service.insert(playlist).subscribe(() => this.getPlaylist());
  }

  gamePlaylist(playlist: PlaylistDTO) {

    let list = new Map<String, any>();
    this._gamesArray = new Array();
    this.fawService.getAll().subscribe(obj => {
      obj.forEach(rk => {
        list = new Map<String, any>();
        list.set("id", rk.id);
        list.set("solution", rk.solution);
        list.set("typeGame", rk.typeGame);
        list.set("name", 'Find A Word');
        this.gpService.findGameInPlaylist(playlist.id, rk.id, rk.typeGame).subscribe(checked => list.set("checked", checked));
        list.set("idPlaylist", playlist.id);
        this._gamesArray.push(list);
      });
    });
    this.fmService.getAll().subscribe(obj => {
      obj.forEach(rk => {
        list = new Map<String, any>();
        list.set("id", rk.id);
        list.set("solution", rk.solution);
        list.set("typeGame", rk.typeGame);
        list.set("name", 'Find Mistake');
        this.gpService.findGameInPlaylist(playlist.id, rk.id, rk.typeGame).subscribe(checked => list.set("checked", checked));
        list.set("idPlaylist", playlist.id);
        this._gamesArray.push(list);
      });
    });
    this.gupService.getAll().subscribe(obj => {
      obj.forEach(rk => {
        list = new Map<String, any>();
        list.set("id", rk.id);
        list.set("solution", rk.solution);
        list.set("typeGame", rk.typeGame);
        list.set("name", 'Guess Picture');
        this.gpService.findGameInPlaylist(playlist.id, rk.id, rk.typeGame).subscribe(checked => list.set("checked", checked));
        list.set("idPlaylist", playlist.id);
        this._gamesArray.push(list);
      });
    });
    this.hService.getAll().subscribe(obj => {
      obj.forEach(rk => {
        list = new Map<String, any>();
        list.set("id", rk.id);
        list.set("solution", rk.solution);
        list.set("typeGame", rk.typeGame);
        list.set("name", 'Hangman');
        this.gpService.findGameInPlaylist(playlist.id, rk.id, rk.typeGame).subscribe(checked => list.set("checked", checked));
        list.set("idPlaylist", playlist.id);
        this._gamesArray.push(list);
      });
    });
    this.osService.getAll().subscribe(obj => {
      obj.forEach(rk => {
        list = new Map<String, any>();
        list.set("id", rk.id);
        list.set("solution", rk.solution);
        list.set("typeGame", rk.typeGame);
        list.set("name", 'Organize Sentence');
        this. gpService.findGameInPlaylist(playlist.id, rk.id, rk.typeGame).subscribe(checked => list.set("checked", checked));
        list.set("idPlaylist", playlist.id);
        this._gamesArray.push(list);
      });
    });
    this.qService.getAll().subscribe(obj => {
      obj.forEach(rk => {
        list = new Map<String, any>();
        list.set("id", rk.id);
        list.set("solution", rk.solution);
        list.set("typeGame", rk.typeGame);
        list.set("name", 'Quiz');
        this.gpService.findGameInPlaylist(playlist.id, rk.id, rk.typeGame).subscribe(checked => list.set("checked", checked));
        list.set("idPlaylist", playlist.id);
        this._gamesArray.push(list);
      });
    });



    console.log(this._gamesArray)
    return this._gamesArray
  }

  updatePlaylist(playlist_id: number) {
    console.log(playlist_id)
    let gameList: Array<any> = new Array<any>();
    let checkboxes: Array<any> = Array.from(this.gamelist.nativeElement.querySelectorAll('.selection_cb'))
    for (let checkbox of checkboxes) {
      let list = {}
      if (checkbox.checked) {
        list["id"] = checkbox.dataset.id.toString()
        list["typeGame"] = checkbox.dataset.typeGame
        gameList.push(list)
      }
    }
    console.log(gameList)
    this.gpService.updatePlaylist(playlist_id, gameList).subscribe(() => this.getPlaylist());
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
