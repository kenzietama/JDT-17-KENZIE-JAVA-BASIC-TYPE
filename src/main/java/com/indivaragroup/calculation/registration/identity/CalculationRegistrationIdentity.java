package com.indivaragroup.calculation.registration.identity;

public class CalculationRegistrationIdentity {
    // 1. SAYA PUNYA UANG 10000
    // 2. KALAU SAYA PUNYA UANG 10000 BERARTI SAYA HARUS BUAT KTP
    // 3. KALAU SAYA HARUS BUAT KTP BERARTI SAYA HARUS BAYAR UANG SEBESAR 5000
    // 4. BUATKAN KONDISI IF ELSE NYA YANG TERJADI DARI POINT 1,2
    // 5. HASIL TOTAL PENGURANGANNYA
    // HINT GUNAKAN ARITMATIKA OPERATOR
    int uang = 10000;
    int biayaktp = 5000;

    public void register () {
        if (uang == 10000) {
            System.out.println("Uang saya, " + uang + " Saya harus buat ktp");
            uang = uang - biayaktp;
            System.out.println("saya bayar " + biayaktp + " untuk buat ktp, sisanya = " + uang);
        } else if (uang != 10000) {
            System.out.println("saya tidak harus buat ktp");
        }
    }
}
