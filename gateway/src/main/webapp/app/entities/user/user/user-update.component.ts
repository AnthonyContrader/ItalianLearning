import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IUser } from 'app/shared/model/user/user.model';
import { UserService } from './user.service';

@Component({
    selector: 'jhi-user-update',
    templateUrl: './user-update.component.html'
})
export class UserUpdateComponent implements OnInit {
    private _user: IUser;
    isSaving: boolean;

    constructor(private userService: UserService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ user }) => {
            this.user = user;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.user.id !== undefined) {
            this.subscribeToSaveResponse(this.userService.update(this.user));
        } else {
            this.subscribeToSaveResponse(this.userService.create(this.user));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUser>>) {
        result.subscribe((res: HttpResponse<IUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get user() {
        return this._user;
    }

    set user(user: IUser) {
        this._user = user;
    }
}
