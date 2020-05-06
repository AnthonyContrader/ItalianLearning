import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    FindMistakeComponent,
    FindMistakeDetailComponent,
    FindMistakeUpdateComponent,
    FindMistakeDeletePopupComponent,
    FindMistakeDeleteDialogComponent,
    findMistakeRoute,
    findMistakePopupRoute
} from './';

const ENTITY_STATES = [...findMistakeRoute, ...findMistakePopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FindMistakeComponent,
        FindMistakeDetailComponent,
        FindMistakeUpdateComponent,
        FindMistakeDeleteDialogComponent,
        FindMistakeDeletePopupComponent
    ],
    entryComponents: [FindMistakeComponent, FindMistakeUpdateComponent, FindMistakeDeleteDialogComponent, FindMistakeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayFindMistakeModule {}
