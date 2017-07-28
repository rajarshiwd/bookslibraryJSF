/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookGUI;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
/**
 *
 * @author mounica yalamanchili
 */
public class JDBC_Connections {
    Connection conn = null;
	private static String dbUserName = "root";
	private static String dbPassword = "Mounica";
	public static Connection dbconnect(){
		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
			//JOptionPane.showMessageDialog(null, "Connected Successfully");
			return conn;
			//return null;
		}
		catch(Exception e)
		{
			//JOptionPane.showMessageDialog(null, e);
			
		}
		return null;
			
	}
        
	public static ResultSet showTables(){

		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
	
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "show tables";
			
			PreparedStatement pst1 = conn.prepareStatement(query1);
				
			ResultSet rs2 = pst1.executeQuery();
				
			return rs2;
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}
	
	public static ResultSet showAuthors()
	{
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "select FirstName, LastName from authors";
			
			PreparedStatement pst1 = conn.prepareStatement(query1);
			
			//pst1.setString(1,(String)comboBox.getSelectedItem());
			
			ResultSet rs2 = pst1.executeQuery();
				
			return rs2;
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	
	}
	public static ResultSet showDBreslts(Connection conn)
	{
	//	String Connstring = "jdbc:mysql://localhost:3306/Book";
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
			String query1 = "SELECT titles.ISBN, Title, EditionNumber,Copyright,FirstName as AFname,LastName as ALname from titles, authorisbn,authors where titles.isbn = authorisbn.ISBN and authorisbn.AuthorID = authors.AuthorID ";
			
			PreparedStatement pst1 = conn.prepareStatement(query1);
			
			//pst1.setString(1,(String)comboBox.getSelectedItem());
			
			ResultSet rs2 = pst1.executeQuery();
				
			return rs2;
		
		}
		catch(Exception e)
		{
			//JOptionPane.showMessageDialog(null, e);
			return null;
		}
	
	}
        public static int row_Count(Connection conn)
	{
	//	String Connstring = "jdbc:mysql://localhost:3306/Book";
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
			
	//	Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "select count(ISBN) from titles";
			
			PreparedStatement pst1 = conn.prepareStatement(query1);
			
			//pst1.setString(1,(String)comboBox.getSelectedItem());
			
			ResultSet rs2 = pst1.executeQuery();
                        int count = 0;
                        
                        while(rs2.next())
                        {
                            count = rs2.getInt(0);
                        }
			return count;
		
		}
		catch(Exception e)
		{
			//JOptionPane.showMessageDialog(null, e);
			return 0;
		}
	
	}
	public static ResultSet showDBreslts(String ISBN)
	{
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			int authid = JDBC_Connections.getAuthId(ISBN);
			
			String query1 = "SELECT titles.ISBN, Title, EditionNumber,Copyright,FirstName as AFname,LastName as ALname from titles, authors where titles.isbn = ? and authors.AuthorID = ?";
			
			PreparedStatement pst1 = conn.prepareStatement(query1);
			pst1.setString(1, ISBN);
			pst1.setInt(2, authid);
			//pst1.setString(1,(String)comboBox.getSelectedItem());
			
			ResultSet rs2 = pst1.executeQuery();
				
			return rs2;
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	
	}
	
	
	
	public static ResultSet searchDB(String table, String columnname, String columnvalue ){
		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query = "Select * from book."+table+" "+"where"+" "+columnname+"=? ";
			
			PreparedStatement pst1 = conn.prepareStatement(query);
			
			pst1.setString(1,columnvalue);
			
			ResultSet rs2 = pst1.executeQuery();
				
			return rs2;
			/*ArayList <JavaBean> book = new ArrayList<JavaBean>();
			while(rs2.next())
			{
				JavaBean jb = new JavaBean();
				
			}*/
			
			//return null;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
			
	}
	
	
	//public static  ArrayList<JavaBean> searchDB_new(String table){
	public static  ResultSet searchDB_new(String table){
		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		
			
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query = "Select * from book."+table;
			
			PreparedStatement pst1 = conn.prepareStatement(query);
			
			ResultSet rs2 = pst1.executeQuery();
				
			return rs2;
			//return book;
		}
	
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		//	return null;
		}
		return null;
			
	}
	public static  int getAuthId(String Fname, String Lname){
		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		int maxauthid = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			PreparedStatement pst1 = null;
			ResultSet rs1 = null;
		
			if(Lname.equals("empty"))
			{
				
				String query = "Select AuthorID from Book.Authors where FirstName = ?";
				pst1 = conn.prepareStatement(query);
				pst1.setString(1,Fname);
				rs1 = pst1.executeQuery();
			}
			
			else if(Fname.equals("empty"))
			{
				
				String query = "Select AuthorID from Book.Authors where LastName = ?";
				pst1 = conn.prepareStatement(query);
				pst1.setString(1,Lname);
				rs1 = pst1.executeQuery();
			}

			else
			{
				String query = "Select AuthorID from Book.Authors where FirstName = ? and LastName = ?";
		
				pst1 = conn.prepareStatement(query);
				pst1.setString(1,Fname);
				pst1.setString(2,Lname);
				rs1 = pst1.executeQuery();
			}
			while (rs1.next()) {
				
				maxauthid = rs1.getInt(1);
			}
			
			
			rs1.close();
			pst1.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		System.out.println("GetAuthIdFname :"+maxauthid);
		
		return maxauthid;
			
	}
	
	
	public static  int getAuthId(){
		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		int maxauthid = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "Select * from Book.Authors";
				
			PreparedStatement pst2 = conn.prepareStatement(query1);
			ResultSet rs2 = pst2.executeQuery();
			
			while(rs2.next())
			{
				maxauthid = rs2.getInt(1);
			
			}
			rs2.close();
			pst2.close();
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		System.out.println("GetAuthId :"+maxauthid);
		
		return maxauthid;
			
	}
	
	public static  int getAuthId(String ISBN){
		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		int maxauthid = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "Select AuthorID from Book.authorisbn where ISBN = ?";
				
			PreparedStatement pst2 = conn.prepareStatement(query1);
			pst2.setString(1,  ISBN);
			ResultSet rs2 = pst2.executeQuery();
			
			while(rs2.next())
			{
				maxauthid = rs2.getInt(1);
				return maxauthid;
			}
			rs2.close();
			pst2.close();
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		System.out.println("GetAuthId :"+maxauthid);
		
		return maxauthid;
			
	}
	
	public static  int getISBN(String Bookname){
		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		int ISBN = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "Select ISBN from Book.titles where title = ?";
				
			PreparedStatement pst2 = conn.prepareStatement(query1);
			pst2.setString(1,Bookname);
			ResultSet rs2 = pst2.executeQuery();
			
			while(rs2.next())
			{
				ISBN = rs2.getInt(1);
			
			}
			rs2.close();
			pst2.close();
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		System.out.println("GetISBN :"+ISBN);
		
		return ISBN;
			
	}
	
	public static  int[] getISBN(int Authid){
		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		int [] ISBN = new int[100];
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "Select ISBN from Book.authorisbn where AuthorID = ?";
				
			PreparedStatement pst2 = conn.prepareStatement(query1);
			pst2.setInt(1,Authid);
			ResultSet rs2 = pst2.executeQuery();
			int i = 0;
			while(rs2.next())
			{
				ISBN[i ++] = rs2.getInt(1);
			
			}
			rs2.close();
			pst2.close();
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		int j = 0;
		while(true)
		{
			System.out.println("GetISBN :"+ISBN[j ++]);
			if (ISBN [j] == 0)
				break;
		}
		
		return ISBN;
			
	}
	
	public static  String[] getISBN_new(int Authid){
		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		String [] ISBN = new String[100];
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "Select ISBN from Book.authorisbn where AuthorID = ?";
				
			PreparedStatement pst2 = conn.prepareStatement(query1);
			pst2.setInt(1,Authid);
			ResultSet rs2 = pst2.executeQuery();
			int i = 0;
			while(rs2.next())
			{
				ISBN[i ++] = rs2.getString(1);
			
			}
			rs2.close();
			pst2.close();
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		int j = 0;
		//while(true)
		{
			//System.out.println("GetISBN :"+ISBN[j ++]);
			//if (ISBN [j].equals("0"))
				//break;
		}
		
		return ISBN;
			
	}
	
	public static  int getISBN_count(String ISBN){
		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		int ISBN_count = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "SELECT ISBN, COUNT(*) FROM authorisbn where ISBN = ?";
				
			PreparedStatement pst2 = conn.prepareStatement(query1);
			pst2.setString(1,ISBN);
			ResultSet rs2 = pst2.executeQuery();
			//int i = 0;
			while(rs2.next())
			{
				ISBN_count = rs2.getInt(2);
			
			}
			rs2.close();
			pst2.close();
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	
		return ISBN_count;
			
	}
	
	public static  int getISBN_count(int authid){
		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		int ISBN_count = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "SELECT ISBN, COUNT(*) FROM authorisbn where AuthorID = ?";
				
			PreparedStatement pst2 = conn.prepareStatement(query1);
			pst2.setInt(1,authid);
			ResultSet rs2 = pst2.executeQuery();
			//int i = 0;
			while(rs2.next())
			{
				ISBN_count = rs2.getInt(2);
			
			}
			rs2.close();
			pst2.close();
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	
		return ISBN_count;
			
	}
	
	public static  boolean Update (String params[], Connection conn){
		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		int ISBN_count = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			
			if(params.length == 3)
			{
				System.out.println("Length is 3");
				int i = 0;
				String names[] = new String[10];
				for (String name:params)
				{
					names[i] = name;
					System.out.println("values in string array I:"+i+" "+names[i++]);
					
				}
				
				if(names[0].equals("Copyright")|| names[0].equals("EditionNumber")||names[0].equals("Title"))
				{
					
					String query1 = "UPDATE titles set "+names[0]+"  =?"+" where ISBN = ?";
					System.out.println("Query"+query1);
					//int Authid = Connectionclass.getAuthId(names[0]);
					//System.out.println("AuthorId:"+Authid);
					PreparedStatement pst1 = conn.prepareStatement(query1);
					pst1.setString(1, names[1]);
					pst1.setString(2, names[2]);
					pst1.execute();
				}
				else
				{
					int Authid = JDBC_Connections.getAuthId(names[2]);
					System.out.println("AuthorId:"+Authid);
					String query2 = "UPDATE authors set "+names[0]+" =?"+" where AuthorID = ?";
					PreparedStatement pst2 = conn.prepareStatement(query2);
					pst2.setString(1, names[1]);
					pst2.setInt(2, Authid);
					pst2.execute();
				}
				
				
			}
			else
			{
			int i = 0;
			String names[] = new String[10];
			for (String name:params)
			{
				names[i] = name;
				System.out.println("values in string array I:"+i+" "+names[i++]);
			}
			
			String query1 = "UPDATE titles set Title = ?,EditionNumber = ?,Copyright = ? where ISBN = ?";
			
			int Authid = JDBC_Connections.getAuthId(names[0]);
			System.out.println("AuthorId:"+Authid);
			String query2 = "UPDATE authors set  FirstName = ?,LastName = ? where AuthorID = ?";
			
			PreparedStatement pst1 = conn.prepareStatement(query1);
			pst1.setString(1, names[1]);
			pst1.setString(2,names[2]);
			pst1.setString(3,names[3]);
			pst1.setString(4,names[0]);
			
			pst1.execute();
			PreparedStatement pst2 = conn.prepareStatement(query2);
			pst2.setString(1, names[4]);
			pst2.setString(2,names[5]);
			pst2.setInt(3, Authid);
			pst2.execute();
		

			pst1.close();
		
			pst2.close();
		}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
                        return false;
		}
	
			return true;
	}
	
