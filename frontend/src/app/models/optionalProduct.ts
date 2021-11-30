export class OptionalProduct {
    
    _name: string;
    _monthlyFee: number;
    
    public get name(): string {
        return this._name;
    }
    
    public set name(value: string) {
        this._name = value;
    }

    public get monthlyFee() {
        return this._monthlyFee
    }

    public set monthlyFee(value: number) {
        this._monthlyFee = value;
    }

    constructor(name: string, monthlyFee: number) {
        this._name = name;
        this._monthlyFee = monthlyFee;
    }
}