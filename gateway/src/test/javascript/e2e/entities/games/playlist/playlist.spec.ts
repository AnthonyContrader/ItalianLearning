import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { PlaylistComponentsPage, PlaylistUpdatePage } from './playlist.page-object';

describe('Playlist e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let playlistUpdatePage: PlaylistUpdatePage;
    let playlistComponentsPage: PlaylistComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Playlists', async () => {
        await navBarPage.goToEntity('playlist');
        playlistComponentsPage = new PlaylistComponentsPage();
        expect(await playlistComponentsPage.getTitle()).toMatch(/gatewayApp.gamesPlaylist.home.title/);
    });

    it('should load create Playlist page', async () => {
        await playlistComponentsPage.clickOnCreateButton();
        playlistUpdatePage = new PlaylistUpdatePage();
        expect(await playlistUpdatePage.getPageTitle()).toMatch(/gatewayApp.gamesPlaylist.home.createOrEditLabel/);
        await playlistUpdatePage.cancel();
    });

    it('should create and save Playlists', async () => {
        await playlistComponentsPage.clickOnCreateButton();
        await playlistUpdatePage.setNameInput('name');
        expect(await playlistUpdatePage.getNameInput()).toMatch('name');
        await playlistUpdatePage.setDescriptionInput('description');
        expect(await playlistUpdatePage.getDescriptionInput()).toMatch('description');
        await playlistUpdatePage.save();
        expect(await playlistUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
