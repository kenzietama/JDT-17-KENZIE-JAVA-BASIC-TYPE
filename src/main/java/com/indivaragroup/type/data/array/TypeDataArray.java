package com.indivaragroup.type.data.array;

public class TypeDataArray {
    public void getTypeDataArray() {
        int[][] data = {
                {1, 2, 3},
                {4, 5, 6},
        };

        //Akses elemen
        int nilai = data[1][2];
        System.out.println(nilai);

        //Deklarasi alternatif
        int[][] matrix = new int[2][3];

        matrix[0][0] = 10;
        matrix[0][1] = 20;
    }
}
