package com.wiley.cfireader.books;

public class PsychologyInAction {
    public String getActualSectionName(String sectionName) {
        System.out.println();
        System.out.println();
        System.out.println("checking in switch:"+sectionName+":");
        String tmpSection[]=sectionName.split(" ",2);
        return "//h2[text()=' "+tmpSection[1]+"']/b[text()='"+tmpSection[0]+"']/..___"+sectionName;

    }
}
