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
                    type: 'bar',
                    series: [{
                        values: [2, 3, 4, 5, 3, 3, 2]
                    }]
                },
                height: 300
            }
        );
        this.charts = [];
        this.charts.push(chart);
    }


}
