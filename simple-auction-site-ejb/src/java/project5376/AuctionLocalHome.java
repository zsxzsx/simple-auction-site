/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;

import java.rmi.RemoteException;
import java.util.*;
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.sql.Timestamp;  
import javax.ejb.EJBHome;

/**
 *
 * @author tcook
 */
public interface AuctionLocalHome extends EJBLocalHome {

  
   project5376.AuctionLocal findByPrimaryKey(java.lang.Integer key)  throws FinderException;

    project5376.AuctionLocal create(java.lang.Integer key)  throws CreateException;

    project5376.AuctionLocal create(Integer auctionNo, UserLocal sellerId, ItemLocal itemNo, Timestamp startTime, Timestamp stopTime)  throws CreateException;

    project5376.AuctionLocal findByAuctionNo(Integer auctionNo) throws javax.ejb.FinderException;
    
    Collection findAllAuctions() throws javax.ejb.FinderException;

}
