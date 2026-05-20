package com.indivaragroup.demo.character;

public class TipeDataCharacter {
    public void charDemo() {
        char huruf = 'A';
        char angka = '7';
        char simbol = '#';
        char spasi = ' ';

        System.out.println("Huruf : " + huruf);
        System.out.println("Angka : " + angka);
        System.out.println("Simbol : " + simbol);
        System.out.println("Spasi : [" + spasi + "]");

        // Escape sequence
        char newLine = '\n';
        char tab = '\t';
        char quote = '\'';

        System.out.println("Escape sequence:");
        System.out.println("Baris 1" + newLine + "Baris 2");
        System.out.println("Kolom1" + tab + "Kolom2");
        System.out.println("Tanda kutip: " + quote);
    }
}
