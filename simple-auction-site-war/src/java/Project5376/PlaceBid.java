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
import javax.servlet.*;
import javax.servlet.http.*;
import project5376.*;
import java.util.Collection;
import java.util.*;
import java.lang.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
/**
 *
 * @author Rory
 */
public class PlaceBid extends HttpServlet
{
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
   
  /** 
  * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
  * @param request servlet request
  * @param response servlet response
  * @throws ServletException if a servlet-specific error occurs
  * @throws IOException if an I/O error occurs
  */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    // response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try
    {
      response.setContentType("text/html");
      HttpSession session = request.getSession();
      String username = (String)session.getAttribute("userID");
      Integer userNo = (Integer)session.getAttribute("userNo");
      Integer auctionNo = new Integer(request.getParameter("auction"));
      session.setAttribute("auction",auctionNo);
      if(username == null)
      {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Welcome to gBay! Veiw Auction</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Invalid userID.  Please log in or contact administrator.</h1>");
        out.println("<h3><a href=\"" + response.encodeURL("LoginServlet") + "\">Log in.</a></p></h3>");

        out.println("<p><a href=" + response.encodeURL("homepageServlet") + ">Return home</a></p>");

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
        out.println("<h1>Welcome to the auction " + username  +"!!</h1>");
        Auction auc = null;
        try
        {
          auc = auction.getAuctionDetails(auctionNo, userNo);
        }
        catch (Exception e)
        {
          System.out.println("There was an error in getting the data. HomepageSerlet GetuserAuctions: " + e.getMessage());
        }
        try
        {
          out.println("Auction #:  "+ auc.getAuctionNo()+"<br />");
          out.println("Item Name:  " + auc.getItemName()+"<br />");
          out.println("Item Description:  " + auc.getItemDesc()+"<br />");
          out.println("Item Condition:  " + auc.getCondition()+"<br />");
          out.println("Highest Bidder:  " + auc.getHighBidder()+"<br />");
          out.println("Highest Bid:  " + auc.getHighBid()+"<br />");
          out.println("Start Date:  " + auc.getStartTime().toString()+"<br />");
          out.println("Close Date:  " + auc.getStopTime().toString()+"<br />");

        }
        catch (Exception e)
        {
          log("There was an error in getting  data. " + e.getMessage());
        }
        out.println("<form id=\"bidding\" name=\"bidding\" method=\"POST\" action=\"" +
                    response.encodeURL("PlaceBid") + "\">");
        out.print("<p>New Bid: ");
        out.println("<input id=\"bid\" type=\"text\" name=\"bid\"></p>");
        out.print("<p><input type=\"submit\" value=\"Submit\" /></p>");
        out.println("</form>");
        out.println("<script type=\"text/javascript\">");
        out.println("document.bidding.bid.focus();");
        out.println("</script>");
        out.println("<p><a href=" + response.encodeURL("homepageServlet") + ">Return home</a></p>");
        out.println("</body>");
        out.println("</html>");
      }
    }
    catch(Exception e)
    {
      log("unknown error");
    }
    finally
    {
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
    throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("userID");
        Integer userNo = (Integer)session.getAttribute("userNo");
        Integer auctionNo = new Integer(request.getParameter("auction"));
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<head><title>Place Bid</title>");
        out.println("<body>");
        out.println("Welcome to gBay " + username + "</p>");
        out.println("Do you wish to see auction number " + auctionNo+ "?</p>");
        out.println("</body>");
        out.println("</html>");

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("userID");
        Integer userNo = (Integer)session.getAttribute("userNo");
        Integer auctionNo = (Integer)session.getAttribute("auction");
        Integer bid = new Integer(request.getParameter("bid"));
        response.setContentType("text/html");
        if (auction.placeBid(userNo, auctionNo, bid))
        {

          out.println("<html>");
          out.println("<head><title>Place Bid</title>");
          out.println("<body>");
          out.println("You're bid of "+bid+" dollars has been accepted.</p>");
          out.println("<p><a href=" + response.encodeURL("PlaceBid?auction="+auctionNo) + ">Return to auction</a></p>");
          out.println("<p><a href=" + response.encodeURL("homepageServlet") + ">Return home</a></p>");
          out.println("</body>");
          out.println("</html>");
        }
        else
        {
          out.println("<html>");
          out.println("<head><title>Place Bid</title>");
          out.println("<body>");
          out.println("You're bid of "+bid+" dollars has NOT been accepted.</p>");
          out.println("make sure your bid is higher than the highest bid.</p>");
          out.println("<p><a href=" + response.encodeURL("PlaceBid?auction="+auctionNo) + ">Return to auction</a></p>");
          out.println("<p><a href=" + response.encodeURL("homepageServlet") + ">Return home</a></p>");
          out.println("</body>");
          out.println("</html>");

        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
