import { Component, NgZone, Input, AfterViewInit, OnDestroy } from '@angular/core';
import { Chart } from '../../service/chart.model';
let zingchart: any = require('node_modules/zingchart/index.js');


@Component({
    selector: 'as-zing-chart',
    template: '<div id="{{chart.id}}"></div>'
})
export class Zingchart implements AfterViewInit, OnDestroy {

    @Input() chart: Chart;

    constructor(private zone: NgZone) { }

    ngAfterViewInit() {
        this.zone.runOutsideAngular(() => zingchart.render(this.chart));
    }

    ngOnDestroy() {
        zingchart.exec(this.chart.id, 'destroy');
    }
}
