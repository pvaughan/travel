import { Component } from '@angular/core';
import { Chart } from '../service/chart.model';
import { StatisticsService } from '../service/statistics.service';

@Component({
    moduleId: module.id,
    selector: 'as-statistics',
    templateUrl: 'statistics.html',
    providers: [StatisticsService]
})
export class StatisticsComponent {

    public charts: Chart[];
    public charts2: Chart[];

    constructor(private _statisticsService: StatisticsService) {
        this.charts2 = [];
        this.charts = [];
    }

    ngOnInit() {
        this._statisticsService.getStatisticsReport().subscribe(
            statistics => {
                // Emit list event
                this.charts.push(this.getGaugeChart(statistics.avgResponseTime, 0,
                    statistics.maxResponseTime, 'chart-1', 'avg response time(ms)'));
                this.charts.push(this.getGaugeChart(statistics.minResponseTime, 0,
                    statistics.maxResponseTime, 'chart-3', 'min response time(ms)'));
                this.charts.push(this.getGaugeChart(statistics.maxResponseTime, 0,
                    statistics.maxResponseTime, 'chart-2', 'max response time(ms)'));

                this.charts2.push(this.getBarChart('chart-4',
                    statistics.respoonse200.length,
                    statistics.respoonse404.length,
                    statistics.respoonse500.length));

            },
            err => {
                // Log errors if any
                console.log(err);
            });
    }

    private getGaugeChart(value: number, min: number, max: number, id: string, label: string) {
        let step: number = Math.floor((max - min) / 10);
        return new Chart(
            {
                id: id,
                data: {
                    type: 'gauge',
                    scaleR: {
                        aperture: 180,
                        minValue: min,
                        step: step,
                        maxValue: max
                    },
                    labels: [
                        {
                            id: 'score-text',
                            text: label,
                            x: '50%',
                            y: '250',
                            anchor: 'c',
                            fontSize: 23
                        }
                    ],
                    series: [
                        {
                            values: [value],
                            animation: {
                                effect: 2,
                                method: 0,
                                sequence: 4,
                                delay: 700,
                                speed: 900
                            },
                        }
                    ],
                    label: 'respon'
                },
                height: 300
            }
        );
    }

    private getBarChart(id: string, nrOf200: number, nrOf404: number, nrOf500: number) {
        return new Chart(
            {
                id: id,
                data: this.getConfig(nrOf200, nrOf404, nrOf500)
            }
        );
    }

    private getConfig(nrOf200: number, nrOf404: number, nrOf500: number) {
        return {
            'type': 'hbar',
            'font-family': 'Arial',
            'title': {
                'text': 'Number of requests by response type',
                'font-family': 'Arial',
                'background-color': 'none',
                'font-color': '#005b82',
                'font-size': '18px'
            },
            'plot': {
                'bars-overlap': '100%',
                'borderRadius': 8,
                'hover-state': {
                    'visible': false
                },
                'animation': {
                    'delay': 300,
                    'effect': 3,
                    'speed': '500',
                    'method': '0',
                    'sequence': '3'
                }
            },
            'plotarea': {
                'margin': '60px 20px 20px 140px'
            },
            'scale-x': {
                'line-color': 'none',
                'values': ['200', '404', '500'],
                'tick': {
                    'visible': false
                },
                'guide': {
                    'visible': false
                },
                'item': {
                    'font-size': '14px',
                    'padding-right': '20px',
                    'auto-align': true,
                    'rules': [
                        {
                            'rule': '%i==2',
                            'font-color': '#FCCC65'
                        },
                        {
                            'rule': '%i==0',
                            'font-color': '#A0BE4A'
                        },
                        {
                            'rule': '%i==1',
                            'font-color': '#6FA6DF'
                        }
                    ]
                }
            },
            'scale-y': {
                'visible': false,
                'guide': {
                    'visible': false
                }
            },
            'series': [
                {
                    'values': [100, 100, 100],
                    'bar-width': '40px',
                    'background-color': '#f2f2f2',
                    'border-color': '#e8e3e3',
                    'border-width': 2,
                    'fill-angle': 90,
                    'tooltip': {
                        'visible': false
                    }
                },
                {
                    'values': [nrOf200, nrOf404, nrOf500],
                    'bar-width': '32px',
                    'max-trackers': 0,
                    'value-box': {
                        'placement': 'top-out',
                        'text': '%v',
                        'decimals': 0,
                        'font-color': '#A4A4A4',
                        'font-size': '14px',
                        'alpha': 0.6
                    },
                    'rules': [
                        {
                            'rule': '%i==2',
                            'background-color': '#FCCC65'
                        },
                        {
                            'rule': '%i==0',
                            'background-color': '#A0BE4A'
                        },
                        {
                            'rule': '%i==1',
                            'background-color': '#6FA6DF'
                        }
                    ]
                }
            ]
        };
    }

}
