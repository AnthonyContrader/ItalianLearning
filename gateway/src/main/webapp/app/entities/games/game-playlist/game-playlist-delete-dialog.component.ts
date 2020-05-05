import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGamePlaylist } from 'app/shared/model/games/game-playlist.model';
import { GamePlaylistService } from './game-playlist.service';

@Component({
    selector: 'jhi-game-playlist-delete-dialog',
    templateUrl: './game-playlist-delete-dialog.component.html'
})
export class GamePlaylistDeleteDialogComponent {
    gamePlaylist: IGamePlaylist;

    constructor(
        private gamePlaylistService: GamePlaylistService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.gamePlaylistService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'gamePlaylistListModification',
                content: 'Deleted an gamePlaylist'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-game-playlist-delete-popup',
    template: ''
})
export class GamePlaylistDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ gamePlaylist }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GamePlaylistDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.gamePlaylist = gamePlaylist;
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
