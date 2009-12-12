/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.sql.Date;
import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;

/**
 *
 * @author tcook
 */
public abstract class PaymentBean implements EntityBean {

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
    
    
    public java.lang.Integer ejbCreate(java.lang.Integer key, UserLocal userId, AuctionLocal auctionId, Integer payment, String cardNo, Integer code, String cardType, java.sql.Date expDate)  throws CreateException {
        if (key == null) {
            throw new CreateException("The field \"key\" must not be null");
        }
        System.out.println("made it to ejbCreate Payment");
        setPaymentNo(key);
        System.out.println("able to set key");
        Double pay=null;
        try
        {
        pay = new Double(payment.doubleValue());
        System.out.println("payment is " + pay );
          setAmount(pay);
        }
        catch(Exception e)
        {
            System.out.println("problem is with payment " + pay );
        }
        setType(cardType);
        setCardNo(cardNo);
        try
        {
            setExpDate(expDate);
        }
        catch(Exception e)
        {
            System.out.println("problem is with experation date " + expDate);
        }
        setSecurityCode(code);

        // TODO add additional validation code, throw CreateException if data is not valid

        return null;
    }

    public void ejbPostCreate(java.lang.Integer key, UserLocal userId, AuctionLocal auctionId, Integer payment, String cardNo, Integer code, String cardType, java.sql.Date expDate)
    {
        // TODO populate relationships here if appropriate
        setBuyerNo(userId);
        setAuction(auctionId);
    }

/*    public java.lang.Integer ejbCreate(Integer key, UserLocal userId, AuctionLocal auctionId, Integer payment, String cardNo,
                                  Date expDate, Integer code, String cardType) throws CreateException
    {
        if (key == null) {
            throw new CreateException("The field \"key\" must not be null");
        }
        setPaymentNo(key);
        Double pay = new Double(payment.intValue());
        setAmount(pay);
        setType(cardType);
        setCardNo(cardNo);
        setExpDate(expDate);
        setSecurityCode(code);
        return null;
    }

    public void ejbPostCreate(Integer key, UserLocal userId, AuctionLocal auctionId, Integer payment, String cardNo,
                                  Date expDate, Integer code, String cardType) 
    {
        setBuyerNo(userId);
        setAuction(auctionId);
    }
*/
    public abstract Integer getPaymentNo();
    public abstract void setPaymentNo(Integer paymentNo);

    public abstract Double getAmount();
    public abstract void setAmount(Double amount);

    public abstract String getType();
    public abstract void setType(String type);

    public abstract String getCardNo();
    public abstract void setCardNo(String cardNo);

    public abstract java.sql.Date getExpDate();
    public abstract void setExpDate(java.sql.Date expDate);

    public abstract Integer getSecurityCode();
    public abstract void setSecurityCode(Integer securityCode);

    public abstract UserLocal getBuyerNo();
    public abstract void setBuyerNo(UserLocal buyerNo);

    public abstract AuctionLocal getAuction();
    public abstract void setAuction(AuctionLocal auction);

}
