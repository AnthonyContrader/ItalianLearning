import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Level } from 'app/shared/model/games/level.model';
import { LevelService } from './level.service';
import { LevelComponent } from './level.component';
import { LevelDetailComponent } from './level-detail.component';
import { LevelUpdateComponent } from './level-update.component';
import { LevelDeletePopupComponent } from './level-delete-dialog.component';
import { ILevel } from 'app/shared/model/games/level.model';

@Injectable({ providedIn: 'root' })
export class LevelResolve implements Resolve<ILevel> {
    constructor(private service: LevelService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((level: HttpResponse<Level>) => level.body));
        }
        return of(new Level());
    }
}

export const levelRoute: Routes = [
    {
        path: 'level',
        component: LevelComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'gatewayApp.gamesLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level/:id/view',
        component: LevelDetailComponent,
        resolve: {
            level: LevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level/new',
        component: LevelUpdateComponent,
        resolve: {
            level: LevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level/:id/edit',
        component: LevelUpdateComponent,
        resolve: {
            level: LevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const levelPopupRoute: Routes = [
    {
        path: 'level/:id/delete',
        component: LevelDeletePopupComponent,
        resolve: {
            level: LevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesLevel.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
