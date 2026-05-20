package com.indivaragroup.type.data.primitive;

public class TypeDataNonPrimitive {
    public void getTypeDataNonPrimitive() {
        byte primitifByte = 10;
        short primitifShort = 20;
        int primitifInt = 30;
        long primitifLong = 60;
        float primitifFloat = 50.5f;
        double primitifDouble = 60.75;
        char primitifCharacter = 'A';
        boolean primitifBoolean = true;

        Byte wrapperByte = primitifByte;
        Short wrapperShort = primitifShort;
        Integer wrapperInt = primitifInt;
        Long wrapperLong = primitifLong;
        Float wrapperFloat = primitifFloat;
        Double wrapperDouble = primitifDouble;
        Character wrapperCharacter = primitifCharacter;
        Boolean wrapperBoolean = primitifBoolean;

        System.out.println("Primitif vs Wrapper Class");
        System.out.println("byte -> Byte : " + primitifByte + " / " + wrapperByte);
        System.out.println("short -> Short : " + primitifShort + " / " + wrapperShort);
        System.out.println("int -> Integer : " + primitifInt + " / " + wrapperInt);
        System.out.println("long -> Long : " + primitifLong + " / " + wrapperLong);
        System.out.println("float -> Float : " + primitifFloat + " / " + wrapperFloat);
        System.out.println("double -> Double : " + primitifDouble + " / " + wrapperDouble);
        System.out.println("char -> Character : " + primitifCharacter + " / " + wrapperCharacter);
        System.out.println("boolean -> Boolean : " + wrapperBoolean + " / " + wrapperBoolean);
    }
}
