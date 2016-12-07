import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { AirportsService } from '../../service/airports.service';
import { AirPortModel } from '../../service/airport.model';
import { Observable, Subject } from 'rxjs/Rx';

@Component({
    moduleId: module.id,
    selector: 'as-search-box',
    templateUrl: 'searchbox.component.html',
    providers: [AirportsService],
    styleUrls: [
        'searchbox.component.css'
    ]
})
export class SearchBoxComponent implements OnInit {

    @Input() public title: string;
    @Input() public label: string;

    @Output() public onAirportSelected: EventEmitter<any> = new EventEmitter();

    private airPorts: Observable<AirPortModel[]>;
    private origen: AirPortModel;
    private origenSelected: boolean;
    private origenAiportSearch: string;

    private searchTerms = new Subject<string>();

    constructor(private _airportsService: AirportsService) { }

    ngOnInit() {

        this.origen = new AirPortModel('', '', '');

        this.airPorts = this.searchTerms
            .debounceTime(300)        // wait for 300ms pause in events
            .distinctUntilChanged()   // ignore if next search term is same as previous
            .switchMap(term => term   // switch to new observable each time
                // return the http search observable
                ? this._airportsService.getAirportsByName(term)
                // or the observable of empty heroes if no search term
                : Observable.of<AirPortModel[]>([]))
            .catch(error => {
                // TODO: real error handling
                console.log(error);
                return Observable.of<AirPortModel[]>([]);
            });
    }

    getAirports(): void {
        if (this.origenAiportSearch.length >= 3) {
            this.searchTerms.next(this.origenAiportSearch);
        }
    }

    reSelectOrigen(): void {
        this.origenSelected = false;
        this.origenAiportSearch = '';
    }

    selectOrigen(origen: AirPortModel): void {
        this.origenSelected = true;
        this.origen = origen;
        this.searchTerms.next(null);
        this.onAirportSelected.emit([origen]);
    }

}
