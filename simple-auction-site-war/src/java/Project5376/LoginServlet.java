/*
 */

package Project5376;

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
public class LoginServlet extends HttpServlet 
{
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

  public void destroy(){};  
        
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
     ServletContext ctx = this.getServletContext();
     HttpSession session = request.getSession();
     String servletName = this.getServletName();
     String action = request.getParameter("action");

     //System.out.println("\n\ncalling doGet: action == " + request.getParameter("action") +"\n\n");

     if(action == null)
     {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Welcome to gBay</title>");
        /*
         out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
         out.println("<link rel=\"stylesheet\" href=\"stripy_tables.css\" type=\"text/css\" />");
         out.println("<script type=\"text/javascript\" src=\"core.js\"></script>");
         out.println("<script type=\"text/javascript\" src=\"stripy_tables.js\"></script>
        */
        out.println("</head><body>");
        out.println("<p>Welcome to gBay.  Please log in.</p><br>");
        out.println("<form id=\"login\" name=\"login\" method=\"POST\" action=\"" +
                    response.encodeURL("LoginServlet?action=login") + "\">");
        out.print("<p>User name: ");
        out.println("<input id=\"username\" type=\"text\" name=\"username\"></p>");
        out.print("<p>Password: ");
        out.println("<input type=\"password\" name=\"password\"></p>");
        out.print("<p><input type=\"submit\" ");
        out.println("name=\"Submit\" value=\"Login\"></p>");
        out.println("</form>");
        out.println("<script type=\"text/javascript\">");
        out.println("document.login.username.focus();");
        out.println("</script>");
        out.println("<p><a href=\"" + response.encodeURL("LoginServlet?action=register") + "\"> register</a></p>");
        out.println("</body></html>");
     } 
     else if (action.equals("register"))
     {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Welcome to gBay</title>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        out.println("<link rel=\"stylesheet\" href=\"stripy_tables.css\" type=\"text/css\" />");
        out.println("<script type=\"text/javascript\" src=\"core.js\"></script>");
        out.println("<script type=\"text/javascript\" src=\"stripy_tables.js\"></script></head><body>");
        out.println("<html>");
        out.println("<body>");
        out.println("<form method=\"POST\" action=\"" +
                      response.encodeURL("LoginServlet?action=register.do") + "\">");
        out.println("<div align=\"justify\"><table class=\"dataTable\" width=600 border=0><tbody>");

        out.print("<tr><td>ID(integer): <input type=\"text\" name=\"id\"></td>");
        out.print("<td>Email: <input align=\"right\" type=\"text\" name=\"email\"></td></tr>");

        out.print("<tr><td>Username: <input align=\"right\" type=\"text\" name=\"username\"></td>");
        out.print("<td>Address: <input align=\"right\" type=\"text\" name=\"address1\"></td></tr>");

        out.print("<tr><td>Password: <input type=\"text\" name=\"password\"></td>");
        out.print("<td>Address1: <input type=\"text\" name=\"address2\"></td></tr>");

        out.print("<tr><td>First Name: <input type=\"text\" name=\"firstname\"></td>");
        out.print("<td>City: <input type=\"text\" name=\"city\"></td></tr>");

        out.print("<tr><td>Last Name: <input type=\"text\" name=\"lastname\"></td>");
        out.print("<td>State: <input align=\"right\" type=\"text\" name=\"state\"></td></tr>");

        out.print("<tr><td>Zip: <input align=\"right\" type=\"text\" name=\"zip\"></td>");
        out.print("<td><input type=\"submit\" ");

        out.println("name=\"Submit\" value=\"Register\"></td></tr></table>");
        out.println("</div></form>");
        out.println("</body></html>");
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
    throws ServletException, IOException 
  {
    String action = request.getParameter("action");
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    String reason = "<h1>Password does not match for username: "+username+". Please try again.</h1>";


    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try 
    {
      if (action==null) 
      {
         reason = "<h1>Unknown error post with action == null, please contact webmaster@gbay.com</h1>";
         error_html(out, reason);
      } 
      else if (action.equals("login")) 
      {
         String status = "fail";
         Collection user_col=null;
		     Iterator it=null;
         UserLocal user=null;
         Integer userNo= new Integer(0);
         try
         {
           user_col = userhome.findByUserId(username);
         } 
         catch (FinderException fe)
         {
           status = "fail";
           reason = "<h1>Unknown username: " + username + "  Please try again.</h1>";
           //System.out.println("\n\nException in " + fe);
			     //fe.printStackTrace();
	 }
         if(!user_col.isEmpty()) 
         {
           it = user_col.iterator();
           while (it.hasNext()) 
           {
              user = (UserLocal) it.next();
              //System.out.println("Comparing passwords: " + user.getPassword()+ " to supplied password: " +password);
              if(user.getPassword().equals(password)) 
              {
                userNo=user.getUserNo();
                System.out.println("userNo to set = " + userNo);
                status = "pass";
                break;
              }
           }
         }
         //System.out.println("STATUS== " +status);

         if(status.equals("fail"))
         {
            error_html(out, reason);
         } 
         else if (status.equals("pass"))
         {
            HttpSession session = request.getSession();
            session.setAttribute("userID", username);
            session.setAttribute("userNo", userNo);

            // tcc - cant get the forward to work here, so I used redirect instead.. code for forward is commented out here:
            //ServletContext ctx = this.getServletContext();
            //RequestDispatcher dispatcher = ctx.getRequestDispatcher("/homepageServlet");
            //dispatcher.forward(request, response);
            response.sendRedirect(response.encodeURL("homepageServlet"));
         }
      } 
      else if (action.equals("register.do"))
      {
         if (!find_user(username)) 
         {
            response.sendRedirect(response.encodeURL("LoginError.html"));
         }

         String result = register_user(request);

         out.println("<html>");
         out.println("<head><title>gBay Registration</title></head>");
         out.println("<body>");
         out.println("<p>gBay Registration</p>");
         out.println("<p> </p>");
         out.print("<p>id: " + request.getParameter("id") + "</p>");
         out.print("<p>Username: " + request.getParameter("username") + "</p>");
         out.print("<p>Password: " + request.getParameter("password") + "</p>");
         out.print("<p>First Name: " + request.getParameter("firstname") + "</p>");
         out.print("<p>Last Name: " + request.getParameter("lastname") + "</p>");
         out.print("<p>Address: " + request.getParameter("address1") + "</p>");
         out.print("<p>Address1: " + request.getParameter("address2") + "</p>");
         out.print("<p>City: " + request.getParameter("city") + "</p>");
         out.print("<p>State: " + request.getParameter("state") + "</p>");
         out.print("<p>Zip Code: " + request.getParameter("zip") + "</p>");
         out.print("<p>Email: " + request.getParameter("email") + "</p>");
         out.println("</body></html>");
      }

    } 
    finally 
    {
       out.close();
    }

  }

    boolean find_user(String username){

        boolean status = true;
        Collection user_col=null;

        try{
            user_col = userhome.findByUserId(username);
        } catch (FinderException fe){
                status = false;
        }
        return status;
    }

  String register_user(HttpServletRequest request)
  {

    String status = "pass";
    Collection user_col=null;
    Iterator it=null;
    UserLocal user=null;

    try
    {
      userhome.create(  new Integer(request.getParameter("id")), request.getParameter("username"), request.getParameter("password"),
         request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("address1"),
         request.getParameter("address2"),request.getParameter("city"), request.getParameter("state"),
         request.getParameter("zip"),request.getParameter("email"));
      //System.out.println("DONE calling create in Login Servlet");
    } 
    catch (CreateException ne) 
    {
       System.out.println("create Exception for user in Login Servlet");
       //throw new ServletException();
       ne.printStackTrace();
       status = "fail";
    }
    return status;
  }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void error_html(PrintWriter out, String reason) 
    {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet LoginServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(reason);
        out.println("</body>");
        out.println("</html>");
    }

}
