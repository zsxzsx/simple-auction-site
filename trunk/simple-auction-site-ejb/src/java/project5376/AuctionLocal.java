/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.sql.Timestamp;
import java.util.*;
import javax.ejb.EJBLocalObject;
import javax.ejb.EJBObject;

/**
 *
 * @author tcook
 */
public interface AuctionLocal extends EJBLocalObject {

    Integer getAuctionNo();

    void setAuctionNo(Integer auctionNo);

    Timestamp getStartTime();

    void setStartTime(Timestamp startTime);

    Timestamp getStopTime();

    void setStopTime(Timestamp stopTime);

    ItemLocal getItemNo();

    void setItemNo(ItemLocal itemNo);

    UserLocal getSellerId();

    void setSellerId(UserLocal sellerId);

    PaymentLocal getPayment();

    void setPayment(PaymentLocal payment);

    Collection getBidCollection();

    void setBidCollection(Collection bidCollection);

    ArrayList getAuctionBids();

}
