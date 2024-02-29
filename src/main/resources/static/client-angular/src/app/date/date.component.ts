import { HttpClient               } from '@angular/common/http';
import { HttpHeaders              } from '@angular/common/http';
import { Component                } from '@angular/core';
import { MatDatepickerModule      } from '@angular/material/datepicker';
import { MatFormFieldModule       } from '@angular/material/form-field';
import { MatCardModule            } from '@angular/material/card';
import { provideNativeDateAdapter } from '@angular/material/core';
import { ActivatedRoute           } from '@angular/router';
import { Router                   } from '@angular/router';
import { DateService              } from './date.service'

@Component({
	selector    : 'app-date',
	templateUrl : './date.component.html',
	styleUrl    : './date.component.css',
	standalone  : true,
	imports     : [
		MatCardModule,
		MatDatepickerModule
	],
	providers   : [
		provideNativeDateAdapter()
	]
})

export class DateComponent {

	activeDate        : Date   | null;
	activeDescription : string | null;

	constructor (
		private router          : Router,
		private routerActivated : ActivatedRoute,
		private dateService     : DateService
	) {}

	public queryDate () {
		if (this.activeDate) {
			const date = this.activeDate.toISOString().split('T')[0];

			this.dateService.getDateType(date).subscribe((response : any) => {
				console.log(response);
			});

			this.router.navigate([], {
				relativeTo          : this.routerActivated,
				queryParams         : { 'selected' : date },
				queryParamsHandling : 'merge'
			});
		}
		else {
			console.log("The query date is null.");
		}
	}

}
