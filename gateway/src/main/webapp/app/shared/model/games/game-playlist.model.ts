export interface IGamePlaylist {
    id?: number;
    idGame?: number;
    typeGame?: string;
    playlistTitle?: string;
    playlistId?: number;
}

export class GamePlaylist implements IGamePlaylist {
    constructor(
        public id?: number,
        public idGame?: number,
        public typeGame?: string,
        public playlistTitle?: string,
        public playlistId?: number
    ) {}
}
