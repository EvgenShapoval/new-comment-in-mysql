package data;

import java.sql.*;

public class JDBCBase {

	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://db1.cityhost.com.ua/agrobuild";

	   //  Database credentials
	   static final String USER = "onRequest";
	   static final String PASS = "onRequest";
//	 	I will provide username and password ("onRequest") 
//	   on demand to email: comsilium@gmail.com
	   
	   Connection conn = null;
	   Statement stmt = null;

	   
	   public String selectAuthorEmail(String nameAuthorSel) 
			   throws SQLException, ClassNotFoundException {
		   
		      String authorEmail = "";
		   
	   try{
		      //STEP 2: Register JDBC driver
		      Class.forName(JDBC_DRIVER);

		      //STEP 3: Open a connection
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      
		      //STEP 4: Execute a query
		      stmt = conn.createStatement();
      
		      String sql = "SELECT comment_author_email FROM wp_comments "
		      		+ "WHERE comment_author='" + nameAuthorSel + "'";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
//		         int id  = rs.getInt("comment_ID");
//		         String authorName = rs.getString("comment_author");
		         authorEmail = rs.getString("comment_author_email");

//		         //Display values
		         System.out.println("Author Email: " + authorEmail);
		      }
		      rs.close();
				
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
	  
	   return authorEmail; 
	   } 
	   
	   public void deleteRow(String nameAuthorDel) 
			   throws SQLException, ClassNotFoundException {
	   
		   try{
		   //STEP 2: Register JDBC driver
		      Class.forName(JDBC_DRIVER);

		      //STEP 3: Open a connection
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      
		      //STEP 4: Execute a query
		      stmt = conn.createStatement();
   
		      String sql = "DELETE FROM wp_comments " +
	                   "WHERE comment_author='" + nameAuthorDel + "'";
		      stmt.executeUpdate(sql);
		      
				System.out.println("Delete Row");
		      
		   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			         System.out.println("--- Connection MySQL close ---");
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
	   }
}