/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.sql.Date;
import javax.ejb.EJBLocalObject;

/**
 *
 * @author tcook
 */
public interface PaymentLocal extends EJBLocalObject {

    Integer getPaymentNo();

    void setPaymentNo(Integer paymentNo);

    Double getAmount();

    void setAmount(Double amount);

    String getType();

    void setType(String type);

    String getCardNo();

    void setCardNo(String cardNo);

    java.sql.Date getExpDate();

    void setExpDate(java.sql.Date expDate);

    Integer getSecurityCode();

    void setSecurityCode(Integer securityCode);
    UserLocal getBuyerNo();
    void setBuyerNo(UserLocal buyerNo);
    AuctionLocal getAuction();
    void setAuction(AuctionLocal auction);



}
