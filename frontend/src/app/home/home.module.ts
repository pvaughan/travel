import { NgModule } from '@angular/core';
import { HomeComponent } from './index';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SearchBoxComponent } from './component/searchbox.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule
    ],
    declarations: [
        HomeComponent, SearchBoxComponent
    ],
    exports: [
        HomeComponent, SearchBoxComponent
    ]
})
export class HomeModule {
}

