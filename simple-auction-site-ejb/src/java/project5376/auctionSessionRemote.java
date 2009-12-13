package project5376;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.util.*;
import java.util.List;

public interface auctionSessionRemote extends EJBObject
{
  public void auctionSessionBean() throws RemoteException;
  public Collection getAllAuctions() throws RemoteException;
  public void addAuction(Integer sellerId, Date startTime, Date stopTime, String itemName, String itemDesc, String itemCond) throws RemoteException;
  public ArrayList getUserAuctions(Integer sellerId) throws RemoteException;
  public String getItemName(Integer auctionNo) throws RemoteException;
  public ArrayList getUserBids(Integer bidderId) throws RemoteException;
  public Auction getAuctionDetails(Integer auctionNo, Integer userNo) throws RemoteException;
  public boolean placeBid(Integer bidderNo, Integer auctionNo, Integer bid)  throws RemoteException;
  public ArrayList getUserWinningBids(Integer bidderId) throws RemoteException;
  public boolean pay(Integer userNo, Integer auctionNo, Integer payment, String cardNo,
                     String expDate, Integer code, String cardType) throws RemoteException;
  public ArrayList getAuctionList() throws RemoteException;
}