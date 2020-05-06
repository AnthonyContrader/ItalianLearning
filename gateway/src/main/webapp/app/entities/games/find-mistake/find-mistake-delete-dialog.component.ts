import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFindMistake } from 'app/shared/model/games/find-mistake.model';
import { FindMistakeService } from './find-mistake.service';

@Component({
    selector: 'jhi-find-mistake-delete-dialog',
    templateUrl: './find-mistake-delete-dialog.component.html'
})
export class FindMistakeDeleteDialogComponent {
    findMistake: IFindMistake;

    constructor(
        private findMistakeService: FindMistakeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.findMistakeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'findMistakeListModification',
                content: 'Deleted an findMistake'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-find-mistake-delete-popup',
    template: ''
})
export class FindMistakeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ findMistake }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FindMistakeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.findMistake = findMistake;
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
