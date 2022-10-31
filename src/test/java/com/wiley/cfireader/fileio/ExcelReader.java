package com.wiley.cfireader.fileio;

//public class ExcelReader {
//
//}

import com.wiley.cfireader.pojo.ExcelBook;
import com.wiley.cfireader.pojo.ExcelSheet;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelReader{
    int bookNameIndex=0;
    int cellPrefixIndex=0;
    int cellCfiSuffixIndex=0;
    int cellSectionNameIndex=0;

    public ExcelBook readeExcelSheet(String excelFilePath, String appUrl){
        try {
            //URL url = Thread.currentThread().getContextClassLoader().getResource(excelFilePath);
            File file = new File(excelFilePath);   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            //creating Workbook instance that refers to .xlsx file
            //create workbook instance
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            //create a sheet object to retrieve the sheet
            XSSFSheet sheet = wb.getSheetAt(0);

            //to evaluate cell type
            FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
            ExcelBook excelBook = new ExcelBook();

            for(Row row : sheet)
            {
                ExcelSheet excelSheet = new ExcelSheet();
                for(Cell cell : row)
                {
                    switch(formulaEvaluator.evaluateInCell(cell).getCellType())
                    {
                        case NUMERIC:
                            System.out.println("=============================="+cell.getColumnIndex());
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case STRING:
                            System.out.println("==============================:"+cell.getStringCellValue()+":=========");
                            addBookData(excelBook, cell, appUrl);
                            addSectionPrefix(excelSheet, cell);
                            addCfiLinkSuffix(excelSheet, cell);
                            addSectionName(excelSheet, cell);
                            if (excelSheet.getSectionPrefix() != null && excelSheet.getCfiEntryPoint() != null && excelSheet.getSectionName() != null) {
                               excelBook.getExcelSheetDataList().add(excelSheet);
                               excelSheet = new ExcelSheet();
                            }
                            System.out.println("---->"+excelBook.getExcelSheetDataList().size());
                            break;
                        default:
                            break;

                    }
                }
            }
           return excelBook;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private void assignBook(String bookName, ExcelBook excelBook, String appUrl){
        if (bookName.equals("Nijman, The World Today, 8e")){
            excelBook.setBookName("Nijman, The World Today, 8e");
            excelBook.setBookId("9781119577614");
        } else  if (bookName.equals("Nijman, Regions, 18e")){
            excelBook.setBookName("Nijman, Regions, 18e");
            excelBook.setBookId("9781119607366");
        } else  if (bookName.equals("Fouberg, Understanding World Regional Geography, 2e")){
            excelBook.setBookName("Fouberg, Understanding World Regional Geography, 2e");
            excelBook.setBookId("9781119393900");
        } else  if (bookName.equals("Tortora, Principles of Anatomy and Physiology, 16e")){
            excelBook.setBookName("Tortora, Principles of Anatomy and Physiology, 16e");
            excelBook.setBookId("9781119662686");
            excelBook.setSectionNum(true);
        }else  if (bookName.equals("Young, College Algebra & Trigonometry, 4e")){
            excelBook.setBookName("Young, College Algebra & Trigonometry, 4e");
            excelBook.setBookId("9781119320869");
            excelBook.setSectionNum(true);
        }else  if (bookName.equals("Lock, Statistics, 3e")){
            excelBook.setBookName("Lock, Statistics, 3e");
            excelBook.setBookId("9781119674160");
            excelBook.setSectionNum(true);
        } else if (bookName.equals("Hughes-Hallett, Calculus, 8e")){
            excelBook.setBookName("Hughes-Hallett, Calculus, 8e");
            excelBook.setBookId("9781119694298");
            excelBook.setSectionNum(true);
        } else if (bookName.equals("Cutnell, Physics, 11e")){
            excelBook.setBookName("Cutnell, Physics, 11e");
            excelBook.setBookId("9781119326342");
            excelBook.setSectionNum(true);
        } else if (bookName.equals("Wessner, Microbiology, 3e")){
            excelBook.setBookName("Wessner, Microbiology, 3e");
            excelBook.setBookId("9781119592402");
            excelBook.setSectionNum(true);
        } else if (bookName.equals("Weygandt, Financial Accounting, 11e")){
            excelBook.setBookName("Weygandt, Financial Accounting, 11e");
            excelBook.setBookId("9781119594611");
        } else if (bookName.equals("Huffman, Psychology in Action, 12e")){
            excelBook.setBookName("Huffman, Psychology in Action, 12e");
            excelBook.setBookId("9781119394839");
            excelBook.setSectionNum(true);
        } else if (bookName.equals("Sanderson, Real World Psychology, 3e")){
            excelBook.setBookName("Sanderson, Real World Psychology, 3e");
            excelBook.setBookId("9781119577737");
            excelBook.setSectionNum(true);
        }else if (bookName.equals("Olmsted, Chemistry, 4e Canadian")){
            excelBook.setBookName("Olmsted, Chemistry, 4e Canadian");
            excelBook.setBookId("9781119709411");
            excelBook.setSectionNum(true);
        }
        excelBook.setUrlPrefix(appUrl+"/books/"+excelBook.getBookId());
    }

    private void addBookData(ExcelBook excelBook, Cell cell, String appUrl){
        if (StringUtils.isEmpty(excelBook.getBookName()) && cell.getStringCellValue().equals("Product")){
            bookNameIndex=cell.getColumnIndex();
        }
        if (StringUtils.isEmpty(excelBook.getBookName()) && !cell.getStringCellValue().equals("NextGen") && !cell.getStringCellValue().equals("Product") && bookNameIndex==cell.getColumnIndex()){
            assignBook(cell.getStringCellValue(), excelBook, appUrl);

        }
    }

    private void addSectionPrefix(ExcelSheet excelSheet, Cell cell){
        if (cellPrefixIndex ==0 && cell.getStringCellValue().equals("Section prefix")){
            cellPrefixIndex=cell.getColumnIndex();
        }
        if (cellPrefixIndex!= 0 && cellPrefixIndex==cell.getColumnIndex() && !cell.getStringCellValue().equals("NextGen") && !cell.getStringCellValue().equals("Section prefix")){
            excelSheet.setSectionPrefix(cell.getStringCellValue());
        }
    }

    private void addSectionName(ExcelSheet excelSheet, Cell cell){
        if (cellSectionNameIndex ==0 && cell.getStringCellValue().equals("Section name")){
            cellSectionNameIndex=cell.getColumnIndex();
        }
        if (cellSectionNameIndex !=0 && cellSectionNameIndex==cell.getColumnIndex() && !cell.getStringCellValue().equals("NextGen") && !cell.getStringCellValue().equals("Section name")){
            excelSheet.setSectionName(cell.getStringCellValue());
        }
    }

    private void addCfiLinkSuffix(ExcelSheet excelSheet, Cell cell){
        if (cellCfiSuffixIndex ==0 && cell.getStringCellValue().equals("*Entry point")){
            cellCfiSuffixIndex=cell.getColumnIndex();
        }
        if (cellCfiSuffixIndex !=0 && cellCfiSuffixIndex==cell.getColumnIndex() && !cell.getStringCellValue().equals("NextGen") && !cell.getStringCellValue().equals("*Entry point")){
            excelSheet.setCfiEntryPoint(cell.getStringCellValue());
        }
    }
}
