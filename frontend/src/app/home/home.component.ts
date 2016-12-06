import { Component } from '@angular/core';
import { AirFaresService } from '../service/airfares.service';
import { AirPortModel } from '../service/airport.model';
import { AirFareModel } from '../service/airfare.model';
import { Observable } from 'rxjs/Rx';

@Component({
    moduleId: module.id,
    selector: 'as-home',
    templateUrl: 'home.html',
    providers: [AirFaresService],
    styleUrls: [
        'home.css'
    ]
})
export class HomeComponent {

    private airFares: Observable<AirFareModel[]>;
    private origen: AirPortModel;
    private destination: AirPortModel;
    private isLoading: boolean;
   
    private origenSelected: boolean;
    private destinationSelected: boolean;
    private  origenDestination: boolean;

    constructor(private _airFaresService: AirFaresService) {
    }

    ngOnInit() {
        this.origenSelected = false;
        this.isLoading = false;
        this.destinationSelected = false;
        this.origen = new AirPortModel('', '', '');
        this.destination = new AirPortModel('', '', '');
        this.origenDestination = true;
    }

    selectOrigen(origen: AirPortModel) {
        this.origen = origen;
        this.origenSelected = true;
        this.isLoading = this.origenDestination = false;
        this.airFares = null;
    }

    selectDesitnation(destination: AirPortModel) {
        this.destination = destination;
        this.origenDestination = true;
        this.isLoading = true;
        this.airFares = this._airFaresService.getAirportsByName(this.origen.code, this.destination.code);
    }

}
