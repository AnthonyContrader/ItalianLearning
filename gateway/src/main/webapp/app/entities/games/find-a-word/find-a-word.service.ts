import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFindAWord } from 'app/shared/model/games/find-a-word.model';

type EntityResponseType = HttpResponse<IFindAWord>;
type EntityArrayResponseType = HttpResponse<IFindAWord[]>;

@Injectable({ providedIn: 'root' })
export class FindAWordService {
    private resourceUrl = SERVER_API_URL + 'games/api/find-a-words';

    constructor(private http: HttpClient) {}

    create(findAWord: IFindAWord): Observable<EntityResponseType> {
        return this.http.post<IFindAWord>(this.resourceUrl, findAWord, { observe: 'response' });
    }

    update(findAWord: IFindAWord): Observable<EntityResponseType> {
        return this.http.put<IFindAWord>(this.resourceUrl, findAWord, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFindAWord>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFindAWord[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
