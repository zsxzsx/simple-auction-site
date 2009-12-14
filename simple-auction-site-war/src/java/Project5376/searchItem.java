/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Project5376;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.ArrayList;
import javax.rmi.PortableRemoteObject;
import project5376.*;

/**
 *
 * @author tcook
 */
public class searchItem extends HttpServlet {

  private SearchItemSessionRemoteHome searchitemhome;
  private SearchItemSessionRemote searchitemlocal;
  private auctionSessionRemote auction;
  private auctionSessionRemoteHome home;

  public void init() throws ServletException
  {
        try
        {
           searchitemhome = ServiceProvider.lookupSearchItemSession();
        }
        catch (Exception ne)
        {
            System.out.println("Naming Exception in search Servlet");
//          throw new ServletException();
        }
        try
        {
            searchitemlocal = searchitemhome.create();
        }
        catch (CreateException ne)
        {
            System.out.println("Create Exception in search Servlet");
            throw new ServletException();
        }
        catch (RemoteException ne)
        {
            System.out.println("Create Exception in search Servlet");
            throw new ServletException();
        }
        try
        {
           home = lookupHome();
        }
        catch (Exception ne2)
        {
            System.out.println("Naming Exception in search Servlet");
//          throw new ServletException();
        }
        try
        {
            auction = home.create();
        }
        catch (CreateException ne3)
        {
            System.out.println("Create Exception in search Servlet");
            throw new ServletException();
        }
        catch (RemoteException ne4)
        {
            System.out.println("Create Exception in search Servlet");
            throw new ServletException();
        }
  }
  
  public static Context getInitialContext( ) throws javax.naming.NamingException
  {
	  return new javax.naming.InitialContext( );
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

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String str="";
        String item_desc = request.getParameter("item_desc");
        String action = request.getParameter("action");
        if(action == null){
            str=searchitemlocal.SearchItemsByName(item_desc);
        } else if (action.equals("byDesc")){
            str=searchitemlocal.SearchItemsByDescription(item_desc);
        }
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {
            //TODO output your page here
            out.println("<html>");
            ServiceProvider.print_header(out);
            out.println("<body>");
            out.println("<h3>you searched for: " + item_desc  + "</h3><br>");
            out.println(str);
            out.println("</body>");
            out.println("</html>");
        } finally { 
            out.close();
        }
    } 

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
        //processRequest(request, response);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
 //       String username = (String)session.getAttribute("userID");
//        Integer userNo = (Integer)session.getAttribute("userNo");
//        UserLocal user=null;
        Integer auctionNo = new Integer(0);


        //System.out.println("\n\ncalling doGet: action == " + request.getParameter("action") +"\n\n");

         if(action == null)
         {
            try {
                //TODO output your page here
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet searchItem</title>");
                out.println("</head>");
                out.println("<body>");

                out.println("<p><a href=\"searchItem?action=showAll\">list all items</a></p><br><br>");
                out.println("<form id=\"form1\" name=\"form1\" method=\"post\" action=\"searchItem\">");
                out.println("<label><input type=\"text\" name=\"item_desc\" /></label><label>");
                out.println("<input type=\"submit\" name=\"SEARCH\" value=\"Search Items by Name\" /></label></form>");

                out.println("<br><form id=\"form2\" name=\"form2\" method=\"post\" action=\"searchItem?action=byDesc\">");
                out.println("<label><input type=\"text\" name=\"item_desc\" /></label><label>");
                out.println("<input type=\"submit\" name=\"SEARCH\" value=\"Search Items by Description\" /></label></form>");

                out.println("<script type=\"text/javascript\">");
                out.println("document.form1.item_desc.focus();");
                out.println("</script>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
         } else if (action.equals("showAll")) {

                String str=searchitemlocal.SearchItemsAll();

                response.setContentType("text/html;charset=UTF-8");

                try {
      out.println("<html>");
       out.println("<head>");
       out.println("<title>Welcome to gBay!</title>");
       out.println("</head>");
       out.println("<body>");
       ArrayList auctionList = null;

       try
       {
         auctionList = auction.getAuctionList();
       }
       catch (Exception e)
       {
         System.out.println("There was an error in getting the data. HomepageSerlet GetuserAuctions: " + e.getMessage());
       }
       if(auctionList.isEmpty())
       {
         out.println("There are currently no open auctions");
         out.println("<p>");
       }
       else
       {
         out.println("<h3>All items:</h3><br>");
         ServiceProvider.print_header(out);
         out.println("<table class=\"dataTable\" width=600 border=0>");

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
       }                    //TODO output your page here
        
     } 
     finally
     {
         out.close();
     }

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
    }

}
