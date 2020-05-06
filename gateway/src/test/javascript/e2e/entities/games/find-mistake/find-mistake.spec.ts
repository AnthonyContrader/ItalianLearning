import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { FindMistakeComponentsPage, FindMistakeUpdatePage } from './find-mistake.page-object';

describe('FindMistake e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let findMistakeUpdatePage: FindMistakeUpdatePage;
    let findMistakeComponentsPage: FindMistakeComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load FindMistakes', async () => {
        await navBarPage.goToEntity('find-mistake');
        findMistakeComponentsPage = new FindMistakeComponentsPage();
        expect(await findMistakeComponentsPage.getTitle()).toMatch(/gatewayApp.gamesFindMistake.home.title/);
    });

    it('should load create FindMistake page', async () => {
        await findMistakeComponentsPage.clickOnCreateButton();
        findMistakeUpdatePage = new FindMistakeUpdatePage();
        expect(await findMistakeUpdatePage.getPageTitle()).toMatch(/gatewayApp.gamesFindMistake.home.createOrEditLabel/);
        await findMistakeUpdatePage.cancel();
    });

    /* it('should create and save FindMistakes', async () => {
        await findMistakeComponentsPage.clickOnCreateButton();
        await findMistakeUpdatePage.setSolutionInput('solution');
        expect(await findMistakeUpdatePage.getSolutionInput()).toMatch('solution');
        await findMistakeUpdatePage.setDefinitionInput('definition');
        expect(await findMistakeUpdatePage.getDefinitionInput()).toMatch('definition');
        await findMistakeUpdatePage.setSentenceInput('sentence');
        expect(await findMistakeUpdatePage.getSentenceInput()).toMatch('sentence');
        await findMistakeUpdatePage.setOptionAInput('optionA');
        expect(await findMistakeUpdatePage.getOptionAInput()).toMatch('optionA');
        await findMistakeUpdatePage.setOptionBInput('optionB');
        expect(await findMistakeUpdatePage.getOptionBInput()).toMatch('optionB');
        await findMistakeUpdatePage.setOptionCInput('optionC');
        expect(await findMistakeUpdatePage.getOptionCInput()).toMatch('optionC');
        await findMistakeUpdatePage.categorySelectLastOption();
        await findMistakeUpdatePage.levelSelectLastOption();
        await findMistakeUpdatePage.save();
        expect(await findMistakeUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
