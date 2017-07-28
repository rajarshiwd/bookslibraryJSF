/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookGUI;
import bookquery.QueryBooks;
import bookquery.QueryBooks_Service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.java.dev.jaxb.array.StringArray;

/**
 *
 * @author rajarshi
 */
@ManagedBean
@RequestScoped
public class DbUpdateBean {
    /**
     * Creates a new instance of DbUpdateBean
     * 
     */
    private String FirstName, LastName, Title, Edition, Copyright, ISBN;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getEdition() {
        return Edition;
    }

    public void setEdition(String Edition) {
        this.Edition = Edition;
    }

    public String getCopyright() {
        return Copyright;
    }

    public void setCopyright(String Copyright) {
        this.Copyright = Copyright;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    public DbUpdateBean() {
    }

    public DbUpdateBean(String FirstName, String LastName, String Title, String Edition, String Copyright, String ISBN) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Title = Title;
        this.Edition = Edition;
        this.Copyright = Copyright;
        this.ISBN = ISBN;
    }
    
public void Reset()
{
    this.setCopyright("");
    this.setEdition("");
    this.setFirstName("");
    this.setISBN("");
    this.setTitle("");
    this.setLastName("");
}
    
      	   public ArrayList<DbUpdateBean> GetAllBooks() {
            
	        ArrayList<DbUpdateBean> arr = new ArrayList<>();
                FacesContext fc = FacesContext.getCurrentInstance();
                HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
                HttpSession session = (HttpSession)req.getSession(false);
                Connection conn_sess =  (Connection)session.getAttribute("DB");
           QueryBooks_Service gs = new QueryBooks_Service();
           final QueryBooks service = gs.getQueryBooksPort();
        //final GetWebService service = gs.getGetWebServicePort();
       List <StringArray> arr_new = new ArrayList<>();
       arr_new = service.getBooks(ISBN, ISBN);
        for (Iterator<StringArray> it = arr_new.iterator(); it.hasNext();) {
            StringArray a = it.next();
            

         
            //while(1){
                DbUpdateBean at = new DbUpdateBean();
                at.setISBN(a.getItem().get(0));
                at.setTitle(a.getItem().get(1));
                at.setEdition(a.getItem().get(2));
                at.setCopyright(a.getItem().get(3));
                at.setFirstName(a.getItem().get(4));
                at.setLastName(a.getItem().get(5));
                arr.add(at);
            //}
        }
        return arr;
   }
     public void Add() 
     {
        
         System.out.println("method called");
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
         String idStudent = request.getParameter("ISBN");
         
         System.out.println(ISBN);
         
         
    
            String Fname = this.getFirstName();
            String Lname = this.getLastName();
	    String Bookname = this.getTitle();
            String ID = this.getISBN();
            String Edit = this.getEdition();
            String copyright = this.getCopyright();
            
            
            
            
            String [] Params = {ID, Bookname, Edit, copyright, Fname, Lname};
            HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
            HttpSession session = (HttpSession)req.getSession(false);
            Connection conn_sess =  (Connection)session.getAttribute("DB");
        try {
            JDBC_Connections.Add(ID, Bookname, Edit, copyright, Fname, Lname,conn_sess);
            Reset();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbUpdateBean.class.getName()).log(Level.SEVERE, null, ex);
        }
           
	  
    }
     
     public void Delete() 
     {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        String idStudent = null;
        idStudent = request.getParameter("ISBN");
    
    
            String Fname = this.getFirstName();
            String Lname = this.getLastName();
	    String Bookname = this.getTitle();
            String ID = this.getISBN();
            String Edit = this.getEdition();
            String copyright = this.getCopyright();
         //acesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
            HttpSession session = (HttpSession)req.getSession(false);
            Connection conn_sess =  (Connection)session.getAttribute("DB");
        try {
            JDBC_Connections.deleteData( ID, conn_sess);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbUpdateBean.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public void Edit() {
        ArrayList<DbUpdateBean> arrList = GetAllBooks();
        FacesContext fc = FacesContext.getCurrentInstance();
        String isbn = null;
        HttpServletRequest request_1 = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        isbn = request_1.getParameter("ISBN");
        //
        for (DbUpdateBean dbUpdateBean : arrList) {
            if (dbUpdateBean.getISBN().equals(isbn)) {
                this.setISBN(dbUpdateBean.getISBN());
                this.setTitle(dbUpdateBean.getTitle());
                this.setEdition(dbUpdateBean.getEdition());
                this.setCopyright(dbUpdateBean.getCopyright());
                this.setFirstName(dbUpdateBean.getFirstName());
                this.setLastName(dbUpdateBean.getLastName());
            }

        }

    }
     public void Update() 
     {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        String  idStudent = request.getParameter("ISBN");
    
           String Fname = this.getFirstName();
            String Lname = this.getLastName();
	    String Bookname = this.getTitle();
            String ID = this.getISBN();
            String Edit = this.getEdition();
            String copyright = this.getCopyright();
            
             HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
            HttpSession session = (HttpSession)req.getSession(false);
            Connection conn_sess =  (Connection)session.getAttribute("DB");
            JDBC_Connections.UpdateData(ID, Bookname, Edit, copyright, Fname, Lname,conn_sess);
            
    }
     
    public  void deal(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        String  idStudent = request.getParameter("ISBN");
    
           String Fname = this.getFirstName();
            String Lname = this.getLastName();
	    String Bookname = this.getTitle();
            String ID = this.getISBN();
            String Edit = this.getEdition();
            String copyright = this.getCopyright();
             HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
            HttpSession session = (HttpSession)req.getSession(false);
            Connection conn_sess =  (Connection)session.getAttribute("DB");
            ResultSet rs = null;
           rs  =  JDBC_Connections.showDBreslts(ID);
          
      
    } 
    public ArrayList<DbUpdateBean> Results() {
	        ArrayList<DbUpdateBean> arr = new ArrayList<>();
                Connection conn_sess = JDBC_Connections.dbconnect();
                 String Fname = this.getFirstName();
            String Lname = this.getLastName();
	    String Bookname = this.getTitle();
            String ID = this.getISBN();
            String Edit = this.getEdition();
            String copyright = this.getCopyright();
	   //Connection conn_sess = Connectionclass_new.dbconnect();
          ResultSet rs = null;
                rs  = JDBC_Connections.showDBreslts(ID);
                //////////////////////showDBreslts
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
