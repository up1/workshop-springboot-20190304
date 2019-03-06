package aqi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Demo {
	public static void main(String[] args) throws Exception {
		
		// 1. Load jdbc driver
		Class.forName("postgresql");
		
		// 2. Create connection
		Connection connection = DriverManager.getConnection("", "", "");
		
		// 3. Prepared Statement
		String sql = "SELECT * FROM TABLE WHERE name=?";
		PreparedStatement pStmt = connection.prepareStatement(sql);
		
		// 4. Query
		ResultSet resultSet = pStmt.executeQuery();
		while(resultSet.next()) {
			
		}
		
		// 5. Release resource
		if(resultSet != null) {
			resultSet.close();
			resultSet = null;
		}
				

	}
}
