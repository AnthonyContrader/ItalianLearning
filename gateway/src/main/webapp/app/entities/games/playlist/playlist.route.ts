import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Playlist } from 'app/shared/model/games/playlist.model';
import { PlaylistService } from './playlist.service';
import { PlaylistComponent } from './playlist.component';
import { PlaylistDetailComponent } from './playlist-detail.component';
import { PlaylistUpdateComponent } from './playlist-update.component';
import { PlaylistDeletePopupComponent } from './playlist-delete-dialog.component';
import { IPlaylist } from 'app/shared/model/games/playlist.model';

@Injectable({ providedIn: 'root' })
export class PlaylistResolve implements Resolve<IPlaylist> {
    constructor(private service: PlaylistService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((playlist: HttpResponse<Playlist>) => playlist.body));
        }
        return of(new Playlist());
    }
}

export const playlistRoute: Routes = [
    {
        path: 'playlist',
        component: PlaylistComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'gatewayApp.gamesPlaylist.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'playlist/:id/view',
        component: PlaylistDetailComponent,
        resolve: {
            playlist: PlaylistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesPlaylist.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'playlist/new',
        component: PlaylistUpdateComponent,
        resolve: {
            playlist: PlaylistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesPlaylist.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'playlist/:id/edit',
        component: PlaylistUpdateComponent,
        resolve: {
            playlist: PlaylistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesPlaylist.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const playlistPopupRoute: Routes = [
    {
        path: 'playlist/:id/delete',
        component: PlaylistDeletePopupComponent,
        resolve: {
            playlist: PlaylistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesPlaylist.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
