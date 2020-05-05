import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { LevelComponentsPage, LevelUpdatePage } from './level.page-object';

describe('Level e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let levelUpdatePage: LevelUpdatePage;
    let levelComponentsPage: LevelComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Levels', async () => {
        await navBarPage.goToEntity('level');
        levelComponentsPage = new LevelComponentsPage();
        expect(await levelComponentsPage.getTitle()).toMatch(/gatewayApp.gamesLevel.home.title/);
    });

    it('should load create Level page', async () => {
        await levelComponentsPage.clickOnCreateButton();
        levelUpdatePage = new LevelUpdatePage();
        expect(await levelUpdatePage.getPageTitle()).toMatch(/gatewayApp.gamesLevel.home.createOrEditLabel/);
        await levelUpdatePage.cancel();
    });

    it('should create and save Levels', async () => {
        await levelComponentsPage.clickOnCreateButton();
        await levelUpdatePage.setNameInput('name');
        expect(await levelUpdatePage.getNameInput()).toMatch('name');
        await levelUpdatePage.setDescriptionInput('description');
        expect(await levelUpdatePage.getDescriptionInput()).toMatch('description');
        await levelUpdatePage.setScoreInput('5');
        expect(await levelUpdatePage.getScoreInput()).toMatch('5');
        await levelUpdatePage.save();
        expect(await levelUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
