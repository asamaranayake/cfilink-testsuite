package com.wiley.cfireader.fileio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class CsvUtils {
    public static void writeDataToCSVFile(String bookName, String results, boolean isAppend){
        StringBuilder builder = new StringBuilder();
        FileWriter pw;
        try {
            File file = new File(bookName+".csv");
            file.createNewFile();
            pw = new FileWriter(file, true);
            if(!isAppend) {
                String columnNamesList = "SectionId,SectionName,CFILink,TestStatus";
                builder.append(columnNamesList +"\n");
            }else {
                builder.append(results);
                builder.append('\n');
            }
        pw.write(builder.toString());
        pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception ex){
            System.out.println("cannot write the file");
        }
    }

    public static void writeDataToCSVFiles(String csvFile, String results, boolean isAppend){
        StringBuilder builder = new StringBuilder();
        FileWriter pw;
        try {
            File file = new File("target/perfusers/"+csvFile+".csv");
            file.createNewFile();
            pw = new FileWriter(file, true);
            if(!isAppend) {
                String columnNamesList = results;
                builder.append(columnNamesList +"\n");
            }else {
                builder.append(results);
                builder.append('\n');
            }
            pw.write(builder.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception ex){
            System.out.println("cannot write the file");
        }
    }



}
