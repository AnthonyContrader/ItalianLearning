import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    FindAWordComponent,
    FindAWordDetailComponent,
    FindAWordUpdateComponent,
    FindAWordDeletePopupComponent,
    FindAWordDeleteDialogComponent,
    findAWordRoute,
    findAWordPopupRoute
} from './';

const ENTITY_STATES = [...findAWordRoute, ...findAWordPopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FindAWordComponent,
        FindAWordDetailComponent,
        FindAWordUpdateComponent,
        FindAWordDeleteDialogComponent,
        FindAWordDeletePopupComponent
    ],
    entryComponents: [FindAWordComponent, FindAWordUpdateComponent, FindAWordDeleteDialogComponent, FindAWordDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayFindAWordModule {}
