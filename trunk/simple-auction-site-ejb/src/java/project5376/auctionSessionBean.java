package project5376;

import java.util.ArrayList;
import java.util.*;
import java.util.Collection;
import java.util.Collections;
import java.rmi.RemoteException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.lang.Double;
import javax.ejb.EntityContext;
import javax.naming.Context;
import java.lang.Integer;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import java.util.Properties;
import javax.rmi.PortableRemoteObject;
import java.util.Iterator;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class auctionSessionBean implements SessionBean
{
  protected SessionContext sctx;
  private Context ctx;
  private String url;
 
  private AuctionLocalHome aucHome;
  private AuctionLocal auc;
  private UserLocalHome userHome;
  private UserLocal user;
  private ItemLocalHome itemHome;
  private ItemLocal item;
  private BidLocalHome bidHome;
  private BidLocal bid;
  private PaymentLocalHome payHome;
  private PaymentLocal pmnt;
  private Integer bidAmt;
  private String bidder;
  private Integer hiBidder;
  private static int aucPk;
  private static int itemPk;
  private static int bidPk;
  private static int userPk;
  private static int payPk;


public void auctionSessionBean()
{
    bidAmt= new Integer(0);
    bidder = new String("");
    Collection col=null;
    log("Session Bean Create");
     this.url=url;

     try
     {
       aucHome = lookupAuctionHome();
     }
     catch (NamingException ne)
     {
       log("The client was unable to lookup the EJBHome. Please make sure "
           + "that you have deployed the ejb with the JNDI name "
           + "AuctionBean on the WebLogic server at " + url);
     }
     aucPk = 0;
     try
     {
      col = (Collection)aucHome.findAllAuctions();
      Iterator it = col.iterator();
      while (it.hasNext())
      {
        auc = (AuctionLocal) PortableRemoteObject.narrow(it.next(), AuctionLocal.class);

        if ((auc.getAuctionNo()).intValue() > aucPk)
        {
          aucPk = (auc.getAuctionNo()).intValue();
        }
      }
     }
     catch (Exception e)
     {
	     System.out.println("AuctionPrimary Key find error: "+e);
     }

     try
     {
       itemHome = lookupItemHome();
     }
     catch (NamingException ne)
     {
       log("The client was unable to lookup the EJBHome. Please make sure "
           + "that you have deployed the ejb with the JNDI name "
           + "ItemBean on the WebLogic server at " + url);
     }
     itemPk = 0;
     try
     {
      col = (Collection)itemHome.findAllItems();
      Iterator it = col.iterator();
      while (it.hasNext())
      {
        item = (ItemLocal) PortableRemoteObject.narrow(it.next(), ItemLocal.class);

        if ((item.getItemNo()).intValue() > itemPk)
        {
          itemPk = (item.getItemNo()).intValue();
        }
      }
    }
    catch (Exception e)
    {
	    System.out.println("Item Primary Key find error: "+e);
    }

     try
     {
       userHome = lookupUserHome();
     }
     catch (NamingException ne)
     {
       log("The client was unable to lookup the EJBHome. Please make sure "
           + "that you have deployed the ejb with the JNDI name "
           + "ItemBean on the WebLogic server at " + url);
     }
     userPk = 0;
     try
     {
     col = (Collection)userHome.findAllUsers();
     }
      catch (Exception e)
      {
	    System.out.println("User Primary Key coll find error: "+e);
      }
     try
     {
      Iterator it = col.iterator();
      while (it.hasNext())
      {
        user = (UserLocal) PortableRemoteObject.narrow(it.next(), UserLocal.class);

        if ((user.getUserNo()).intValue() > userPk)
        {
          userPk = (user.getUserNo()).intValue();
        }
      }
    }
    catch (Exception e)
    {
	    System.out.println("User Primary Key find error: "+e);
    }
     try
     {
       payHome = lookupPayHome();
     }
     catch (NamingException ne)
     {
       log("The client was unable to lookup the EJBHome. Please make sure "
           + "that you have deployed the ejb with the JNDI name "
           + "BidBean on the WebLogic server at " + url);
     }
     payPk = 0;
     try
     {
     col = (Collection)payHome.findAllPayments();
     }
      catch (Exception e)
      {
	    System.out.println("User Primary Key coll find error: "+e);
      }
     try
     {
      Iterator it = col.iterator();
      while (it.hasNext())
      {
        pmnt = (PaymentLocal) PortableRemoteObject.narrow(it.next(), PaymentLocal.class);

        if ((pmnt.getPaymentNo()).intValue() > payPk)
        {
          payPk = (pmnt.getPaymentNo()).intValue();
        }
      }
    }
    catch (Exception e)
    {
	    System.out.println("Payment Primary Key find error: "+e);
    }


     try
     {
       bidHome = lookupBidHome();
     }
     catch (NamingException ne)
     {
       log("The client was unable to lookup the EJBHome. Please make sure "
           + "that you have deployed the ejb with the JNDI name "
           + "BidBean on the WebLogic server at " + url);
     }
     bidPk = 0;
     try
     {
      col = (Collection)bidHome.findAllBids();
      Iterator it = col.iterator();
      while (it.hasNext())
      {
        bid = (BidLocal) PortableRemoteObject.narrow(it.next(), BidLocal.class);

        if ((bid.getBidNo()).intValue() > bidPk)
        {
          bidPk = (bid.getBidNo()).intValue();
        }
      }
    }
    catch (Exception e)
    {
	    System.out.println("Bid Primary Key find error: "+e);
    }

    log("Highest Primary key is: "+aucPk+" "+itemPk+" "+ userPk+" "+bidPk);

	}

	private AuctionLocalHome lookupAuctionHome() throws NamingException
  {
    ctx = getInitialContext();
    try
    {
      Object home = ctx.lookup("AuctionBean");
      return (AuctionLocalHome) PortableRemoteObject.narrow(home, AuctionLocalHome.class);
    }
    catch (NamingException ne)
    {
      log("The client was unable to lookup the EJBHome. Please make sure "
           + "that you have deployed the ejb with the JNDI name "
           + "auctionBean on the WebLogic server at " + url);
      throw ne;
    }
 
  }

  private ItemLocalHome lookupItemHome() throws NamingException
  {
    ctx = getInitialContext();
    try
    {
      Object home = ctx.lookup("ItemBean");
      return (ItemLocalHome) PortableRemoteObject.narrow(home, ItemLocalHome.class);
    }
    catch (NamingException ne)
    {
      log("The client was unable to lookup the EJBHome. Please make sure "
           + "that you have deployed the ejb with the JNDI name "
           + "ItemBean on the WebLogic server at " + url);
      throw ne;
    }
  }

  private PaymentLocalHome lookupPayHome() throws NamingException
  {
    ctx = getInitialContext();
    try
    {
      Object home = ctx.lookup("PaymentBean");
      return (PaymentLocalHome) PortableRemoteObject.narrow(home, PaymentLocalHome.class);
    }
    catch (NamingException ne)
    {
      log("The client was unable to lookup the EJBHome. Please make sure "
           + "that you have deployed the ejb with the JNDI name "
           + "PaymentBean on the WebLogic server at " + url);
      throw ne;
    }
  }

private BidLocalHome lookupBidHome() throws NamingException
  {
    ctx = getInitialContext();
    try
    {
      Object home = ctx.lookup("BidBean");
      return (BidLocalHome) PortableRemoteObject.narrow(home, BidLocalHome.class);
    }
    catch (NamingException ne)
    {
      log("The client was unable to lookup the EJBHome. Please make sure "
           + "that you have deployed the ejb with the JNDI name "
           + "BidBean on the WebLogic server at " + url);
      throw ne;
    }
  }
  private UserLocalHome lookupUserHome() throws NamingException
  {
    ctx = getInitialContext();
    try
    {
      Object home = ctx.lookup("UserBean");
      return (UserLocalHome) PortableRemoteObject.narrow(home, UserLocalHome.class);
    }
    catch (NamingException ne)
    {
      log("The client was unable to lookup the EJBHome. Please make sure "
           + "that you have deployed the ejb with the JNDI name "
           + "UserBean on the WebLogic server at " + url);
      throw ne;
    }
  }

  private Context getInitialContext() throws NamingException
  {
    try
    {
      // Get an InitialContext
      Properties h = new Properties();
      h.put(Context.INITIAL_CONTEXT_FACTORY,
        "weblogic.jndi.WLInitialContextFactory");
    //  h.put(Context.PROVIDER_URL, url);
      return new InitialContext(h);
    }
    catch (NamingException ne)
    {
      log("We were unable to get a connection to the WebLogic server at ");
      log("Please make sure that the server is running.");
      throw ne;
    }
  }
public  String getItemName(Integer auctionNo) throws RemoteException
{
    String itemName=null;
    try
    {
      AuctionLocal auction = aucHome.findByAuctionNo(auctionNo);
      ItemLocal itemInstance=auction.getItemNo();
      itemName = itemInstance.getItemName();
        
    }
    catch(Exception e)
    {
      log("Aget item name in auction Session bean: "+e);
    }
    return itemName;
}
  public void addAuction(Integer sellerNo, java.util.Date startTime, java.util.Date stopTime, String itemName, String itemDesc, String itemCond) throws RemoteException
  {
    ItemLocal itemInstance=null;
    aucPk = aucPk + 1;
    itemPk = itemPk+1;
    Integer auctionNo = new Integer(aucPk);
    Integer itemNo = new Integer(itemPk);
    log("sellerNo is " + sellerNo);

    try
    {
      log("\n\n\n\n SellerNo is : "+sellerNo);
      Integer cond = new Integer(itemCond);
      itemInstance = itemHome.create(itemNo, itemName, itemDesc, cond);
          }
     catch (Exception e)
    {
	    log("Add auction item failed WHY???: "+e);
	    return;
    }
    try
    {
      log("\n\n\n\n SellerNo is : "+sellerNo);
      UserLocal sellerId = userHome.findByUserNo(sellerNo);
      Timestamp startTimeStamp = new Timestamp(startTime.getTime());
      Timestamp stopTimeStamp = new Timestamp(stopTime.getTime());
      if (sellerId==null)
      {
          log("\n\n\n\n SellerID is : null for sellerNo"+sellerNo);
      }
      else
      {
          log("\n\n\n\n SellerID is not null creating auction");
         aucHome.create(auctionNo, sellerId, itemInstance, startTimeStamp, stopTimeStamp);
      }
     }
  
     catch (Exception e)
    {
	    log("Add auction failure: "+e);
	    return;
    }
   
    return;
  }

  public boolean pay(Integer userNo, Integer auctionNo, Integer payment, String cardNo,
                     String expDate, Integer code, String cardType) throws RemoteException
  {
    DateFormat df = new SimpleDateFormat("MM/yyyy");
    java.util.Date exp = new java.util.Date();
    java.sql.Date dt = null;
    try
    {
      exp = df.parse(expDate);
      long t = exp.getTime();
      dt = new java.sql.Date(t);
    }
    catch(Exception e)
    {
        log("\n\n Exporation Date is : "+expDate);
        log("Add payment failure - Date format parse: "+e.getStackTrace());
        return false;
    }
    PaymentLocal newPmnt=null;
    payPk = payPk + 1;
    Integer payNo = new Integer(payPk);
    log("auctionNo is " + auctionNo);

    try
    {
      log("\n\n\n\n UserNo is : "+userNo);
      UserLocal userId = userHome.findByUserNo(userNo);
      AuctionLocal auctionId = aucHome.findByAuctionNo(auctionNo);
      if (userId==null)
      {
          log("\n\n\n\n userID is : null for userNo"+userNo);
      }

      else if (auctionId==null)
      {
          log("\n\n\n\n auctionID is : null for auctionNo"+auctionNo);
      }
      else
      {
          log("\n\n\n\n bidder and auction are good creating payment");
          log("\npayNo= "+payNo+" paymemt= $"+ payment+" cardNo is "+ cardNo +" security code is "+code);
          log (cardType+" experation date is "+ dt);
         newPmnt =payHome.create(payNo, userId, auctionId, payment, cardNo, code, cardType, dt);

      }
     }

     catch (Exception e)
    {
	    log("Add payment failure: Error "+e.getMessage());

	    return false;
    }
    log("Returning True");
    return true;
  }


  public boolean placeBid(Integer bidderNo, Integer auctionNo, Integer bid)  throws RemoteException
  {

    BidLocal newBid=null;
    bidPk = bidPk + 1;
    Integer bidNo = new Integer(bidPk);
    log("bidNo is " + bidNo);

    try
    {
      log("\n\n\n\n BidderNo is : "+bidderNo);
      UserLocal bidderId = userHome.findByUserNo(bidderNo);
      AuctionLocal auctionId = aucHome.findByAuctionNo(auctionNo);
      if (bidderId==null)
      {
          log("\n\n\n\n BidderID is : null for bidderNo"+bidderNo);
          return false;
      }

      else if (auctionId==null)
      {
          log("\n\n\n\n auctionID is : null for auctionNo"+auctionNo);
          return false;
      }
      else
      {
         log("\n\n\n\n bidder and auction are good creating bid");
         java.util.Date curTime = new java.util.Date();
         Timestamp currentTime = new Timestamp(curTime.getTime());
         if (auc.getStopTime().after(currentTime))
         {
           newBid =bidHome.create(bidNo, bid, bidderId, auctionId);
         }
      }
      getHighBidInfo(auctionId);
      
     }
  
     catch (Exception e)
    {
	    log("Add bid failure: "+e);
	    return false;
    }
    if ((bidAmt.intValue() == bid.intValue())&& (hiBidder.intValue()==bidderNo.intValue()))
    {
       return true;
    }
    else
    {
        log("Highest bid found was "+ bidAmt);
        log("your Bid was "+ bid);
        log("Highest bidder found was "+ hiBidder);
        log("your Bidder no was "+ bidderNo);
        try
        {
          newBid.remove();
        }
        catch(Exception e)
        {
            log("New bid not removed unkown error: "+ e);
        }

        return false;
    }
  }
  
  public Collection getAllAuctions() throws RemoteException
  {
    log("Made it to Get All Auctions");
    Collection col=null;
    try
    {
      col=(Collection)aucHome.findAllAuctions();
    }
    catch (Exception e)
    {
	    log("Get All Auctions: "+e);
	    return null;
    }
    return col;
  }

  public void getHighBidInfo(AuctionLocal auc) throws RemoteException
  {
    bidAmt = new Integer(0);
    UserLocal bidderno = null;
    bidder = "None";
    ArrayList list=null;
    try
    {
        list=(ArrayList)auc.getAuctionBids();
    }
    catch (Exception e)
    {
	    System.out.println("Get high Bidder: Get Auction Bids: "+e);
	    return;
    }
     for (int i = 0; i < list.size(); i++)
    {
        bid = (BidLocal)list.get(i);
        if ((bid.getBidAmt().intValue()) > (bidAmt.intValue()))
        {
          bidAmt = (bid.getBidAmt());
          bidderno = bid.getBidderId();
          hiBidder = bidderno.getUserNo();
          bidder = bidderno.getUserId();
        }
    }
    return;
  }
  public Integer getHighBidForUser(AuctionLocal auc, Integer userNo) throws RemoteException
  {
    bidAmt = new Integer(0);
    ArrayList list=null;
    try
    {
        list=(ArrayList)auc.getAuctionBids();
    }
    catch (Exception e)
    {
	    System.out.println("Get high Bidder: Get Auction Bids: "+e);
	    return bidAmt;
    }
    for (int i = 0; i < list.size(); i++)
    {        
        bid = (BidLocal)list.get(i);
        if ((bid.getBidderId().getUserNo().intValue()==userNo.intValue())&&((bid.getBidAmt().intValue()) > bidAmt.intValue()))
        {
          bidAmt = (bid.getBidAmt());
        }
      }
    return bidAmt;
  }

    public ArrayList getUserAuctions(Integer sellerId) throws RemoteException
    {
      log("Made it to Get User Auctions");
    ArrayList list=null;
    ArrayList auctionList=new ArrayList();
    try
    {
        user= userHome.findByUserNo(sellerId);
        list=(ArrayList)user.getUserAuctions();
    }
    catch (Exception e)
    {
	    System.out.println("Get User Auctions: "+e);
	    return null;
    }
    for (int i = 0; i < list.size(); i++)
    {
        auc = (AuctionLocal)list.get(i);
        String itemName=null;
        String itemDesc=null;
        try
        {
          ItemLocal itemInstance=auc.getItemNo();
          itemName = itemInstance.getItemName();
          itemDesc = itemInstance.getDescription();
          getHighBidInfo(auc);
    }
    catch(Exception e)
    {
      log("get item name in auction Session bean: "+e);
    }
        Auction auction = new Auction(auc.getAuctionNo(), auc.getStartTime(), auc.getStopTime(),
                                      itemName, itemDesc, bidAmt, bidder);
        auctionList.add(auction);
    }
    return auctionList;
    }

  public Integer getAuctionNoFromItem(Integer ItemNo) throws RemoteException
  {
    log("Made it to Get Auction No from item no itemNo=" + ItemNo);
    try{
        Collection col = (Collection)itemHome.findByItemNo(ItemNo);
        Iterator it = col.iterator();
        ItemLocal item=null;
    
        while (it.hasNext())
        {
            item = (ItemLocal) PortableRemoteObject.narrow(it.next(), ItemLocal.class);
            System.out.println("ITEM FOUND! itemNo=" + item.getItemNo());
        }
    } catch (Exception e)
    {
      log("Exception Get Auction No from item- find by itemNo: "+e);
      return null;
    }
    try
    {
        auc= aucHome.findAuctionByItem(item);
    }
    catch (Exception e)
    {
      log("Get Auction Details - find by itemNo: "+e);
      return null;
    }
    Integer aucNo = auc.getAuctionNo();
    System.out.println("Auction number from lookup is: ");
    return aucNo;
  }

  public Auction getAuctionDetails(Integer auctionNo, Integer userNo) throws RemoteException
  {
    log("Made it to Get Auction Details");
    try
    {
        auc= aucHome.findByAuctionNo(auctionNo);
    }
    catch (Exception e)
    {
      log("Get Auction Details - find by auctionNo: "+e);
      return null;
    }
    String itemName=null;
    ItemLocal itemInstance=null;
    String itemDesc=null;
    UserLocal sellerNo =null;
    String sellerId = "";
    ArrayList ratings = null;
    double sellerRating =0.0;
    RatingLocal rating = null;
    Integer userBidAmt=getHighBidForUser(auc, userNo);
    try
    {
      itemInstance=auc.getItemNo();
      itemName = itemInstance.getItemName();
      itemDesc = itemInstance.getDescription();
      getHighBidInfo(auc);
      sellerNo =auc.getSellerId();
      sellerId = sellerNo.getUserId();
      ratings = sellerNo.getSellerRatings();
      for (int i = 0; i < ratings.size(); i++)
      {
        rating = (RatingLocal)ratings.get(i);
        sellerRating= sellerRating+ rating.getRating();
      }
      sellerRating= sellerRating/ratings.size();
    }
    catch(Exception e)
    {
      log("get item name in auction Session bean: "+e);
    }
    Auction auction = new Auction(auc.getAuctionNo(), auc.getStartTime(), auc.getStopTime(),
                                      itemName, itemDesc, bidAmt, bidder);
    auction.setSellerId(sellerId);
    auction.setSellerRating(sellerRating);
    auction.setCondition(itemInstance.getCondition1());
    auction.setUserBid(userBidAmt);
    return auction;
  }

  public ArrayList getUserBids(Integer bidderId) throws RemoteException
  {
    log("Made it to Get User Bids");
    ArrayList list=null;
    ArrayList auctionList=new ArrayList();
    try
    {
        user= userHome.findByUserNo(bidderId);
        list=user.getUserBids();       
    }
    catch (Exception e)
    {
	    System.out.println("Session Bean: Get User Bids: "+e);
	    return null;
    }
    System.out.println("size of list: " + list.size() );

    String itemName=null;
    String itemDesc=null;
    //for (int i = 0; i < list.size(); i++)
    //{
    BidLocal localBid = null;
    Iterator iter = list.iterator();
    while ( iter.hasNext()){
       localBid = (BidLocal)iter.next();
       log("Bid No " + localBid.getBidNo() + " ptr " + localBid);
 //     localBid = (BidLocal)list.get(i);
      auc = localBid.getAuctionNo();
      Integer userBidAmt=getHighBidForUser(auc, bidderId);
      log("Repeating bid Bid No " + localBid.getBidNo() );
  //    log("bid Number is " + localBid.getBidNo()+ " for i = " + i);
      log("high bid for auction No " + auc.getAuctionNo()+ " is " + userBidAmt);
      log("your bid for auction No " + auc.getAuctionNo()+ " is " + localBid.getBidAmt());
      if ((userBidAmt.intValue())==(localBid.getBidAmt().intValue()))
      {
        try
        {
          ItemLocal itemInstance=auc.getItemNo();
          itemName = itemInstance.getItemName();
          itemDesc = itemInstance.getDescription();
          getHighBidInfo(auc);
        }
        catch(Exception e)
        {
          log("Aget item name in auction Session bean: "+e);
        }
        Auction auction = new Auction(auc.getAuctionNo(), auc.getStartTime(), auc.getStopTime(),
                                      itemName, itemDesc, bidAmt, bidder, localBid.getBidAmt());
        java.util.Date curTime = new java.util.Date();
        Timestamp currentTime = new Timestamp(curTime.getTime());
        if (auc.getStopTime().after(currentTime))
        {
          auctionList.add(auction);
        }
      }
    }
    log("size of arrayList: " + auctionList.size() );
    return auctionList;
  }
     public ArrayList getAuctionList()
     {
       ArrayList list = new ArrayList();
       ArrayList auctionList = new ArrayList();
       ItemLocal itemInstance=null;
       try
       {
        Iterator c = aucHome.findAllAuctions().iterator();
        while (c.hasNext())
        {
            list.add(c.next());
        }
       }
       catch(Exception e)
       {
           log("failed to find all Auctions in auction session Bean: "+e);
       }
       for (int i = 0; i < list.size(); i++)
       {
         auc = (AuctionLocal)list.get(i);
         String itemName=null;
         String itemDesc=null;
         try
         {
           itemInstance=auc.getItemNo();
           itemName = itemInstance.getItemName();
           itemDesc = itemInstance.getDescription();
           getHighBidInfo(auc);
          }
          catch(Exception e)
          {
            log("get item name in auction Session bean: "+e);
          }
          Auction auction = new Auction(auc.getAuctionNo(), auc.getStartTime(), auc.getStopTime(),
                                      itemName, itemDesc, bidAmt, bidder);
          auction.setCondition(itemInstance.getCondition1());
          java.util.Date curTime = new java.util.Date();
          Timestamp currentTime = new Timestamp(curTime.getTime());
          if (auc.getStopTime().after(currentTime))
          {
            auctionList.add(auction);
          }
        }
        return auctionList;
    }

    public ArrayList getUserWinningBids(Integer bidderId) throws RemoteException
  {
    log("Made it to Get User Winning Bids");
    ArrayList list=null;
    ArrayList auctionList=new ArrayList();
    try
    {
        user= userHome.findByUserNo(bidderId);
        list=user.getUserBids();
    }
    catch (Exception e)
    {
	    System.out.println("Session Bean: Get User Bids: "+e);
	    return null;
    }
    System.out.println("size of list: " + list.size() );

    String itemName=null;
    String itemDesc=null;
    //for (int i = 0; i < list.size(); i++)
    //{
    BidLocal localBid = null;
    Iterator iter = list.iterator();
    while ( iter.hasNext())
    {
       localBid = (BidLocal)iter.next();
       log("Bid No " + localBid.getBidNo() + " ptr " + localBid);
       auc = localBid.getAuctionNo();
       PaymentLocal payment = auc.getPayment();
       if (payment==null)
       {
         java.util.Date curTime = new java.util.Date();
         Timestamp currentTime = new Timestamp(curTime.getTime());
         if (auc.getStopTime().before(currentTime))
         {
           log("Repeating bid Bid No " + localBid.getBidNo() );
           log("your bid for auction No " + auc.getAuctionNo()+ " is " + localBid.getBidAmt());
           getHighBidInfo(auc);
           if ((bidAmt.intValue())==(localBid.getBidAmt().intValue()))
           {
              try
              {
                ItemLocal itemInstance=auc.getItemNo();
                itemName = itemInstance.getItemName();
                itemDesc = itemInstance.getDescription();
              }
              catch(Exception e)
              {
                 log("getUserWinningBids item name failure in auction Session bean: "+e);
              }
              Auction auction = new Auction(auc.getAuctionNo(), auc.getStartTime(), auc.getStopTime(),
                                      itemName, itemDesc, bidAmt, bidder, localBid.getBidAmt());
              auctionList.add(auction);
           }
          }
       }
    }
    log("size of arrayList: " + auctionList.size() );
    return auctionList;
  }



	public void ejbCreate()
	{
	  auctionSessionBean();
	}
	public void ejbRemove(){}
	public void ejbActivate(){}
	public void ejbPassivate(){}
  public void setSessionContext(SessionContext sctx) throws RemoteException
  {
    this.sctx = sctx;
  }

  public void unsetSessionContext() throws RemoteException
  {
    sctx = null;
  }
  private static void log (String s)
  {
    System.out.println(s);
  }

}
