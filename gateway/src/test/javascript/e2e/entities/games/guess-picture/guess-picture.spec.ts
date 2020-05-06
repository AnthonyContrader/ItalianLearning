import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { GuessPictureComponentsPage, GuessPictureUpdatePage } from './guess-picture.page-object';
import * as path from 'path';

describe('GuessPicture e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let guessPictureUpdatePage: GuessPictureUpdatePage;
    let guessPictureComponentsPage: GuessPictureComponentsPage;
    const fileToUpload = '../../../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load GuessPictures', async () => {
        await navBarPage.goToEntity('guess-picture');
        guessPictureComponentsPage = new GuessPictureComponentsPage();
        expect(await guessPictureComponentsPage.getTitle()).toMatch(/gatewayApp.gamesGuessPicture.home.title/);
    });

    it('should load create GuessPicture page', async () => {
        await guessPictureComponentsPage.clickOnCreateButton();
        guessPictureUpdatePage = new GuessPictureUpdatePage();
        expect(await guessPictureUpdatePage.getPageTitle()).toMatch(/gatewayApp.gamesGuessPicture.home.createOrEditLabel/);
        await guessPictureUpdatePage.cancel();
    });

    /* it('should create and save GuessPictures', async () => {
        await guessPictureComponentsPage.clickOnCreateButton();
        await guessPictureUpdatePage.setSolutionInput('solution');
        expect(await guessPictureUpdatePage.getSolutionInput()).toMatch('solution');
        await guessPictureUpdatePage.setImageInput(absolutePath);
        await guessPictureUpdatePage.levelSelectLastOption();
        await guessPictureUpdatePage.categorySelectLastOption();
        await guessPictureUpdatePage.save();
        expect(await guessPictureUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
