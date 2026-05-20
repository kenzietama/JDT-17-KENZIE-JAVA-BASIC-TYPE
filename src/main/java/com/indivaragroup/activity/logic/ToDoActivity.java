package com.indivaragroup.activity.logic;

import com.indivaragroup.activity.dto.TaskDto;
import java.util.ArrayList;
import java.util.Date;

public class ToDoActivity {

    // ArrayList sebagai in-memory database
    private ArrayList<TaskDto> taskList = new ArrayList<>();

    public ToDoActivity() {
        initDummyData();
    }

    public ArrayList<TaskDto> getTaskList() {
        return taskList;
    }

    public void addTask(TaskDto task) {
        taskList.add(task);
    }

    // METHOD: Progress Berdasarkan Status
    public double getProgressStatus(TaskDto task) {
        String status = task.getStatus();
        if (status.equals("Open")) return 0.0; // Open = 0% [cite: 14]
        else if (status.equals("In Progress")) return 50.0; // In Progress = 50% [cite: 15]
        else if (status.equals("Ready Review")) return 80.0; // Ready Review = 80% [cite: 16]
        else if (status.equals("Done")) return 100.0; // Done = 100% [cite: 17, 18]
        return 0.0; // Cancelled tidak dihitung (progress nol) [cite: 19]
    }

    // METHOD: Progress Berdasarkan Subtask
    public double getProgressSubtask(TaskDto task) {
        if (task.getTotalSubtasks() == 0) return 0.0;
        // CASTING tipe data ke (double) agar hasil pembagian desimal tidak hilang (contoh: 2/5 = 0.4)
        return ((double) task.getCompletedSubtasks() / task.getTotalSubtasks()) * 100.0; // Progress Subtask = Jumlah Subtask Done / Total Subtask x 100 [cite: 21, 22]
    }

    // METHOD: Progress Project (Total Bobot Task Selesai / Total Bobot Semua Task x 100)
    public double getProjectProgress() {
        double totalBobotProgress = 0.0;
        double totalBobotSemua = 0.0;

        for (int i = 0; i < taskList.size(); i++) {
            TaskDto task = taskList.get(i);

            // Aturan Bisnis: Task Cancelled tidak dihitung dalam progress [cite: 54]
            if (task.getStatus().equals("Cancelled")) {
                continue;
            }

            totalBobotSemua += task.getWeight();
            totalBobotProgress += task.getWeight() * (getProgressStatus(task) / 100.0);
        }

        if (totalBobotSemua == 0) return 0.0;
        return (totalBobotProgress / totalBobotSemua) * 100.0; // Progress Project [cite: 29]
    }

    // METHOD: Cek Workload Assignee
    public int getWorkloadAssignee(String assignee) {
        int totalWorkload = 0;
        for (int i = 0; i < taskList.size(); i++) {
            TaskDto task = taskList.get(i);
            if (task.getAssignee().equals(assignee)) {
                String status = task.getStatus();
                // Workload = Total Estimated Hour task aktif (Open, In Progress, Ready Review) [cite: 37, 39, 40]
                if (status.equals("Open") || status.equals("In Progress") || status.equals("Ready Review")) {
                    totalWorkload += task.getEstimatedHour();
                }
            }
        }
        return totalWorkload;
    }

    // METHOD: Cek Overdue (SLA)
    public boolean isOverdue(TaskDto task) {
        if (task.getDueDate() == null) return false;
        Date today = new Date();
        // Aturan Bisnis: Jika task melewati due date dan belum Done, status SLA menjadi Overdue [cite: 57]
        return today.after(task.getDueDate()) && !task.getStatus().equals("Done");
    }

    // METHOD: Variance Estimasi vs Aktual (Actual - Estimated) [cite: 42, 43]
    public int getVariance(TaskDto task) {
        return task.getActualHour() - task.getEstimatedHour();
    }

    // METHOD: Produktivitas Task (Estimated / Actual * 100) [cite: 49, 50]
    public double getProductivity(TaskDto task) {
        if (task.getActualHour() == 0) return 0.0;
        return ((double) task.getEstimatedHour() / task.getActualHour()) * 100.0;
    }

