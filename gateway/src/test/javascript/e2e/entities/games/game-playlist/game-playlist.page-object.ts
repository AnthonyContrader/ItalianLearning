import { element, by, ElementFinder } from 'protractor';

export class GamePlaylistComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-game-playlist div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class GamePlaylistUpdatePage {
    pageTitle = element(by.id('jhi-game-playlist-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    typeGameInput = element(by.id('field_typeGame'));
    idGameInput = element(by.id('field_idGame'));
    playlistSelect = element(by.id('field_playlist'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setTypeGameInput(typeGame) {
        await this.typeGameInput.sendKeys(typeGame);
    }

    async getTypeGameInput() {
        return this.typeGameInput.getAttribute('value');
    }

    async setIdGameInput(idGame) {
        await this.idGameInput.sendKeys(idGame);
    }

    async getIdGameInput() {
        return this.idGameInput.getAttribute('value');
    }

    async playlistSelectLastOption() {
        await this.playlistSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async playlistSelectOption(option) {
        await this.playlistSelect.sendKeys(option);
    }

    getPlaylistSelect(): ElementFinder {
        return this.playlistSelect;
    }

    async getPlaylistSelectedOption() {
        return this.playlistSelect.element(by.css('option:checked')).getText();
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
