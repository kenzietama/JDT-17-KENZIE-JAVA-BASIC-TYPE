package com.indivaragroup.house.floor.logic;

import com.indivaragroup.house.floor.dto.Ceramic;

public class CheckCeramic {
    // 1. TOLONG ISI NAMA KERAMIK = MARMER
    // 2. BUATKAN KONDISI APABILA DARI CLASS CERAMIC NAME CERAMIC VARIABLENYA ADALAH MARMER OUTPUTNYA TRUE
    public void checkCeramic() {
        Ceramic ceramic = new Ceramic();
        ceramic.setNameCeramic("MARMER");

        if(ceramic.getNameCeramic().equals("MARMER")) {
            System.out.println("ini betul marmer");
        } else {
            System.out.println("ini bukan marmer");
        }
    }
}
