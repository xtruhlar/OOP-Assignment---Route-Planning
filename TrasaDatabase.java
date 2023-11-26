package com.example.secondary;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing a Database of Trasy
 */
public class TrasaDatabase {
    /**
     * Declare database
     */
    public static ArrayList <Tura.Trasa> trasadatabase = new ArrayList<>();

    /**
     * Method for loading database in memory afrer program is launched
     */
    public static void loadData() {
            File file = new File("src/main/java/com/example/secondary/trasy.txt");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                if (line.length >= 6) { // skontroluj dĺžku poľa
                    double vzdialenost = Double.parseDouble(line[2]);
                    int cas = Integer.parseInt(line[3]);
                    int lvl = Integer.parseInt(line[4]);
                    String inter = line[5];
//                    if (!line[6].isEmpty()){
//                        inter += "," + line[6];
//                    }
                    Tura.Trasa t = new Tura.Trasa(line[0], line[1], vzdialenost, cas, lvl, inter);
                    trasadatabase.add(t);
                }
            }
            // aj sem aplikovat exception!
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}