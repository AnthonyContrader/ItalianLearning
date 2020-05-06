import { element, by, ElementFinder } from 'protractor';

export class FindAWordComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-find-a-word div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class FindAWordUpdatePage {
    pageTitle = element(by.id('jhi-find-a-word-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    solutionInput = element(by.id('field_solution'));
    definitionInput = element(by.id('field_definition'));
    sentenceInput = element(by.id('field_sentence'));
    categorySelect = element(by.id('field_category'));
    levelSelect = element(by.id('field_level'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setSolutionInput(solution) {
        await this.solutionInput.sendKeys(solution);
    }

    async getSolutionInput() {
        return this.solutionInput.getAttribute('value');
    }

    async setDefinitionInput(definition) {
        await this.definitionInput.sendKeys(definition);
    }

    async getDefinitionInput() {
        return this.definitionInput.getAttribute('value');
    }

    async setSentenceInput(sentence) {
        await this.sentenceInput.sendKeys(sentence);
    }

    async getSentenceInput() {
        return this.sentenceInput.getAttribute('value');
    }

    async categorySelectLastOption() {
        await this.categorySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async categorySelectOption(option) {
        await this.categorySelect.sendKeys(option);
    }

    getCategorySelect(): ElementFinder {
        return this.categorySelect;
    }

    async getCategorySelectedOption() {
        return this.categorySelect.element(by.css('option:checked')).getText();
    }

    async levelSelectLastOption() {
        await this.levelSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async levelSelectOption(option) {
        await this.levelSelect.sendKeys(option);
    }

    getLevelSelect(): ElementFinder {
        return this.levelSelect;
    }

    async getLevelSelectedOption() {
        return this.levelSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}
