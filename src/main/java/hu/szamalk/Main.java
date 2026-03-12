package hu.szamalk;

import hu.szamalk.controller.FileMuveletek;
import hu.szamalk.modell.Ember;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        // resources/emberek.txt beolvasása helyfüggetlenül
        Path emberekPath = Path.of(
                Main.class.getClassLoader().getResource("emberek.txt").toURI()
        );

        List<Ember> emberek = FileMuveletek.beolvasEmberek(emberekPath);

        System.out.println("===== Emberek =====");
        emberek.forEach(System.out::println);



        List<Ember> kevert = listaKeverese(emberek);

        System.out.println("Keverve:");
        kevert.forEach(System.out::println);




        // --- 1. feladat: hány azonos ember van?
        int azonosDarab = FileMuveletek.hanyAzonosEmberVan(emberek);
        System.out.println("\n===== Hány azonos ember szerepel a listában? =====");
        System.out.println(azonosDarab + " db azonos ember található a listában.");

        // --- 2. feladat: mely emberek szerepelnek többször?
        List<Ember> tobbszor = FileMuveletek.tobbszorSzereplok(emberek);
        System.out.println("\n===== Többször szereplő emberek =====");

        if (tobbszor.isEmpty()) {
            System.out.println("Nincs olyan ember, aki többször szerepel.");
        } else {
            tobbszor.forEach(e ->
                    System.out.println(e.getNev() + " (" + e.getKor() + ") - " + e.getCim())
            );
        }



    /*
        // ---- PROGRAMOZÁSI TÉTELEK ----

        System.out.println("\n===== Legidősebb ember =====");
        Ember legidosebb = emberek.get(0);
        for (Ember e : emberek) {
            if (e.getKor() > legidosebb.getKor()) {
                legidosebb = e;
            }
        }
        System.out.println("Legidősebb: " + legidosebb.getNev());


        System.out.println("\n===== Eldöntés tétel – mindenki elmúlt 18? =====");
        boolean mindenFelnott = true;
        for (Ember e : emberek) {
            if (e.getKor() < 18) {
                mindenFelnott = false;
                break;
            }
        }
        System.out.println("Mindenki felnőtt? " + mindenFelnott);


        System.out.println("\n===== Hány féle helyen laknak? (HashSet) =====");
        Set<String> helyek = new HashSet<>();
        for (Ember e : emberek) {
            helyek.add(e.getCim());
        }
        System.out.println("Helyek száma: " + helyek.size());
        System.out.println("Helyek: " + helyek);


        System.out.println("\n===== Hol hányan laknak? (Map<String, Integer>) =====");
        Map<String, Integer> stat = new HashMap<>();
        for (Ember e : emberek) {
            String hely = e.getCim();
            if (stat.containsKey(hely)) {
                stat.put(hely, stat.get(hely) + 1);
            } else {
                stat.put(hely, 1);
            }
        }

        stat.forEach((hely, db) ->
                System.out.println(hely + ": " + db + " fő")
        );

     */

    }
}