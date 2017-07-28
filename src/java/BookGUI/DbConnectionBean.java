/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookGUI;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author rajarshi 
 */
@ManagedBean
@RequestScoped
public class DbConnectionBean {
    /**
     * Creates a new instance of DbConnectionBean
     */
 private String Username, Password;

    public String getUsername() {
        return Username;
    }///////////////////////////////////////////////////////////////////////

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
 
     public String Login() throws IOException{
         
         
         if(Username.equals("rajarshi") && Password.equals("raja123"))
         {
             HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                 session.setAttribute("Username", Username);
                 Connection conn_new = JDBC_Connections.dbconnect();
                 session.setAttribute("DB", conn_new);
                 
                              return "Admin?faces-redirect=true";
         }
         else
         {
            // FacesContext fc = FacesContext.getCurrentInstance();
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("UserName/Password is Wrong"));
             return null;
         }
     }
public void Reset()
{
    this.setUsername("");
    this.setPassword("");
}
    public DbConnectionBean() {
       
    }

    public DbConnectionBean(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }
    
    public String Session_check()
     {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        HttpSession session = (HttpSession)req.getSession(false);
        if(session.getAttribute("Username") == null)
        {
            try {
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("../TestLoginpage.xhtml");
             } 
            
            catch (IOException ex) {
             Logger.getLogger(DbConnectionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
           return (String)session.getAttribute("Username");
        }
        
        return null;
     }

    public void Logout(){
         
         HttpSession session = (HttpSession)  FacesContext.getCurrentInstance().getExternalContext().getSession(false);
         session.invalidate();
         
               try {
             
            // return "Login";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/Loginpage.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DbConnectionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }
}
