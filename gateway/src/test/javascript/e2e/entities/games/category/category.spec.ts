import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { CategoryComponentsPage, CategoryUpdatePage } from './category.page-object';

describe('Category e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let categoryUpdatePage: CategoryUpdatePage;
    let categoryComponentsPage: CategoryComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Categories', async () => {
        await navBarPage.goToEntity('category');
        categoryComponentsPage = new CategoryComponentsPage();
        expect(await categoryComponentsPage.getTitle()).toMatch(/gatewayApp.gamesCategory.home.title/);
    });

    it('should load create Category page', async () => {
        await categoryComponentsPage.clickOnCreateButton();
        categoryUpdatePage = new CategoryUpdatePage();
        expect(await categoryUpdatePage.getPageTitle()).toMatch(/gatewayApp.gamesCategory.home.createOrEditLabel/);
        await categoryUpdatePage.cancel();
    });

    it('should create and save Categories', async () => {
        await categoryComponentsPage.clickOnCreateButton();
        await categoryUpdatePage.setTitleInput('title');
        expect(await categoryUpdatePage.getTitleInput()).toMatch('title');
        await categoryUpdatePage.setDescriptionInput('description');
        expect(await categoryUpdatePage.getDescriptionInput()).toMatch('description');
        await categoryUpdatePage.save();
        expect(await categoryUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
