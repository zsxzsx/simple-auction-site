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
  private static int aucPk;
  private static int itemPk;
  private static int bidPk;
  private static int userPk;
public void auctionSessionBean()
{
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
      Collection col = (Collection)aucHome.findAllAuctions();
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
      Collection col = (Collection)itemHome.findAllItems();
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
      Collection col = (Collection)userHome.findAllUsers();
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
      Collection col = (Collection)bidHome.findAllBids();
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

    log("Highest Primary key is: "+aucPk+" "+itemPk);

	}

	private AuctionLocalHome lookupAuctionHome() throws NamingException
  {
    ctx = getInitialContext();
    try
    {
      Object home = ctx.lookup("auctionBean");
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
      Object home = ctx.lookup("itemBean");
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

  private BidLocalHome lookupBidHome() throws NamingException
  {
    ctx = getInitialContext();
    try
    {
      Object home = ctx.lookup("bidBean");
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
      Object home = ctx.lookup("userBean");
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

  public void addAuction(Integer sellerNo, Date startTime, Date stopTime, String itemName, String itemDesc, String itemCond) throws RemoteException
  {

    aucPk = aucPk + 1;
    itemPk = itemPk+1;
    Integer auctionNo = new Integer(aucPk);
    Integer itemNo = new Integer(itemPk);


    try
    {
      Integer cond = new Integer(itemCond);
      ItemLocal itemInstance = itemHome.create(itemNo, itemName, itemDesc, cond);
      UserLocal sellerId = userHome.findByUserNo(sellerNo);
      Timestamp startTimeStamp = new Timestamp(startTime.getTime());
      Timestamp stopTimeStamp = new Timestamp(stopTime.getTime());
      aucHome.create(auctionNo, sellerId, itemInstance, startTimeStamp, stopTimeStamp);
//      auc.setItemNo( itemNo);
//      auc.setStartTime( startTimeStamp);
//      auc.setStopTime( stopTimeStamp);
      
     //tcc  todo: need to set sellerID  get it from HttpSession in servlet then do findby userID
      // see   LoginServlet.java:263  find_user function for example

/*           user = ServiceProvider.lookupUserHome();
      auc.setSellerId( user.findbyPrimaryKey (sellerId));
 */
    }
     catch (Exception e)
    {
	    log("Add auction: "+e);
	    return;
    }
        try
    {
      itemHome.create( itemNo);
      item.setItemName( itemName);
      item.setDescription( itemDesc);
      Integer cond = new Integer (itemCond);
      item.setCondition1( cond);
    }
     catch (Exception e)
    {
	    log("Add item: "+e);
	    return;
    }
    return;
  }

  public Collection getAllAuctions() throws RemoteException
  {
    log("Made it to Get All Auctions");
    Collection enum=null;
    try
    {
      enum=(Collection)aucHome.findAllAuctions();
    }
    catch (Exception e)
    {
	    log("Get All Auctions: "+e);
	    return null;
    }
    return enum;
  }

  public Integer getHighBidByAuctionNo(Integer auctionNo) throws RemoteException
  {
    Integer bidAmt= new Integer(0);
    log("Made it to Get High Bid by Auction No");
    Collection col = null;
    try
    {
/*      col = (Collection)bidHome.findByAuction(auctionNo);
      Iterator it = col.iterator();
      while (it.hasNext())
      {
        bid = (BidLocal) PortableRemoteObject.narrow(it.next(), BidLocal.class);

        if ((bid.getBidAmt().intValue()) > bidAmt.intValue())
        {
          bidAmt = (bid.getBidAmt());
        }
      }*/
    }
    catch (Exception e)
    {
	    System.out.println("Bid Primary Key find error: "+e);
    }
    return bidAmt;
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
