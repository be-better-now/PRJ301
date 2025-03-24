/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoryDAO;
import dao.ExamDAO;
import dao.ExamResultDAO;
import dao.QuestionDAO;
import dao.UserDAO;
import dto.CategoryDTO;
import dto.ExamDTO;
import dto.ExamResultDTO;
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

    private static final String LOGIN = "login.jsp";
    private static final String TAKE_EXAM = "takeExam.jsp";
    private static final String EXAM_RESULT = "examResult.jsp";
    private static final String ADD_QUESTION = "addQuestion.jsp";

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
            } else if (action.equals("takeExam")) {
                url = processTakeExam(request);
            } else if (action.equals("startExam")) {
                url = processStartExam(request);
            } else if (action.equals("submitExam")) {
                url = processSubmitExam(request);
            } else if (action.equals("viewResults")) {
                url = processViewResults(request);
            } else if (action.equals("addQuestion")) {
                url = processAddQuestion(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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

    private String processTakeExam(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user == null || !"Student".equals(user.getRole())) {
                return LOGIN;
            }
            ExamDAO examDAO = new ExamDAO();
            List<ExamDTO> examList = examDAO.getAllExams();
            request.setAttribute("EXAM_LIST", examList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TAKE_EXAM;
    }

    private String processStartExam(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user == null || !"Student".equals(user.getRole())) {
                return LOGIN;
            }
            String examId = request.getParameter("examId");
            ExamDAO examDAO = new ExamDAO();
            QuestionDAO questionDAO = new QuestionDAO();
            ExamDTO selectedExam = examDAO.getExamById(Integer.parseInt(examId));
            List<QuestionDTO> questionList = questionDAO.getQuestionsByExamId(Integer.parseInt(examId));
            request.setAttribute("SELECTED_EXAM", selectedExam);
            request.setAttribute("QUESTION_LIST", questionList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TAKE_EXAM;
    }

    private String processSubmitExam(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user == null || !"Student".equals(user.getRole())) {
                return LOGIN;
            }
            String examId = request.getParameter("examId");
            ExamDAO examDAO = new ExamDAO();
            QuestionDAO questionDAO = new QuestionDAO();
            List<QuestionDTO> questionList = questionDAO.getQuestionsByExamId(Integer.parseInt(examId));
            int totalMarks = examDAO.getExamById(Integer.parseInt(examId)).getTotalMarks();
            int score = calculateScore(request, questionList, totalMarks);
            ExamResultDAO resultDAO = new ExamResultDAO();
            resultDAO.saveExamResult(new ExamResultDTO(0, Integer.parseInt(examId), user.getUsername(), score));
            request.setAttribute("SCORE", score);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EXAM_RESULT;
    }

    private String processViewResults(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user == null || !"Student".equals(user.getRole())) {
                return LOGIN;
            }
            ExamResultDAO resultDAO = new ExamResultDAO();
            List<ExamResultDTO> resultList = resultDAO.getResultsByStudent(user.getUsername());
            request.setAttribute("RESULT_LIST", resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EXAM_RESULT;
    }

    private int calculateScore(HttpServletRequest request, List<QuestionDTO> questionList, int totalMarks) {
        int correctAnswers = 0;
        for (QuestionDTO question : questionList) {
            String userAnswer = request.getParameter("answer_" + question.getQuestionId());
            if (userAnswer != null && userAnswer.equals(question.getCorrectOption())) {
                correctAnswers++;
            }
        }
        return (int) ((correctAnswers / (double) questionList.size()) * totalMarks);
    }
    
    private String processAddQuestion(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user == null || !"Instructor".equals(user.getRole())) {
                return LOGIN;
            }

            ExamDAO examDAO = new ExamDAO();
            List<ExamDTO> examList = examDAO.getAllExams();
            request.setAttribute("EXAM_LIST", examList);

            String examIdStr = request.getParameter("examId");
            String questionText = request.getParameter("questionText");
            String optionA = request.getParameter("optionA");
            String optionB = request.getParameter("optionB");
            String optionC = request.getParameter("optionC");
            String optionD = request.getParameter("optionD");
            String correctOption = request.getParameter("correctOption");

            if (examIdStr != null && questionText != null) {
                int examId = Integer.parseInt(examIdStr);
                QuestionDTO question = new QuestionDTO(0, examId, questionText, optionA, optionB, optionC, optionD, correctOption, user.getUsername());
                QuestionDAO questionDAO = new QuestionDAO();
                boolean result = questionDAO.addQuestion(question);

                if (result) {
                    request.setAttribute("MESSAGE", "Add question successfully!");
                } else {
                    request.setAttribute("ERROR_MESSAGE", "Failed to add question.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR_MESSAGE", "Error: " + e.getMessage());
        }
        return ADD_QUESTION;
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
