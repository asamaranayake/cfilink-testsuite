package com.wiley.cfireader.books;

public class CutnellPhysics {
    public String getActualSectionName(String sectionName) {
        System.out.println();
        System.out.println();
        System.out.println("checking in switch:"+sectionName+":");
        String[] tmpSection =sectionName.split(" ",2);
        return "//h2[text()=' "+tmpSection[1]+"']/span/sup[text()='"+tmpSection[0]+"']/../..___ "+sectionName;

    }
}
