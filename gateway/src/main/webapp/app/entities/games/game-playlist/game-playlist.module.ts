import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    GamePlaylistComponent,
    GamePlaylistDetailComponent,
    GamePlaylistUpdateComponent,
    GamePlaylistDeletePopupComponent,
    GamePlaylistDeleteDialogComponent,
    gamePlaylistRoute,
    gamePlaylistPopupRoute
} from './';

const ENTITY_STATES = [...gamePlaylistRoute, ...gamePlaylistPopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GamePlaylistComponent,
        GamePlaylistDetailComponent,
        GamePlaylistUpdateComponent,
        GamePlaylistDeleteDialogComponent,
        GamePlaylistDeletePopupComponent
    ],
    entryComponents: [
        GamePlaylistComponent,
        GamePlaylistUpdateComponent,
        GamePlaylistDeleteDialogComponent,
        GamePlaylistDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayGamePlaylistModule {}
