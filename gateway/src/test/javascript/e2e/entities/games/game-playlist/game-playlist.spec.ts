import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { GamePlaylistComponentsPage, GamePlaylistUpdatePage } from './game-playlist.page-object';

describe('GamePlaylist e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let gamePlaylistUpdatePage: GamePlaylistUpdatePage;
    let gamePlaylistComponentsPage: GamePlaylistComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load GamePlaylists', async () => {
        await navBarPage.goToEntity('game-playlist');
        gamePlaylistComponentsPage = new GamePlaylistComponentsPage();
        expect(await gamePlaylistComponentsPage.getTitle()).toMatch(/gatewayApp.gamesGamePlaylist.home.title/);
    });

    it('should load create GamePlaylist page', async () => {
        await gamePlaylistComponentsPage.clickOnCreateButton();
        gamePlaylistUpdatePage = new GamePlaylistUpdatePage();
        expect(await gamePlaylistUpdatePage.getPageTitle()).toMatch(/gatewayApp.gamesGamePlaylist.home.createOrEditLabel/);
        await gamePlaylistUpdatePage.cancel();
    });

    /* it('should create and save GamePlaylists', async () => {
        await gamePlaylistComponentsPage.clickOnCreateButton();
        await gamePlaylistUpdatePage.setIdGameInput('5');
        expect(await gamePlaylistUpdatePage.getIdGameInput()).toMatch('5');
        await gamePlaylistUpdatePage.setTypeGameInput('typeGame');
        expect(await gamePlaylistUpdatePage.getTypeGameInput()).toMatch('typeGame');
        await gamePlaylistUpdatePage.playlistSelectLastOption();
        await gamePlaylistUpdatePage.save();
        expect(await gamePlaylistUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
