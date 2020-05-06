import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrganizeSentence } from 'app/shared/model/games/organize-sentence.model';

@Component({
    selector: 'jhi-organize-sentence-detail',
    templateUrl: './organize-sentence-detail.component.html'
})
export class OrganizeSentenceDetailComponent implements OnInit {
    organizeSentence: IOrganizeSentence;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ organizeSentence }) => {
            this.organizeSentence = organizeSentence;
        });
    }

    previousState() {
        window.history.back();
    }
}
