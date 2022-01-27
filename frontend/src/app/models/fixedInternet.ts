import { Service } from './service';

export class FixedInternet extends Service {

    private _gigabytes: number;
    private _gigabytesExtraFee: number;
    
    constructor(title: string, gigabytes: number, gigabytesExtraFee: number) {
        super(title);
        this._gigabytes = gigabytes;
        this._gigabytesExtraFee = gigabytesExtraFee;
    }
    
    public get gigabytes(): number {
        return this._gigabytes;
    }
    
    
    public set gigabytes(value: number) {
        this._gigabytes = value;
    }
    
    public get gigabytesExtraFee(): number {
        return this._gigabytesExtraFee;
    }

    public set gigabytesExtraFee(value: number) {
        this._gigabytesExtraFee = value;
    }

}