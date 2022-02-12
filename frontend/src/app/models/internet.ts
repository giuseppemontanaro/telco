import { Service } from './service';

export class Internet extends Service {

    gigabytes: number;
    gigabytes_extra_fee: number;
    
    constructor(title: string, gigabytes: number, gigabytesExtraFee: number) {
        super(title);
        this.gigabytes = gigabytes;
        this.gigabytes_extra_fee = gigabytesExtraFee;
    }

}