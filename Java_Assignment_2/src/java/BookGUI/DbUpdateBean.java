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
 * @author mounica yalamanchili
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
        
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
         String idStudent = request.getParameter("ISBN");
    
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
            boolean Insert = JDBC_Connections.Insert(Params, conn_sess);
           
	   String Message = null;
            if(Insert == true)
            {
                Reset();
                Message = "Book Added Succesfully...";
            }
            else
            {
                Message = "Book not added some error occured...please try after sometime";
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Message));
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
            String [] Params = {Fname, Lname, idStudent, Bookname};
         //acesContext fc = FacesContext.getCurrentInstance();
            HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
            HttpSession session = (HttpSession)req.getSession(false);
            Connection conn_sess =  (Connection)session.getAttribute("DB");
            boolean Delete = JDBC_Connections.delete(Params, conn_sess);
            if(Delete == true)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Book DeletedSuccesfully"));
            }
            else
            {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sorry Book Deletion Failed, Please try again"));
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
            String ID = idStudent;
            String Edit = this.getEdition();
            String copyright = this.getCopyright();
            String [] Params = {ID, Bookname,Edit,copyright,Fname,Lname};
             HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
            HttpSession session = (HttpSession)req.getSession(false);
            Connection conn_sess =  (Connection)session.getAttribute("DB");
            boolean update = JDBC_Connections.Update(Params, conn_sess);
            if(update == true)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Book Updated Succesfully"));
            }
            else
            {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sorry Book Update Failed, Please try again"));
            }
    }
     
     
    
}
