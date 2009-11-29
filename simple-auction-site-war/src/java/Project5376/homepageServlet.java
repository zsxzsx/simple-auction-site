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
    throws ServletException, IOException {
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
// tcc
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("userID");

        try {
        
            if(username == null){
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Welcome to gBay!</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Invalid userID.  Internal Error, contact administrator.</h1>");
                out.println("</body>");
                out.println("</html>");
            }else {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Welcome to gBay!</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h3>You are now logged in.</h3><br><br><h1>Welcome to gBay " + username  +"!!</h1>");
                out.println("<p><a href=\"" + response.encodeURL("editProfile") + "\"> Edit Profile</a></p>");
                out.println("</body>");
                out.println("</html>");
            }
        } finally {
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
