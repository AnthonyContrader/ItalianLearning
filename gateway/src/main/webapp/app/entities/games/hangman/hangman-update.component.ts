import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IHangman } from 'app/shared/model/games/hangman.model';
import { HangmanService } from './hangman.service';
import { ICategory } from 'app/shared/model/games/category.model';
import { CategoryService } from 'app/entities/games/category';
import { ILevel } from 'app/shared/model/games/level.model';
import { LevelService } from 'app/entities/games/level';

@Component({
    selector: 'jhi-hangman-update',
    templateUrl: './hangman-update.component.html'
})
export class HangmanUpdateComponent implements OnInit {
    private _hangman: IHangman;
    isSaving: boolean;

    categories: ICategory[];

    levels: ILevel[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private hangmanService: HangmanService,
        private categoryService: CategoryService,
        private levelService: LevelService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ hangman }) => {
            this.hangman = hangman;
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
        if (this.hangman.id !== undefined) {
            this.subscribeToSaveResponse(this.hangmanService.update(this.hangman));
        } else {
            this.subscribeToSaveResponse(this.hangmanService.create(this.hangman));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IHangman>>) {
        result.subscribe((res: HttpResponse<IHangman>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get hangman() {
        return this._hangman;
    }

    set hangman(hangman: IHangman) {
        this._hangman = hangman;
    }
}
