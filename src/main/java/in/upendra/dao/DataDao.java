package in.upendra.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import in.upendra.user;

public class DataDao {
	private static  String DB_Driver="com.mysql.cj.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/upendra";
	private static String uname="root";
	private static String upwd="Upendra@123";
	
	

	public int connection(user u) throws SQLException, ClassNotFoundException {
		Class.forName(DB_Driver);
		String sql="insert into user_info (name,email,phno)values(?,?,?)";
		Connection con =DriverManager.getConnection(url, uname, upwd);
		PreparedStatement stm= con.prepareStatement(sql);
			stm.setString(1,u.getName());
			stm.setString(2, u.getEmail());
			stm.setInt(3, u.getPhno());

			return stm.executeUpdate();	
		
	}
	public List<user> getAllRecords(int id) throws ClassNotFoundException, SQLException {
	    Class.forName(DB_Driver);
	    Connection con = DriverManager.getConnection(url, uname, upwd);
	    StringBuilder allRecords = new StringBuilder("select * from user_info");

	    if (id != 0) {
	        allRecords.append(" where id=?");
	    }

	    PreparedStatement pstmt = con.prepareStatement(allRecords.toString());

	    if (id != 0) {
	        pstmt.setInt(1, id);
	    }

	    ResultSet r = pstmt.executeQuery();
	    List<user> data1 = new ArrayList<>();

	    while (r.next()) {
	        user u = new user();
	        u.setId(r.getInt(1));
	        u.setName(r.getString(2));
	        u.setEmail(r.getString(3));
	        u.setPhno(r.getInt(4));
	        data1.add(u);
	    }
	    
	    return data1;
	}
	
	
	//public user editData(int id) throws Exception {
		//Class.forName(DB_Driver);
	   // Connection con = DriverManager.getConnection(url, uname, upwd);
	   // user user = new user();
	   // String query="select * from user_info where id=?";
	   // PreparedStatement ps = con.prepareStatement(query);
	   // ps.setInt(1, id);
	  //  ResultSet r = ps.executeQuery();
	  //  if(r.next()) {
	    	//user.setId(r.getInt(1));
	    	//user.setName(r.getString(2));
	    	//user.setEmail(r.getString(3));
	    	//user.setPhone(r.getInt(4));

	   // }
		//return user;
		
	//}
	
	
    public boolean updateUser(user u) throws Exception {
    	Class.forName(DB_Driver);
	    Connection con = DriverManager.getConnection(url, uname, upwd);
        String updateQuery = "UPDATE user_info SET name=?, email=?, phno=? WHERE id=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, u.getName());
            preparedStatement.setString(2, u.getEmail());
            preparedStatement.setInt(3, u.getPhno());
            preparedStatement.setInt(4, u.getId());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
		} catch (SQLException e) {
		e.printStackTrace();
		}
            return false; 
            
            
    }

	
    public boolean deleteUser(int id) throws SQLException, ClassNotFoundException {
       boolean f=false;
    	Class.forName(DB_Driver);

        String sql = "DELETE FROM user_info WHERE id = ?";
        try (	    Connection connection = DriverManager.getConnection(url, uname, upwd);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
           if(rowsAffected==1) {
        	   f=true;
           }
         
        }
		return f;
    }
}



