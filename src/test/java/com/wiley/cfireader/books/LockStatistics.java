package com.wiley.cfireader.books;

public class LockStatistics {
    public String getActualSectionName(String sectionName) {
        System.out.println();
        System.out.println();
        System.out.println("checking in switch:"+sectionName+":");
        if(sectionName.startsWith("UNIT")){
            String[] tmpSection =sectionName.split(" ",3);
            return "//h1/span[text()='"+tmpSection[0]+" "+tmpSection[1]+"']/../span[text()='"+tmpSection[2].toUpperCase()+"']___"+tmpSection[1].toUpperCase();
        } else {
            String[] tmpSection =sectionName.split(" ",2);
            return "//h2/span[text()='Section "+tmpSection[0]+"']/../span[text()='"+tmpSection[1].toUpperCase()+"']___"+tmpSection[1].toUpperCase();
        }

    }
}