public static  boolean Insert(String [] Params, Connection connection){
		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
		int ISBN_count = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			String ISBN = Params[0];
			String Bookname = Params[1];
			String Edition = Params[2];
			String Copyright = Params[3];
			String AFname = Params[4];
			String ALname = Params[5];
			//Connection connection = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
	
			
			
			String names[] = new String[10];
			int i = 0;
			for (String name:Params)
			{
				names[i] = name;
				System.out.println("values in string array I:"+i+" "+names[i++]);
				
			}
			
			String query1 = "insert into book.titles(ISBN, TITLE, EditionNumber,Copyright) values(?,?,?,?)";
			
			PreparedStatement pst1 = connection.prepareStatement(query1);
			pst1.setString(1,names[0]);
			pst1.setString(2,names[1]);
			pst1.setString(3,names[2]);
			pst1.setString(4,names[3]);
			
			String query2 = "insert into book.authors(FirstName, LastName) values(?, ?)";
			
			System.out.println(query2);
			PreparedStatement pst2 = connection.prepareStatement(query2);
			
			//pst2.setString(1, textFieldAuthorId.getText());
			pst2.setString(1,names[4]);
			pst2.setString(2,names[5]);
			
			

			
				int ISBN_new = JDBC_Connections.getISBN(Bookname);
				if(ISBN_new == 0)
				{
					pst1.execute();
					pst1.close();
				}
				int curr_id = JDBC_Connections.getAuthId(AFname, ALname);
				if(curr_id == 0)
				{
					pst2.execute();
					pst2.close();
					String query3 = "insert into book.authorisbn(AuthorID, ISBN) values(?,?)";
				
					PreparedStatement pst3 = connection.prepareStatement(query3);
						int Auth_id = JDBC_Connections.getAuthId();
						pst3.setInt(1, Auth_id);
						if(ISBN_new == 0)
						{
							pst3.setString(2,ISBN);
						}
						else if(ISBN_new != 0)
						{
							pst3.setInt(2,ISBN_new);
						}
					//System.out.println("Auth_id"+Auth_id+ " textfield value is:"+textFieldIsbn.getText());
						pst3.execute();
						pst3.close();
			//		JOptionPane.showMessageDialog(null, "Book Added Sucessfully");
				}
				
				else if(curr_id != 0)
				{
					//pst2.execute();
					//pst2.close();
					
					String query3 = "insert into book.authorisbn(AuthorID, ISBN) values(?,?)";
				
					PreparedStatement pst3 = connection.prepareStatement(query3);
						int Auth_id = JDBC_Connections.getAuthId(AFname, ALname);
						pst3.setInt(1, Auth_id);
						//pst3.setString(2,textFieldIsbn.getText());
						if(ISBN_new == 0)
						{
							pst3.setString(2,ISBN);
						}
						else if(ISBN_new != 0)
						{
							pst3.setInt(2,ISBN_new);
						}
					//System.out.println("Auth_id"+Auth_id+ " textfield value is:"+textFieldIsbn.getText());
						pst3.execute();
						pst3.close();
					
				}
			
		
				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
                        return false;
		}
                
                return true;
	}

	public static boolean delete(String [] Params, Connection connection){
	
		

		
		String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			String AFname = Params[0];
			String ALname = Params[1];
			String ISBN = Params[2];
			String  Bookname = Params[3];
		//	Connection connection = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		
		String query1 = "delete from authorisbn where ISBN = ?";
		
		PreparedStatement pst1 = connection.prepareStatement(query1);
		pst1.setString(1,ISBN);
		
		String query2 = "delete from titles where Title = '"+ Bookname + "'  ";
		 //System.out.println(query1);
		
		PreparedStatement pst2 = connection.prepareStatement(query2);
		
		int AuthorId = 0, ISBN_count = 0;
		
		String query3 = "delete from authors where AuthorId = ?";
		AuthorId = JDBC_Connections.getAuthId(ISBN);
		PreparedStatement pst3 = connection.prepareStatement(query3);
                pst3.setInt(1,AuthorId);
                pst1.execute();
                pst1.close();
                pst2.execute();
                pst2.close();
                //pst3.execute();
                //pst3.close();

	}
	catch(Exception e)
	{
            return false;
		//JOptionPane.showMessageDialog(null, e);
	}
                return true;
}
	

}
