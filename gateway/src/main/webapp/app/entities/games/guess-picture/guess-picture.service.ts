import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGuessPicture } from 'app/shared/model/games/guess-picture.model';

type EntityResponseType = HttpResponse<IGuessPicture>;
type EntityArrayResponseType = HttpResponse<IGuessPicture[]>;

@Injectable({ providedIn: 'root' })
export class GuessPictureService {
    private resourceUrl = SERVER_API_URL + 'games/api/guess-pictures';

    constructor(private http: HttpClient) {}

    create(guessPicture: IGuessPicture): Observable<EntityResponseType> {
        return this.http.post<IGuessPicture>(this.resourceUrl, guessPicture, { observe: 'response' });
    }

    update(guessPicture: IGuessPicture): Observable<EntityResponseType> {
        return this.http.put<IGuessPicture>(this.resourceUrl, guessPicture, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGuessPicture>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGuessPicture[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
