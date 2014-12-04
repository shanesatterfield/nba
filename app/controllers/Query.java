package controllers;

import java.util.*;
import java.sql.*;

import play.*;
import play.db.*;

public class Query {
	public static ArrayList<ArrayList<String>> insertRow() {
		final String sqlCode = "INSERT INTO Team (teamName,teamCity,teamState,teamHomeColors,teamAwayColors,teamRanking,teamDivision,teamConference) VALUES ('asdf', 'Coast City', 'CC', 'Green', 'Yellow', 30, 'Pacific', 'Western');";
		query(sqlCode, false);

		ArrayList<ArrayList<String>> result = query("SELECT teamName AS Team, teamCity AS City, teamState AS State, teamHomeColors AS 'Home Colors', teamAwayColors AS 'Away Colors', teamRanking AS Ranking, teamDivision AS Division, teamConference AS Conference FROM Team;", true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});

		return result;
	}

	public static ArrayList<ArrayList<String>> updateRow() {
		final String sqlCode = "UPDATE Team SET teamState = 'NY' WHERE teamName = 'asdf';";
		query(sqlCode, false);

		ArrayList<ArrayList<String>> result = query("SELECT teamName AS Team, teamCity AS City, teamState AS State, teamHomeColors AS 'Home Colors', teamAwayColors AS 'Away Colors', teamRanking AS Ranking, teamDivision AS Division, teamConference AS Conference FROM Team;", true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});

		return result;
	}

	public static ArrayList<ArrayList<String>> deleteRow() {
		final String sqlCode = "DELETE FROM Team WHERE teamName = 'asdf';";
		query(sqlCode, false);

		ArrayList<ArrayList<String>> result = query("SELECT teamName AS Team, teamCity AS City, teamState AS State, teamHomeColors AS 'Home Colors', teamAwayColors AS 'Away Colors', teamRanking AS Ranking, teamDivision AS Division, teamConference AS Conference FROM Team;", true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});

		return result;
	}

	public static ArrayList<ArrayList<String>> query1() {		
		final String sqlCode = "SELECT T.teamName AS 'Team Name', SUM(P.playerWeight) AS 'Total Weight', COUNT(S.staffID) AS 'Staff Members'\n" +
		"FROM Person PS\n" +
		"LEFT JOIN Player P\n" +
		"ON PS.personID = P.playerID\n" +
		"LEFT JOIN Staff S\n" +
		"ON PS.personID = S.staffID\n" +
		"INNER JOIN Employment E\n" +
		"ON PS.personID = E.employmentID\n" +
		"INNER JOIN Team T\n" +
		"ON E.employmentTeam = T.teamName\n" +
		"GROUP BY T.teamName\n" +
		"HAVING SUM(P.playerWeight) > 3300";
		ArrayList<ArrayList<String>> result = query(sqlCode, true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});
		return result;
	}

	public static ArrayList<ArrayList<String>> query2() {		
		final String sqlCode = "SELECT T.teamName AS 'Team Name', PS.personFirstName AS 'First Name', PS.personLastName AS 'Last Name', COALESCE(P.playerPosition, S.staffTitle) AS 'Position/Title'\n" +
		"FROM Person PS\n" +
		"LEFT JOIN Player P\n" +
		"\tON PS.personID = P.playerID\n" +
		"LEFT JOIN Staff S\n" +
		"\tON PS.personID = S.staffID\n" +
		"INNER JOIN Employment E\n" +
		"\tON PS.personID = E.employmentID\n" +
		"INNER JOIN Team T\n" +
		"\tON E.employmentTeam = T.teamName\n" +
		"WHERE T.teamName = 'Clippers'\n" +
		"ORDER BY T.TeamName";
		ArrayList<ArrayList<String>> result = query(sqlCode, true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});
		return result;
	}

	public static ArrayList<ArrayList<String>> query3() {		
		final String sqlCode = "SELECT T.teamName, PS.personFirstName, PS.personLastName\n" +
		"FROM Team T\n" +
		"INNER JOIN Employment E\n" +
		"ON T.teamName = E.employmentTeam\n" +
		"INNER JOIN Person PS\n" +
		"ON E.employmentID = PS.personID\n" +
		"INNER JOIN Player P\n" +
		"ON PS.personID = P.playerID\n" +
		"WHERE P.playerPosition = 'Power Forward'";
		ArrayList<ArrayList<String>> result = query(sqlCode, true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});
		return result;
	}

	public static ArrayList<ArrayList<String>> query4() {		
		final String sqlCode = "SELECT  G.gameAwayTeam, COUNT(G.gameNumber), MAX(C.CoachName) AS 'Head Coach'\n" +
		"FROM    Game G\n" +
		"INNER JOIN Team T\n" +
		"ON G.gameAwayTeam = T.teamName\n" +
		"LEFT JOIN (SELECT E.employmentTeam AS CoachTeam, CONCAT(P.personFirstName, ' ', P.personLastName) AS CoachName FROM Person P INNER JOIN Staff S ON P.personID = S.staffID INNER JOIN Employment E ON P.personID = E.employmentID WHERE S.staffTitle = 'Head Coach') C\n" +
		"ON T.teamName = C. CoachTeam\n" +
		"WHERE   G.GameDate <= '2014-12-31'\n" +
		"        AND G.GameDate >= '2014-12-01'\n" +
		"GROUP BY G.gameAwayTeam\n" +
		"HAVING COUNT(G.gameAwayTeam) = (SELECT MAX(X.GameCount)\n" +
		"            FROM (SELECT COUNT(D.gameNumber) AS GameCount\n" +
		"                    FROM Game D\n" +
		"                    WHERE D.GameDate <= '2014-12-31'\n" +
		"                    AND D.GameDate >= '2014-12-01'\n" +
		"                    GROUP BY D.gameAwayTeam) X\n" +
		"            )";
		ArrayList<ArrayList<String>> result = query(sqlCode, true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});
		return result;
	}

	public static ArrayList<ArrayList<String>> query5() {		
		final String sqlCode = "SELECT conferenceName, MIN(total) AS rankingsTotal\n" +
		"FROM\n" +
		"(\n" +
		"	(SELECT conferenceName, SUM(teamRanking) AS total\n" +
		"	 FROM Team\n" +
		"	 INNER JOIN Division\n" +
		"	 ON Team.teamDivision = Division.divisionName\n" +
		"	 INNER JOIN Conference\n" +
		"	 ON Division.divisionConfer = Conference.conferenceName\n" +
		"	 WHERE conferenceName = 'Eastern'\n" +
		"     )\n" +
		"	 UNION(\n" +
		"	 	SELECT conferenceName, SUM(teamRanking) AS total\n" +
		"	 	FROM Team\n" +
		"	 	INNER JOIN Division\n" +
		"	 	ON Team.teamDivision = Division.divisionName\n" +
		"	 	INNER JOIN Conference\n" +
		"	 	ON Division.divisionConfer = Conference.conferenceName\n" +
		"	 	WHERE conferenceName = 'Western'\n" +
		"     )\n" +
		")AS sums";
		ArrayList<ArrayList<String>> result = query(sqlCode, true);
		result.add(0, new ArrayList<String>(){{ add(sqlCode); }});
		return result;
	}

	public static ArrayList<ArrayList<String>> query6() {		
		final String sqlCode = "SELECT teamName, venueName, COUNT(Game.gameNumber) AS numberOfGames\n" +
		"FROM Team\n" +
		"LEFT OUTER JOIN Game\n" +
		"\tON Team.teamName = Game.gameHomeTeam OR Team.teamName = Game.gameAwayTeam\n" +
		"LEFT OUTER JOIN Venue\n" +
		"\tON Game.gameVenue = Venue.venueName\n" +
		"LEFT OUTER JOIN Network\n" +
		"\tON Game.gameNetwork = Network.networkName\n" +
		"WHERE Network.networkName IN ( SELECT networkName FROM Network WHERE networkName <> 'NBA TV') AND Venue.venueName='Staples Center'\n" +
		"GROUP BY Team.teamName\n" +
		"HAVING COUNT(Game.gameNumber) >=2;";
		
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