import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGuessPicture } from 'app/shared/model/games/guess-picture.model';
import { GuessPictureService } from './guess-picture.service';

@Component({
    selector: 'jhi-guess-picture-delete-dialog',
    templateUrl: './guess-picture-delete-dialog.component.html'
})
export class GuessPictureDeleteDialogComponent {
    guessPicture: IGuessPicture;

    constructor(
        private guessPictureService: GuessPictureService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.guessPictureService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'guessPictureListModification',
                content: 'Deleted an guessPicture'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-guess-picture-delete-popup',
    template: ''
})
export class GuessPictureDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ guessPicture }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GuessPictureDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.guessPicture = guessPicture;
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
