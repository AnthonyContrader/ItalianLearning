/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { FindAWordDetailComponent } from 'app/entities/games/find-a-word/find-a-word-detail.component';
import { FindAWord } from 'app/shared/model/games/find-a-word.model';

describe('Component Tests', () => {
    describe('FindAWord Management Detail Component', () => {
        let comp: FindAWordDetailComponent;
        let fixture: ComponentFixture<FindAWordDetailComponent>;
        const route = ({ data: of({ findAWord: new FindAWord(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [FindAWordDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FindAWordDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FindAWordDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.findAWord).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
