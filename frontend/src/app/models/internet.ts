import { Service } from './service';

export class Internet extends Service {

    gigabytes: number;
    gigabytesExtraFee: number;
    
    constructor(title: string, gigabytes: number, gigabytesExtraFee: number) {
        super(title);
        this.gigabytes = gigabytes;
        this.gigabytesExtraFee = gigabytesExtraFee;
    }

}