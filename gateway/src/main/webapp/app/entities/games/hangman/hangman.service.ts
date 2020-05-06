import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHangman } from 'app/shared/model/games/hangman.model';

type EntityResponseType = HttpResponse<IHangman>;
type EntityArrayResponseType = HttpResponse<IHangman[]>;

@Injectable({ providedIn: 'root' })
export class HangmanService {
    private resourceUrl = SERVER_API_URL + 'games/api/hangmen';

    constructor(private http: HttpClient) {}

    create(hangman: IHangman): Observable<EntityResponseType> {
        return this.http.post<IHangman>(this.resourceUrl, hangman, { observe: 'response' });
    }

    update(hangman: IHangman): Observable<EntityResponseType> {
        return this.http.put<IHangman>(this.resourceUrl, hangman, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IHangman>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IHangman[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
