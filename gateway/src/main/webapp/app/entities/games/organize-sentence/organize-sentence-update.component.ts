import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IOrganizeSentence } from 'app/shared/model/games/organize-sentence.model';
import { OrganizeSentenceService } from './organize-sentence.service';
import { ILevel } from 'app/shared/model/games/level.model';
import { LevelService } from 'app/entities/games/level';
import { ICategory } from 'app/shared/model/games/category.model';
import { CategoryService } from 'app/entities/games/category';

@Component({
    selector: 'jhi-organize-sentence-update',
    templateUrl: './organize-sentence-update.component.html'
})
export class OrganizeSentenceUpdateComponent implements OnInit {
    private _organizeSentence: IOrganizeSentence;
    isSaving: boolean;

    levels: ILevel[];

    categories: ICategory[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private organizeSentenceService: OrganizeSentenceService,
        private levelService: LevelService,
        private categoryService: CategoryService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ organizeSentence }) => {
            this.organizeSentence = organizeSentence;
        });
        this.levelService.query().subscribe(
            (res: HttpResponse<ILevel[]>) => {
                this.levels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.categoryService.query().subscribe(
            (res: HttpResponse<ICategory[]>) => {
                this.categories = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.organizeSentence.id !== undefined) {
            this.subscribeToSaveResponse(this.organizeSentenceService.update(this.organizeSentence));
        } else {
            this.subscribeToSaveResponse(this.organizeSentenceService.create(this.organizeSentence));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOrganizeSentence>>) {
        result.subscribe((res: HttpResponse<IOrganizeSentence>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackLevelById(index: number, item: ILevel) {
        return item.id;
    }

    trackCategoryById(index: number, item: ICategory) {
        return item.id;
    }
    get organizeSentence() {
        return this._organizeSentence;
    }

    set organizeSentence(organizeSentence: IOrganizeSentence) {
        this._organizeSentence = organizeSentence;
    }
}
