/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoryDAO;
import dao.ExamDAO;
import dao.QuestionDAO;
import dao.UserDAO;
import dto.CategoryDTO;
import dto.ExamDTO;
import dto.QuestionDTO;
import dto.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
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
 * @author huudu
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String url = "index.jsp";

        try {
            if (action == null) {
                url = "index.jsp";
            } else if (action.equals("login")) {
                url = processLogin(request);
            } else if (action.equals("logout")) {
                url = processLogout(request);
            } else if (action.equals("viewCategories")) {
                url = processViewCategories(request);
            } else if (action.equals("viewExams")) {
                url = processViewExams(request);
            } else if (action.equals("createExam")) {
                url = processCreateExam(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    private String processLogin(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDTO user = UserDAO.checkLogin(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("USER", user);
            return "home.jsp";
        } else {
            request.setAttribute("ERROR", "Invalid username or password");
            return "login.jsp";
        }
    }

    private String processLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Hủy session
        }
        return "login.jsp"; // Chuyển hướng về trang login
    }

    private String processViewCategories(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        List<CategoryDTO> categories = CategoryDAO.getAllCategories();
        request.setAttribute("CATEGORIES", categories);
        return "viewCategories.jsp";
    }

    private String processViewExams(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String categoryName = request.getParameter("category"); // Lấy category name từ request

        List<CategoryDTO> categoryList = CategoryDAO.getAllCategories(); // Lấy danh sách categories
        List<ExamDTO> examList = null;

        if (categoryName != null && !categoryName.isEmpty()) {
            examList = new ExamDAO().getExamsByCategory(categoryName); // Gọi DAO để lấy danh sách exams theo name
        }

        request.setAttribute("CATEGORY_LIST", categoryList);
        request.setAttribute("EXAM_LIST", examList);
        request.setAttribute("SELECTED_CATEGORY", categoryName); // Giữ lại category đã chọn

        return "viewExams.jsp";
    }

    private String processCreateExam(HttpServletRequest request) {
        try {
            List<CategoryDTO> categoryList = CategoryDAO.getAllCategories2(); // Dùng getAllCategories2
            request.setAttribute("CATEGORY_LIST", categoryList);
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user == null) {
                return "login.jsp";
            }

            String examTitle = request.getParameter("examTitle");
            String subject = request.getParameter("subject");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int totalMarks = Integer.parseInt(request.getParameter("totalMarks"));
            int duration = Integer.parseInt(request.getParameter("duration"));
            String createdBy = user.getUsername(); // Dùng getUsername()

            ExamDTO exam = new ExamDTO(0, examTitle, subject, categoryId, totalMarks, duration, createdBy);
            ExamDAO examDAO = new ExamDAO();
            boolean result = examDAO.createExam(exam);

            if (result) {
                request.setAttribute("MESSAGE", "Đã tạo exam thành công!"); // Thông báo thành công
            } else {
                request.setAttribute("ERROR_MESSAGE", "Failed to create exam."); // Thông báo lỗi
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR_MESSAGE", "Error: " + e.getMessage());
        }
        return "createExam.jsp";

    }

    private String processUpdateExam(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        int examId = Integer.parseInt(request.getParameter("examId"));
        String title = request.getParameter("examTitle");
        String subject = request.getParameter("subject");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int totalMarks = Integer.parseInt(request.getParameter("totalMarks"));
        int duration = Integer.parseInt(request.getParameter("duration"));

        ExamDTO exam = new ExamDTO(examId, title, subject, categoryId, totalMarks, duration, "");
        ExamDAO.updateExam(exam);
        return "MainController?action=listExams";
    }

    private String processDeleteExam(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        int examId = Integer.parseInt(request.getParameter("examId"));
        ExamDAO.deleteExam(examId);
        return "MainController?action=listExams";
    }

    private String processListQuestions(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        int examId = Integer.parseInt(request.getParameter("examId"));
        List<QuestionDTO> questions = QuestionDAO.getQuestionsByExamId(examId);
        request.setAttribute("QUESTIONS", questions);
        return "questionList.jsp";
    }

    private String processCreateQuestion(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        int examId = Integer.parseInt(request.getParameter("examId"));
        String questionText = request.getParameter("questionText");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        String correctOption = request.getParameter("correctOption");
        String createdBy = request.getParameter("createdBy");

        QuestionDTO question = new QuestionDTO(0, examId, questionText, optionA, optionB, optionC, optionD, correctOption, createdBy);
        QuestionDAO.createQuestion(question);
        return "MainController?action=listQuestions&examId=" + examId;
    }

    private String processUpdateQuestion(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String questionText = request.getParameter("questionText");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        String correctOption = request.getParameter("correctOption");

        QuestionDTO question = new QuestionDTO(questionId, 0, questionText, optionA, optionB, optionC, optionD, correctOption, "");
        QuestionDAO.updateQuestion(question);
        return "MainController?action=listQuestions";
    }

    private String processDeleteQuestion(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        QuestionDAO.deleteQuestion(questionId);
        return "MainController?action=listQuestions";
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
