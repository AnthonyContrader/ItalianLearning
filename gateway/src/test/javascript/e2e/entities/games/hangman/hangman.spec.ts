import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { HangmanComponentsPage, HangmanUpdatePage } from './hangman.page-object';

describe('Hangman e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let hangmanUpdatePage: HangmanUpdatePage;
    let hangmanComponentsPage: HangmanComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Hangmen', async () => {
        await navBarPage.goToEntity('hangman');
        hangmanComponentsPage = new HangmanComponentsPage();
        expect(await hangmanComponentsPage.getTitle()).toMatch(/gatewayApp.gamesHangman.home.title/);
    });

    it('should load create Hangman page', async () => {
        await hangmanComponentsPage.clickOnCreateButton();
        hangmanUpdatePage = new HangmanUpdatePage();
        expect(await hangmanUpdatePage.getPageTitle()).toMatch(/gatewayApp.gamesHangman.home.createOrEditLabel/);
        await hangmanUpdatePage.cancel();
    });

    /* it('should create and save Hangmen', async () => {
        await hangmanComponentsPage.clickOnCreateButton();
        await hangmanUpdatePage.setSolutionInput('solution');
        expect(await hangmanUpdatePage.getSolutionInput()).toMatch('solution');
        await hangmanUpdatePage.setDefinitionInput('definition');
        expect(await hangmanUpdatePage.getDefinitionInput()).toMatch('definition');
        await hangmanUpdatePage.setSentenceInput('sentence');
        expect(await hangmanUpdatePage.getSentenceInput()).toMatch('sentence');
        await hangmanUpdatePage.categorySelectLastOption();
        await hangmanUpdatePage.levelSelectLastOption();
        await hangmanUpdatePage.save();
        expect(await hangmanUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
