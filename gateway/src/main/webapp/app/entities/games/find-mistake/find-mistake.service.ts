import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFindMistake } from 'app/shared/model/games/find-mistake.model';

type EntityResponseType = HttpResponse<IFindMistake>;
type EntityArrayResponseType = HttpResponse<IFindMistake[]>;

@Injectable({ providedIn: 'root' })
export class FindMistakeService {
    private resourceUrl = SERVER_API_URL + 'games/api/find-mistakes';

    constructor(private http: HttpClient) {}

    create(findMistake: IFindMistake): Observable<EntityResponseType> {
        return this.http.post<IFindMistake>(this.resourceUrl, findMistake, { observe: 'response' });
    }

    update(findMistake: IFindMistake): Observable<EntityResponseType> {
        return this.http.put<IFindMistake>(this.resourceUrl, findMistake, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFindMistake>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFindMistake[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
