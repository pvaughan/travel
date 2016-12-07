export class StaticsResult {
    constructor(
        public avgResponseTime: number,
        public minResponseTime: number,
        public maxResponseTime: number,
        public respoonse200: StaticsResultItems[],
        public respoonse404: StaticsResultItems[],
        public respoonse500: StaticsResultItems[]
    ) { }
}

export class StaticsResultItems {
    constructor(
        public timeStamp: Date,
        public requestCompletionTime: number,
        public responseCode: number
    ) { }
}

