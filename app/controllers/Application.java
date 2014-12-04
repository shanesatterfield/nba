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
				result = Query.query1();
				break;
			case 2:
				result = Query.query2();
				break;
			case 3:
				result = Query.query3();
				break;
			case 4:
				result = Query.query4();
				break;
			case 5:
				result = Query.query5();
				break;
			case 6:
				result = Query.query6();
				break;
			case 7:
				result = Query.insertRow();
				break;
			case 8:
				result = Query.updateRow();
				break;
			case 9:
				result = Query.deleteRow();
				break;
		}
		if( result != null ) {
			return ok(Json.toJson(result));
		}

		return ok("Error querying");
	}
}
