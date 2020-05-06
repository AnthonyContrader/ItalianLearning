import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IFindMistake } from 'app/shared/model/games/find-mistake.model';
import { FindMistakeService } from './find-mistake.service';
import { ICategory } from 'app/shared/model/games/category.model';
import { CategoryService } from 'app/entities/games/category';
import { ILevel } from 'app/shared/model/games/level.model';
import { LevelService } from 'app/entities/games/level';

@Component({
    selector: 'jhi-find-mistake-update',
    templateUrl: './find-mistake-update.component.html'
})
export class FindMistakeUpdateComponent implements OnInit {
    private _findMistake: IFindMistake;
    isSaving: boolean;

    categories: ICategory[];

    levels: ILevel[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private findMistakeService: FindMistakeService,
        private categoryService: CategoryService,
        private levelService: LevelService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ findMistake }) => {
            this.findMistake = findMistake;
        });
        this.categoryService.query().subscribe(
            (res: HttpResponse<ICategory[]>) => {
                this.categories = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.levelService.query().subscribe(
            (res: HttpResponse<ILevel[]>) => {
                this.levels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.findMistake.id !== undefined) {
            this.subscribeToSaveResponse(this.findMistakeService.update(this.findMistake));
        } else {
            this.subscribeToSaveResponse(this.findMistakeService.create(this.findMistake));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFindMistake>>) {
        result.subscribe((res: HttpResponse<IFindMistake>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCategoryById(index: number, item: ICategory) {
        return item.id;
    }

    trackLevelById(index: number, item: ILevel) {
        return item.id;
    }
    get findMistake() {
        return this._findMistake;
    }

    set findMistake(findMistake: IFindMistake) {
        this._findMistake = findMistake;
    }
}
