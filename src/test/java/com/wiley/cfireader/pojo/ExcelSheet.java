package com.wiley.cfireader.pojo;

public class ExcelSheet {

    private String sectionPrefix;
    private String SectionName;
    private String cfiEntryPoint;

    public String getSectionPrefix() {
        return sectionPrefix;
    }

    public void setSectionPrefix(String sectionPrefix) {
        this.sectionPrefix = sectionPrefix;
    }

    public String getSectionName() {
        return SectionName;
    }

    public void setSectionName(String sectionName) {
        SectionName = sectionName;
    }

    public String getCfiEntryPoint() {
        return cfiEntryPoint;
    }

    public void setCfiEntryPoint(String cfiEntryPoint) {
        this.cfiEntryPoint = cfiEntryPoint;
    }
}
