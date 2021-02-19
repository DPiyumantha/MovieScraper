package com.dimalka.projectui.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Project {

    private int projectId;
    private String projectName;
    String status;
    String projectClient;
    Date deadLine;

    public Project() {
    }

    public Project(String projectName, String status, String projectClient, Date deadLine) {
        this.projectName = projectName;
        this.status = status;
        this.projectClient = projectClient;
        this.deadLine = deadLine;
    }

    public Project(String projectName, String status, String deadLine) {
        this.projectName = projectName;
        this.status = status;
        try {
            this.deadLine = new SimpleDateFormat("yyyy-MM-dd").parse(deadLine);
        } catch (ParseException e) {
            this.deadLine = null;
        }
    }


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getProjectClient() {
        return projectClient;
    }

    public void setProjectClient(String projectClient) {
        this.projectClient = projectClient;
    }

    @Override
    public String toString() {
        return "Project [projectId=" + projectId + ", projectName=" + projectName + ", status=" + status
                + ", projectClient=" + projectClient + ", deadLine=" + deadLine + "]";
    }


}
