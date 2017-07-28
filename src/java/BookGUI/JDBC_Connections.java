/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookGUI;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
/**
 *
 * @author rajarshi
 */
public class JDBC_Connections {
    Connection conn = null;
	private static String dbUserName = "ryelamati";
	private static String dbPassword = "mnagaraju9";
	public static Connection dbconnect(){
		
		String Connstring = "jdbc:mysql://localhost:3306/books";
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

		
		String Connstring = "jdbc:mysql://localhost:3306/books";
	
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
		String Connstring = "jdbc:mysql://localhost:3306/books";
		
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
		String Connstring = "jdbc:mysql://localhost:3306/books";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			int authid = JDBC_Connections.getAuthId(ISBN);
			
			String query1 = "select titles.ISBN,titles.Title,titles.EditionNumber,titles.Copyright,authors.FirstName,authors.LastName from books.titles,books.authors where titles.ISBN =? and authors.AuthorID = ?";
                                        
			
			PreparedStatement pst1 = conn.prepareStatement(query1);
			pst1.setString(1, ISBN);
                       pst1.setInt(2, authid);
			
                        System.out.println(ISBN +"hii this the ISBN value");
                        System.out.println(authid +"hii this the ISBN value");
                        
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
	
	
	
	public static ResultSet searchDB(Connection conn,String table, String columnname, String columnvalue ){
		
		String Connstring = "jdbc:mysql://localhost:3306/books";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			 conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query = "Select * from books."+table+" "+"where"+" "+columnname+"=? ";
			
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
		
		String Connstring = "jdbc:mysql://localhost:3306/books";
		
			
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query = "Select * from books."+table;
			
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
		
		String Connstring = "jdbc:mysql://localhost:3306/books";
		int maxauthid = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			PreparedStatement pst1 = null;
			ResultSet rs1 = null;
		
			if(Lname.equals("empty"))
			{
				
				String query = "Select AuthorID from books.Authors where FirstName = ?";
				pst1 = conn.prepareStatement(query);
				pst1.setString(1,Fname);
				rs1 = pst1.executeQuery();
			}
			
			else if(Fname.equals("empty"))
			{
				
				String query = "Select AuthorID from books.Authors where LastName = ?";
				pst1 = conn.prepareStatement(query);
				pst1.setString(1,Lname);
				rs1 = pst1.executeQuery();
			}

			else
			{
				String query = "Select AuthorID from books.Authors where FirstName = ? and LastName = ?";
		
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
		
		String Connstring = "jdbc:mysql://localhost:3306/books";
		int maxauthid = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "Select * from books.Authors";
				
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
		
		String Connstring = "jdbc:mysql://localhost:3306/books";
		int maxauthid = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "Select AuthorID from books.authorisbn where ISBN = ?";
				
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
		
		String Connstring = "jdbc:mysql://localhost:3306/books";
		int ISBN = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "Select ISBN from books.titles where title = ?";
				
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
		
		String Connstring = "jdbc:mysql://localhost:3306/books";
		int [] ISBN = new int[100];
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "Select ISBN from books.authorisbn where AuthorID = ?";
				
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
		
		String Connstring = "jdbc:mysql://localhost:3306/books";
		String [] ISBN = new String[100];
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
		//	JOptionPane.showMessageDialog(null, "Connected Successfully");
			
			String query1 = "Select ISBN from books.authorisbn where AuthorID = ?";
				
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
		
		String Connstring = "jdbc:mysql://localhost:3306/books";
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
		
		String Connstring = "jdbc:mysql://localhost:3306/books";
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
	
	public static PreparedStatement UpdateData(String ISBN,String Title,String EditionNumber,String Copyright,String FirstName,String LastName,Connection connection){ 
    // Connection con=null;
      PreparedStatement pst1=null;
    String url="jdbc:mysql://localhost:3306/books";
    String name="ryelamati";
    String password="mnagaraju9";
    java.sql.Connection con=null;     
        try{
            Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection(url,name,password);
           String sql1=" Update Titles SET Title= ? ,EditionNumber= ?,Copyright=? WHERE ISBN= ? ";           
           String sql="SELECT AuthorID FROM AuthorISBN where ISBN='"+ISBN+"'";
           System.out.println("ISBN is"+ISBN);
           PreparedStatement pst3=con.prepareStatement(sql);
                ResultSet rs=pst3.executeQuery();
                int Authid=0;
                if(rs.next()){
                  Authid=rs.getInt("AuthorID");
                }
           
            String sql2="Update Authors SET FirstName=? , LastName=? Where AuthorID='"+Authid+"' ";
            PreparedStatement pst=con.prepareStatement(sql1);
            PreparedStatement pst2=con.prepareStatement(sql2);
            pst.setString(1, Title);
            pst.setString(2,EditionNumber);
            pst.setString(3, Copyright);
            pst2.setString(1, FirstName);
            pst2.setString(2,LastName);
            pst.setString(4, ISBN);
            System.out.println("ISBN"+ISBN);
            pst.executeUpdate();
            pst2.executeUpdate();
         // JOptionPane.showMessageDialog(null, "Book Updated Successfully!");
			
   }
     
     catch(SQLException ex){
         ex.printStackTrace();
     }  catch (ClassNotFoundException ex) {  
            Logger.getLogger(JDBC_Connections.class.getName()).log(Level.SEVERE, null, ex);
        }

     return pst1;
 }
	
public static void Add(String ISBN,String Title,String Editionno,String Copyright,String Fname,String Lname,Connection connection) throws ClassNotFoundException{        
    String Connstring="jdbc:mysql://localhost:3306/books";
   // String name="ryelamati";
   // String password="mnagaraju9";
    //java.sql.Connection con=null;     
        try{
            //Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(Connstring, dbUserName, dbPassword);
        String query1 = "INSERT INTO Titles(ISBN, TITLE, EditionNumber,Copyright) values('"+ISBN+"','"+Title+"','"+Editionno+"','"+Copyright+"')";
        PreparedStatement pst1 = con.prepareStatement(query1);	
        String query2 = "INSERT INTO Authors(FirstName, LastName) values('"+Fname+"' , '"+Lname+"' ) ";
        PreparedStatement pst2 = con.prepareStatement(query2);
        
        //JOptionPane.showMessageDialog(null, "executed1!");
         String sql1=" SELECT ISBN from Titles where Title='"+Title+"' and EditionNumber='"+Editionno+"'";
          Statement st1=con.createStatement();
            ResultSet rs=st1.executeQuery(sql1);
            int Book_isbn;
            if(rs.next()){
                Book_isbn=rs.getInt("ISBN");
           //    JOptionPane.showMessageDialog(null, "Executed 1!"+Book_isbn);
           //    JOptionPane.showMessageDialog(null, "Executed 1..Book written by!");
                
            String sql2= "SELECT AuthorID from Authors WHERE FirstName= '"+Fname+"' AND LastName='"+Lname+"' ";
            Statement st2=con.createStatement();
            ResultSet rs1=st2.executeQuery(sql2);
            JOptionPane.showMessageDialog(null, "Executed2!");
            int authId;
                int autoIncKeyFromFunc = -1;
                if(rs1.next()){
         
                    authId=rs1.getInt("AuthorID");
                    String Query3=" INSERT INTO AuthorISBN(AuthorID,ISBN) VALUES ('"+authId+"','"+Book_isbn+"')";
                    st2.executeUpdate(Query3);
              //     JOptionPane.showMessageDialog(null, "executed3!");
             //   JOptionPane.showMessageDialog(null, "Executed 1..Book read by!");
                }
                else{
                     pst2.executeUpdate();           
                    ResultSet rs3 =pst2.executeQuery("SELECT LAST_INSERT_ID()");
                    if (rs3.next()) {
                     autoIncKeyFromFunc = rs3.getInt(1);
                    String Query4=" INSERT INTO AuthorISBN(AuthorID,ISBN) VALUES ('"+autoIncKeyFromFunc+"','"+Book_isbn+"')";
                   PreparedStatement st3=con.prepareStatement(Query4);
                    st3.executeUpdate();
                  // JOptionPane.showMessageDialog(null, "executed4!");
                }
             }
            JOptionPane.showMessageDialog(null, "Book Added Successfully!");
            }
            else 
             {
                 
                 pst1.executeUpdate();
                    String sql2= "SELECT AuthorID from Authors WHERE FirstName= '"+Fname+"' AND LastName='"+Lname+"' ";
                    Statement st2=con.createStatement();
                    ResultSet rs10=st2.executeQuery(sql2);
                  //  JOptionPane.showMessageDialog(null, "Executed5!");
                    int authId;
                    int autoIncKeyFromFunc = -1;
                    if(rs10.next()){
         
                            authId=rs10.getInt("AuthorID");
                             String Query5=" INSERT INTO AuthorISBN(AuthorID,ISBN) VALUES ('"+authId+"','"+ISBN+"')";
                            st2.executeUpdate(Query5);
                     //      JOptionPane.showMessageDialog(null, "executed6!");
        
                    }
                  
            else{
        
                    pst2.executeUpdate();           
                    Statement st3=con.createStatement();
                    ResultSet rs3 =pst2.executeQuery("SELECT LAST_INSERT_ID()");
               //     JOptionPane.showMessageDialog(null, "executed7!");


                if (rs3.next()) {
                        autoIncKeyFromFunc = rs3.getInt(1);
                        String Query6=" INSERT INTO AuthorISBN(AuthorID,ISBN) VALUES ('"+autoIncKeyFromFunc+"','"+ISBN+"')";
                         st3.executeUpdate(Query6);
   //   JOptionPane.showMessageDialog(null, "executed8!");
       }
                    
        }
               
                
            }
            
                
       // JOptionPane.showMessageDialog(null, "Book Added Successfully!");
        
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

	public static void deleteData(String ISBN, Connection connection) throws ClassNotFoundException{
     //Connection con=null;
  String url="jdbc:mysql://localhost:3306/books";
    String name="ryelamati";
    String password="mnagaraju9";
    java.sql.Connection con=null; 
  
     try{
         Class.forName("com.mysql.jdbc.Driver");
        
         con=DriverManager.getConnection(url,name,password);
         String sql1="DELETE from AuthorISBN where ISBN='"+ISBN+"'";
             String sql2="DELETE from Titles WHERE ISBN='"+ISBN+"'";
             
             PreparedStatement pst1=con.prepareStatement(sql1);
             PreparedStatement pst2=con.prepareStatement(sql2);
           
         String sql="SELECT * FROM AuthorISBN WHERE ISBN='"+ISBN+"'";
         PreparedStatement pst=con.prepareStatement(sql);
         
         ResultSet rs= pst.executeQuery();
         int AuthID;
         //double BookISBN;
         if(rs.next()){
             AuthID=rs.getInt(1);
             //ookISBN=rs.getDouble(2);
             String sql3="DELETE from Authors where AuthorID='"+AuthID+"'"; 
             PreparedStatement pst3=con.prepareStatement(sql3);
                String Query1="SELECT COUNT(ISBN) FROM AuthorISBN WHERE AuthorID='"+AuthID+"'";
                PreparedStatement pst4=con.prepareStatement(Query1);
                ResultSet rs1=pst4.executeQuery();
                if(rs1.next()){
               int isbn=rs1.getInt(1);
              // System.out.println(isbn);
                    if(isbn==0){
                        //int confirm = JOptionPane.showConfirmDialog(null, "No books found for this Author, Do you really want to delete this Author?", "Delete", JOptionPane.YES_NO_OPTION);
			//if(confirm == 0)
			//{
				pst3.execute();
				pst3.close();
                           //      JOptionPane.showMessageDialog(null," Book Deleted Successfully...!!");
			//}
                    }
                    else if (isbn==1){
                      //  int confirm = JOptionPane.showConfirmDialog(null, "Do you really want to delete this Author?", "Delete", JOptionPane.YES_NO_OPTION);
			//if(confirm == 0)
			//{
                                pst1.executeUpdate();
                                pst2.executeUpdate();
				pst3.executeUpdate();
				pst3.close();
                                
                                pst2.close();
                                
                                pst1.close();
                        //      JOptionPane.showMessageDialog(null," Book Deleted Successfully...!!");
			//}
                    }
                    else
                    {
                        
                // int confirm = JOptionPane.showConfirmDialog(null, "Another Book found for this Author, Do you really want to delete this Author?", "Delete", JOptionPane.YES_NO_OPTION);
		//	if(confirm == 0)
		//	{
				pst1.execute();
				pst3.close();
                                pst2.execute();
                                pst2.close();
                          JOptionPane.showMessageDialog(null," Book Deleted Successfully...!!");       
			
                }
                    
                }
        }
            else{
                //JOptionPane.showMessageDialog(null, "Error!!");
            }
             
        
         }
     catch(SQLException ex){
          ex.printStackTrace();
     }
 
 }

}
