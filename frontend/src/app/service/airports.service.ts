import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { AirPortModel, AirportsResult } from './airport.model';
import 'rxjs/add/operator/map';



@Injectable()
export class AirportsService {

    private DOSIER__LIST_URL: string = 'http://localhost:9000/airports';

    constructor(private http: Http) { }

    public getAirports(): Observable<AirPortModel[]> {
        return this.http.get(this.DOSIER__LIST_URL)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response): AirPortModel[] {
        let body: AirportsResult = res.json() as AirportsResult;
        return body._embedded.locations || [];
    }

    private handleError(error: Response | any) {
        // In a real world app, we might use a remote logging infrastructure
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    }

}

