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