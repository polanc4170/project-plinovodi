import { NgModule         } from '@angular/core';
import { RouterModule     } from '@angular/router';
import { Routes           } from '@angular/router';
import { HomeComponent    } from "./home/home.component";
import { HolidayComponent } from "./date/holiday/holiday.component";
import { DateComponent    } from "./date/date.component";

const routes : Routes = [
	{ path : '',             component :    HomeComponent },
	{ path : 'date_holiday', component : HolidayComponent },
	{ path : 'date',         component :    DateComponent },
	{ path : '**',           component :    HomeComponent }
];

@NgModule({
	imports: [ RouterModule.forRoot(routes) ],
	exports: [ RouterModule ]
})

export class AppRoutingModule {}
