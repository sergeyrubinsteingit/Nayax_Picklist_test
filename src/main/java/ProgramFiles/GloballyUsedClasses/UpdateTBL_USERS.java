package ProgramFiles.GloballyUsedClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTBL_USERS {

	public static void Update_TBL_USERS() {
		
		System.out.println("\n=============> TABLE USERS UPDATE <================\n");
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			String loginUrl = ProgramFiles.GloballyUsedClasses.TestDataStorage.LOGIN_URL_;
			
			String passToSqlManager = loginUrl.contains("qa2.") | loginUrl.contains("qa.") ? "qa2ilsql01:1433" : "QA1ILSQL01";			
			
			String sqlConnectionUrl =
				    "jdbc:sqlserver://" + passToSqlManager + ";" 
				    + "databaseName=DCS;"
		    		+ "integratedSecurity=true;" 
				    + "encrypt=true;trustServerCertificate=true";
		
			
			Connection connection = DriverManager.getConnection(sqlConnectionUrl); 
			
			Statement createStatement = connection.createStatement();
					
			//////////////////////////////////////////////////////////////
			
			String sqlQuery = 
			"DECLARE @TABLE_USERS TABLE (ix INT, usId varchar(20))\r\n"
			+ "INSERT INTO @TABLE_USERS VALUES(1,'83950145'),(2,'612180145'),(3,'612180145')\r\n"
			+ "\r\n"
			+ "DECLARE @USER_ID VARCHAR(100);\r\n"
			+ "SET @USER_ID = (SELECT usId FROM @TABLE_USERS WHERE ix=1)\r\n"
			+ "\r\n"
			+ "PRINT 'CURRENT USER id: ' + @USER_ID\r\n"
			+ "\r\n"
			+ "DECLARE @STATE_0 VARCHAR(100);\r\n"
			+ "SET @STATE_0 = '0';\r\n"
			+ "\r\n"
			+ "DECLARE @CURRENT_VAL VARCHAR(100);\r\n"
			+ "SET @CURRENT_VAL=(SELECT TBL_ACTOR_AGREEMENT_USER_STATE.state \r\n"
			+ "FROM TBL_ACTOR_AGREEMENT_USER_STATE \r\n"
			+ "WHERE TBL_ACTOR_AGREEMENT_USER_STATE.user_id=@USER_ID);\r\n"
			+ "\r\n"
			+ "IF @CURRENT_VAL != '0'\r\n"
			+ "	SET @STATE_0 = '0'\r\n"
			+ "\r\n"
			+ "UPDATE TBL_ACTOR_AGREEMENT_USER_STATE\r\n"
			+ "SET TBL_ACTOR_AGREEMENT_USER_STATE.state = @STATE_0\r\n"
			+ "WHERE user_id=@USER_ID\r\n"
			+ "\r\n"
			+ "SELECT * FROM TBL_ACTOR_AGREEMENT_USER_STATE\r\n"
			+ "WHERE user_id LIKE '%0145'";//query
			
			//////////////////////////////////////////////////////////////
			
			ResultSet queryResult = createStatement.executeQuery(sqlQuery);
			
			ResultSetMetaData metaData = queryResult.getMetaData();
			
			int columnCount = metaData.getColumnCount();

			while(queryResult.next()) {
				
			    for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
			        Object query_getObject = queryResult.getObject(columnIndex);
			        System.out.printf("%s, ", query_getObject == null ? "NULL" : query_getObject.toString());
			        
			    }//for
			    
			    System.out.printf("%n");
			    
			}//while
			
			/////////////////////////////////////////////////////////////////////////////////
			String user_state_query = 
			"SELECT state FROM TBL_ACTOR_AGREEMENT_USER_STATE\r\n"
			+ "WHERE user_id IN ('83950145')";//query
			/////////////////////////////////////////////////////////////////////////////////
			
			ResultSet user_state_queryResult = createStatement.executeQuery(user_state_query);
			
			while(user_state_queryResult.next()) {
				Object user_state_getObject = user_state_queryResult.getObject("state");			
				System.out.printf("%s, ", user_state_getObject.toString().equals("true") ? "=== state = 1 ===" : "---- state = 0 ----");
				System.out.printf("%n");
			};//while
						
			// releases the thread
			WaitAndNotify.Notify("Update Users Table");
//			System.out.printf("%s, ", user_state_int == 0 ? "====== State = 0 ========" : "-------  State != 0  ---------");
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.out.println(e.toString()+"\n");
			System.out.println("\n<<<<<<<<<<< Couldn't update TBL_ACTOR_AGREEMENT_USER_STATE. Test was shut down. >>>>>>>>>>>>\n");
			System.exit(-1);
			e.printStackTrace();
			
		}  //try
		
	}//Update_TBL_USERS
}// class
