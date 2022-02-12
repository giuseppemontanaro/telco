import { Service } from './service';

export class MobilePhone extends Service {
    
    minutes: number;
    sms: number;
    minutes_extra_fee: number;
    sms_extra_fee: number;
    
    constructor(title: string, minutes: number, sms: number, minutesExtraFee: number, smsExtraFee: number) {
        super(title);
        this.minutes = minutes;
        this.sms = sms;
        this.minutes_extra_fee = minutesExtraFee;
        this.sms_extra_fee = smsExtraFee;
    }
    
}