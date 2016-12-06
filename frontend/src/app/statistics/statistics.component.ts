import { Component } from '@angular/core';
import { Chart } from '../service/chart.model';

@Component({
    moduleId: module.id,
    selector: 'as-statistics',
    templateUrl: 'statistics.html'
})
export class StatisticsComponent {

    public charts: Chart[];

    constructor() {

        let chart = new Chart(
            {
                id: 'chart-1',
                data: {
                    type: 'gauge',
                    series: [
                        { values: [87] }
                    ]
                },
                height: 300
            }
        );
        this.charts = [];
        this.charts.push(chart);
    }


}
