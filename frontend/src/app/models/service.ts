export class Service {

    private _title: string;

    constructor(title: string) {
        this._title = title;
    }

    public get title() {
        return this._title;
    }

    public set title(title: string) {
        this._title = title;
    }
}