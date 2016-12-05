import { Component } from '@angular/core';
import {AirportsService} from '../service/airports.service';
import {AirPortModel} from '../service/airport.model';
import { Observable } from 'rxjs/Observable';


@Component({
    moduleId: module.id,
    selector: 'as-home',
    templateUrl: 'home.html',
    providers: [AirportsService],
    styleUrls: [
        'home.css'
    ]
})
export class HomeComponent {

     airPorts: Observable<AirPortModel[]>;

     constructor(private _airportsService: AirportsService) { }

      ngOnInit() {
        this.airPorts = this._airportsService.getAirports();
      }


}
