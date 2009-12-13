/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Project5376;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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
import java.util.ArrayList;
import java.sql.Timestamp;
/**
 *
 * @author tcook
 */
public class homepageServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    auctionSessionRemote auction;
    private auctionSessionRemoteHome home;


  public void init() throws ServletException
  {

    try
    {
      home = lookupHome();
      auction = home.create();
      System.out.println("create ok");
    }
    catch(Exception e)
    {
     System.out.println("Naming Exception in Login Servlet");
     e.printStackTrace();
    }
  }
    private auctionSessionRemoteHome lookupHome() throws NamingException
  {
    Context ctx = getInitialContext();
    try
    {
      Object home = ctx.lookup("auctionSessionBean");
      return (auctionSessionRemoteHome) PortableRemoteObject.narrow(home, auctionSessionRemoteHome.class);
    }
    catch(NamingException ne)
    {
      log("The client was unable to lookup the EJB Home.Please make sure"+
          "that you have deployed the ejb with the JNDI name" +
          "auctionSessionBean.RR1172FacLookUpSessionHome on the WebLogic server at ");
      throw ne;
    }
  }

public static Context getInitialContext( ) throws javax.naming.NamingException
  {
	  return new javax.naming.InitialContext( );
  }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet homepageServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet homepageServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
{
   //processRequest(request, response);
   response.setContentType("text/html;charset=UTF-8");
   PrintWriter out = response.getWriter();
   HttpSession session = request.getSession();
   String username = (String)session.getAttribute("userID");
   Integer userNo = (Integer)session.getAttribute("userNo");
   UserLocal user=null;
   Integer auctionNo = new Integer(0);


   try
   {
     if(username == null)
     {
       out.println("<html>");
       out.println("<head>");
       out.println("<title>Welcome to gBay! Logged In</title>");
       out.println("</head>");
       out.println("<body>");
       out.println("<h3>You are not logged in. Please <a href=\"" + response.encodeURL("LoginServlet") + "\"> log in.</a></p></h3>");
       out.println("</body>");
       out.println("</html>");
     }
     else
     {
       out.println("<html>");
       out.println("<head>");
       out.println("<title>Welcome to gBay!</title>");
       out.println("</head>");
       out.println("<body>");
       out.println("<h3>You are now logged in.</h3><br><br><h1>Welcome to gBay " + username  +"!!</h1>");
       ArrayList auctionList = null;
       
       try
       {
         auctionList = auction.getUserAuctions(userNo);
       }
       catch (Exception e)
       {
         System.out.println("There was an error in getting the data. HomepageSerlet GetuserAuctions: " + e.getMessage());
       }
       out.println("Selling Auctions");
       if(auctionList.isEmpty())
       {
         out.println("You have no open auctions");
         out.println("<p>");
       }
       else
       {
         out.println("<table border=\"1\">");

         out.println("<tr>");
         out.println("<th>Auction #</th>");
         out.println("<th>Item</th>");
         out.println("<th>Highest Bidder</th>");
         out.println("<th>Bid</th>");
         out.println("<th>Close Date</th>");
         out.println("</tr>");
         for (int i = 0; i < auctionList.size(); i++)
         {
               Auction auc = (Auction)((ArrayList)auctionList).get(i);

            try
            {
              out.println("<tr>");
              auctionNo= auc.getAuctionNo();
              out.println("<td><a href=" + response.encodeURL("PlaceBid?auction="+auc.getAuctionNo()) + ">"+ auc.getAuctionNo() +"</a></td>");
              out.println("<td>" + auc.getItemName() + "</td>");
              out.println("<td>" + auc.getHighBidder() + "</td>");
              out.println("<td>" + auc.getHighBid() + "</td>");
              out.println("<td>" + auc.getStopTime().toString() + "</td>");
              out.println("</tr>");
             }
             catch (Exception e)
             {
               log("There was an error in getting  data. " + e.getMessage());
             }
           }
           out.println("</table>");
       }
       try
       {
         auctionList = auction.getUserBids(userNo);
       }
        catch (Exception e)
       {
         System.out.println("There was an error in getting the data. HomepageSerlet GetUserBids: " + e.getMessage());
       }
       out.println("Bidded on Auctions");
       if(auctionList.isEmpty())
       {
         out.println("You have placed bids on no open auctions");
         out.println("<p>");
       }
       else
       {
         out.println("<table border=\"1\">");

         out.println("<tr>");
         out.println("<th>Auction #</th>");
         out.println("<th>Item</th>");
         out.println("<th>Highest Bidder</th>");
         out.println("<th>Bid</th>");
         out.println("<th>Your Last Bid</th>");
         out.println("<th>Close Date</th>");
         out.println("</tr>");
         for (int i = 0; i < auctionList.size(); i++)
         {
               Auction auc = (Auction)((ArrayList)auctionList).get(i);

            try
            {
              out.println("<tr>");
              auctionNo= auc.getAuctionNo();
              out.println("<td><a href=" + response.encodeURL("PlaceBid?auction="+auc.getAuctionNo()) + ">"+ auc.getAuctionNo() +"</a></td>");
              out.println("<td>" + auc.getItemName() + "</td>");
              out.println("<td>" + auc.getHighBidder() + "</td>");
              out.println("<td>" + auc.getHighBid() + "</td>");
              out.println("<td>" + auc.getUserBid() + "</td>");
              out.println("<td>" + auc.getStopTime().toString() + "</td>");
              out.println("</tr>");
             }
             catch (Exception e)
             {
               log("There was an error in getting  data. " + e.getMessage());
             }
           }
           out.println("</table>");


         }
         try
       {
         auctionList = auction.getUserWinningBids(userNo);
       }
        catch (Exception e)
       {
         System.out.println("There was an error in getting the data. HomepageSerlet GetUserBids: " + e.getMessage());
       }
       if(auctionList.isEmpty())
       {
         out.println("<br /><br />You have no auctions that you have not paid for");
         out.println("<p>");
       }
       else
       {
         out.println("<br /><br />Auctions Won!! Please click on Auction to make payment.");

         out.println("<table border=\"1\">");

         out.println("<tr>");
         out.println("<th>Auction #</th>");
         out.println("<th>Item</th>");
         out.println("<th>Highest Bidder</th>");
         out.println("<th>Bid</th>");
         out.println("<th>Your Last Bid</th>");
         out.println("<th>Close Date</th>");
         out.println("</tr>");
         for (int i = 0; i < auctionList.size(); i++)
         {
               Auction auc = (Auction)((ArrayList)auctionList).get(i);

            try
            {
              out.println("<tr>");
              auctionNo= auc.getAuctionNo();
              out.println("<td><a href=" + response.encodeURL("AddPaymentServlet?auction="+auc.getAuctionNo()) + ">"+ auc.getAuctionNo() +"</a></td>");
              out.println("<td>" + auc.getItemName() + "</td>");
              out.println("<td>" + auc.getHighBidder() + "</td>");
              out.println("<td>" + auc.getHighBid() + "</td>");
              out.println("<td>" + auc.getUserBid() + "</td>");
              out.println("<td>" + auc.getStopTime().toString() + "</td>");
              out.println("</tr>");
             }
             catch (Exception e)
             {
               log("There was an error in getting  data. " + e.getMessage());
             }
           }
           out.println("</table>");


         }

         out.println("<p><a href=\"" + response.encodeURL("editProfile") + "\"> Edit Profile</a></p>");
         out.println("<p><a href=\"" + response.encodeURL("addAuctionServlet") + "\"> sell</a></p>");
       }
       out.println("</body>");
       out.println("</html>");
     }
     catch (Exception e)
     {
       System.out.println("Are we failing here? "+e);
     }
     finally
     {
       out.close();
     }
 } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
