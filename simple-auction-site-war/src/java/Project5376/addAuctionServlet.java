/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Project5376;

import java.util.Collection;
import java.util.*;
import java.rmi.*;
import javax.servlet.*;
import java.io.*;
import javax.naming.*;
import javax.servlet.http.*;
import javax.rmi.PortableRemoteObject;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import project5376.*;



public class addAuctionServlet extends HttpServlet
{
  auctionSessionRemote auction;
  private auctionSessionRemoteHome home;


  private static void log2 (String s)
  {
    System.out.println(s);
  }

   private Context getInitialContext() throws NamingException
   {
      try
      {
         // Get an InitialContext
         Properties h = new Properties();
         h.put(Context.INITIAL_CONTEXT_FACTORY,
               "weblogic.jndi.WLInitialContextFactory");
         //h.put(Context.PROVIDER_URL, url);
         return new InitialContext(h);
      }
      catch (NamingException ne)
      {
         log("We were unable to get a connection to the WebLogic server at ");
         log("Please make sure that the server is running.");
         throw ne;
      }
   }

  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
    System.out.println("in init of addAuctionServlet");
    try
    {
      home = lookupHome();
      auction = home.create();
      System.out.println("create ok");
    }
    catch(Exception e)
    {
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
      log2("The client was unable to lookup the EJB Home.Please make sure"+
          "that you have deployed the ejb with the JNDI name" +
          "auctionSessionBean.RR1172FacLookUpSessionHome on the WebLogic server at ");
      throw ne;
    }
  }
protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
     //processRequest(request, response);
     ServletContext ctx = this.getServletContext();
     HttpSession session = request.getSession();
     String servletName = this.getServletName();
     String action = request.getParameter("action");

     log2("\ncalling doGet: action = " + request.getParameter("action") +"\n\n");

     if(action == null)
     {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<head><title>gBay - Create Auction</title>");
        out.println("<script type=\"text/javascript\" src=\"datetimepicker_css.js\"></script>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Enter the following information to create a new auction<h3>");
        out.println("<form name=\"addAuction\" action=\"addAuctionServlet\" method=\"post\">");
        out.println("Item Name:");
        out.println("<input type=\"text\" name=\"itemName\" size=\"40\" />");
        out.println("<br />");
        out.println("Description:<br />");
        out.println("<textarea rows=\"5\" cols=\"30\" type=\"text\" name=\"itemDesc\"></textarea>");
        out.println("<br />");
        out.println("Condition:");
        out.println("<select type=text name=\"itemCond\">");
        out.println("<option value=\"1\">New</option>");
        out.println("<option value=\"2\">Like new</option>");
        out.println("<option value=\"3\">Fine</option>");
        out.println("<option value=\"4\">Very Good</option>");
        out.println("<option value=\"5\">Good</option>");
        out.println("<option value=\"6\">Fair</option>");
        out.println("<option value=\"7\">Poor</option>");
        out.println("</select><br />");
        out.println("<p>");
        out.println("Auction Start Time:");
        out.println(" <input id=\"demo2\" type=\"text\" name=\"startTime\" size=\"25\">");
        out.println("<a href=\"javascript:NewCssCal('demo2','yyyyMMdd','DropDown',true,12)\">");
        out.println("<img src=\"cal.gif\" width=\"16\" height=\"16\" border=\"0\" alt=\"Pick a date\">");
        out.println("</a>");
        out.println("<br />");
        out.println("</p>");
        out.println("<p>");
        out.println("Auction Stop Time:");
        out.println("<input id=\"demo14\" type=\"text\" name=\"stopTime\" size=\"25\">");
        out.println("<a href=\"javascript:NewCssCal('demo14','yyyyMMdd','DropDown',true,12)\">");
        out.println("<img src=\"cal.gif\" width=\"16\" height=\"16\" border=\"0\" alt=\"Pick a date\">");
        out.println(" </a>");
        out.println("<br />");
        out.println("</p>");
        out.println("<br /><input type=submit value=\"Submit\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
     }

  }

  public void doPost(HttpServletRequest req,HttpServletResponse res)
                    throws ServletException,IOException
  {
    System.out.println("in doPost of addAuctionServlet");
    int error = 1;
    HttpSession session = req.getSession();
    Date startAuc= new Date();
    Date stopAuc= new Date();   
    PrintWriter out = res.getWriter();
    String name = req.getParameter("itemName");
    if (name.compareTo("") == 0)
    {
      error = error * 2; // No Item to auction
    }
    else System.out.println("Why isn't name failing?");
    String desc = req.getParameter("itemDesc").trim();
    String cond = req.getParameter("itemCond").trim();
    String start = req.getParameter("startTime");
    String stop = req.getParameter("stopTime");
    System.out.println("Name: " + name + " start " + start + " end " +
                stop + " desc " + desc + " cond " + cond);

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mma");
    
    if  (start.compareTo("") == 0)
    {
      error = error * 7; // no Start Date
    }
    else if  (stop.compareTo("") == 0)
    {
      error = error * 11; // No Stop Date
    }
    else
    {
      try
      {
        startAuc = df.parse(start);
      }
      catch (ParseException e)
      {
        error = error * 7; // Start Date invalid
      }
      try
      {
        stopAuc = df.parse(stop);
      }
      catch (ParseException e)
      {
        error = error * 11; // Stop Date invalid
      }
    }
    if ((error==1) ||(error==2))
    {
      if (stopAuc.before(startAuc))
      {
        error = error * 3; //auction ends before starting
      }
      Date curDate = new Date();
      if (stopAuc.before(curDate))
      {
        error = error * 5; //auction already ended
      }
    }
    Integer sellingUser = (Integer)session.getAttribute("userNo");
     if (error > 1)
    {
      generateErrorPage(out, error);
    }
    else
    {
      try
      {
        auction.addAuction(sellingUser, startAuc, stopAuc, name, desc, cond);
      }
      catch (Exception e)
      {
        log2("There was an error during input of new auction" + e.getMessage());
        return;
      }
      generateAddAuctionPage(out, name, res);
    }
  }

  public void generateErrorPage(PrintWriter out, int error) throws IOException
  {
    // This loop prints the error message.
    out.print("<H1>Oops!</H1>");

    out.print("<BR>");
    if (error!=7)
    {
      out.print("Some information is missing in incorrect from your auction input form.<BR>");
      out.print("Please go back and fill in or correct the following items so we can continue:<BR>");
    	if ((error % 2)==0)
    	{
     		out.println("Item Name is missing");
        out.print("<BR>");
   	  }
      if ((error % 3)==0)
  	  {
    		out.println("Auction is ending before it starts");
        out.print("<BR>");
   	  }
      if ((error % 5)==0)
  	  {
    		out.println("Auction is ending prior to the current time");
        out.print("<BR>");
   	  }
      if ((error % 7)==0)
  	  {
    		out.println("Auction start time is missing or incorrectly formatted");
        out.print("<BR>");
   	  }
      if ((error % 11)==0)
  	  {
    		out.println("Auction stop time is missing or incorrectly formatted");
        out.print("<BR>");
   	  }
    }
    out.println("</BODY></HTML>");
    out.flush();
    out.close();
    return;
  } // end of printErrorPage()


  private void generateAddAuctionPage(PrintWriter out, String name, HttpServletResponse response)
  {
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Added Auction.</title>");
    out.println("</head>");
    out.println("<body>");
    out.println(""+name + " has been added to be auctioned.");
    out.println("<p>");
    
    out.println("<p><a href=\"" + response.encodeURL("homepageServlet") + "\"> Back to home page</a></p>");

  //  out.println("<input type=\"button\" name=\"Menu\" value=\"Back to Menu\" onclick=\"window.location = 'index.html' \" />");
    out.println("</body>");
    out.println("</html>");
  }



}