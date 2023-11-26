package com.example.Pouzivatelia;

/**
 * Interface
 */
public interface Kondicia {
    /**
     * setter
     * @param kondicia kondicia
     */
    void setKondicia(int kondicia);

    /**
     * getter
     * @param weight vaha
     * @param height vyska
     * @param age vek
     * @return kondicia
     */
    int getKondicia(double weight, double height, int age);
}