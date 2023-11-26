package com.example.pomocneFunkcie;

import com.example.Pouzivatelia.Kondicia;

/**
 * BMI Class
 */
public class BMI implements Kondicia {
    /**
     * Method to calculate BMI
     * @param weight vaha
     * @param height vyska
     * @return kondicia
     */
    public static double bmi(double weight, double height) {
        double bmi = weight / (height * height);
        return Math.round(bmi * 100.0) / 100.0;
    }

    @Override
    public void setKondicia(int kondicia) {

    }

    @Override
    public int getKondicia(double weight, double height, int age) {
        return 0;
    }
}
