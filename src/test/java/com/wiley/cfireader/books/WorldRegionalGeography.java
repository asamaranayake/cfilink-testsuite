package com.wiley.cfireader.books;

import com.wiley.cfireader.base.CommonPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WorldRegionalGeography {

    public String getActualSectionName(String sectionName) {
        switch (sectionName) {
            case "What Is Geography?":
                return "What Is Geography";
            case "What Tools Do Geographers Use to Study World Regional Geography?":
                return "What Tools Do Geographers Use to Study World Regional Geography";
            case "How Are Places Connected, and Why Are Some Places More Connected Than Others?":
                return "How Are Places Connected, and Why Are Some Places More Connected than Others?";
            case "What Is Development and Where Did the Idea of Development Originate?":
                return "What is Development and Where Did the Idea of Development Originate?";
            case "Why Is Development Uneven?":
                return "Why is Development Uneven?";
            case "Why Are the Dynamics of HIV/AIDS in Africa South of the Sahara Different from Those in Other World Regions?":
                return "Why are the Dynamics of HIV/AIDS in Africa South of the Sahara Different from Those in Other World Regions?";
            case "How Is the Region Interconnected Through Trade?":
                return "How Is the Region Interconnected through Trade?";
        }
        return sectionName;
    }
}
