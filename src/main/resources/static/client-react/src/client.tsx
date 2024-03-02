export default class Client {

	static baseURL = "http://localhost:8080";

	//
	// DateController
	//

	static async getDateType (date : string) : Promise<any> {
		const url      = `${Client.baseURL}/date/${date}`;
		const response = await fetch(url);

		return await response.json();
	}

	//
	// HolidayController
	//

	static async getHolidays () : Promise<any> {
		const url      = `${Client.baseURL}/date/holiday`;
		const response = await fetch(url);

		return await response.json();
	}

	//
	// UserController
	//

	static async getUserHours () : Promise<any> {
		const url      = `${Client.baseURL}/user`;
		const response = await fetch(url);

		return await response.json();
	}

	static async getUserHour (id : number) : Promise<any> {
		const url      = `${Client.baseURL}/user/${id}`;
		const response = await fetch(url);

		return await response.json();
	}

	//
	// InterventionController
	//

	static async getInterventions () : Promise<any> {
		const url      = `${Client.baseURL}/user/intervention`;
		const response = await fetch(url);

		return await response.json();
	}

	//
	// ReportController
	//

	static async addReport (report : any) : Promise<any> {
		const url     = `${Client.baseURL}/user/report`;
		const method  = "POST";
		const body    = JSON.stringify(report);
		const headers = {
			"Content-Type" : "application/json"
		};

		return fetch(url, {
			method  : method,
			headers : headers,
			body    : body
		});
	}

	static async getReports () : Promise<any> {
		const url      = `${Client.baseURL}/user/report`;
		const response = await fetch(url);

		return await response.json();
	}

	static async getReport (id : number) : Promise<any> {
		const url      = `${Client.baseURL}/user/report/${id}`;
		const response = await fetch(url);

		return await response.json();
	}

	static async updateReport (id : number, report : any) : Promise<any> {
		const url     = `${Client.baseURL}/user/report/${id}`;
		const method  = "PUT";
		const body    = JSON.stringify(report);
		const headers = {
			"Content-Type" : "application/json"
		};

		return fetch(url, {
			method  : method,
			headers : headers,
			body    : body
		});
	}

}
