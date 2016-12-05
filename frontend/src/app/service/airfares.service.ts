import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { AirFareModel } from './airfare.model';
import 'rxjs/add/operator/map';

@Injectable()
export class AirFaresService {

    private FAREOFFER__LIST_URL: string = 'http://localhost:9000/fareOffer';
    constructor(private http: Http) { }

    public getAirportsByName(origen: string, destination: string): Observable<AirFareModel[]> {
        return this.http.get(this.FAREOFFER__LIST_URL + '/' + origen + '/' + destination)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response): AirFareModel[] {
        let body: AirFareModel = res.json() as AirFareModel;

        let airFares: AirFareModel[] = [];
        airFares.push(body);

        return airFares;
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

