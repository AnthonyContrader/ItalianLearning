import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { OrganizeSentenceComponentsPage, OrganizeSentenceUpdatePage } from './organize-sentence.page-object';

describe('OrganizeSentence e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let organizeSentenceUpdatePage: OrganizeSentenceUpdatePage;
    let organizeSentenceComponentsPage: OrganizeSentenceComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load OrganizeSentences', async () => {
        await navBarPage.goToEntity('organize-sentence');
        organizeSentenceComponentsPage = new OrganizeSentenceComponentsPage();
        expect(await organizeSentenceComponentsPage.getTitle()).toMatch(/gatewayApp.gamesOrganizeSentence.home.title/);
    });

    it('should load create OrganizeSentence page', async () => {
        await organizeSentenceComponentsPage.clickOnCreateButton();
        organizeSentenceUpdatePage = new OrganizeSentenceUpdatePage();
        expect(await organizeSentenceUpdatePage.getPageTitle()).toMatch(/gatewayApp.gamesOrganizeSentence.home.createOrEditLabel/);
        await organizeSentenceUpdatePage.cancel();
    });

    /* it('should create and save OrganizeSentences', async () => {
        await organizeSentenceComponentsPage.clickOnCreateButton();
        await organizeSentenceUpdatePage.setSolutionInput('solution');
        expect(await organizeSentenceUpdatePage.getSolutionInput()).toMatch('solution');
        await organizeSentenceUpdatePage.setDefinitionInput('definition');
        expect(await organizeSentenceUpdatePage.getDefinitionInput()).toMatch('definition');
        await organizeSentenceUpdatePage.setSentenceInput('sentence');
        expect(await organizeSentenceUpdatePage.getSentenceInput()).toMatch('sentence');
        await organizeSentenceUpdatePage.categorySelectLastOption();
        await organizeSentenceUpdatePage.levelSelectLastOption();
        await organizeSentenceUpdatePage.save();
        expect(await organizeSentenceUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
