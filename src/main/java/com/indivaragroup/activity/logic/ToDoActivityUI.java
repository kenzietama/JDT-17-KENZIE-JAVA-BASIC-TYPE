package com.indivaragroup.activity.logic;

import com.indivaragroup.activity.dto.TaskDto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ToDoActivityUI {
    private ToDoActivity logic = new ToDoActivity();
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("=========================================");
            System.out.println("   TO DO LIST & PROJECT TRACKER LOGIN    ");
            System.out.println("=========================================");
            System.out.println("Pilih Role Akun Dummy:");
            System.out.println("1. Project Manager (Rina_PM)");
            System.out.println("2. Tech Lead (Joko_TL)");
            System.out.println("3. PIC / Assignee (Budi_PIC)");
            System.out.println("4. Reviewer (Andi_Reviewer)");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("Masukkan pilihan (0-4): ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: showMenuPM("Rina_PM"); break;
                case 2: showMenuTL("Joko_TL"); break;
                case 3: showMenuPIC("Budi_PIC"); break;
                case 4: showMenuReviewer("Andi_Reviewer"); break;
                case 0:
                    isRunning = false;
                    System.out.println("Keluar dari aplikasi...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // === DISPLAY TABLE METHOD ===
    private void displayAllTasks() {
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-7s | %-12s | %-25s | %-10s | %-12s | %-10s | %-6s | %-6s | %-5s | %-8s | %-6s |\n",
                "Task ID", "Project", "Title", "Assignee", "Status", "Priority", "Est(H)", "Act(H)", "Wght", "Prog(St)", "SLA");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < logic.getTaskList().size(); i++) {
            TaskDto t = logic.getTaskList().get(i);

            // Kondisi SLA: Overdue atau Safe
            String sla = logic.isOverdue(t) ? "OVERDUE" : "Safe";

            System.out.printf("| %-7s | %-12s | %-25s | %-10s | %-12s | %-10s | %-6d | %-6d | %-5d | %.1f%%    | %-6s |\n",
                    t.getTaskId(), truncate(t.getProject(), 12), truncate(t.getTitle(), 25), truncate(t.getAssignee(), 10),
                    t.getStatus(), t.getPriority(), t.getEstimatedHour(), t.getActualHour(), t.getWeight(),
                    logic.getProgressStatus(t), sla);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    // Helper method untuk memotong text kepanjangan
    private String truncate(String text, int length) {
        if (text == null) return "-";
        if (text.length() <= length) return text;
        return text.substring(0, length - 2) + "..";
    }

    private TaskDto findTaskById(String taskId) {
        for (int i = 0; i < logic.getTaskList().size(); i++) {
            if (logic.getTaskList().get(i).getTaskId().equalsIgnoreCase(taskId)) {
                return logic.getTaskList().get(i);
            }
        }
        return null;
    }

    // === ROLE 1: PROJECT MANAGER ===
    private void showMenuPM(String loggedUser) {
        boolean session = true;
        while (session) {
            displayAllTasks();
            System.out.println("\n=== PM MENU (" + loggedUser + ") ===");
            System.out.println("1. Buat Task Baru");
            System.out.println("2. Lihat Report Performa Proyek");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            int menu = Integer.parseInt(scanner.nextLine());

            if (menu == 1) {
                TaskDto newTask = new TaskDto();
                // Generate otomatis ID
                newTask.setTaskId("TASK-" + (logic.getTaskList().size() + 1));
                System.out.print("Project: "); newTask.setProject(scanner.nextLine());
                System.out.print("Title: "); newTask.setTitle(scanner.nextLine());
                System.out.print("Description: "); newTask.setDescription(scanner.nextLine());

                System.out.print("Due Date (yyyy-MM-dd): ");
                try {
                    newTask.setDueDate(sdf.parse(scanner.nextLine()));
                } catch (Exception e) {
                    System.out.println("Format salah, set ke null.");
                }

                System.out.print("Estimated Hour: "); newTask.setEstimatedHour(Integer.parseInt(scanner.nextLine()));
                System.out.print("Total Subtasks (Angka): "); newTask.setTotalSubtasks(Integer.parseInt(scanner.nextLine()));

                // Set default field
                newTask.setCreatedBy(loggedUser);
                newTask.setStatus("Open");
                newTask.setAssignee("-");
                newTask.setReviewer("-");
                newTask.setPriority("Medium");

                logic.addTask(newTask);
                System.out.println("Task Baru Berhasil Dibuat: " + newTask.getTaskId());

            } else if (menu == 2) {
                System.out.println("\n=== REPORT PERFORMA PROYEK ===");
                System.out.printf("Progress Proyek Keseluruhan : %.2f%%\n", logic.getProjectProgress());
                System.out.println("Detail Per Task (Productivity & Variance):");
                for (int i = 0; i < logic.getTaskList().size(); i++) {
                    TaskDto t = logic.getTaskList().get(i);
                    if(!t.getStatus().equals("Open") && t.getActualHour() > 0) {
                        System.out.printf("- %s (%s) : Variance %d Jam | Productivity: %.1f%%\n",
                                t.getTaskId(), t.getTitle(), logic.getVariance(t), logic.getProductivity(t));
                    }
                }
                System.out.println("Tekan enter untuk kembali...");
                scanner.nextLine();
            } else if (menu == 0) {
                session = false;
            }
        }
    }

    // === ROLE 2: TECH LEAD ===
    private void showMenuTL(String loggedUser) {
        boolean session = true;
        while (session) {
            displayAllTasks();
            System.out.println("\n=== TECH LEAD MENU (" + loggedUser + ") ===");
            System.out.println("1. Assign Task & Set Detail (Weight, Priority, Assignee)");
            System.out.println("2. Lihat Workload Assignee");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            int menu = Integer.parseInt(scanner.nextLine());

            if (menu == 1) {
                System.out.print("Masukkan ID Task yang akan di-update: ");
                String taskId = scanner.nextLine();
                TaskDto t = findTaskById(taskId);

                if (t != null) {
                    System.out.print("Set Assignee (Saat ini: " + t.getAssignee() + "): ");
                    String assignee = scanner.nextLine();

                    // Business Rule Check: Limit Workload Maksimal [cite: 58]
                    int currentWorkload = logic.getWorkloadAssignee(assignee);
                    if (currentWorkload + t.getEstimatedHour() > 40) { // Limit asumsi 40 jam per minggu
                        System.out.println("GAGAL: Assignee melebihi kapasitas beban (Workload: " + currentWorkload + " + " + t.getEstimatedHour() + " > 40 Jam)");
                    } else {
                        t.setAssignee(assignee);
                        System.out.print("Set Reviewer: "); t.setReviewer(scanner.nextLine());
                        System.out.print("Set Weight (Bobot): "); t.setWeight(Integer.parseInt(scanner.nextLine()));
                        System.out.print("Set Priority (Low/Medium/High/Critical): "); t.setPriority(scanner.nextLine());
                        System.out.println("Update berhasil!");
                    }
                } else {
                    System.out.println("Task tidak ditemukan.");
                }
            } else if (menu == 2) {
                System.out.print("Masukkan Nama Assignee (misal: Budi_PIC): ");
                String assignee = scanner.nextLine();
                int workload = logic.getWorkloadAssignee(assignee);
                System.out.println("Total Workload " + assignee + " untuk task aktif: " + workload + " jam.");
                System.out.println("Tekan enter untuk kembali...");
                scanner.nextLine();
            } else if (menu == 0) {
                session = false;
            }
        }
    }

    // === ROLE 3: PIC / ASSIGNEE ===
    private void showMenuPIC(String loggedUser) {
        boolean session = true;
        while (session) {
            displayAllTasks();
            System.out.println("\n=== PIC MENU (" + loggedUser + ") ===");
            System.out.println("1. Update Status & Subtask");
            System.out.println("2. Input Actual Hour");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            int menu = Integer.parseInt(scanner.nextLine());

            if (menu == 1) {
                System.out.print("Masukkan ID Task Anda: ");
                String taskId = scanner.nextLine();
                TaskDto t = findTaskById(taskId);

                if (t != null && t.getAssignee().equals(loggedUser)) {
                    // Update Status
                    System.out.println("Status saat ini: " + t.getStatus());
                    System.out.print("Ubah ke (In Progress / Ready Review): ");
                    String newStatus = scanner.nextLine();

                    // Aturan Bisnis: Hanya boleh ubah status dan jika Done harus oleh Reviewer [cite: 52]
                    if(newStatus.equalsIgnoreCase("Done")) {
                        System.out.println("GAGAL: Hanya Reviewer yang bisa set status ke Done!");
                    } else {
                        t.setStatus(newStatus);
                    }

                    // Update Subtask
                    System.out.println("Progress Subtask Saat ini: " + logic.getProgressSubtask(t) + "%");
                    System.out.print("Jumlah Subtask yang Selesai dari total " + t.getTotalSubtasks() + ": ");
                    int completed = Integer.parseInt(scanner.nextLine());
                    if (completed <= t.getTotalSubtasks()) {
                        t.setCompletedSubtasks(completed);
                        System.out.println("Update berhasil!");
                    } else {
                        System.out.println("GAGAL: Subtask selesai tidak boleh melebihi total subtask.");
                    }
                } else {
                    System.out.println("Task tidak ditemukan atau ini bukan task milik Anda.");
                }
            } else if (menu == 2) {
                System.out.print("Masukkan ID Task: ");
                String taskId = scanner.nextLine();
                TaskDto t = findTaskById(taskId);
                if (t != null && t.getAssignee().equals(loggedUser)) {
                    System.out.print("Masukkan Total Actual Hour: ");
                    t.setActualHour(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Berhasil input Actual Hour.");
                }
            } else if (menu == 0) {
                session = false;
            }
        }
    }

    // === ROLE 4: REVIEWER ===
    private void showMenuReviewer(String loggedUser) {
        boolean session = true;
        while (session) {
            displayAllTasks();
            System.out.println("\n=== REVIEWER MENU (" + loggedUser + ") ===");
            System.out.println("1. Review Task (Approve/Reject)");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            int menu = Integer.parseInt(scanner.nextLine());

            if (menu == 1) {
                System.out.print("Masukkan ID Task untuk direview: ");
                String taskId = scanner.nextLine();
                TaskDto t = findTaskById(taskId);

                if (t != null && t.getReviewer().equals(loggedUser)) {
                    // Aturan bisnis: Task Ready Review belum boleh dihitung selesai sebelum disetujui [cite: 53, 59]
                    if (t.getStatus().equals("Ready Review")) {
                        System.out.print("Approve task ini menjadi Done? (Y/N): ");
                        String approve = scanner.nextLine();
                        if (approve.equalsIgnoreCase("Y")) {
                            logic.completeTask(t);
                            System.out.println("Task di-Approve. Status diubah ke Done.");
                        } else {
                            t.setStatus("In Progress");
                            System.out.println("Task ditolak (Reject). Status kembali ke In Progress.");
                        }
                    } else {
                        System.out.println("Task ini belum 'Ready Review'.");
                    }
                } else {
                    System.out.println("Task tidak ditemukan atau Anda bukan Reviewer task ini.");
                }
            } else if (menu == 0) {
                session = false;
            }
        }
    }
}