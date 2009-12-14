/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package project5376;
import java.rmi.RemoteException;
import java.util.*;
import javax.ejb.CreateException;

import javax.ejb.FinderException;
import java.sql.Timestamp;
import javax.ejb.EJBHome;
/**
 *
 * @author Rory
 */
public interface AuctionRemoteHome extends EJBHome{
   project5376.AuctionRemote findByPrimaryKey(java.lang.Integer key)  throws FinderException, RemoteException;

    project5376.AuctionRemote create(java.lang.Integer key)  throws CreateException, RemoteException;

//    project5376.AuctionRemote create(Integer auctionNo, UserLocal sellerId, ItemLocal itemNo, Timestamp startTime, Timestamp stopTime)  throws CreateException, RemoteException;
    
    project5376.AuctionRemote findAuctionByItem(project5376.ItemLocal ItemNo) throws javax.ejb.FinderException, RemoteException;

    project5376.AuctionRemote findByAuctionNo(Integer auctionNo) throws javax.ejb.FinderException, RemoteException;

    Collection findAllAuctions() throws javax.ejb.FinderException, RemoteException;
}
