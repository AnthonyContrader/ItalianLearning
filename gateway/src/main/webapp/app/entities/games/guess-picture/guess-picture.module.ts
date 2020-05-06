import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    GuessPictureComponent,
    GuessPictureDetailComponent,
    GuessPictureUpdateComponent,
    GuessPictureDeletePopupComponent,
    GuessPictureDeleteDialogComponent,
    guessPictureRoute,
    guessPicturePopupRoute
} from './';

const ENTITY_STATES = [...guessPictureRoute, ...guessPicturePopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GuessPictureComponent,
        GuessPictureDetailComponent,
        GuessPictureUpdateComponent,
        GuessPictureDeleteDialogComponent,
        GuessPictureDeletePopupComponent
    ],
    entryComponents: [
        GuessPictureComponent,
        GuessPictureUpdateComponent,
        GuessPictureDeleteDialogComponent,
        GuessPictureDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayGuessPictureModule {}
