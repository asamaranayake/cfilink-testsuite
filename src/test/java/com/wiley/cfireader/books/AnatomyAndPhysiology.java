package com.wiley.cfireader.books;

import com.wiley.cfireader.helpers.Helper;

public class AnatomyAndPhysiology {
    public String getActualSectionName(String sectionName) {
        System.out.println("checking in switch:"+sectionName+":");
        switch (sectionName){
            case " Ch 3: Disorders: Homeostatic Imbalances":
            case " Ch 4: Disorders: Homeostatic Imbalances":
            case " Ch 5: Disorders: Homeostatic Imbalances":
            case " Ch 6: Disorders: Homeostatic Imbalances":
            case " Ch 7: Disorders: Homeostatic Imbalances":
            case " Ch 8: Disorders: Homeostatic Imbalances":
            case " Ch 10: Disorders: Homeostatic Imbalances":
            case " Ch 11: Disorders: Homeostatic Imbalances":
            case " Ch 12: Disorders: Homeostatic Imbalances":
            case " Ch 13: Disorders: Homeostatic Imbalances":
            case " Ch 14: Disorders: Homeostatic Imbalances":
            case " Ch 15: Disorders: Homeostatic Imbalances":
            case " Ch 16: Disorders: Homeostatic Imbalances":
            case " Ch 17: Disorders: Homeostatic Imbalances":
            case " Ch 18: Disorders: Homeostatic Imbalances":
            case " Ch 19: Disorders: Homeostatic Imbalances":
            case " Ch 20: Disorders: Homeostatic Imbalances":
            case " Ch 21: Disorders: Homeostatic Imbalances":
            case " Ch 22: Disorders: Homeostatic Imbalances":
            case " Ch 23: Disorders: Homeostatic Imbalances":
            case " Ch 24: Disorders: Homeostatic Imbalances":
            case " Ch 25: Disorders: Homeostatic Imbalances":
            case " Ch 26: Disorders: Homeostatic Imbalances":
            case " Ch 28: Disorders: Homeostatic Imbalances":
            case " Ch 29: Disorders: Homeostatic Imbalances":
            case " Ch 9: Disorders: Homeostatic Imbalances that Affect Joints":
                return "Disorders: Homeostatic Imbalances";
            case " Ch 5: Focus on Homeostasis: The Integumentary System":
            case " Ch 8: Focus on Homeostasis":
            case " Ch 11: Focus on Homeostasis":
            case " Ch 15: Focus on Homeostasis: Nervous System":
            case " Ch 18: Focus on Homeostasis":
            case " Ch 21: Focus on Homeostasis":
            case " Ch 22: Focus on Homeostasis":
            case " Ch 23: Focus on Homeostasis":
            case " Ch 24: Focus on Homeostasis":
            case " Ch 26: Focus on Homeostasis":
            case " Ch 28: Focus on Homeostasis":
                return "//h2/span[text()='Focus on Homeostasis']___Focus on Homeostasis";
            case "5.6 Development of the Integumentary System":
                return " 5.6 Development of the Integumentary System";
            case "6.7 Bone's Role in Calcium Homeostasis":
                return "6.7 Bone’s Role in Calcium Homeostasis";
            case "8.4 False and True Pelves":
                return "8.4 Greater and Lesser Pelves";
            case "8.7 Development of the Skeletal System":
                return " 8.7 Development of the Skeletal System";
            case "11.5 Muscles of the Head That Move the Eyeballs (Extrinsic Eye Muscles) and Upper Eyelids":
                return "11.5 Muscles of the Head That Move the Eyeballs and Upper Eyelids";
            case "12.1 Overview of the Nervous System":
                return "//h2/b[text()='"+sectionName+"']___"+sectionName+"";
            case "19.1 Functions and Properties of Blood":
            case "19.3 Red Blood Cells":
            case "19.4 White Blood Cells":
            case "19.5 Platelets":
            case "19.6 Stem Cell Transplants from Bone Marrow and Cord Blood":
            case "19.7 Hemostasis":
            case "19.8 Blood Groups and Blood Types":
                String tmpSection[]=sectionName.split(" ",2);
                return "//h2[text()='"+tmpSection[0]+" "+"']/b[text()='"+tmpSection[1]+"']___"+tmpSection[1];
            case "12.7 Signal Transmissions at Synapses":
                return "12.7 Signal Transmission at Synapses";
            case "14.3 The Brain Stem and Reticular Formation":
                return "14.3 The Brainstem and Reticular Formation";
            case "18.9 Adrenal Glands":
                return "18.9 Suprarenal (Adrenal) Glands";
            case "21.10 The Arch of the Aorta":
                return "21.10 The Aortic Arch";
            case "21.23 Development of Blood Vessels and Blood":
                return " 21.23 Development of Blood Vessels and Blood";
            case "22.5 Development of Lymphoid Tissues":
                return " 22.5 Development of Lymphoid Tissues";
            case "22.12 Aging and the Immune System":
                return "22.12 Aging and the Lymphoid System";
            case "23.10 Development of the Respiratory System":
                return "  23.10 Development of the Respiratory System";
            case "24.2 Layers of the GI Tract":
                return "24.2 Layers of the Digestive Canal";
            case "24.3 Neural Innervation of the GI Tract":
                return "24.3 Neural Innervation of the Digestive Canal";
            case "24.15 Development of the Digestive System":
                return " 24.15 Development of the Digestive System";
            case "26.11 Development of the Urinary System":
                return " 26.11 Development of the Urinary System";
            case "27.3 Acid-Base Balance":
                return "27.3 Acid–Base Balance";
            case "27.4 Aging and Fluid, Electrolyte, and Acid-Base Homeostasis":
                return "27.4 Aging and Fluid, Electrolyte, and Acid–Base Homeostasis";
            default:
                return Helper.capitalizeWord(sectionName);
        }
    }
    }
