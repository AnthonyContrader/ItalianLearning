import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFindAWord } from 'app/shared/model/games/find-a-word.model';
import { FindAWordService } from './find-a-word.service';

@Component({
    selector: 'jhi-find-a-word-delete-dialog',
    templateUrl: './find-a-word-delete-dialog.component.html'
})
export class FindAWordDeleteDialogComponent {
    findAWord: IFindAWord;

    constructor(private findAWordService: FindAWordService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.findAWordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'findAWordListModification',
                content: 'Deleted an findAWord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-find-a-word-delete-popup',
    template: ''
})
export class FindAWordDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ findAWord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FindAWordDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.findAWord = findAWord;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
