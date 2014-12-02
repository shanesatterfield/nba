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
    	String teamName = "Could not obtain.";
    	Connection conn = null;
    	Statement stmnt = null;
    	ResultSet rs = null;
    	ResultSetMetaData rsmd = null;
    	ArrayList<String> headers = new ArrayList<String>();
    	ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
    	String sqlCode = "SELECT * FROM Team;";
 
    	try {
    		conn = DB.getConnection("nba");

	    	stmnt = conn.createStatement();
	    	//rs = stmnt.executeQuery("SELECT teamName AS Name FROM Team;");
	    	//rs.next();
	    	//teamName = rs.getString("Name");
	    	rs = stmnt.executeQuery( sqlCode );

	    	rsmd = rs.getMetaData();
	    	for( int i = 1; i <= rsmd.getColumnCount(); ++i ) {
	    		headers.add( rsmd.getColumnName(i) );
	    	}

	    	while( rs.next() ) {
	    		ArrayList<String> currRow = new ArrayList<String>();
				for( int i = 1; i <= rsmd.getColumnCount(); ++i ) {
		    		currRow.add( rs.getString(i) );
		    	}	    		
	    		rows.add( currRow );
	    	}

	    } catch( SQLException e ) {
	    	e.printStackTrace();
	    } finally {
	    	try {
		    	if( rs != null )
		    		rs.close();
		    	
		    	if( stmnt != null )
		    		stmnt.close();
		    	
		    	if( conn != null )
		    		conn.close();
	    	} catch( SQLException e ) {}
	    }

        return ok(index.render("JDBC Test", rows, headers, sqlCode));
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
