package com.wiley.cfireader.pojo;

import java.util.ArrayList;
import java.util.List;

public class ExcelBook {
    private String bookName;
    private String bookId;
    private String urlPrefix;
    private boolean isSectionNum;
    private List<ExcelSheet> excelSheetDataList = new ArrayList<>();

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public List<ExcelSheet> getExcelSheetDataList() {
        return excelSheetDataList;
    }

    public void setExcelSheetDataList(List<ExcelSheet> excelSheetDataList) {
        this.excelSheetDataList = excelSheetDataList;
    }

    public boolean isSectionNum() {
        return isSectionNum;
    }

    public void setSectionNum(boolean sectionNum) {
        isSectionNum = sectionNum;
    }
}
