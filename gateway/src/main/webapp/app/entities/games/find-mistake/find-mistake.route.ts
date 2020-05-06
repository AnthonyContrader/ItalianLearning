import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FindMistake } from 'app/shared/model/games/find-mistake.model';
import { FindMistakeService } from './find-mistake.service';
import { FindMistakeComponent } from './find-mistake.component';
import { FindMistakeDetailComponent } from './find-mistake-detail.component';
import { FindMistakeUpdateComponent } from './find-mistake-update.component';
import { FindMistakeDeletePopupComponent } from './find-mistake-delete-dialog.component';
import { IFindMistake } from 'app/shared/model/games/find-mistake.model';

@Injectable({ providedIn: 'root' })
export class FindMistakeResolve implements Resolve<IFindMistake> {
    constructor(private service: FindMistakeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((findMistake: HttpResponse<FindMistake>) => findMistake.body));
        }
        return of(new FindMistake());
    }
}

export const findMistakeRoute: Routes = [
    {
        path: 'find-mistake',
        component: FindMistakeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'gatewayApp.gamesFindMistake.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'find-mistake/:id/view',
        component: FindMistakeDetailComponent,
        resolve: {
            findMistake: FindMistakeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesFindMistake.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'find-mistake/new',
        component: FindMistakeUpdateComponent,
        resolve: {
            findMistake: FindMistakeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesFindMistake.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'find-mistake/:id/edit',
        component: FindMistakeUpdateComponent,
        resolve: {
            findMistake: FindMistakeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesFindMistake.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const findMistakePopupRoute: Routes = [
    {
        path: 'find-mistake/:id/delete',
        component: FindMistakeDeletePopupComponent,
        resolve: {
            findMistake: FindMistakeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesFindMistake.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
