/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;

/**
 *
 * @author tcook
 */
public abstract class UserBean implements EntityBean, java.io.Serializable {

    private EntityContext context;
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click on the + sign on the left to edit the code.">

    // TODO Consider creating Transfer Object to encapsulate data
    // TODO Review finder methods

    /**
     * @see javax.ejb.EntityBean#setEntityContext(javax.ejb.EntityContext)
     */
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }
    
    /**
     * @see javax.ejb.EntityBean#unsetEntityContext()
     */
    public void unsetEntityContext() {
        context = null;
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {
        
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
        
    }

    // </editor-fold>
    
    
    public java.lang.Integer ejbCreate(java.lang.Integer id, String username, String password,
            String firstname, String lastname, String address1, String address2, String city,
            String state, String zip, String email)  throws CreateException {
        System.out.println("inside ejbCreate for User id== " + id.toString());

/*        if (key == null) {
        System.out.println("inside ejbCreate for User 2222");
            System.out.println("key == null in ejbCreate");
            throw new CreateException("The field \"key\" must not be null");
        }*/

        setUserNo(id);
        setUserId(username);
        setPassword(password);
        setFirstName(firstname);
        setLastName(lastname);
        setAddress1(address1);
        setAddress2(address2);
//      setCity(city);
        setState(state);
        setZipCode(new Integer(zip));
        setEmail(email);

        System.out.println("inside ejbCreate for User 3333");
        
        // TODO add additional validation code, throw CreateException if data is not valid

        return null;
    }

/*    public void ejbPostCreate(java.lang.Integer key) {
        // TODO populate relationships here if appropriate
    }
*/
    public void ejbPostCreate(java.lang.Integer id, String username, String password,
            String firstname, String lastname, String address1, String address2, String city,
            String state, String zip, String email) {
            // TODO populate relationships here if appropriate
    }
    public abstract Integer getUserNo();

    public abstract void setUserNo(Integer userNo);

    public abstract String getUserId();

    public abstract void setUserId(String userId);

    public abstract String getFirstName();

    public abstract void setFirstName(String firstName);

    public abstract String getLastName();

    public abstract void setLastName(String lastName);

    public abstract String getAddress1();

    public abstract void setAddress1(String address1);

    public abstract String getAddress2();

    public abstract void setAddress2(String address2);

    public abstract String getState();

    public abstract void setState(String state);

    public abstract Integer getZipCode();

    public abstract void setZipCode(Integer zipCode);

    public abstract String getEmail();

    public abstract void setEmail(String email);

    public abstract String getPassword();

    public abstract void setPassword(String password);

    public abstract Collection getAuctionCollection();

    public abstract void setAuctionCollection(Collection auctionCollection);

    public abstract Collection getPaymentCollection();

    public abstract void setPaymentCollection(Collection paymentCollection);

    public abstract Collection getRatingCollection();

    public abstract void setRatingCollection(Collection ratingCollection);

    public abstract Collection getRatingCollection1();

    public abstract void setRatingCollection1(Collection ratingCollection1);

    public abstract Collection getBidCollection();

    public abstract void setBidCollection(Collection bidCollection);
    public ArrayList getUserAuctions()
    {
      ArrayList list = new ArrayList();

        Iterator c = getAuctionCollection().iterator();
        while (c.hasNext())
        {
            list.add(c.next());
        }
        return list;
    }
public ArrayList getUserBids()
    {
      ArrayList list = new ArrayList();

        Iterator c = getBidCollection().iterator();
        while (c.hasNext())
        {
            list.add(c.next());
        }
        return list;
    }
public ArrayList getSellerRatings()
 {
   ArrayList list = new ArrayList();

   Iterator c = getRatingCollection1().iterator();
   while (c.hasNext())
   {
    list.add(c.next());
   }
   return list;
 }
}
