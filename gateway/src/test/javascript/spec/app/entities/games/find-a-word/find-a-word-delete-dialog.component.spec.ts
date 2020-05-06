/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GatewayTestModule } from '../../../../test.module';
import { FindAWordDeleteDialogComponent } from 'app/entities/games/find-a-word/find-a-word-delete-dialog.component';
import { FindAWordService } from 'app/entities/games/find-a-word/find-a-word.service';

describe('Component Tests', () => {
    describe('FindAWord Management Delete Component', () => {
        let comp: FindAWordDeleteDialogComponent;
        let fixture: ComponentFixture<FindAWordDeleteDialogComponent>;
        let service: FindAWordService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [FindAWordDeleteDialogComponent]
            })
                .overrideTemplate(FindAWordDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FindAWordDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FindAWordService);
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
