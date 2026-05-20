package com.indivaragroup.student.gradingsystem;

public class StudentGradingSystem {
    // CHALLENGE 2
    // 1. SAYA PUNYA ANAK MURID ADA 17 ORANG
    // 2. DAN SAYA HARUS MEMBERIKAN NILAI UNTUK MENILAI KEMAMPUAN MEREKA
    // 3. HASIL NILAI TERSEBUT HARUS SAYA OLAH BERDASARKAN NILAI APABILA
    // * NILAI 100 = DIA JAGO BANGET
    // * NILAI 90 = DIA BELUM JAGO BANGET TAPI JAGO AJA
    // * NILAI 70 = DIA B AJA ( BIASA AJA)
    // * NILAI DIBAWAH 70 PERLU BELAJAR LAGI
    // 4. KALAU SUDAH KELUAR PASTI NYA TOLONG DI PRINT NAMANYA
    // BERARTI NAMANYA ITU BISA DINAMIS ATAU BISA DIINPUT

    String[] student = {"Ucok", "Budi", "Andi", "Rudi", "Agus", "Joko", "Rendy", "Bagas",
            "Bambang", "Damar", "Setyo", "Adit", "Asep", "Ahmad", "Deni", "Yanto", "Maman"};
    int[] grade = {88, 100, 77, 69, 91, 95, 60, 75,
            82, 83, 90, 70, 60, 82, 80, 74, 100};

    public void classify() {
        String description;
        for (int i = 0; i < student.length; i++) {
            if (grade[i] == 100) {
                description = "DIA JAGO BANGET";
            } else if (grade[i] < 100 && grade[i] >= 90) {
                description = "DIA BELUM JAGO BANGET TAPI JAGO AJA";
            } else if (grade[i] < 90 && grade[i] >= 70) {
                description = "DIA B AJA (BIASA AJA)";
            } else {
                description = "DIA PERLU BELAJAR LAGI";
            }
            System.out.println(student[i] + ", nilai dia: " + grade[i] + ", " + description);
        }
    }
}
