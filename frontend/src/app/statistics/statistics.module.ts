import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { StatisticsComponent } from './index';
import { ChartModule } from 'angular2-highcharts';

@NgModule({
    declarations: [
        StatisticsComponent
    ],
    imports: [
        FormsModule,
        BrowserModule,
        ChartModule
    ],
    exports: [
        StatisticsComponent
    ]
})
export class StatisticsModule {
}
