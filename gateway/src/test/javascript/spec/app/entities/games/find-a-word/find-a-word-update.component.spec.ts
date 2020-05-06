/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { FindAWordUpdateComponent } from 'app/entities/games/find-a-word/find-a-word-update.component';
import { FindAWordService } from 'app/entities/games/find-a-word/find-a-word.service';
import { FindAWord } from 'app/shared/model/games/find-a-word.model';

describe('Component Tests', () => {
    describe('FindAWord Management Update Component', () => {
        let comp: FindAWordUpdateComponent;
        let fixture: ComponentFixture<FindAWordUpdateComponent>;
        let service: FindAWordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [FindAWordUpdateComponent]
            })
                .overrideTemplate(FindAWordUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FindAWordUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FindAWordService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FindAWord(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.findAWord = entity;
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
                    const entity = new FindAWord();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.findAWord = entity;
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
