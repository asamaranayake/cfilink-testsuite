package com.wiley.cfireader.books;

public class FinancialAccounting {
    public String getActualSectionName(String sectionName) {
        System.out.println();
        System.out.println();
        System.out.println("checking in switch:"+sectionName+":");
        return "//h2/span[text()='"+sectionName+"']___"+sectionName;

    }
}
