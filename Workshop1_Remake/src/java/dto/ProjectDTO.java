/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;

/**
 *
 * @author huuduy
 */

import java.io.Serializable;
import java.sql.Date;

public class ProjectDTO implements Serializable {
    private int projectID;
    private String projectName;
    private String description;
    private String status;
    private Date launchDate;
    private String founderID;

    public ProjectDTO() {}

    public ProjectDTO(int projectID, String projectName, String description, String status, Date launchDate, String founderID) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.launchDate = launchDate;
        this.founderID = founderID;
    }

    public ProjectDTO(int projectID, String projectName, String description, String status, Date launchDate) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.launchDate = launchDate;
    }
    
    

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public String getFounderID() {
        return founderID;
    }

    public void setFounderID(String founderID) {
        this.founderID = founderID;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "projectID=" + projectID +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", launchDate=" + launchDate +
                ", founderID='" + founderID + '\'' +
                '}';
    }
}


