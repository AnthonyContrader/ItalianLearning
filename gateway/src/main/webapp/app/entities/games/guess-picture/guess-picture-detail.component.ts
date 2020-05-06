import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IGuessPicture } from 'app/shared/model/games/guess-picture.model';

@Component({
    selector: 'jhi-guess-picture-detail',
    templateUrl: './guess-picture-detail.component.html'
})
export class GuessPictureDetailComponent implements OnInit {
    guessPicture: IGuessPicture;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ guessPicture }) => {
            this.guessPicture = guessPicture;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
