package com.indivaragroup;

import com.indivaragroup.house.floor.logic.CheckCeramic;
import com.indivaragroup.calculation.registration.identity.CalculationRegistrationIdentity;
import com.indivaragroup.student.gradingsystem.StudentGradingSystem;
import com.indivaragroup.calculation.casting.KonversiTipeDataNumber;
import com.indivaragroup.type.data.primitive.TypeDataNonPrimitive;
import com.indivaragroup.type.data.array.TypeDataArray;
import com.indivaragroup.movie.logic.PlayLIstEveryYear;
import com.indivaragroup.activity.logic.ToDoActivityUI;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String hello = "Hello JDT17";
        System.out.println("INI STRING UNTUK HELLO - WORLD = " + hello);
        int helloInteger = 25;
        System.out.println("INI STRING UNTUK HELLO - INTEGER = " + helloInteger);
        double helloDouble = 25.5;
        System.out.println("INI STRING UNTUK HELLO - DOUBLE = " + helloDouble);
        boolean helloBoolean = true;
        System.out.println("INI STRING UNTUK HELLO - BOOLEAN = " + helloBoolean);
        char helloChar = 'A';
        System.out.println("INI STRING UNTUK HELLO - CHAR = " + helloChar);
        System.out.println();

        System.out.println("1. HOUSE FLOOR / KERAMIK");
        System.out.println("--------------------------------------------------------------");
        CheckCeramic checkCeramic = new CheckCeramic();
        checkCeramic.checkCeramic();
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        System.out.println("2. REGISTRATION PERSONAL ID");
        System.out.println("--------------------------------------------------------------");
        CalculationRegistrationIdentity calculationRegistrationIdentity = new CalculationRegistrationIdentity();
        calculationRegistrationIdentity.register();
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        System.out.println("3. SCORING STUDENT");
        System.out.println("--------------------------------------------------------------");
        StudentGradingSystem system = new StudentGradingSystem();
        system.classify();
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        System.out.println("4. NARROWING / CASTING DATA NUMBER");
        System.out.println("--------------------------------------------------------------");
        KonversiTipeDataNumber converter = new KonversiTipeDataNumber();
        converter.cast();
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        System.out.println("5. PRIMITIVE DATA");
        System.out.println("--------------------------------------------------------------");
        TypeDataNonPrimitive typeDataNonPrimitive = new TypeDataNonPrimitive();
        typeDataNonPrimitive.getTypeDataNonPrimitive();
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        System.out.println("6. TYPE DATA ARRAY");
        System.out.println("--------------------------------------------------------------");
        TypeDataArray typeDataArray = new TypeDataArray();
        typeDataArray.getTypeDataArray();
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        System.out.println("7. FAVORITE LIST VIDEO AV");
        System.out.println("--------------------------------------------------------------");
        PlayLIstEveryYear playLIstEveryYear = new PlayLIstEveryYear();
        playLIstEveryYear.getFavouritePlaylist();
        System.out.println("--------------------------------------------------------------");
        System.out.println();

        System.out.println("8. TO DO ACTIVITY");
        System.out.println("--------------------------------------------------------------");
        ToDoActivityUI toDoActivityUI = new ToDoActivityUI();
        toDoActivityUI.run();
        System.out.println("--------------------------------------------------------------");

    }
}