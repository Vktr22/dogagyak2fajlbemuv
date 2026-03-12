package hu.szamalk.controller;


import hu.szamalk.modell.Ember;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class FileMuveletek {

    // 1️⃣ Feldarabolás tetszőleges separátor alapján
    public static String[] darabol(String sor, String separator) {
        return sor.split(separator);
    }

    // 2️⃣ Fölösleges szóközök eltávolítása
    public static String tisztit(String szoveg) {
        return szoveg.trim().replaceAll("\\s+", " ");
    }


    // 4. Több soros fájl beolvasása String listába
    public static List<String> beolvasSorok(Path path) throws IOException {
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }



    // 5. Több soros fájl beolvasása Ember listába
    public static List<Ember> beolvasEmberek(Path path) throws IOException {
        List<String> sorok = beolvasSorok(path);
        List<Ember> emberek = new ArrayList<>();

        // első sor a fejléc
        for (int i = 1; i < sorok.size(); i++) {
            String sor = sorok.get(i);
            String[] darabok = sor.split(",");

            if (darabok.length != 3) {
                throw new RuntimeException("Hibás sor (nem 3 oszlop): " + sor);
            }

            String nev = darabok[0].trim();
            String korStr = darabok[1].trim();
            String cim = darabok[2].trim();

            try {
                int kor = Integer.parseInt(korStr);
                emberek.add(new Ember(nev, kor, cim));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Érvénytelen szám: " + korStr + " (sor: " + sor + ")");
            }
        }

        return emberek;
    }

    //olyan metódus, ami ezeket az embereket névvsor szerint adja vissza a listámat
    public static List<Ember> abcSorrendEmberekLista(List<String> eredetiLista){
        Collections.sort(emberek);
    }

    public static <T> List<T> listaKeverese(List<T> eredeti) {
        List<T> masolat = new ArrayList<>(eredeti); // ne módosítsuk az eredetit
        Collections.shuffle(masolat);
        return masolat;
    }

    //feladat:
    //hany azonos ember (minden adattagjaban megegyezo) van a listaban:
    public static int hanyAzonosEmberVan(List<Ember> lista) {
        Map<Ember, Integer> szamlalo = new HashMap<>();

        for (Ember e : lista) {
            szamlalo.put(e, szamlalo.getOrDefault(e, 0) + 1);
        }

        int osszesDupla = 0;
        for (Integer db : szamlalo.values()) {
            if (db > 1) {
                osszesDupla += db;
            }
        }

        return osszesDupla;
    }

    //feladat:
    //adjuk vissza azokat akik tobbszor szerepelnek:
    public static List<Ember> tobbszorSzereplok(List<Ember> lista) {
        Map<Ember, Integer> szamlalo = new HashMap<>();

        for (Ember e : lista) {
            szamlalo.put(e, szamlalo.getOrDefault(e, 0) + 1);
        }

        List<Ember> eredmeny = new ArrayList<>();
        for (Map.Entry<Ember, Integer> entry : szamlalo.entrySet()) {
            if (entry.getValue() > 1) {
                eredmeny.add(entry.getKey());
            }
        }

        return eredmeny;
    }




}
