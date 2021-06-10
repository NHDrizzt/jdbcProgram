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
import db.DbException;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			
			st = conn.prepareStatement("update seller set BaseSalary = 29090 where Id in (2,4)");
			
			int row1 = st.executeUpdate();
			if(row1 < 2) {
				throw new SQLException("Fake error");
			}
			int row2 = st.executeUpdate("update seller set BaseSalary = 200 where Id = 3");
			
			
			conn.commit();
			
			System.out.println("row1: " + row1);
			System.out.println("row2: " + row2);

			int rowsAffected = st.executeUpdate();
			System.out.println("Rows Affected: "+ rowsAffected);
			
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Rolledback! Cause by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error: " + e1.getMessage());
			}
		}
		finally {
			DB.closeConnection();
			DB.closeStatement(st);
		}
	}

}
