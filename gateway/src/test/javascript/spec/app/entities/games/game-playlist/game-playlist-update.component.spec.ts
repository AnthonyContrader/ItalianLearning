/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { GamePlaylistUpdateComponent } from 'app/entities/games/game-playlist/game-playlist-update.component';
import { GamePlaylistService } from 'app/entities/games/game-playlist/game-playlist.service';
import { GamePlaylist } from 'app/shared/model/games/game-playlist.model';

describe('Component Tests', () => {
    describe('GamePlaylist Management Update Component', () => {
        let comp: GamePlaylistUpdateComponent;
        let fixture: ComponentFixture<GamePlaylistUpdateComponent>;
        let service: GamePlaylistService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [GamePlaylistUpdateComponent]
            })
                .overrideTemplate(GamePlaylistUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GamePlaylistUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GamePlaylistService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GamePlaylist(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.gamePlaylist = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GamePlaylist();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.gamePlaylist = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
