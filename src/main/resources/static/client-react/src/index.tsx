import { BrowserRouter } from "react-router-dom";
import React             from "react";
import ReactDOM          from "react-dom";
import Application       from "./application";
import "./css/index.css";

ReactDOM.render(
	<BrowserRouter>
		<Application/>
	</BrowserRouter>,

	document.getElementById("root")
);

