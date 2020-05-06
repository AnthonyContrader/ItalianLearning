import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    HangmanComponent,
    HangmanDetailComponent,
    HangmanUpdateComponent,
    HangmanDeletePopupComponent,
    HangmanDeleteDialogComponent,
    hangmanRoute,
    hangmanPopupRoute
} from './';

const ENTITY_STATES = [...hangmanRoute, ...hangmanPopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        HangmanComponent,
        HangmanDetailComponent,
        HangmanUpdateComponent,
        HangmanDeleteDialogComponent,
        HangmanDeletePopupComponent
    ],
    entryComponents: [HangmanComponent, HangmanUpdateComponent, HangmanDeleteDialogComponent, HangmanDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayHangmanModule {}
