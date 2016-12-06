import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { StatisticsComponent } from './index';
import { Zingchart } from './component/zingchart.component';

@NgModule({
    declarations: [
        StatisticsComponent, Zingchart
    ],
    imports: [
        FormsModule,
        BrowserModule

    ],
    exports: [
        StatisticsComponent
    ]
})
export class StatisticsModule {
}
