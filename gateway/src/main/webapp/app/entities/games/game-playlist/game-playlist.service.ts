import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGamePlaylist } from 'app/shared/model/games/game-playlist.model';

type EntityResponseType = HttpResponse<IGamePlaylist>;
type EntityArrayResponseType = HttpResponse<IGamePlaylist[]>;

@Injectable({ providedIn: 'root' })
export class GamePlaylistService {
    private resourceUrl = SERVER_API_URL + 'games/api/game-playlists';

    constructor(private http: HttpClient) {}

    create(gamePlaylist: IGamePlaylist): Observable<EntityResponseType> {
        return this.http.post<IGamePlaylist>(this.resourceUrl, gamePlaylist, { observe: 'response' });
    }

    update(gamePlaylist: IGamePlaylist): Observable<EntityResponseType> {
        return this.http.put<IGamePlaylist>(this.resourceUrl, gamePlaylist, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGamePlaylist>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGamePlaylist[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
