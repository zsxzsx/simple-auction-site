package project5376;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.util.*;
import java.util.List;

public interface auctionSessionRemote extends EJBObject
{
  public void auctionSessionBean() throws RemoteException;
  public Collection getAllAuctions() throws RemoteException;
  public Integer getHighBidByAuctionNo(Integer auctionNo) throws RemoteException;
  public void addAuction(Integer sellerId, Date startTime, Date stopTime, String itemName, String itemDesc, String itemCond) throws RemoteException;

}