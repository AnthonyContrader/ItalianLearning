import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IGamePlaylist } from 'app/shared/model/games/game-playlist.model';
import { GamePlaylistService } from './game-playlist.service';
import { IPlaylist } from 'app/shared/model/games/playlist.model';
import { PlaylistService } from 'app/entities/games/playlist';

@Component({
    selector: 'jhi-game-playlist-update',
    templateUrl: './game-playlist-update.component.html'
})
export class GamePlaylistUpdateComponent implements OnInit {
    private _gamePlaylist: IGamePlaylist;
    isSaving: boolean;

    playlists: IPlaylist[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private gamePlaylistService: GamePlaylistService,
        private playlistService: PlaylistService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ gamePlaylist }) => {
            this.gamePlaylist = gamePlaylist;
        });
        this.playlistService.query().subscribe(
            (res: HttpResponse<IPlaylist[]>) => {
                this.playlists = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.gamePlaylist.id !== undefined) {
            this.subscribeToSaveResponse(this.gamePlaylistService.update(this.gamePlaylist));
        } else {
            this.subscribeToSaveResponse(this.gamePlaylistService.create(this.gamePlaylist));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGamePlaylist>>) {
        result.subscribe((res: HttpResponse<IGamePlaylist>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackPlaylistById(index: number, item: IPlaylist) {
        return item.id;
    }
    get gamePlaylist() {
        return this._gamePlaylist;
    }

    set gamePlaylist(gamePlaylist: IGamePlaylist) {
        this._gamePlaylist = gamePlaylist;
    }
}
