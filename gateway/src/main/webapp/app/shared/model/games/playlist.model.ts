export interface IPlaylist {
    id?: number;
    name?: string;
    description?: string;
}

export class Playlist implements IPlaylist {
    constructor(public id?: number, public name?: string, public description?: string) {}
}
