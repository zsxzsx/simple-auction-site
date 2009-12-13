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
public class AddPaymentServlet extends HttpServlet
{

    private auctionSessionRemote auction;
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
      response.setContentType("text/html;charset=UTF-8");
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
        out.println("<h1>Invalid userID.  Internal Error, contact administrator.</h1>");
        out.println("</body>");
        out.println("</html>");
      }
      else
      {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Pay for item</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Congragulations on winning this auction " + username  +"!!</h1>");
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
          out.println("Make Pament on auction below:<br />");
          out.println("Auction #:  "+ auc.getAuctionNo()+"<br />");
          out.println("Item Name:  " + auc.getItemName()+"<br />");
          out.println("Item Description:  " + auc.getItemDesc()+"<br />");
          out.println("Payment required:  $" + auc.getHighBid()+"<br />");
          session.setAttribute("paymentAmt",auc.getHighBid());


        }
        catch (Exception e)
        {
          log("There was an error in getting  data. " + e.getMessage());
        }
        Date today = new Date();
        int tyear = today.getYear()+1900;

        out.println("<form id=\"payment\" name=\"payment\" method=\"POST\" action=\"" +
                    response.encodeURL("AddPaymentServlet") + "\">");
        out.print("<p>Credit Card Number: ");
        out.println("<input id=\"cardNo\" type=\"text\" name=\"cardNo\"></p>");
        out.print("<p>Experation Date: ");
        out.println("<select type=text name=\"month\">");
        out.println("<option value=\"01/\">January</option>");
        out.println("<option value=\"02/\">February</option>");
        out.println("<option value=\"03/\">March</option>");
        out.println("<option value=\"04/\">April</option>");
        out.println("<option value=\"05/\">May</option>");
        out.println("<option value=\"06/\">June</option>");
        out.println("<option value=\"07/\">July</option>");
        out.println("<option value=\"08/\">August</option>");
        out.println("<option value=\"09/\">September</option>");
        out.println("<option value=\"10/\">October</option>");
        out.println("<option value=\"11/\">November</option>");
        out.println("<option value=\"12/\">December</option>");
        out.println("</select>");
        out.println("<select type=text name=\"year\">");
        out.println("<option value=\""+tyear+"\">"+tyear+"</option>");
        tyear=tyear+1;
        out.println("<option value=\""+tyear+"\">"+tyear+"</option>");
        tyear=tyear+1;
        out.println("<option value=\""+tyear+"\">"+tyear+"</option>");
        tyear=tyear+1;
        out.println("<option value=\""+tyear+"\">"+tyear+"</option>");
        tyear=tyear+1;
        out.println("<option value=\""+tyear+"\">"+tyear+"</option>");
        tyear=tyear+1;
        out.println("<option value=\""+tyear+"\">"+tyear+"</option>");
        tyear=tyear+1;
        out.println("<option value=\""+tyear+"\">"+tyear+"</option>");
        tyear=tyear+1;
        out.println("<option value=\""+tyear+"\">"+tyear+"</option>");
        out.println("</select></p>");
        out.print("<p>Security Code: ");
        out.println("<input id=\"securityCode\" type=\"text\" name=\"securityCode\"></p>");
        out.print("<p>Card Type: ");
        out.println("<select type=text name=\"cardType\">");
        out.println("<option value=\"Master Card\">Master Card</option>");
        out.println("<option value=\"Visa\">Visa</option>");
        out.println("<option value=\"American Express\">American Express</option>");
        out.println("<option value=\"Discover\">Discover</option>");
        out.println("</select></p>");
        out.print("<p><input type=\"submit\" value=\"Submit\" /></p>");
        out.println("</form>");
        out.println("<script type=\"text/javascript\">");
        out.println("document.payment.cardNo.focus();");
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
    throws ServletException, IOException
    {
        processRequest(request, response);
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("userID");
        Integer userNo = (Integer)session.getAttribute("userNo");
        Integer auctionNo = (Integer)session.getAttribute("auction");
        Integer payment = (Integer)session.getAttribute("paymentAmt");
        String cardNo = new String(request.getParameter("cardNo"));
        String expMonth = new String(request.getParameter("month"));
        String expYear = new String(request.getParameter("year"));
        Integer code = new Integer(request.getParameter("securityCode"));
        String cardType = new String(request.getParameter("cardType"));
        response.setContentType("text/html");
        String expDate=expMonth+expYear;
        if (auction.pay(userNo, auctionNo, payment, cardNo, expDate, code, cardType))
        {

          out.println("<html>");
          out.println("<head><title>Pay for item</title></head>");
          out.println("<body>");
          out.println("You're payment of "+payment+" dollars has been accepted.</p>");
          out.println("<p><a href=" + response.encodeURL("PlaceBid?auction="+auctionNo) + ">Return to auction</a></p>");
          out.println("<p><a href=" + response.encodeURL("homepageServlet") + ">Return home</a></p>");
          out.println("</body>");
          out.println("</html>");
        }
        else
        {
          out.println("<html>");
          out.println("<head><title>Pay for item</title>");
          out.println("<body>");
          out.println("You're payment of "+payment+" dollars has NOT been accepted.</p>");
          out.println("unknown failure please contact system administrator.</p>");
          out.println("<p><a href=" + response.encodeURL("homepageServlet") + ">Return home</a></p>");
          out.println("</body>");
          out.println("</html>");

        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
