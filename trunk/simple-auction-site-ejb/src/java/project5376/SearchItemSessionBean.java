/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.util.Collection;
import java.util.Vector;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.FinderException;
import javax.rmi.PortableRemoteObject;
import java.util.Iterator;

/**
 *
 * @author tcook
 */
public class SearchItemSessionBean implements SessionBean {
    
    private SessionContext context;
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">;

    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations

    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }
    
    // </editor-fold>;

    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    
    private ItemLocalHome itemhome;
    public void ejbCreate() {
        // TODO implement ejbCreate if necessary, acquire resources
        // This method has access to the JNDI context so resource aquisition
        // spanning all methods can be performed here such as home interfaces
        // and data sources.
      		try
		{
                   itemhome = ServiceProvider.lookupItemHome();
		}
		catch (NamingException ne)
		{
		  System.out.println("Naming Exception in SearchItemSession bean");
		  //throw new Exception();
		}

    }

    public String SearchItemsByName(String description) {
       //Collection findByItemName(String itemName) throws javax.ejb.FinderException;
        Collection col=null;
        String str="";
        try{
            col = itemhome.findByItemName("%" + description + "%");
            str = ServiceProvider.get_items_html_table(col);
        }catch (FinderException fe){
            System.out.println("Finder exception in SearchItem Session bean.");
        }
        return str;
    }

    public String SearchItemsByDescription(String description) {
        String str="";
        Collection col=null;
        try{
            col = itemhome.findByDescriptionPattern("%" + description + "%");
            str = ServiceProvider.get_items_html_table(col);
        }catch (FinderException fe){
            System.out.println("Finder exception in SearchItem Session bean.");
        }
        return str;
    }
    public String SearchItemsAll() {
        String str="";
        Collection col=null;
        try{
            col = itemhome.findAllItems();
            str = ServiceProvider.get_items_html_table(col);
        }catch (FinderException fe){
            System.out.println("Finder exception in SearchItem Session bean.");
        }
        return str;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
    
}
