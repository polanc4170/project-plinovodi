import { Component   } from '@angular/core';
import { OnInit      } from '@angular/core';
import { DateService } from "../date.service";
import { Holiday     } from "./holiday.model";

@Component({
	selector    : 'app-date-holiday',
	templateUrl : './holiday.component.html',
	styleUrl    : './holiday.component.css'
})

export class HolidayComponent implements OnInit {

	holidays : Holiday [];

	constructor (private dateService : DateService) {
		this.holidays = [
			new Holiday(new Date(2024, 1, 1), "Novo Leto"),
			new Holiday(new Date(2024, 1, 2), "Novo Leto")
		];
	}

	public ngOnInit () {
		this.dateService.getHolidays().subscribe((data : Holiday []) => {
			this.holidays = data;
		})
	}

}
