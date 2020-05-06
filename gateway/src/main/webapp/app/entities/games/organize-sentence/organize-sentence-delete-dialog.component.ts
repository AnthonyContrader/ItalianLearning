import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrganizeSentence } from 'app/shared/model/games/organize-sentence.model';
import { OrganizeSentenceService } from './organize-sentence.service';

@Component({
    selector: 'jhi-organize-sentence-delete-dialog',
    templateUrl: './organize-sentence-delete-dialog.component.html'
})
export class OrganizeSentenceDeleteDialogComponent {
    organizeSentence: IOrganizeSentence;

    constructor(
        private organizeSentenceService: OrganizeSentenceService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.organizeSentenceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'organizeSentenceListModification',
                content: 'Deleted an organizeSentence'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-organize-sentence-delete-popup',
    template: ''
})
export class OrganizeSentenceDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ organizeSentence }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OrganizeSentenceDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.organizeSentence = organizeSentence;
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
