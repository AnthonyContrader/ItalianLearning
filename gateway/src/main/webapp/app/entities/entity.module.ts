import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { GatewayUserModule as UserUserModule } from './user/user/user.module';
import { GatewayCategoryModule as GamesCategoryModule } from './games/category/category.module';
import { GatewayLevelModule as GamesLevelModule } from './games/level/level.module';
import { GatewayPlaylistModule as GamesPlaylistModule } from './games/playlist/playlist.module';
import { GatewayGamePlaylistModule as GamesGamePlaylistModule } from './games/game-playlist/game-playlist.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        UserUserModule,
        GamesCategoryModule,
        GamesLevelModule,
        GamesPlaylistModule,
        GamesGamePlaylistModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayEntityModule {}
