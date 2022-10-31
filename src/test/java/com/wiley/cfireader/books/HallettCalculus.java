package com.wiley.cfireader.books;

public class HallettCalculus {
    public String getActualSectionName(String sectionName) {
        System.out.println();
        System.out.println();
        System.out.println("checking in switch:"+sectionName+":");
         String[] tmpSection =sectionName.split(" ",2);
            return "//h1/span[text()='"+tmpSection[0]+"']/../span[text()='"+tmpSection[1].toUpperCase()+"']___"+tmpSection[1].toUpperCase();

    }
}
