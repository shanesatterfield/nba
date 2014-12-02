package controllers;

import java.util.*;
import java.sql.*;

import play.*;
import play.db.*;

public class Query {
	public static ArrayList<ArrayList<String>> insertRow() {
		String sqlCode = "INSERT INTO Team (teamName,teamCity,teamState,teamHomeColors,teamAwayColors,teamRanking,teamDivision,teamConference) VALUES ('asdf', 'Coast City', 'CC', 'Green', 'Yellow', 30, 'Pacific', 'Western');";
		query(sqlCode, false);

		ArrayList<ArrayList<String>> result = query("SELECT teamName AS Team, teamCity AS City, teamState AS State, teamHomeColors AS 'Home Colors', teamAwayColors AS 'Away Colors', teamRanking AS Ranking, teamDivision AS Division, teamConference AS Conference FROM Team;", true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});

		return result;
	}

	public static ArrayList<ArrayList<String>> updateRow() {
		String sqlCode = "UPDATE Team SET teamState = 'NY' WHERE teamName = 'asdf';";
		query(sqlCode, false);

		ArrayList<ArrayList<String>> result = query("SELECT teamName AS Team, teamCity AS City, teamState AS State, teamHomeColors AS 'Home Colors', teamAwayColors AS 'Away Colors', teamRanking AS Ranking, teamDivision AS Division, teamConference AS Conference FROM Team;", true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});

		return result;
	}

	public static ArrayList<ArrayList<String>> deleteRow() {
		String sqlCode = "DELETE FROM Team WHERE teamName = 'asdf';";
		query(sqlCode, false);

		ArrayList<ArrayList<String>> result = query("SELECT teamName AS Team, teamCity AS City, teamState AS State, teamHomeColors AS 'Home Colors', teamAwayColors AS 'Away Colors', teamRanking AS Ranking, teamDivision AS Division, teamConference AS Conference FROM Team;", true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});

		return result;
	}

	public static ArrayList<ArrayList<String>> query1() {		
		String sqlCode = "SELECT t.teamDivision AS Division, teamName as Team, t.teamRanking AS Ranking FROM Team t INNER JOIN (SELECT teamDivision, MIN(teamRanking) AS maxRank FROM Team t INNER JOIN Division d ON t.teamDivision = d.divisionName GROUP BY divisionName) r ON t.teamDivision = r.teamDivision AND t.teamRanking = r.maxRank;";
		ArrayList<ArrayList<String>> result = query(sqlCode, true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});
		return result;
	}

	public static ArrayList<ArrayList<String>> query2() {		
		String sqlCode = "SELECT teamName AS Team FROM Team WHERE teamName NOT IN (SELECT DISTINCT teamName FROM Network n INNER JOIN (SELECT gameHomeTeam AS teamName, gameNetwork FROM Game UNION SELECT gameAwayTeam AS teamName, gameNetwork FROM Game) g ON n.networkName = g.gameNetwork);";
		ArrayList<ArrayList<String>> result = query(sqlCode, true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});
		return result;
	}

	public static ArrayList<ArrayList<String>> query3() {		
		String sqlCode = "SELECT teamName AS Team FROM Team WHERE teamName NOT IN (SELECT DISTINCT gameHomeTeam FROM Network n INNER JOIN Game g ON n.networkName = g.gameNetwork);";
		ArrayList<ArrayList<String>> result = query(sqlCode, true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});
		return result;
	}


	public static ArrayList<ArrayList<String>> query( String sqlCode, boolean isQuery ) {
		Connection conn = null;
		Statement stmnt = null;
		ResultSet rs = null;
		ArrayList<ArrayList<String>> result = null;
		try {

			conn = DB.getConnection("nba");
			stmnt = conn.createStatement();

			if( isQuery )
				rs = stmnt.executeQuery( sqlCode );
			else {
				stmnt.executeUpdate( sqlCode );
				conn.commit();
			}

			result = getQueryData( rs );

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
			} catch( SQLException e ) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static ArrayList<ArrayList<String>> getQueryData( ResultSet rs ) throws SQLException
	{
		if( rs == null )
			return null;

		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

		// Do the headers.
		ArrayList<String> headers = new ArrayList<String>();
		ResultSetMetaData rsmd = rs.getMetaData();
		for( int i = 1; i <= rsmd.getColumnCount(); ++i )
			headers.add( rsmd.getColumnLabel(i) );
		result.add( headers );

		// Do the body of the query.		
		while( rs.next() ) {
			ArrayList<String> currRow = new ArrayList<String>();
			for( int i = 1; i <= headers.size(); ++i )
				currRow.add( rs.getString(i) );
			result.add( currRow );
		}

		return result;
	}
}