    // Mengubah task menjadi Done (otomatis progress subtask 100%)
    public void completeTask(TaskDto task) {
        task.setStatus("Done");
        // Aturan Bisnis: Jika task sudah Done, progress otomatis menjadi 100% [cite: 56]
        task.setCompletedSubtasks(task.getTotalSubtasks());
    }

    // Inisialisasi Dummy Data (5 Data untuk mewakili semua status)
    private void initDummyData() {
        Date now = new Date(); // Tanggal hari ini
        Date pastDate = new Date(now.getTime() - (1000 * 60 * 60 * 24 * 2)); // 2 hari lalu
        Date futureDate = new Date(now.getTime() + (1000 * 60 * 60 * 24 * 5)); // 5 hari ke depan

        // Task 1: Open
        TaskDto t1 = new TaskDto();
        t1.setTaskId("TASK-1");
        t1.setProject("Mobile App");
        t1.setTitle("Design UI/UX");
        t1.setAssignee("-"); t1.setReviewer("-");
        t1.setStatus("Open"); t1.setPriority("High");
        t1.setEstimatedHour(5); t1.setActualHour(0); t1.setWeight(0);
        t1.setTotalSubtasks(2); t1.setCompletedSubtasks(0);
        t1.setDueDate(futureDate); t1.setCreatedBy("Rina_PM");
        taskList.add(t1);

        // Task 2: In Progress
        TaskDto t2 = new TaskDto();
        t2.setTaskId("TASK-2");
        t2.setProject("Mobile App");
        t2.setTitle("Develop Backend API");
        t2.setAssignee("Budi_PIC"); t2.setReviewer("Andi_Reviewer");
        t2.setStatus("In Progress"); t2.setPriority("Critical");
        t2.setEstimatedHour(10); t2.setActualHour(4); t2.setWeight(40);
        t2.setTotalSubtasks(4); t2.setCompletedSubtasks(2);
        t2.setDueDate(futureDate); t2.setCreatedBy("Rina_PM");
        taskList.add(t2);

        // Task 3: Ready Review
        TaskDto t3 = new TaskDto();
        t3.setTaskId("TASK-3");
        t3.setProject("Web Portal");
        t3.setTitle("Integrasi Payment Gateway");
        t3.setAssignee("Budi_PIC"); t3.setReviewer("Andi_Reviewer");
        t3.setStatus("Ready Review"); t3.setPriority("High");
        t3.setEstimatedHour(8); t3.setActualHour(10); t3.setWeight(30);
        t3.setTotalSubtasks(3); t3.setCompletedSubtasks(3);
        t3.setDueDate(pastDate); t3.setCreatedBy("Rina_PM");
        taskList.add(t3); // SLA akan terhitung Overdue untuk dummy ini

        // Task 4: Done
        TaskDto t4 = new TaskDto();
        t4.setTaskId("TASK-4");
        t4.setProject("Web Portal");
        t4.setTitle("Setup Server & DB");
        t4.setAssignee("Budi_PIC"); t4.setReviewer("Andi_Reviewer");
        t4.setStatus("Done"); t4.setPriority("Medium");
        t4.setEstimatedHour(15); t4.setActualHour(12); t4.setWeight(20);
        t4.setTotalSubtasks(5); t4.setCompletedSubtasks(5);
        t4.setDueDate(futureDate); t4.setCreatedBy("Rina_PM");
        taskList.add(t4);

        // Task 5: Cancelled
        TaskDto t5 = new TaskDto();
        t5.setTaskId("TASK-5");
        t5.setProject("Mobile App");
        t5.setTitle("Fitur Push Notification (Hold)");
        t5.setAssignee("Budi_PIC"); t5.setReviewer("-");
        t5.setStatus("Cancelled"); t5.setPriority("Low");
        t5.setEstimatedHour(8); t5.setActualHour(0); t5.setWeight(10);
        t5.setTotalSubtasks(2); t5.setCompletedSubtasks(0);
        t5.setDueDate(futureDate); t5.setCreatedBy("Rina_PM");
        taskList.add(t5);
    }
}