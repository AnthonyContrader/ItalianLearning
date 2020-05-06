/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { HangmanDetailComponent } from 'app/entities/games/hangman/hangman-detail.component';
import { Hangman } from 'app/shared/model/games/hangman.model';

describe('Component Tests', () => {
    describe('Hangman Management Detail Component', () => {
        let comp: HangmanDetailComponent;
        let fixture: ComponentFixture<HangmanDetailComponent>;
        const route = ({ data: of({ hangman: new Hangman(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [HangmanDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(HangmanDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HangmanDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.hangman).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
