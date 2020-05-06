/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { FindMistakeUpdateComponent } from 'app/entities/games/find-mistake/find-mistake-update.component';
import { FindMistakeService } from 'app/entities/games/find-mistake/find-mistake.service';
import { FindMistake } from 'app/shared/model/games/find-mistake.model';

describe('Component Tests', () => {
    describe('FindMistake Management Update Component', () => {
        let comp: FindMistakeUpdateComponent;
        let fixture: ComponentFixture<FindMistakeUpdateComponent>;
        let service: FindMistakeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [FindMistakeUpdateComponent]
            })
                .overrideTemplate(FindMistakeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FindMistakeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FindMistakeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FindMistake(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.findMistake = entity;
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
                    const entity = new FindMistake();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.findMistake = entity;
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
