export class AirPortModel {
    constructor(
        public code: string,
        public name: string,
        public description: string
    ) { }
}

export class AirportsResult {
    constructor(
        public _embedded: AirportsResultItems,
        public page: AirportsResultPage,

    ) { }
}

export class AirportsResultItems {
    constructor(
        public locations: AirPortModel[]
    ) { }
}

export class AirportsResultPage {
    constructor(
        public size: number,
        public totalElements: number,
        public totalPages: number,
        public number: number
    ) { }
}
