/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookGUI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author rajarshi
 */
@ManagedBean
@RequestScoped
public class DbQueryBean {
    /**
     * Creates a new instance of DbQueryBean
     */
    private String Search;
    public DbQueryBean() {
    }

    public String getSearch() {
        return Search;
    }

    public void setSearch(String Search) {
        this.Search = Search;
    }

    public DbQueryBean(String Search) {
        this.Search = Search;
    }
    
     public ArrayList<DbUpdateBean> Filter_Results() {
	        ArrayList<DbUpdateBean> arr = new ArrayList<>();
                Connection conn_sess = JDBC_Connections.dbconnect();
	   //Connection conn_sess = Connectionclass_new.dbconnect();
          ResultSet rs = null;
                rs  = JDBC_Connections.showDBreslts(conn_sess);
                //////////////////////showDBreslts
            String errorMessage = null;
          int flag_search = 0;
        try {
            
            while (rs.next()) {
                
                if(rs.getString("ISBN").equals(this.getSearch())||rs.getString("Title").toLowerCase().equals(this.getSearch().toLowerCase())|| rs.getString("AFname").toLowerCase().equals(this.getSearch().toLowerCase()) || rs.getString("ALname").toLowerCase().equals(this.getSearch().toLowerCase()))
                {
                    flag_search = 1;
                    DbUpdateBean at = new DbUpdateBean();
                    at.setISBN(rs.getString("ISBN"));
                    at.setTitle(rs.getString("Title"));
                    at.setEdition(rs.getString("EditionNumber"));
                    at.setCopyright(rs.getString("Copyright"));
                    at.setFirstName(rs.getString("AFname"));
                    at.setLastName(rs.getString("ALname"));
                    arr.add(at);
                }
            }
            errorMessage = "Books found for your search....";
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(DbUpdateBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
           if(arr.isEmpty())
           {
	     errorMessage = "Sorry noo books found for your search....!!";
           }
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(errorMessage));
               return arr;
    }
     
      public ArrayList<DbUpdateBean> Show_AllBooks() {
	        ArrayList<DbUpdateBean> arr = new ArrayList<>();
                Connection conn_sess = JDBC_Connections.dbconnect();
	   //Connection conn_sess = Connectionclass_new.dbconnect();
          ResultSet rs = null;
                rs  = JDBC_Connections.showDBreslts(conn_sess);
            String errorMessage = null;
          int flag_search = 0;
        try {
            
            while (rs.next()) {
                
                //if(rs.getString("ISBN").equals(this.getSearch())||rs.getString("Title").toLowerCase().equals(this.getSearch().toLowerCase())|| rs.getString("AFname").toLowerCase().equals(this.getSearch().toLowerCase()) || rs.getString("ALname").toLowerCase().equals(this.getSearch().toLowerCase()))
                {
                    flag_search = 1;
                    DbUpdateBean at = new DbUpdateBean();
                    at.setISBN(rs.getString("ISBN"));
                    at.setTitle(rs.getString("Title"));
                    at.setEdition(rs.getString("EditionNumber"));
                    at.setCopyright(rs.getString("Copyright"));
                    at.setFirstName(rs.getString("AFname"));
                    at.setLastName(rs.getString("ALname"));
                    arr.add(at);
                    
                }
            }
            //errorMessage = "Books found for your search....";
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(DbUpdateBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
           
            return arr;
    }
      
}
