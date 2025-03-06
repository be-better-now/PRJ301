/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import dao.UserDAO;
import dao.ProjectDAO;
import dto.UserDTO;
import dto.ProjectDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author huuduy
 */


@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String url = "login.jsp";
        

        try {           
            UserDAO userDAO = new UserDAO();
            ProjectDAO projectDAO = new ProjectDAO();
            String action = request.getParameter("action");
            if (action == null) {
                url = "login.jsp";
            } else if(action.equals("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                UserDTO user = userDAO.login(username, password);

                if (user != null) {
                    session.setAttribute("USER", user);
                    url = "dashboard.jsp";
                } else {
                    request.setAttribute("ERROR", "Invalid username or password");
                }
            } else if (action.equals("logout")) {
                session.invalidate();
                url = "login.jsp";
            } else if (session != null && session.getAttribute("USER") != null && action.equals("viewProjects")) {
                List<ProjectDTO> projects = projectDAO.getAllProjects();
                session.setAttribute("PROJECTS", projects);
                url = "dashboard.jsp";
            } else if (action.equals("createProject")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String desc = request.getParameter("description");
                String status = request.getParameter("status");
                String launchDate = request.getParameter("launchDate");

                projectDAO.createProject(0, name, desc, status, launchDate);
                request.setAttribute("MESSAGE", "Project created successfully!");
                url = "dashboard.jsp";
            } else if (action.equals("updateStatus")) {
                int projectId = Integer.parseInt(request.getParameter("projectId"));
                String newStatus = request.getParameter("status");

                projectDAO.updateProjectStatus(projectId, newStatus);
                request.setAttribute("MESSAGE", "Project status updated!");
                url = "dashboard.jsp";
            } else if (action.equals("searchProject")) {
                String keyword = request.getParameter("keyword");
                List<ProjectDTO> searchResults = projectDAO.searchProjectsByName(keyword);
                request.setAttribute("PROJECTS", searchResults);
                url = "dashboard.jsp";
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        }

            response.sendRedirect(url);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
