import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFindMistake } from 'app/shared/model/games/find-mistake.model';

@Component({
    selector: 'jhi-find-mistake-detail',
    templateUrl: './find-mistake-detail.component.html'
})
export class FindMistakeDetailComponent implements OnInit {
    findMistake: IFindMistake;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ findMistake }) => {
            this.findMistake = findMistake;
        });
    }

    previousState() {
        window.history.back();
    }
}
