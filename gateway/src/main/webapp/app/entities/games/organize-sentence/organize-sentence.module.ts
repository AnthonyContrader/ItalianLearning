import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    OrganizeSentenceComponent,
    OrganizeSentenceDetailComponent,
    OrganizeSentenceUpdateComponent,
    OrganizeSentenceDeletePopupComponent,
    OrganizeSentenceDeleteDialogComponent,
    organizeSentenceRoute,
    organizeSentencePopupRoute
} from './';

const ENTITY_STATES = [...organizeSentenceRoute, ...organizeSentencePopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OrganizeSentenceComponent,
        OrganizeSentenceDetailComponent,
        OrganizeSentenceUpdateComponent,
        OrganizeSentenceDeleteDialogComponent,
        OrganizeSentenceDeletePopupComponent
    ],
    entryComponents: [
        OrganizeSentenceComponent,
        OrganizeSentenceUpdateComponent,
        OrganizeSentenceDeleteDialogComponent,
        OrganizeSentenceDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayOrganizeSentenceModule {}
