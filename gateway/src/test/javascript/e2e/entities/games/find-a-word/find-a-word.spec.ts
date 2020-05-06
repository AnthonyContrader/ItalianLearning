import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { FindAWordComponentsPage, FindAWordUpdatePage } from './find-a-word.page-object';

describe('FindAWord e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let findAWordUpdatePage: FindAWordUpdatePage;
    let findAWordComponentsPage: FindAWordComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load FindAWords', async () => {
        await navBarPage.goToEntity('find-a-word');
        findAWordComponentsPage = new FindAWordComponentsPage();
        expect(await findAWordComponentsPage.getTitle()).toMatch(/gatewayApp.gamesFindAWord.home.title/);
    });

    it('should load create FindAWord page', async () => {
        await findAWordComponentsPage.clickOnCreateButton();
        findAWordUpdatePage = new FindAWordUpdatePage();
        expect(await findAWordUpdatePage.getPageTitle()).toMatch(/gatewayApp.gamesFindAWord.home.createOrEditLabel/);
        await findAWordUpdatePage.cancel();
    });

    /* it('should create and save FindAWords', async () => {
        await findAWordComponentsPage.clickOnCreateButton();
        await findAWordUpdatePage.setSolutionInput('solution');
        expect(await findAWordUpdatePage.getSolutionInput()).toMatch('solution');
        await findAWordUpdatePage.setDefinitionInput('definition');
        expect(await findAWordUpdatePage.getDefinitionInput()).toMatch('definition');
        await findAWordUpdatePage.setSentenceInput('sentence');
        expect(await findAWordUpdatePage.getSentenceInput()).toMatch('sentence');
        await findAWordUpdatePage.categorySelectLastOption();
        await findAWordUpdatePage.levelSelectLastOption();
        await findAWordUpdatePage.save();
        expect(await findAWordUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
