import { Component } from '@angular/core';
import { AirportsService } from '../service/airports.service';
import { AirFaresService } from '../service/airfares.service';
import { AirPortModel } from '../service/airport.model';
import { AirFareModel } from '../service/airfare.model';
import { Observable } from 'rxjs/Rx';

@Component({
    moduleId: module.id,
    selector: 'as-home',
    templateUrl: 'home.html',
    providers: [AirportsService, AirFaresService],
    styleUrls: [
        'home.css'
    ]
})
export class HomeComponent {

    airPorts: Observable<AirPortModel[]>;
    airFares: Observable<AirFareModel[]>;
    origen: AirPortModel;
    destination: AirPortModel;
    isLoading: boolean;

    origenAiportSearch: string;
    origenSelected: boolean;
    destinationSelected: boolean;

    counter: number = 0;

    origenDestination: boolean;

    constructor(private _airportsService: AirportsService, private _airFaresService: AirFaresService) {
    }

    ngOnInit() {
        this.origenSelected = false;
        this.isLoading = false;
        this.destinationSelected = false;
        this.origen = new AirPortModel('', '', '');
        this.destination = new AirPortModel('', '', '');
        this.origenDestination = true;
    }

    getAirports() {
        if (this.origenAiportSearch.length > 3) {
            this.airPorts = this._airportsService.getAirportsByName(this.origenAiportSearch);
        }
    }

    reSelectOrigen() {
        this.origenSelected = false;
        this.origenAiportSearch = '';
        this.airPorts = null;
    }

    selectOrigen(origen: AirPortModel) {
        this.origen = origen;
        this.origenSelected = true;
        this.isLoading = this.origenDestination = false;
        this.airPorts = null;
        this.origenAiportSearch = '';
        this.airFares = null;
    }

    selectDesitnation(destination: AirPortModel) {
        this.destination = destination;
        this.origenDestination = true;
        this.isLoading = true;
        this.airFares = this._airFaresService.getAirportsByName(this.origen.code, this.destination.code);
    }

}
