/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { GamePlaylistDetailComponent } from 'app/entities/games/game-playlist/game-playlist-detail.component';
import { GamePlaylist } from 'app/shared/model/games/game-playlist.model';

describe('Component Tests', () => {
    describe('GamePlaylist Management Detail Component', () => {
        let comp: GamePlaylistDetailComponent;
        let fixture: ComponentFixture<GamePlaylistDetailComponent>;
        const route = ({ data: of({ gamePlaylist: new GamePlaylist(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [GamePlaylistDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GamePlaylistDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GamePlaylistDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.gamePlaylist).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
