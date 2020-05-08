import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { GatewayCategoryModule as GamesCategoryModule } from './games/category/category.module';
import { GatewayLevelModule as GamesLevelModule } from './games/level/level.module';
import { GatewayPlaylistModule as GamesPlaylistModule } from './games/playlist/playlist.module';
import { GatewayGamePlaylistModule as GamesGamePlaylistModule } from './games/game-playlist/game-playlist.module';
import { GatewayGuessPictureModule as GamesGuessPictureModule } from './games/guess-picture/guess-picture.module';
import { GatewayHangmanModule as GamesHangmanModule } from './games/hangman/hangman.module';
import { GatewayFindMistakeModule as GamesFindMistakeModule } from './games/find-mistake/find-mistake.module';
import { GatewayQuizModule as GamesQuizModule } from './games/quiz/quiz.module';
import { GatewayOrganizeSentenceModule as GamesOrganizeSentenceModule } from './games/organize-sentence/organize-sentence.module';
import { GatewayFindAWordModule as GamesFindAWordModule } from './games/find-a-word/find-a-word.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        GamesCategoryModule,
        GamesLevelModule,
        GamesPlaylistModule,
        GamesGamePlaylistModule,
        GamesGuessPictureModule,
        GamesHangmanModule,
        GamesFindMistakeModule,
        GamesQuizModule,
        GamesOrganizeSentenceModule,
        GamesFindAWordModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayEntityModule {}
