export class ValidityPeriod {

    _monthsNumber: number;
    _monthlyFee: number;

    public get monthlyFee(): number {
        return this._monthlyFee;
    }
    
    public set monthlyFee(value: number) {
        this._monthlyFee = value;
    }

    public get monthsNumber(): number {
        return this._monthsNumber;
    }

    public set monthsNumber(value: number) {
        this._monthsNumber = value;
    }

    constructor(monthsNumber: number, monthlyFee: number) {
        this._monthsNumber = monthsNumber;
        this._monthlyFee = monthlyFee;
    }

    public get cost(): number {
        return this._monthsNumber * this._monthlyFee;
    }

}