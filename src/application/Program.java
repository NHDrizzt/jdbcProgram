package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DBIntegrityException;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					" delete from department "
					+ " WHERE "
					+ " (Id = ?)");
			
			st.setInt(1, 2);

			int rowsAffected = st.executeUpdate();
			System.out.println("Rows Affected: "+ rowsAffected);
			
		}
		catch(SQLException e) {
			throw new DBIntegrityException(e.getMessage());
		}
		finally {
			DB.closeConnection();
			DB.closeStatement(st);
		}
	}

}
