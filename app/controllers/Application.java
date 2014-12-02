package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.sql.*;
import play.db.*;

import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;


public class Application extends Controller {

    public static Result index() {
        return ok(index.render("JDBC Test"));
    }

    public static Result queryCommand( String num ) {
    	int theCommand = Integer.parseInt( num );
		ArrayList<ArrayList<String>> result = null;
		switch( theCommand ) {
			case 1:
				result = Query.insertRow();
				break;
			case 2:
				result = Query.updateRow();
				break;
			case 3:
				result = Query.deleteRow();
				break;
			case 4:
				result = Query.query1();
				break;
			case 5:
				result = Query.query2();
				break;
			case 6:
				result = Query.query3();
				break;
		}
		if( result != null ) {
			return ok(Json.toJson(result));
		}

		return ok("Error querying");
	}
}
