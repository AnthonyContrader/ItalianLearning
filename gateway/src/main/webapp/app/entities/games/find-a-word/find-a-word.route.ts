import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FindAWord } from 'app/shared/model/games/find-a-word.model';
import { FindAWordService } from './find-a-word.service';
import { FindAWordComponent } from './find-a-word.component';
import { FindAWordDetailComponent } from './find-a-word-detail.component';
import { FindAWordUpdateComponent } from './find-a-word-update.component';
import { FindAWordDeletePopupComponent } from './find-a-word-delete-dialog.component';
import { IFindAWord } from 'app/shared/model/games/find-a-word.model';

@Injectable({ providedIn: 'root' })
export class FindAWordResolve implements Resolve<IFindAWord> {
    constructor(private service: FindAWordService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((findAWord: HttpResponse<FindAWord>) => findAWord.body));
        }
        return of(new FindAWord());
    }
}

export const findAWordRoute: Routes = [
    {
        path: 'find-a-word',
        component: FindAWordComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'gatewayApp.gamesFindAWord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'find-a-word/:id/view',
        component: FindAWordDetailComponent,
        resolve: {
            findAWord: FindAWordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesFindAWord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'find-a-word/new',
        component: FindAWordUpdateComponent,
        resolve: {
            findAWord: FindAWordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesFindAWord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'find-a-word/:id/edit',
        component: FindAWordUpdateComponent,
        resolve: {
            findAWord: FindAWordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesFindAWord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const findAWordPopupRoute: Routes = [
    {
        path: 'find-a-word/:id/delete',
        component: FindAWordDeletePopupComponent,
        resolve: {
            findAWord: FindAWordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesFindAWord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
