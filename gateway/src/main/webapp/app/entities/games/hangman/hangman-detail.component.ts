import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHangman } from 'app/shared/model/games/hangman.model';

@Component({
    selector: 'jhi-hangman-detail',
    templateUrl: './hangman-detail.component.html'
})
export class HangmanDetailComponent implements OnInit {
    hangman: IHangman;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ hangman }) => {
            this.hangman = hangman;
        });
    }

    previousState() {
        window.history.back();
    }
}
