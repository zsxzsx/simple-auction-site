/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Collection;
import javax.ejb.EJBLocalObject;
import javax.ejb.EJBObject;
/**
 *
 * @author Rory
 */
public interface AuctionRemote extends EJBObject{

    Integer getAuctionNo() throws RemoteException;

 //   void setAuctionNo(Integer auctionNo);
/*
    Timestamp getStartTime();

    void setStartTime(Timestamp startTime);

    Timestamp getStopTime();

    void setStopTime(Timestamp stopTime);

    ItemLocal getItemNo();

    void setItemNo(ItemLocal itemNo);

    UserLocal getSellerId();

    void setSellerId(UserLocal sellerId);
*/
    //Collection getPaymentCollection();

//    void setPaymentCollection(Collection paymentCollection);

    //Collection getBidCollection();
//   void setBidCollection(Collection bidCollection);

}
