import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGamePlaylist } from 'app/shared/model/games/game-playlist.model';

@Component({
    selector: 'jhi-game-playlist-detail',
    templateUrl: './game-playlist-detail.component.html'
})
export class GamePlaylistDetailComponent implements OnInit {
    gamePlaylist: IGamePlaylist;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ gamePlaylist }) => {
            this.gamePlaylist = gamePlaylist;
        });
    }

    previousState() {
        window.history.back();
    }
}
