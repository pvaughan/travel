import { Component } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'as-statistics',
    templateUrl: 'statistics.html'
})
export class StatisticsComponent {
 
    public options: Object;


    constructor() {


        this.options = {
            title : { text : 'simple chart' },
            series: [{
                data: [29.9, 71.5, 106.4, 129.2],
            }]
        };
    }


}
