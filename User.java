package com.example.Pouzivatelia;

/**
 * Class representing a User
 * !** Agregácia **!
 */
public abstract class User implements Kondicia {
    /**
     * private information about user
     * !** Zapuzdrenie **!
     */
    protected String username, password;
    private final boolean equip;
    private boolean loggedin;
    private final Integer condition;

    /**
     * Constructor
     * @param username username
     * @param password heslo
     * @param equip horolezecka vybava
     * @param loggedin check if user is logged in
     * @param condition kondicia
     */
    public User(String username, String password, boolean equip, boolean loggedin, Integer condition){
        this.username = username;
        this.password = password;
        this.equip =  equip;
        this.loggedin = loggedin;
        this.condition = condition;
    }

    /**
     * Constructor
     * @param equip vybavenie
     * @param condition kondicia
     */
    public User(boolean equip, Integer condition) {
        this.equip = equip;
        this.condition = condition;
    }

    /**
     * getter for password
     * @return heslo
     */
    public String getPassword() {
        return password;
    }

    /**
     * getter for username
     * @return username
     */
    public String getUsername() {
        return username;
    }


    /**
     * getter for equipment
     * @return vybavenie
     */
    public boolean getEquip() {
        return equip;
    }

    /**
     * check which user is logged in
     * @param loggedin check if user is logged in
     */
    public void setLoggedin(boolean loggedin){
        this.loggedin = loggedin;
    }


    /**
     * getter for condition
     * @return kondicia
     */
    public int getCondition(){
        return this.condition;
    }

    /**
     * Method to turn information about user into string
     * @return string
     */
    @Override
    public String toString() {
        return "User{" +
                "Username='" + username + '\'' +
                ", heslo=" + password +
                ", vybava=" + equip +
                ", kondicia=" + condition +
                '}';
    }

    /**
     * Abstract method to compute time on Trasa
     * @param cas vstup
     * @return cas
     */
    public abstract int computeTime(int cas);
}