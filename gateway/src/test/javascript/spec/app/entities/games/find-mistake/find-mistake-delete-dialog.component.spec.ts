/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GatewayTestModule } from '../../../../test.module';
import { FindMistakeDeleteDialogComponent } from 'app/entities/games/find-mistake/find-mistake-delete-dialog.component';
import { FindMistakeService } from 'app/entities/games/find-mistake/find-mistake.service';

describe('Component Tests', () => {
    describe('FindMistake Management Delete Component', () => {
        let comp: FindMistakeDeleteDialogComponent;
        let fixture: ComponentFixture<FindMistakeDeleteDialogComponent>;
        let service: FindMistakeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [FindMistakeDeleteDialogComponent]
            })
                .overrideTemplate(FindMistakeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FindMistakeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FindMistakeService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
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
            ));
        });
    });
});
