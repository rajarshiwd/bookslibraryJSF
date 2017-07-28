/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookquery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author rajarshi
 */
@WebService(serviceName = "QueryBooks")
public class QueryBooks {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "GetBooks")
    public  ArrayList<String []> GetBooks(String a, String b) {
       
       
        Connection con = null;
        ResultSet rs = null;
        String [] Params = null;
        int i = 0, j = 1;
        String Connstring = "jdbc:mysql://localhost:3306/bookapplication";
	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(Connstring, "root", "Mounica");
			
			String query1 = "SELECT titles.ISBN, Title, EditionNumber,Copyright,FirstName as AFname,LastName as ALname from titles, authorisbn,authors authors where titles.isbn = authorisbn.ISBN and authorisbn.AuthorID = authors.AuthorID";
			//String query1 = "Select * from titles";
			PreparedStatement pst1 = conn.prepareStatement(query1);
				
                        rs = pst1.executeQuery();
		ArrayList<String []> arr = new ArrayList<>();		
                    while(rs.next())
                    {
                        //AuthorBean ab = new AuthorBean();
                            String[] ab = new String[6];
                        
                        ab[0] = rs.getString("ISBN");
                        ab[1] = rs.getString("Title");
                        ab[2] = rs.getString("EditionNumber");
                        ab[3] = rs.getString("Copyright");
                        ab[4] = rs.getString("AFname");
                        ab[5] = rs.getString("ALname");
                    
                        arr.add(ab);
                }
                    
                    return arr;
		}
		catch(Exception e)
                {
			return null;
		}
         
		
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "operation")
    public String operation() {
        //TODO write your implementation code here:
        return null;
    }
}
