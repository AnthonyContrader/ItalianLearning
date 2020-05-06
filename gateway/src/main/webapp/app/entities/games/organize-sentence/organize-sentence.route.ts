import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { OrganizeSentence } from 'app/shared/model/games/organize-sentence.model';
import { OrganizeSentenceService } from './organize-sentence.service';
import { OrganizeSentenceComponent } from './organize-sentence.component';
import { OrganizeSentenceDetailComponent } from './organize-sentence-detail.component';
import { OrganizeSentenceUpdateComponent } from './organize-sentence-update.component';
import { OrganizeSentenceDeletePopupComponent } from './organize-sentence-delete-dialog.component';
import { IOrganizeSentence } from 'app/shared/model/games/organize-sentence.model';

@Injectable({ providedIn: 'root' })
export class OrganizeSentenceResolve implements Resolve<IOrganizeSentence> {
    constructor(private service: OrganizeSentenceService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((organizeSentence: HttpResponse<OrganizeSentence>) => organizeSentence.body));
        }
        return of(new OrganizeSentence());
    }
}

export const organizeSentenceRoute: Routes = [
    {
        path: 'organize-sentence',
        component: OrganizeSentenceComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'gatewayApp.gamesOrganizeSentence.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'organize-sentence/:id/view',
        component: OrganizeSentenceDetailComponent,
        resolve: {
            organizeSentence: OrganizeSentenceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesOrganizeSentence.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'organize-sentence/new',
        component: OrganizeSentenceUpdateComponent,
        resolve: {
            organizeSentence: OrganizeSentenceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesOrganizeSentence.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'organize-sentence/:id/edit',
        component: OrganizeSentenceUpdateComponent,
        resolve: {
            organizeSentence: OrganizeSentenceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesOrganizeSentence.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const organizeSentencePopupRoute: Routes = [
    {
        path: 'organize-sentence/:id/delete',
        component: OrganizeSentenceDeletePopupComponent,
        resolve: {
            organizeSentence: OrganizeSentenceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.gamesOrganizeSentence.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
