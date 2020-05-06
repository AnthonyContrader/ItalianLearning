/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { OrganizeSentenceUpdateComponent } from 'app/entities/games/organize-sentence/organize-sentence-update.component';
import { OrganizeSentenceService } from 'app/entities/games/organize-sentence/organize-sentence.service';
import { OrganizeSentence } from 'app/shared/model/games/organize-sentence.model';

describe('Component Tests', () => {
    describe('OrganizeSentence Management Update Component', () => {
        let comp: OrganizeSentenceUpdateComponent;
        let fixture: ComponentFixture<OrganizeSentenceUpdateComponent>;
        let service: OrganizeSentenceService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [OrganizeSentenceUpdateComponent]
            })
                .overrideTemplate(OrganizeSentenceUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OrganizeSentenceUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrganizeSentenceService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new OrganizeSentence(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.organizeSentence = entity;
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
                    const entity = new OrganizeSentence();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.organizeSentence = entity;
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
