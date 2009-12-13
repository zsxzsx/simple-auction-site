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
import project5376.*;

/**
 *
 * @author tcook
 */
public class searchItem extends HttpServlet {

  private SearchItemSessionRemoteHome searchitemhome;
  private SearchItemSessionRemote searchitemlocal;

  public void init() throws ServletException
  {
        try {
           searchitemhome = ServiceProvider.lookupSearchItemSession();
        }
        catch (Exception ne)
        {
            System.out.println("Naming Exception in search Servlet");
//          throw new ServletException();
        }
        try  {
            searchitemlocal = searchitemhome.create();
      } catch (CreateException ne) {
            System.out.println("Create Exception in search Servlet");
            throw new ServletException();
      } catch (RemoteException ne) {
            System.out.println("Create Exception in search Servlet");
            throw new ServletException();
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

        String item_desc = request.getParameter("item_desc");

        System.out.println("ITEM DESC:" + item_desc);
        System.out.println("HERE 0000");
        String str=searchitemlocal.SearchItemsByDescription(item_desc);
        System.out.println("HERE 1111");

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
                out.println("<input type=\"submit\" name=\"SEARCH\" value=\"Search Items\" /></label></form>");
                //out.println("<h3>This is search Servlet</h3>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
         } else if (action.equals("showAll")) {

                String str=searchitemlocal.SearchItemsAll();

                response.setContentType("text/html;charset=UTF-8");

                try {
                    //TODO output your page here
                    out.println("<html>");
                    ServiceProvider.print_header(out);
                    out.println("<body>");
                    out.println("<h3>All items:</h3><br>");
                    out.println(str);
                    out.println("</body>");
                    out.println("</html>");
                } finally {
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
