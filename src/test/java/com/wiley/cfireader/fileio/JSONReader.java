package com.wiley.cfireader.fileio;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;

public class JSONReader {

    public static JSONObject readConfigFile(){

        JSONParser parser = new JSONParser();
        //URL url = Thread.currentThread().getContextClassLoader().getResource("conf/config.json");
        try (Reader reader = new FileReader("src/test/resources/conf/config.json")){

            return (JSONObject) parser.parse(reader);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
