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
import javax.ejb.FinderException;
import javax.naming.NamingException;
import java.util.Collection;
import java.util.Iterator;

import project5376.*;

/**
 *
 * @author tcook
 */
public class editProfile extends HttpServlet {
   
    private UserLocalHome userhome;

    public void init() throws ServletException {
		try{
                    userhome = ServiceProvider.lookupUserHome();
		  } 
                  catch (Exception e)
                  { System.out.println("Exception in Login Servlet");
		  		throw new ServletException();
		  }
	}

    public void destroy(){};



    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Collection user_col=null;
        Iterator it=null;
        UserLocal user=null;
        String username = ServiceProvider.get_username(request);
        String reason = null;
        try{
            user_col = userhome.findByUserId(username);
        } catch (FinderException fe){
                reason = "<h1>Unknown username: " + username + "  Please try again.</h1>";
                System.out.println("\n\nUNKNOWN Username in edit profile: " +username+ " Exception in " + fe);
                //fe.printStackTrace();
                ServiceProvider.error_html(out, reason);
                return;
        }
        if(!user_col.isEmpty()) {
            it = user_col.iterator();
            while (it.hasNext()) {
                user = (UserLocal) it.next();
                //System.out.println("Comparing passwords: " + user.getPassword()+ " to supplied password: " +password);

                out.println("<html>");
                out.println("<head><title>gBay Registration</title></head>");
                out.println("<body>");
                out.print("<form method=\"POST\" action=\"" + response.encodeURL("editProfile") + "\">");
                out.print("<table class=\"dataTable\" width=600 border=0><tbody>");
                out.print("<tr><td>Username: <input type=\"text\" name=\"username\" value=\""+user.getUserId()+"\"></td>");
                out.print("<td>Password: <input type=\"text\" name=\"password\" value=\""+user.getPassword()+"\"></td></tr>");
                out.print("<tr><td>First Name: <input type=\"text\" name=\"firstname\" value=\""+user.getFirstName()+"\"></td>");
                out.print("<td>Last Name: <input type=\"text\" name=\"lastname\" value=\""+user.getLastName()+"\"></td></tr>");
                out.print("<tr><td>Address: <input type=\"text\" name=\"address1\" value=\""+user.getAddress1()+"\"></td>");
                out.print("<td>Address1: <input type=\"text\" name=\"address2\" value=\""+user.getAddress2()+"\"></td></tr>");
//                out.print("City: <input type=\"text\" name=\"city\" value=\""+user.getCity()+"\">");
                out.print("<tr><td>State: <input type=\"text\" name=\"state\" value=\""+user.getState()+"\"></td>");
                out.print("<tr><td>Zip: <input type=\"text\" name=\"zip\" value=\""+user.getZipCode()+"\"></td></tr>");
                out.print("<tr><td>Email: <input type=\"text\" name=\"email\" value=\""+user.getEmail()+"\"></td>");
                out.print("<td><input type=\"submit\" name=\"Submit\" value=\"Update\"></td></tr>");
                out.println("</tbody></form>");
 
                out.println("</body></html>");
                break;  // in case there are more than one with the same username todo: need to check this if that can happen, or prevent
            }
        }
        out.close();
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

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Collection user_col=null;
        Iterator it=null;
        UserLocal user=null;
        String username = ServiceProvider.get_username(request);
        String reason = null;
        try{
            user_col = userhome.findByUserId(username);
        } catch (FinderException fe){
                reason = "<h1>Unknown username: " + username + "  Please try again.</h1>";
                System.out.println("\n\nUNKNOWN Username in edit profile: " +username+ " Exception in " + fe);
                //fe.printStackTrace();
                ServiceProvider.error_html(out, reason);
                return;
        }
        if(!user_col.isEmpty()) {
            it = user_col.iterator();
            while (it.hasNext()) {
                user = (UserLocal) it.next();
                //System.out.println("Comparing passwords: " + user.getPassword()+ " to supplied password: " +password);

                if(!user.getUserId().equals(request.getParameter("username"))){
                    user.setUserId(request.getParameter("username"));
                }
                if(!user.getPassword().equals(request.getParameter("password"))){
                    user.setPassword(request.getParameter("password"));
                }
                if(!user.getFirstName().equals(request.getParameter("firstname"))){
                    user.setFirstName(request.getParameter("firstname"));
                }
                if(!user.getLastName().equals(request.getParameter("lastname"))){
                    user.setLastName(request.getParameter("lastname"));
                }
                if(!user.getAddress1().equals(request.getParameter("address1"))){
                    user.setAddress1(request.getParameter("address1"));
                }
                if(!user.getAddress2().equals(request.getParameter("address2"))){
                    user.setAddress2(request.getParameter("address2"));
                }
                if(!user.getState().equals(request.getParameter("state"))){
                    user.setState(request.getParameter("state"));
                }
                if(!user.getZipCode().equals(request.getParameter("zip"))){
                    user.setZipCode(new Integer(request.getParameter("zip")));
                }
                if(!user.getEmail().equals(request.getParameter("email"))){
                    user.setEmail(request.getParameter("email"));
                }
                break;
            }
        }
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Profile Updated</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Profile successfully updated.</h3>");
        out.println("<a href=\"" + response.encodeURL("homepageServlet") + "\"> return to home page</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();

    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
