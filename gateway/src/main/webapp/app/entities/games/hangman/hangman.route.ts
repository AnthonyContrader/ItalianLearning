import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Hangman } from 'app/shared/model/games/hangman.model';
import { HangmanService } from './hangman.service';
import { HangmanComponent } from './hangman.component';
import { HangmanDetailComponent } from './hangman-detail.component';
import { HangmanUpdateComponent } from './hangman-update.component';
import { HangmanDeletePopupComponent } from './hangman-delete-dialog.component';
import { IHangman } from 'app/shared/model/games/hangman.model';

@Injectable({ providedIn: 'root' })
export class HangmanResolve implements Resolve<IHangman> {
    constructor(private service: HangmanService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((hangman: HttpResponse<Hangman>) => hangman.body));
        }
        return of(new Hangman());
    }
}

export const hangmanRoute: Routes = [
    {
        path: 'hangman',
        component: HangmanComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'gatewayApp.gamesHangman.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hangman/:id/view',
        component: HangmanDetailComponent,
        resolve: {
            hangman: HangmanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesHangman.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hangman/new',
        component: HangmanUpdateComponent,
        resolve: {
            hangman: HangmanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesHangman.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hangman/:id/edit',
        component: HangmanUpdateComponent,
        resolve: {
            hangman: HangmanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesHangman.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const hangmanPopupRoute: Routes = [
    {
        path: 'hangman/:id/delete',
        component: HangmanDeletePopupComponent,
        resolve: {
            hangman: HangmanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesHangman.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
