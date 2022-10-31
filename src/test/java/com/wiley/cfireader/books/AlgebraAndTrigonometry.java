package com.wiley.cfireader.books;

public class AlgebraAndTrigonometry {
    public String getActualSectionName(String sectionName) {
        System.out.println("checking in switch:"+sectionName+":");
        switch (sectionName){
            case "2.2 Graphing Equations, Point‐Plotting, Intercepts, and Symmetry":
                return "//h2[text()=' GRAPHING EQUATIONS, POINT-PLOTTING, INTERCEPTS, AND SYMMETRY']___2.2 GRAPHING EQUATIONS, POINT-PLOTTING, INTERCEPTS, AND SYMMETRY";
            case "2.5 Linear Regression: Best Fit":
                return "//h2/span[text()='LINEAR REGRESSION: BEST FIT']___LINEAR REGRESSION: BEST FIT";
            case "8.7 Products, Quotients, Powers, and Roots of Complex Numbers; De Moivre's Theorem":
                return "//h2[text()=\" PRODUCTS, QUOTIENTS, POWERS, AND ROOTS OF COMPLEX NUMBERS; DE MOIVRE'S THEOREM\"]___8.7 PRODUCTS, QUOTIENTS, POWERS, AND ROOTS OF COMPLEX NUMBERS; DE MOIVRE'S THEOREM";
            case "10.4 The Determinant of a Square Matrix and Cramer's Rule":
                return "//h2[text()=\" THE DETERMINANT OF A SQUARE MATRIX AND CRAMER'S RULE\"]___10.4 THE DETERMINANT OF A SQUARE MATRIX AND CRAMER'S RULE";
            default:
                String tmpSection[]=sectionName.split(" ",2);
                return "//h2[text()=' "+tmpSection[1].toUpperCase()+"']___"+sectionName.toUpperCase();
        }

    }
}
