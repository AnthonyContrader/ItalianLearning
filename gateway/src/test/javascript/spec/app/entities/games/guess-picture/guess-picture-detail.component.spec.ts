/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { GuessPictureDetailComponent } from 'app/entities/games/guess-picture/guess-picture-detail.component';
import { GuessPicture } from 'app/shared/model/games/guess-picture.model';

describe('Component Tests', () => {
    describe('GuessPicture Management Detail Component', () => {
        let comp: GuessPictureDetailComponent;
        let fixture: ComponentFixture<GuessPictureDetailComponent>;
        const route = ({ data: of({ guessPicture: new GuessPicture(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [GuessPictureDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GuessPictureDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GuessPictureDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.guessPicture).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
