/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { GuessPictureUpdateComponent } from 'app/entities/games/guess-picture/guess-picture-update.component';
import { GuessPictureService } from 'app/entities/games/guess-picture/guess-picture.service';
import { GuessPicture } from 'app/shared/model/games/guess-picture.model';

describe('Component Tests', () => {
    describe('GuessPicture Management Update Component', () => {
        let comp: GuessPictureUpdateComponent;
        let fixture: ComponentFixture<GuessPictureUpdateComponent>;
        let service: GuessPictureService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [GuessPictureUpdateComponent]
            })
                .overrideTemplate(GuessPictureUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GuessPictureUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GuessPictureService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GuessPicture(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.guessPicture = entity;
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
                    const entity = new GuessPicture();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.guessPicture = entity;
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
