import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { serverURL  } from "../app.environment";

@Injectable({
	providedIn : 'root'
})

export class DateService {

	private readonly componentURL : string;
	private readonly serviceURL   : string;

	constructor (private http : HttpClient) {
		this.componentURL = `/date`;
		this.serviceURL   = `${serverURL}/${this.componentURL}`;
	}

	public getHolidays () : Observable<any> {
		return this.http.get<any>(`${this.serviceURL}/holiday`);
	}

	public getDateType (date : string) : Observable<any> {
		return this.http.get<any>(`${this.serviceURL}/` + date);
	}

}
