import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';

import { APP_PROVIDERS } from './app.providers';
import { AppComponent } from './app.component';
import { appRoutingProviders, routing } from './app.routing';
import { NavbarModule } from './shared';
import { HomeModule } from './home/home.module';
import { TodolistModule } from './todolist/todolist.module';

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        BrowserModule,
        CommonModule,
        FormsModule,
        HttpModule,
        NavbarModule,
        HomeModule,
        TodolistModule,
        routing
    ],
    providers: [APP_PROVIDERS, appRoutingProviders],
    bootstrap: [AppComponent]
})
export class AppModule {
}
