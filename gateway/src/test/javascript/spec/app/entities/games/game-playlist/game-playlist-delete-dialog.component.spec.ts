/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GatewayTestModule } from '../../../../test.module';
import { GamePlaylistDeleteDialogComponent } from 'app/entities/games/game-playlist/game-playlist-delete-dialog.component';
import { GamePlaylistService } from 'app/entities/games/game-playlist/game-playlist.service';

describe('Component Tests', () => {
    describe('GamePlaylist Management Delete Component', () => {
        let comp: GamePlaylistDeleteDialogComponent;
        let fixture: ComponentFixture<GamePlaylistDeleteDialogComponent>;
        let service: GamePlaylistService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [GamePlaylistDeleteDialogComponent]
            })
                .overrideTemplate(GamePlaylistDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GamePlaylistDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GamePlaylistService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
