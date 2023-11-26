package com.example.Pouzivatelia;

/**
 * Class representing a Horolezec extrends User
 * !** DEDENIE **!
 */
public class Horolezec extends User{
    /**
     * Constructor for Horolezec
     * @param username username
     * @param password heslo
     * @param equip vybavenie
     * @param loggedin prihlaseny?
     * @param condition kondicia
     */
        public Horolezec(String username, String password, boolean equip, boolean loggedin, Integer condition) {
        super(username, password, equip, loggedin, condition);
    }

    @Override
    public int computeTime(int cas) {
        /**
         * !** POLYMORFIZMUS **!
         * Method for computing the time for a climber
         * @param cas cas
         */
        int time = cas;
        if (this.getEquip()) {
            time -= 15;
        }
        return time;
    }

    @Override
    public void setKondicia(int kondicia) {

    }

    @Override
    public int getKondicia(double weight, double height, int age) {
        return 0;
    }
}