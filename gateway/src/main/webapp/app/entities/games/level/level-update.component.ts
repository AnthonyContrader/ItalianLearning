import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ILevel } from 'app/shared/model/games/level.model';
import { LevelService } from './level.service';

@Component({
    selector: 'jhi-level-update',
    templateUrl: './level-update.component.html'
})
export class LevelUpdateComponent implements OnInit {
    private _level: ILevel;
    isSaving: boolean;

    constructor(private levelService: LevelService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ level }) => {
            this.level = level;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.level.id !== undefined) {
            this.subscribeToSaveResponse(this.levelService.update(this.level));
        } else {
            this.subscribeToSaveResponse(this.levelService.create(this.level));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILevel>>) {
        result.subscribe((res: HttpResponse<ILevel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get level() {
        return this._level;
    }

    set level(level: ILevel) {
        this._level = level;
    }
}
