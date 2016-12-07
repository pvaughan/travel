import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { StaticsResult } from './statistics.model';
import 'rxjs/add/operator/map';



@Injectable()
export class StatisticsService {

    private STATISTICS__REPORT_URL: string = 'http://localhost:9000/getStatisticsReport';

    constructor(private http: Http) { }

    public getStatisticsReport(): Observable<StaticsResult> {
        return this.http.get(this.STATISTICS__REPORT_URL)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response): StaticsResult {
        let body: StaticsResult = res.json() as StaticsResult;
        return body;
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
