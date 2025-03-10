/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author huuduy
 */

public class UserDTO {
    private String username;  // Username đóng vai trò là userID
    private String name;      // Tên đầy đủ của user
    private String password;
    private String role;      // Founder hoặc Member

    public UserDTO(String username, String name, String password, String role) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {  //Đổi từ getFullName() thành getName()
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
