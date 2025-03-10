/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjectDAO;
import dao.UserDAO;
import dto.ProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.AuthUtils;

/**
 *
 * @author huuduy
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String DASHBOARD_PAGE = "dashboard.jsp";

    private final UserDAO userDAO = new UserDAO();
    private final ProjectDAO projectDAO = new ProjectDAO();
    
    
    private String processLogin(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String url = LOGIN_PAGE;
    String username = request.getParameter("txtUsername");
    String password = request.getParameter("txtPassword");
    
        if (AuthUtils.isValidLogin(username, password)) {
            url = "dashboard.jsp";
            UserDTO user = AuthUtils.getUser(username);
            request.getSession().setAttribute("user", user);
        } else {
            request.setAttribute("message", "Incorrect Username or Password");
            url = "login.jsp";
        }
    return url;
}

    private String processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        return LOGIN_PAGE;
    }

    private String processViewProjects(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {
            List<ProjectDTO> projects = projectDAO.readAll();
            request.setAttribute("projects", projects);
            return DASHBOARD_PAGE;
        }
        return LOGIN_PAGE;
    }

    protected String createProject(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    String name = request.getParameter("name");
    String description = request.getParameter("description");
    String status = request.getParameter("status");
    String launchDateStr = request.getParameter("launchDate");

    UserDTO user = (UserDTO) request.getSession().getAttribute("user");

    if (user == null || !"Founder".equals(user.getRole())) {
        return "You do not have permission to create a project.";
    }

    try {
        Date launchDate = Date.valueOf(launchDateStr);
        ProjectDTO project = new ProjectDTO(0, name, description, status, launchDate, user.getUsername());

        ProjectDAO projectDAO = new ProjectDAO();
        boolean success = projectDAO.create(project);

        if (success) {
            response.sendRedirect("MainController?action=dashboard");
            return "Project created successfully!";
        } else {
            return "Failed to create project.";
        }
    } catch (IllegalArgumentException e) {
        return "Invalid date format.";
    }
}
    
    private String processSearchProject(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String url = "dashboard.jsp"; // Mặc định về dashboard
        HttpSession session = request.getSession();
        UserDTO user = AuthUtils.getUser(session);

        if (user == null || !user.getRole().equals("Founder")) {
            request.setAttribute("message", "You don't have permission to access this content.");
            return url;
        }

        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm == null) searchTerm = "";

        List<ProjectDTO> projects = projectDAO.searchByName(searchTerm);
        request.setAttribute("projects", projects);
        request.setAttribute("searchTerm", searchTerm);
        return url;
}

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action != null) {
                switch (action) {
                    case "login":
                        url = processLogin(request, response);
                        break;
                    case "logout":
                        url = processLogout(request, response);
                        break;
                    case "viewProjects":
                        url = processViewProjects(request, response);
                        break;
                    case "createProject":
                        url = createProject(request, response);
                        break;
                    case "searchProject":
                        url = processSearchProject(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            log("Error in MainController: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
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
