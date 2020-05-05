import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    LevelComponent,
    LevelDetailComponent,
    LevelUpdateComponent,
    LevelDeletePopupComponent,
    LevelDeleteDialogComponent,
    levelRoute,
    levelPopupRoute
} from './';

const ENTITY_STATES = [...levelRoute, ...levelPopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [LevelComponent, LevelDetailComponent, LevelUpdateComponent, LevelDeleteDialogComponent, LevelDeletePopupComponent],
    entryComponents: [LevelComponent, LevelUpdateComponent, LevelDeleteDialogComponent, LevelDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayLevelModule {}
