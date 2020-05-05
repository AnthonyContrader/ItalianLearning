import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { UserComponentsPage, UserUpdatePage } from './user.page-object';

describe('User e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let userUpdatePage: UserUpdatePage;
    let userComponentsPage: UserComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Users', async () => {
        await navBarPage.goToEntity('user');
        userComponentsPage = new UserComponentsPage();
        expect(await userComponentsPage.getTitle()).toMatch(/gatewayApp.userUser.home.title/);
    });

    it('should load create User page', async () => {
        await userComponentsPage.clickOnCreateButton();
        userUpdatePage = new UserUpdatePage();
        expect(await userUpdatePage.getPageTitle()).toMatch(/gatewayApp.userUser.home.createOrEditLabel/);
        await userUpdatePage.cancel();
    });

    it('should create and save Users', async () => {
        await userComponentsPage.clickOnCreateButton();
        await userUpdatePage.setUsernameInput('username');
        expect(await userUpdatePage.getUsernameInput()).toMatch('username');
        await userUpdatePage.setPasswordInput('password');
        expect(await userUpdatePage.getPasswordInput()).toMatch('password');
        await userUpdatePage.usertypeSelectLastOption();
        await userUpdatePage.save();
        expect(await userUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
