import { Routes, RouterModule } from '@angular/router';

import { HomeRoutes } from './home/index';
import { StatisticsRoutes } from './statistics/index';

const appRoutes: Routes = [
    ...HomeRoutes,
    ...StatisticsRoutes
];

export const appRoutingProviders: any[] = [

];

export const routing = RouterModule.forRoot(appRoutes);
