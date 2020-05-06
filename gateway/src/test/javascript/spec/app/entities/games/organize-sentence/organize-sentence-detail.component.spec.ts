/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { OrganizeSentenceDetailComponent } from 'app/entities/games/organize-sentence/organize-sentence-detail.component';
import { OrganizeSentence } from 'app/shared/model/games/organize-sentence.model';

describe('Component Tests', () => {
    describe('OrganizeSentence Management Detail Component', () => {
        let comp: OrganizeSentenceDetailComponent;
        let fixture: ComponentFixture<OrganizeSentenceDetailComponent>;
        const route = ({ data: of({ organizeSentence: new OrganizeSentence(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [OrganizeSentenceDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OrganizeSentenceDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OrganizeSentenceDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.organizeSentence).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
