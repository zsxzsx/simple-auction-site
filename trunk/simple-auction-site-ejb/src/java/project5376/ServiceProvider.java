/*
 */

package project5376;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.rmi.RemoteException;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.*;
import javax.servlet.http.*;
import project5376.*;
import java.util.Collection;

/**
 *
 */
public class ServiceProvider {


/*    public void init() throws ServletException {
		try{
                    userhome = lookupUserHome();
		  } catch (NamingException ne) { System.out.println("Naming Exception in Login Servlet");
		  		throw new ServletException();
		  }
	}
*/
        
  public static UserLocalHome lookupUserHome() throws NamingException
  {
    Context ctx = getInitialContext();
    String strBeanName = "UserBean";

    try {
      // replace the jndi name to your own
      Object home = ctx.lookup(strBeanName);
      return (UserLocalHome) PortableRemoteObject.narrow(home, UserLocalHome.class);

    } catch (NamingException ne) {
      System.out.println("\n\n ERROR!!\n\n Client unable to lookup EJBHome for userbean");

      System.out.println("The client was unable to lookup the EJBHome.  Please make sure " +
      "that you have deployed the ejb with the JNDI name " + strBeanName +
      "on the WebLogic server at  + url ");  // tcc - fix url here

      throw ne;
    }
  }
  public static ItemLocalHome lookupItemHome() throws NamingException
  {
    Context ctx = getInitialContext();
    String strBeanName = "ItemBean";

    try {
      // replace the jndi name to your own
      Object home = ctx.lookup(strBeanName);
      return (ItemLocalHome) PortableRemoteObject.narrow(home, ItemLocalHome.class);

    } catch (NamingException ne) {
      System.out.println("\n\n ERROR!!\n\n Client unable to lookup EJBHome for itembean");

      System.out.println("The client was unable to lookup the EJBHome.  Please make sure " +
      "that you have deployed the ejb with the JNDI name " + strBeanName +
      "on the WebLogic server at  + url ");  // tcc - fix url here

      throw ne;
    }
  }
  public static auctionSessionRemoteHome lookupAuctionHome() throws NamingException
  {
    Context ctx = getInitialContext();
    try
    {
      Object home = ctx.lookup("auctionSessionBean");
      return (auctionSessionRemoteHome) PortableRemoteObject.narrow(home, auctionSessionRemoteHome.class);
    }
    catch(NamingException ne)
    {
      System.out.println("The client was unable to lookup the EJB Home.Please make sure"+
          "that you have deployed the ejb with the JNDI name" +
          "auctionSessionBean.RR1172FacLookUpSessionHome on the WebLogic server at ");
      throw ne;
    }
  }

  public static String get_items_html_table(Collection col) {

        String itemall = "";
        Iterator it;
        auctionSessionRemote auction=null;
        auctionSessionRemoteHome auchome=null;

        try
        {
           auchome = ServiceProvider.lookupAuctionHome();
        }
        catch (Exception ne2)
        {
            System.out.println("Naming Exception in search Servlet");
//          throw new ServletException();
        }
        try
        {
            auction = auchome.create();
        }
        catch (CreateException ne3)
        {
            System.out.println("Create Exception in search Servlet");
            //throw new ServletException();
        }
        catch (RemoteException ne4)
        {
            System.out.println("Create Exception in search Servlet");
            //throw new ServletException();
        }

        if(!col.isEmpty()) {
            itemall += "<table class=\"dataTable\" width=600 border=0><tr><th>Item No</th><th>Item Name</th><th>Description</th><th>Condition</th></tr>";
            it = col.iterator();
            try{
            while (it.hasNext()) {
                    ItemLocal locitem = (ItemLocal)it.next();
                     // out.println("<td><a href=" + response.encodeURL("PlaceBid?auction="+auc.getAuctionNo())
                    //     +   ">"+ auc.getAuctionNo() +"</a></td>");
                                        
/// tcc - todo: fix this response encode stuff:
//                    itemall += "<tr><td><a href=" + response.encodeURL("PlaceBid?auction="
                      itemall += "<tr><td><a href=PlaceBid?auction="
                            +auction.getAuctionNoFromItem(locitem.getItemNo()) + ">"
                            + locitem.getItemNo() + "</td>";
                    itemall += "<td>" + locitem.getItemName() + "</td>";
                    itemall += "<td>" + locitem.getDescription() + "</td>";
                    itemall += "<td>" + locitem.getCondition1() + "</td></tr>";
            }
            }catch(Exception e){
                System.out.println("exception in ServiceProvier.write_table");
            }
            itemall += "</table>";

        } else {
            itemall += "No items found matching your request.";
        }
        return itemall;
    }

  public static Context getInitialContext( ) throws javax.naming.NamingException {
	return new javax.naming.InitialContext( );
  }

  public static String get_username(HttpServletRequest request){

      HttpSession session = request.getSession();
      return (String)session.getAttribute("userID");
  }

  public static void error_html(PrintWriter out, String reason) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet LoginServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(reason);
        out.println("</body>");
        out.println("</html>");
        out.close();
    }


}