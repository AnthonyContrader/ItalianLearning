import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { UsersComponentsPage, UsersUpdatePage } from './users.page-object';

describe('Users e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let usersUpdatePage: UsersUpdatePage;
    let usersComponentsPage: UsersComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Users', async () => {
        await navBarPage.goToEntity('users');
        usersComponentsPage = new UsersComponentsPage();
        expect(await usersComponentsPage.getTitle()).toMatch(/gatewayApp.userUsers.home.title/);
    });

    it('should load create Users page', async () => {
        await usersComponentsPage.clickOnCreateButton();
        usersUpdatePage = new UsersUpdatePage();
        expect(await usersUpdatePage.getPageTitle()).toMatch(/gatewayApp.userUsers.home.createOrEditLabel/);
        await usersUpdatePage.cancel();
    });

    it('should create and save Users', async () => {
        await usersComponentsPage.clickOnCreateButton();
        await usersUpdatePage.setUsernameInput('username');
        expect(await usersUpdatePage.getUsernameInput()).toMatch('username');
        await usersUpdatePage.setPasswordInput('password');
        expect(await usersUpdatePage.getPasswordInput()).toMatch('password');
        await usersUpdatePage.usertypeSelectLastOption();
        await usersUpdatePage.save();
        expect(await usersUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
