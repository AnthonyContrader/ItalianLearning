import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    UsersComponent,
    UsersDetailComponent,
    UsersUpdateComponent,
    UsersDeletePopupComponent,
    UsersDeleteDialogComponent,
    usersRoute,
    usersPopupRoute
} from './';

const ENTITY_STATES = [...usersRoute, ...usersPopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [UsersComponent, UsersDetailComponent, UsersUpdateComponent, UsersDeleteDialogComponent, UsersDeletePopupComponent],
    entryComponents: [UsersComponent, UsersUpdateComponent, UsersDeleteDialogComponent, UsersDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayUsersModule {}
