import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFindAWord } from 'app/shared/model/games/find-a-word.model';

@Component({
    selector: 'jhi-find-a-word-detail',
    templateUrl: './find-a-word-detail.component.html'
})
export class FindAWordDetailComponent implements OnInit {
    findAWord: IFindAWord;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ findAWord }) => {
            this.findAWord = findAWord;
        });
    }

    previousState() {
        window.history.back();
    }
}
