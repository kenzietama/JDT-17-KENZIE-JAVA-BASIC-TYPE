package com.indivaragroup.activity.dto;

import java.util.Date;

public class TaskDto {
    private String taskId;
    private String project;
    private String title;
    private String description;
    private String assignee;
    private String priority; // Low, Medium, High, Critical
    private String status; // Open, In Progress, Ready Review, Done, Cancelled
    private Date startDate;
    private Date dueDate;
    private int estimatedHour;
    private int actualHour;
    private int weight;
    private String reviewer;
    private String createdBy;

    // Atribut tambahan untuk menghitung progress subtask
    private int totalSubtasks;
    private int completedSubtasks;

    // --- GETTER & SETTER ---
    // Karena kita tidak menggunakan Lombok, semua harus ditulis manual

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public String getProject() { return project; }
    public void setProject(String project) { this.project = project; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAssignee() { return assignee; }
    public void setAssignee(String assignee) { this.assignee = assignee; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public int getEstimatedHour() { return estimatedHour; }
    public void setEstimatedHour(int estimatedHour) { this.estimatedHour = estimatedHour; }

    public int getActualHour() { return actualHour; }
    public void setActualHour(int actualHour) { this.actualHour = actualHour; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public String getReviewer() { return reviewer; }
    public void setReviewer(String reviewer) { this.reviewer = reviewer; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public int getTotalSubtasks() { return totalSubtasks; }
    public void setTotalSubtasks(int totalSubtasks) { this.totalSubtasks = totalSubtasks; }

    public int getCompletedSubtasks() { return completedSubtasks; }
    public void setCompletedSubtasks(int completedSubtasks) { this.completedSubtasks = completedSubtasks; }
}