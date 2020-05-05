import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPlaylist } from 'app/shared/model/games/playlist.model';
import { PlaylistService } from './playlist.service';

@Component({
    selector: 'jhi-playlist-update',
    templateUrl: './playlist-update.component.html'
})
export class PlaylistUpdateComponent implements OnInit {
    private _playlist: IPlaylist;
    isSaving: boolean;

    constructor(private playlistService: PlaylistService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ playlist }) => {
            this.playlist = playlist;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.playlist.id !== undefined) {
            this.subscribeToSaveResponse(this.playlistService.update(this.playlist));
        } else {
            this.subscribeToSaveResponse(this.playlistService.create(this.playlist));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPlaylist>>) {
        result.subscribe((res: HttpResponse<IPlaylist>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get playlist() {
        return this._playlist;
    }

    set playlist(playlist: IPlaylist) {
        this._playlist = playlist;
    }
}
