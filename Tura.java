package com.example.secondary;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class Tura extends TrasaDatabase {
    /**
     * Class for Trasa
     */
    public static class Trasa {
        /**
         * Private atributes -  *! Zapuzdrenie !*
         */
        private final String start;
        private final String ciel;
        private final Double vzdialenost;
        private Integer cas;
        private final Integer narocnost;
        private String zaujimavosti;

        /**
         * Constructor
         * !** Vhniezdená trieda **!
         * !** Zapuzdrenie **!
         * @param start        start
         * @param ciel         ciel
         * @param vzdialenost  vzialenost
         * @param cas          cas
         * @param narocnost    narocnost
         * @param zaujimavosti zaujimavosti
         */
        public Trasa(String start, String ciel, Double vzdialenost, Integer cas, Integer narocnost, String zaujimavosti) {
            this.start = start;
            this.ciel = ciel;
            this.vzdialenost = vzdialenost;
            this.cas = cas;
            this.narocnost = narocnost;
            this.zaujimavosti = zaujimavosti;
        }

        /**
         * getter
         * @return start
         */
        public String getStart() {
            return this.start;
        }

        /**
         * getter
         * @return ciel
         */
        public String getCiel() {
            return this.ciel;
        }

        /**
         * getter
         * @return vzdialenost
         */
        public double getVzdialenost() {
            return this.vzdialenost;
        }

        /**
         * getter
         * @return cas
         */
        public int getCas() {
            return this.cas;
        }

        /**
         * setter
         * @param cas cas
         */
        public void setCas(int cas) {
            this.cas = cas;
        }

        /**
         * getter
         * @return narocnost
         */
        public int getNarocnost() {
            return this.narocnost;
        }

        /**
         * getter
         * @return zaujimavosti
         */
        public String getZaujimavosti() {
            return this.zaujimavosti;
        }

        /**
         * Setter
         * @param inter zaujimavosti
         */
        public void setZaujimavosti(String inter) {
            this.zaujimavosti = inter;
        }

        /**
         * Method to turn Trasa into string
         * @return string
         */
        @Override
        public String toString() {
            return "Trasa:" + "\n" +
                    "Štart: " + start + "\n" +
                    "Cieľ: " + ciel + "\n" +
                    "Čas: " + cas + "\n" +
                    "Vzdialenosť: " + vzdialenost.floatValue() + "\n" +
                    "Nárocnosť: " + narocnost + "\n" +
                    "Zaujímavosti: " + zaujimavosti + "\n";
        }
    }

    /**
     * Method to merge two Trasa together with multithreading
     * !** VIACNITNOST **!
     * @param A first Trasa
     * @param B second Trasa
     * @return resulting Trasa
     */
    public static Trasa combTrasy(Trasa A, Trasa B) throws InterruptedException, ExecutionException {
        if (A.getCiel().equals(B.getStart())) {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            Callable<Trasa> task1 = () -> A;
            Callable<Trasa> task2 = () -> B;
            Future<Trasa> f1 = executor.submit(task1);
            Future<Trasa> f2 = executor.submit(task2);
            Trasa first = f1.get();
            Trasa second = f2.get();
            executor.shutdown();
            return new Trasa(first.getStart(), second.getCiel(), first.getVzdialenost() + second.getVzdialenost(), first.getCas() + second.getCas(), ((first.getNarocnost() + second.getNarocnost()) + 1) / 2, first.getZaujimavosti() + "," + second.getZaujimavosti());
        } else {
            System.out.println("Trasy sa nedaju spojit");
            return A;
        }
    }

    static Set<String> navstiveneMiesta = new HashSet<String>();

    /**
     * Method to find if two places can be merged - if there is place which they have in common
     * @param start start
     * @param ciel ciel
     * @param trasy database
     * @param eat obcerstvenie
     * @param vpad vodopad
     * @param vlek vlek
     * @param lano zlanovanie
     * @return Tura
     */
    public static Trasa najdiSpojenie(String start, String ciel, ArrayList<Trasa> trasy, boolean eat, boolean vpad, boolean vlek, boolean lano) {
        for (Trasa trasa : trasy) {
            if (trasa.getStart().equals(start) && trasa.getCiel().equals(ciel)) {
                if  (kontrolujZaujimavost(trasa, eat, vpad, vlek, lano)){
                    trasa.setZaujimavosti("Na zvolenej trase nie je dana zaujimavost");
                }
                return trasa;
            } else if (trasa.getStart().equals(start) && !navstiveneMiesta.contains(trasa.getCiel())) {
                navstiveneMiesta.add(trasa.getStart()); // pridáme mesto do navštívených miest
                if (kontrolujZaujimavost(trasa, eat, vpad, vlek, lano)){
                    trasa.setZaujimavosti("Na zvolenej trase nie je dana zaujimavost");
                }
                Trasa trasaCiel = najdiSpojenie(trasa.getCiel(), ciel, trasy, eat, vpad, vlek, lano);
                if (trasaCiel != null) {
                    Trasa A = null;
                    try {
                        A = combTrasy(trasa, trasaCiel);
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                    navstiveneMiesta.clear();
                    return A;
                }
                navstiveneMiesta.clear();
            }
        }
        return null;
    }

    /**
     * Method to check if there is choosen Zaujimavost on Trasa
     * @param trasa trasa
     * @param eat Obcerstvenie
     * @param vpad Vodopad
     * @param vlek Vlek
     * @param lano Zlanovanie
     * @return boolean
     */
    public static boolean kontrolujZaujimavost(Trasa trasa, boolean eat, boolean vpad, boolean vlek, boolean lano) {
        if (eat && !trasa.getZaujimavosti().contains("Obcerstvenie")) {
            return true;
        } else {
            //  trasa.setZaujimavosti("Obcerstvenie");
        }
        if (vpad && !trasa.getZaujimavosti().contains("Vodopad")) {
            return true;
        } else {
            //   trasa.setZaujimavosti("Vodopad");
        }
        if (vlek && !trasa.getZaujimavosti().contains("Vlek")) {
            return true;
        } else {
            //   trasa.setZaujimavosti("Vlek");
        }
        if (lano && !trasa.getZaujimavosti().contains("Zlanovanie")) {
            return true;
        } else {
            //  trasa.setZaujimavosti("Zlanovanie");
        }
        return false;
    }
}