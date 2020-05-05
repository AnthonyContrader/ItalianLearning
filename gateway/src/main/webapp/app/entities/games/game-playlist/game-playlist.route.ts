import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GamePlaylist } from 'app/shared/model/games/game-playlist.model';
import { GamePlaylistService } from './game-playlist.service';
import { GamePlaylistComponent } from './game-playlist.component';
import { GamePlaylistDetailComponent } from './game-playlist-detail.component';
import { GamePlaylistUpdateComponent } from './game-playlist-update.component';
import { GamePlaylistDeletePopupComponent } from './game-playlist-delete-dialog.component';
import { IGamePlaylist } from 'app/shared/model/games/game-playlist.model';

@Injectable({ providedIn: 'root' })
export class GamePlaylistResolve implements Resolve<IGamePlaylist> {
    constructor(private service: GamePlaylistService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((gamePlaylist: HttpResponse<GamePlaylist>) => gamePlaylist.body));
        }
        return of(new GamePlaylist());
    }
}

export const gamePlaylistRoute: Routes = [
    {
        path: 'game-playlist',
        component: GamePlaylistComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'gatewayApp.gamesGamePlaylist.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'game-playlist/:id/view',
        component: GamePlaylistDetailComponent,
        resolve: {
            gamePlaylist: GamePlaylistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesGamePlaylist.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'game-playlist/new',
        component: GamePlaylistUpdateComponent,
        resolve: {
            gamePlaylist: GamePlaylistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesGamePlaylist.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'game-playlist/:id/edit',
        component: GamePlaylistUpdateComponent,
        resolve: {
            gamePlaylist: GamePlaylistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesGamePlaylist.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const gamePlaylistPopupRoute: Routes = [
    {
        path: 'game-playlist/:id/delete',
        component: GamePlaylistDeletePopupComponent,
        resolve: {
            gamePlaylist: GamePlaylistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesGamePlaylist.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
