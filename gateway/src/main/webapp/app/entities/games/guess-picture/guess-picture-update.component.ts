import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IGuessPicture } from 'app/shared/model/games/guess-picture.model';
import { GuessPictureService } from './guess-picture.service';
import { ILevel } from 'app/shared/model/games/level.model';
import { LevelService } from 'app/entities/games/level';
import { ICategory } from 'app/shared/model/games/category.model';
import { CategoryService } from 'app/entities/games/category';

@Component({
    selector: 'jhi-guess-picture-update',
    templateUrl: './guess-picture-update.component.html'
})
export class GuessPictureUpdateComponent implements OnInit {
    private _guessPicture: IGuessPicture;
    isSaving: boolean;

    levels: ILevel[];

    categories: ICategory[];

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private guessPictureService: GuessPictureService,
        private levelService: LevelService,
        private categoryService: CategoryService,
        private elementRef: ElementRef,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ guessPicture }) => {
            this.guessPicture = guessPicture;
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

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.guessPicture, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.guessPicture.id !== undefined) {
            this.subscribeToSaveResponse(this.guessPictureService.update(this.guessPicture));
        } else {
            this.subscribeToSaveResponse(this.guessPictureService.create(this.guessPicture));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGuessPicture>>) {
        result.subscribe((res: HttpResponse<IGuessPicture>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get guessPicture() {
        return this._guessPicture;
    }

    set guessPicture(guessPicture: IGuessPicture) {
        this._guessPicture = guessPicture;
    }
}
