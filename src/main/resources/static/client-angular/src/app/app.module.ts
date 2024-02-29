import { NgModule               } from '@angular/core';
import { HttpClientModule       } from '@angular/common/http';
import { FormsModule            } from '@angular/forms';
import { BrowserModule          } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AppComponent           } from './app.component';
import { AppRoutingModule       } from './app.routing.module';
import { HolidayComponent       } from './date/holiday/holiday.component';
import { DateComponent          } from './date/date.component';

@NgModule({
	declarations : [
		AppComponent,
		HolidayComponent
	],
	imports : [
		AppRoutingModule,
		BrowserModule,
		FormsModule,
		HttpClientModule,
		DateComponent
	],
	providers : [
		provideAnimationsAsync()
	],
	bootstrap : [
		AppComponent
	]
})

export class AppModule {}
