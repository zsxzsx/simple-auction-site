/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.sql.Timestamp;
import java.util.*;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;

/**
 *
 * @author tcook
 */

public abstract class AuctionBean  implements EntityBean,java.io.Serializable  {

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
    
    
    public java.lang.Integer ejbCreate(java.lang.Integer key)  throws CreateException {
        if (key == null) {
            throw new CreateException("The field \"key\" must not be null");
        }
        
        // TODO add additional validation code, throw CreateException if data is not valid

        return null;
    }
    public Integer ejbCreate(Integer auctionNo, UserLocal sellerId, ItemLocal itemNo, Timestamp startTime, Timestamp stopTime)
                                        throws CreateException
    {
        setAuctionNo(auctionNo);
        setStartTime(startTime);
        setStopTime(stopTime);
        return null;
    }
 


    public void ejbPostCreate(Integer auctionNo, UserLocal sellerId, ItemLocal itemNo, Timestamp startTime, Timestamp stopTime) {
        // TODO populate relationships here if appropriate
        setItemNo(itemNo);
        setSellerId(sellerId);
    }
    public void ejbPostCreate(java.lang.Integer key) {
        // TODO populate relationships here if appropriate
    }

    public abstract Integer getAuctionNo();

    public abstract void setAuctionNo(Integer auctionNo);

    public abstract Timestamp getStartTime();

    public abstract void setStartTime(Timestamp startTime);

    public abstract Timestamp getStopTime();

    public abstract void setStopTime(Timestamp stopTime);

    public abstract ItemLocal getItemNo();

    public abstract void setItemNo(ItemLocal itemNo);

 //   public abstract Integer getItemNo();

//    public abstract void setItemNo(Integer itemNo);

    public abstract UserLocal getSellerId();

    public abstract void setSellerId(UserLocal sellerId);

 //   public abstract Integer getSellerId();

//    public abstract void setSellerId(Integer sellerId);

    public abstract Collection getPaymentCollection();

    public abstract void setPaymentCollection(Collection paymentCollection);

    public abstract Collection getBidCollection();

    public abstract void setBidCollection(Collection bidCollection);

    public ArrayList getAuctionBids()
    {
        ArrayList list = new ArrayList();

        Iterator c = getBidCollection().iterator();
        while (c.hasNext())
        {
            list.add(c.next());
        }
        return list;
    }

}
