import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IFindAWord } from 'app/shared/model/games/find-a-word.model';
import { FindAWordService } from './find-a-word.service';
import { ICategory } from 'app/shared/model/games/category.model';
import { CategoryService } from 'app/entities/games/category';
import { ILevel } from 'app/shared/model/games/level.model';
import { LevelService } from 'app/entities/games/level';

@Component({
    selector: 'jhi-find-a-word-update',
    templateUrl: './find-a-word-update.component.html'
})
export class FindAWordUpdateComponent implements OnInit {
    private _findAWord: IFindAWord;
    isSaving: boolean;

    categories: ICategory[];

    levels: ILevel[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private findAWordService: FindAWordService,
        private categoryService: CategoryService,
        private levelService: LevelService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ findAWord }) => {
            this.findAWord = findAWord;
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
        if (this.findAWord.id !== undefined) {
            this.subscribeToSaveResponse(this.findAWordService.update(this.findAWord));
        } else {
            this.subscribeToSaveResponse(this.findAWordService.create(this.findAWord));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFindAWord>>) {
        result.subscribe((res: HttpResponse<IFindAWord>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get findAWord() {
        return this._findAWord;
    }

    set findAWord(findAWord: IFindAWord) {
        this._findAWord = findAWord;
    }
}
