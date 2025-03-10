/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.UserDAO;
import dto.UserDTO;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huuduy
 */
public class AuthUtils {

    private static final String FOUNDER_ROLE = "FOUNDER";
    private static final String MEMBER_ROLE = "MEMBER";

    public static UserDTO getUser(String username) {
        UserDAO userDAO = new UserDAO();
        return userDAO.readById(username);
    }

    public static boolean isValidLogin(String username, String password) { // Đổi
    UserDTO user = getUser(username);
    return user != null && user.getPassword().equals(password);
    }

    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    public static UserDTO getUser(HttpSession session) {
        if (!isLoggedIn(session)) {
            return null;
        }
        return (UserDTO) session.getAttribute("user");
    }

    public static boolean isFounder(HttpSession session) {
        if (!isLoggedIn(session)) {
            return false;
        }
        UserDTO user = getUser(session);
        return user.getRole().equals(FOUNDER_ROLE);
    }
}

