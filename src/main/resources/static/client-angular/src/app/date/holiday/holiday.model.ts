export class Holiday {

	date? : string;
	name? : string;

	constructor (date : Date, name : string) {
		this.date = date.toISOString().split('T')[0];
		this.name = name;
	}

}
