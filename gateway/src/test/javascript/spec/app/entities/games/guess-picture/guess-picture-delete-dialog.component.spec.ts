/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GatewayTestModule } from '../../../../test.module';
import { GuessPictureDeleteDialogComponent } from 'app/entities/games/guess-picture/guess-picture-delete-dialog.component';
import { GuessPictureService } from 'app/entities/games/guess-picture/guess-picture.service';

describe('Component Tests', () => {
    describe('GuessPicture Management Delete Component', () => {
        let comp: GuessPictureDeleteDialogComponent;
        let fixture: ComponentFixture<GuessPictureDeleteDialogComponent>;
        let service: GuessPictureService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [GuessPictureDeleteDialogComponent]
            })
                .overrideTemplate(GuessPictureDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GuessPictureDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GuessPictureService);
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
