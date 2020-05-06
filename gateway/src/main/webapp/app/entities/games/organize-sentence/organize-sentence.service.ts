import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOrganizeSentence } from 'app/shared/model/games/organize-sentence.model';

type EntityResponseType = HttpResponse<IOrganizeSentence>;
type EntityArrayResponseType = HttpResponse<IOrganizeSentence[]>;

@Injectable({ providedIn: 'root' })
export class OrganizeSentenceService {
    private resourceUrl = SERVER_API_URL + 'games/api/organize-sentences';

    constructor(private http: HttpClient) {}

    create(organizeSentence: IOrganizeSentence): Observable<EntityResponseType> {
        return this.http.post<IOrganizeSentence>(this.resourceUrl, organizeSentence, { observe: 'response' });
    }

    update(organizeSentence: IOrganizeSentence): Observable<EntityResponseType> {
        return this.http.put<IOrganizeSentence>(this.resourceUrl, organizeSentence, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOrganizeSentence>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOrganizeSentence[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
