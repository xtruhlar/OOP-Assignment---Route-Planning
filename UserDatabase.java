package com.example.Pouzivatelia;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing a Database of Users
 */
public class UserDatabase {
    /**
     * Declare database
     */
    public static ArrayList <User> userdatabase = new ArrayList<>();
    /**
     * Registration
     * @param name username
     * @param password password
     * @param equipment vybava
     * @param loggedin is logged in?
     * @param condition kondicia
     */
    public static void registerUser(String name, String password, boolean equipment, boolean loggedin, int condition) {
        User newUser;
        if (condition == 1 || condition == 2 && (equipment)) {
            newUser = new Horolezec(name, password, equipment, loggedin, condition);
        } else {
            newUser = new User(name, password, equipment, loggedin, condition) {
                @Override
                public void setKondicia(int kondicia) {

                }

                @Override
                public int getKondicia(double weight, double height, int age) {
                    return 0;
                }

                @Override
                public int computeTime(int cas) {
                    return 0;
                }
            };
        }
        userdatabase.add(newUser);
    }

    /**
     * Method for loading database in memory afrer program is launched
     */
    public static void loadData() {
        File file = new File("src/main/java/com/example/Pouzivatelia/uzivatelia.txt");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                if (line.length >= 4) { // skontroluj dĺžku poľa
                    boolean eq = line[2].equals("true");
                    boolean li = line[3].equals("true");
                    Integer lv = Integer.parseInt(line[4]);
                    registerUser(line[0], line[1], eq, li, lv);
                }
            }
            // sem aplikovat exception s oknom WARNING!
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}