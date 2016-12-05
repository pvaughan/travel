import { AirPortModel } from './airport.model';

export class AirFareModel {
    constructor(private amount: number,
        private currency: string,
        private origin: string,
        private destination: string,
        private originInfo: AirPortModel,
        private destinationInfo: AirPortModel) {

    }
}

