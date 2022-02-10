import { Service } from './service';

export class MobilePhone extends Service {
    
    minutes: number;
    sms: number;
    minutesExtraFee: number;
    smsExtraFee: number;
    
    constructor(title: string, minutes: number, sms: number, minutesExtraFee: number, smsExtraFee: number) {
        super(title);
        this.minutes = minutes;
        this.sms = sms;
        this.minutesExtraFee = minutesExtraFee;
        this.smsExtraFee = smsExtraFee;
    }
    
}