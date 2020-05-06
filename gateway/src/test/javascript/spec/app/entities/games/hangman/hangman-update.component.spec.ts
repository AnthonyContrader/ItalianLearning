/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { HangmanUpdateComponent } from 'app/entities/games/hangman/hangman-update.component';
import { HangmanService } from 'app/entities/games/hangman/hangman.service';
import { Hangman } from 'app/shared/model/games/hangman.model';

describe('Component Tests', () => {
    describe('Hangman Management Update Component', () => {
        let comp: HangmanUpdateComponent;
        let fixture: ComponentFixture<HangmanUpdateComponent>;
        let service: HangmanService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [HangmanUpdateComponent]
            })
                .overrideTemplate(HangmanUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(HangmanUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HangmanService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Hangman(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.hangman = entity;
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
                    const entity = new Hangman();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.hangman = entity;
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
