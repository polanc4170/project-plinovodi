import { Switch }    from "react-router-dom";
import { Route  }    from "react-router-dom";
import React         from "react";
import Date          from "./date";
import Error         from "./error";
import Holidays      from "./holidays";
import Home          from "./home";
import Intervention  from "./intervention";
import Interventions from "./interventions";
import Menu          from "./menu";
import Report        from "./report";
import Reports       from "./reports";
import User          from "./user";
import Users         from "./users";

export default function Application () {

	return (
		<div>
			<Menu/>

			<Switch>
				<Route exact path = "/"                     ><Home         /></Route>
				<Route exact path = "/date/holiday"         ><Holidays     /></Route>
				<Route exact path = "/date/:date"           ><Date         /></Route>
				<Route exact path = "/user/report"          ><Reports      /></Route>
				<Route exact path = "/user/report/:id"      ><Report       /></Route>
				<Route exact path = "/user/intervention"    ><Intervention /></Route>
				<Route exact path = "/user/intervention/:id"><Interventions/></Route>
				<Route exact path = "/user/"                ><Users        /></Route>
				<Route exact path = "/user/:id"             ><User         /></Route>
				<Route exact path = "*"                     ><Error        /></Route>
			</Switch>
		</div>
	);

}
