import { Service } from './service';

export class MobilePhone extends Service {

    private _minutes: number;
    private _sms: number;
    private _minutesExtraFee: number;
    private _smsExtraFee: number;
    
    constructor(title: string, minutes: number, sms: number, minutesExtraFee: number, smsExtraFee: number) {
        super(title);
        this._minutes = minutes;
        this._sms = sms;
        this._minutesExtraFee = minutesExtraFee;
        this._smsExtraFee = smsExtraFee;
    }
    
    public get minutes(): number {
        return this._minutes;
    }
    
    
    public set minutes(value: number) {
        this._minutes = value;
    }
    
    public get sms(): number {
        return this._sms;
    }

    public set sms(value: number) {
        this._sms = value;
    }
    
    public get minutesExtraFee(): number {
        return this._minutesExtraFee;
    }

    public set minutesExtraFee(value: number) {
        this._minutesExtraFee = value;
    }
    
    public get smsExtraFee(): number {
        return this._smsExtraFee;
    }

    public set smsExtraFee(value: number) {
        this._smsExtraFee = value;
    }

}