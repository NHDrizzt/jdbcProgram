package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement("insert into seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "values (?,?,?,?,?) ", 
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, "KarlTheGuy");
			st.setString(2, "Karl@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("27/11/2000").getTime()));
			st.setDouble(4, 5000);
			st.setInt(5, 4);
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Id: "+id);
				}
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeConnection();
			DB.closeStatement(st);
			DB.closeResultSet(rs);		}
		
	}

}
