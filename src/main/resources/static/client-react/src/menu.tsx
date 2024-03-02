import { NavLink }   from "react-router-dom";
import "./css/menu.css";

export default function Menu () {

	return (
		<nav>
			<NavLink exact to = "/"                 >Home         </NavLink>
			<NavLink exact to = "/date/2024-03-01"  >Date         </NavLink>
			<NavLink exact to = "/date/holiday"     >Holidays     </NavLink>
			<NavLink exact to = "/user"             >Users        </NavLink>
			<NavLink exact to = "/user/report"      >Reports      </NavLink>
			<NavLink exact to = "/user/intervention">Interventions</NavLink>
		</nav>
	);

}
