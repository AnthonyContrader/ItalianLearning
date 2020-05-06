import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { QuizComponentsPage, QuizUpdatePage } from './quiz.page-object';

describe('Quiz e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let quizUpdatePage: QuizUpdatePage;
    let quizComponentsPage: QuizComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Quizzes', async () => {
        await navBarPage.goToEntity('quiz');
        quizComponentsPage = new QuizComponentsPage();
        expect(await quizComponentsPage.getTitle()).toMatch(/gatewayApp.gamesQuiz.home.title/);
    });

    it('should load create Quiz page', async () => {
        await quizComponentsPage.clickOnCreateButton();
        quizUpdatePage = new QuizUpdatePage();
        expect(await quizUpdatePage.getPageTitle()).toMatch(/gatewayApp.gamesQuiz.home.createOrEditLabel/);
        await quizUpdatePage.cancel();
    });

    /* it('should create and save Quizzes', async () => {
        await quizComponentsPage.clickOnCreateButton();
        await quizUpdatePage.setSolutionInput('solution');
        expect(await quizUpdatePage.getSolutionInput()).toMatch('solution');
        await quizUpdatePage.setDefinitionInput('definition');
        expect(await quizUpdatePage.getDefinitionInput()).toMatch('definition');
        await quizUpdatePage.setSentenceInput('sentence');
        expect(await quizUpdatePage.getSentenceInput()).toMatch('sentence');
        await quizUpdatePage.categorySelectLastOption();
        await quizUpdatePage.levelSelectLastOption();
        await quizUpdatePage.save();
        expect(await quizUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
