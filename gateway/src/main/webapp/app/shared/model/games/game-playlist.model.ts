export interface IGamePlaylist {
    id?: number;
    typeGame?: string;
    idGame?: number;
    playlistName?: string;
    playlistId?: number;
}

export class GamePlaylist implements IGamePlaylist {
    constructor(
        public id?: number,
        public typeGame?: string,
        public idGame?: number,
        public playlistName?: string,
        public playlistId?: number
    ) {}
}
