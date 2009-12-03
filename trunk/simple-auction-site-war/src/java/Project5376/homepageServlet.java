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
    private UserLocalHome userhome;
  public void init() throws ServletException
  {
		try
		{
                 userhome = lookupUserHome();
		}
		catch (NamingException ne)
		{
		  System.out.println("Naming Exception in Login Servlet");
		  throw new ServletException();
		}
  }
  UserLocalHome lookupUserHome() throws NamingException
  {
    Context ctx = getInitialContext();
    String strBeanName = "UserBean";

    try
    {
      // replace the jndi name to your own
      Object home = ctx.lookup(strBeanName);
      return (UserLocalHome) PortableRemoteObject.narrow(home, UserLocalHome.class);
    }
    catch (NamingException ne)
    {
      System.out.println("\n\n ERROR!!\n\n Client unable to lookup EJBHome for userbean");

      log("The client was unable to lookup the EJBHome.  Please make sure " +
      "that you have deployed the ejb with the JNDI name " + strBeanName +
      "on the WebLogic server at  + url ");  // tcc - fix url here

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

   try
   {
     if(username == null)
     {
       out.println("<html>");
       out.println("<head>");
       out.println("<title>Welcome to gBay!</title>");
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
       out.println("<title>Welcome to gBay!</title>");
       out.println("</head>");
       out.println("<body>");
       out.println("<h3>You are now logged in.</h3><br><br><h1>Welcome to gBay " + username  +"!!</h1>");
       out.println("<p><a href=\"" + response.encodeURL("editProfile") + "\"> Edit Profile</a></p>");
       Integer userNo = (Integer)session.getAttribute("userNo");
       /*   try
       {
         UserLocal user = userhome. findByUserNo(userNo);
         Collection col = (Collection)user.getAuctionCollection();
         Iterator it = col.iterator();
         while (it.hasNext())
         {
           AuctionLocal auction = (AuctionLocal) PortableRemoteObject.narrow(it.next(), AuctionLocal.class);
           ItemLocal item = auction.getItemNo();
           Collection col2 = (Collection)auction.getBidCollection();
           Iterator itt = col2.iterator();
         }
        }
        catch (Exception e)
        {
	  System.out.println("Item Primary Key find error: "+e);
        }    */
      }
      out.println("</body>");
      out.println("</html>");
   }
   catch (Exception e)
   {
     System.out.println("Item Primary Key find error: "+e);
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
