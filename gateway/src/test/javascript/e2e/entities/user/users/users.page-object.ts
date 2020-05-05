import { element, by, ElementFinder } from 'protractor';

export class UsersComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-users div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class UsersUpdatePage {
    pageTitle = element(by.id('jhi-users-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    usernameInput = element(by.id('field_username'));
    passwordInput = element(by.id('field_password'));
    usertypeSelect = element(by.id('field_usertype'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setUsernameInput(username) {
        await this.usernameInput.sendKeys(username);
    }

    async getUsernameInput() {
        return this.usernameInput.getAttribute('value');
    }

    async setPasswordInput(password) {
        await this.passwordInput.sendKeys(password);
    }

    async getPasswordInput() {
        return this.passwordInput.getAttribute('value');
    }

    async setUsertypeSelect(usertype) {
        await this.usertypeSelect.sendKeys(usertype);
    }

    async getUsertypeSelect() {
        return this.usertypeSelect.element(by.css('option:checked')).getText();
    }

    async usertypeSelectLastOption() {
        await this.usertypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
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
