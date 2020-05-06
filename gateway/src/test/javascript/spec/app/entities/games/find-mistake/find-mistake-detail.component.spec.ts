/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { FindMistakeDetailComponent } from 'app/entities/games/find-mistake/find-mistake-detail.component';
import { FindMistake } from 'app/shared/model/games/find-mistake.model';

describe('Component Tests', () => {
    describe('FindMistake Management Detail Component', () => {
        let comp: FindMistakeDetailComponent;
        let fixture: ComponentFixture<FindMistakeDetailComponent>;
        const route = ({ data: of({ findMistake: new FindMistake(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [FindMistakeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FindMistakeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FindMistakeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.findMistake).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
