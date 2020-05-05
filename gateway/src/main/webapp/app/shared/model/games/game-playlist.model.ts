export interface IGamePlaylist {
    id?: number;
    idGame?: number;
    typeGame?: string;
    playlistName?: string;
    playlistId?: number;
}

export class GamePlaylist implements IGamePlaylist {
    constructor(
        public id?: number,
        public idGame?: number,
        public typeGame?: string,
        public playlistName?: string,
        public playlistId?: number
    ) {}
}
