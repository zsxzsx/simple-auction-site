/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.util.Collection;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.Date;

/**
 *
 * @author tcook
 */
public interface PaymentLocalHome extends EJBLocalHome {

    project5376.PaymentLocal findByPrimaryKey(java.lang.Integer key)  throws FinderException;
    
    project5376.PaymentLocal create(java.lang.Integer key, UserLocal userId, AuctionLocal auctionId, Integer payment, String cardNo, Integer code, String cardType, java.sql.Date expDate)  throws CreateException;

   // project5376.PaymentLocal create(Integer key, UserLocal userId, AuctionLocal auctionId, Integer payment, String cardNo,
   //                               Date expDate, Integer code, String cardType) throws CreateException;

    Collection findByPaymentNo(Integer paymentNo) throws javax.ejb.FinderException;

    Collection findByAmount(Double amount) throws javax.ejb.FinderException;

    Collection findByType(String type) throws javax.ejb.FinderException;

    Collection findByCardNo(String cardNo) throws javax.ejb.FinderException;

    Collection findBySecurityCode(Integer securityCode) throws javax.ejb.FinderException;
    Collection findAllPayments() throws javax.ejb.FinderException;

}
