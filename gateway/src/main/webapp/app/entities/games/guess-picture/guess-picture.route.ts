import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GuessPicture } from 'app/shared/model/games/guess-picture.model';
import { GuessPictureService } from './guess-picture.service';
import { GuessPictureComponent } from './guess-picture.component';
import { GuessPictureDetailComponent } from './guess-picture-detail.component';
import { GuessPictureUpdateComponent } from './guess-picture-update.component';
import { GuessPictureDeletePopupComponent } from './guess-picture-delete-dialog.component';
import { IGuessPicture } from 'app/shared/model/games/guess-picture.model';

@Injectable({ providedIn: 'root' })
export class GuessPictureResolve implements Resolve<IGuessPicture> {
    constructor(private service: GuessPictureService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((guessPicture: HttpResponse<GuessPicture>) => guessPicture.body));
        }
        return of(new GuessPicture());
    }
}

export const guessPictureRoute: Routes = [
    {
        path: 'guess-picture',
        component: GuessPictureComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'gatewayApp.gamesGuessPicture.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'guess-picture/:id/view',
        component: GuessPictureDetailComponent,
        resolve: {
            guessPicture: GuessPictureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesGuessPicture.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'guess-picture/new',
        component: GuessPictureUpdateComponent,
        resolve: {
            guessPicture: GuessPictureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesGuessPicture.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'guess-picture/:id/edit',
        component: GuessPictureUpdateComponent,
        resolve: {
            guessPicture: GuessPictureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesGuessPicture.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const guessPicturePopupRoute: Routes = [
    {
        path: 'guess-picture/:id/delete',
        component: GuessPictureDeletePopupComponent,
        resolve: {
            guessPicture: GuessPictureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesGuessPicture.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
