example:



import java.io.File;                  // Import the File class

import java.io.FileNotFoundException; // Import this class to handle errors

import java.util.Scanner;             // Import the Scanner class to read text files





public class ReadFile {

&nbsp; public static void main(String\[] args) {

&nbsp;   File myObj = new File("filename.txt");



&nbsp;   // try-with-resources: Scanner will be closed automatically

&nbsp;   try (Scanner myReader = new Scanner(myObj)) {

&nbsp;     while (myReader.hasNextLine()) {

&nbsp;       String data = myReader.nextLine();

&nbsp;       System.out.println(data);

&nbsp;     }

&nbsp;   } catch (FileNotFoundException e) {

&nbsp;     System.out.println("An error occurred.");

&nbsp;     e.printStackTrace();

&nbsp;   }

&nbsp; }

}

//This program opens the file named filename.txt and reads it line by line using a Scanner. Each line is printed to the console. If the file cannot be found, the program will print "An error occurred." instead.







&nbsp;/\*

&nbsp;       a lista--> collections osztály



&nbsp;       stringbeEgySorbol

&nbsp;       stringListabaTobbSorbol



&nbsp;       sajatTipustTobbSorbol -->

&nbsp;       NÉV;KOR;CÍM

&nbsp;       Béla;22;Bp

&nbsp;       Cecil;33;Bp

&nbsp;       Anna;18;Érd

&nbsp;       ehhez Ember osztályt létrehozni --- ide kellenek getterek, h elerjuk az adattagokat(mivel privatak ugye)



&nbsp;       kiirni/kilistazni a legidosebb ember nevet

&nbsp;       mindenki elmult 18? --->eldontes tetele

&nbsp;       hany féle helyen laknak? --->set (halmaz) HashSet<>()

&nbsp;       hol, mennyien laknak? --->map

&nbsp;           (kulcs értékek növelése if-ben, elseben adott kulcsra az 1 ertek elhelyezese

&nbsp;           iter + tab

&nbsp;       jegyzet, stackoverflow



&nbsp;       rendezesek!!! --sajat osztalyok rendezese

&nbsp;   \*/

prompt:

írj nekem rövid példákat filebeolvasásra javaból, olyan metódusokkal, amik az alábbiakat tudják:

legyen egy olyan metódus, ami a megadott separátor mentén szétszedi az elemeket (pl , vagy ,),

egy olyan, ami kiveszi a felesleges szóközöket,

legyen egy olyan beolvasás, ami stringbe olyan fileból, ami 1 soros, legyen egy olyan ami string listába olyan fileból ami több soros, és legyen egy olyan, ami saját típusú és több soros fileból(pl: év,kor,cím\\nBéla,22,Budapest\\nAnna,11,Érd)-->ehhez ember osztályt létrehozni

mindegyik tudja kezelni az ékezeteket

aztán az alábbi műveletek legyenek meg, használva a progtételeket:

-kiirni/kilistázni a legidősebb ember nevét

-mindenki elmúlt 18? (eldontes tetele)

-hány féle helyen laknak?(HashSet<>)

-hol, mennyien laknak? (kilcs ertek novelese if-ben, else-ben adott kulcsra az 1 ertek elhelyezese)

ne is rövid példák legyenek, hanem projekt, magyarázatokkal



src/

&nbsp;├── Main.java

&nbsp;├── Ember.java

&nbsp;├── FileUtils.java

resources/

&nbsp;├── egy\_soros.txt

&nbsp;├── tobb\_soros.txt

&nbsp;└── emberek.txt





**Ember osztály:**

public class Ember {

&nbsp;   private String nev;

&nbsp;   private int kor;

&nbsp;   private String cim;



&nbsp;   public Ember(String nev, int kor, String cim) {

&nbsp;       this.nev = nev;

&nbsp;       this.kor = kor;

&nbsp;       this.cim = cim;

&nbsp;   }



&nbsp;   public String getNev() { return nev; }

&nbsp;   public int getKor() { return kor; }

&nbsp;   public String getCim() { return cim; }



&nbsp;   @Override

&nbsp;   public String toString() {

&nbsp;       return nev + " (" + kor + ") - " + cim;

&nbsp;   }

}



**FileUtils osztály (minden fájlkezelő metódussal)**

import java.io.IOException;

import java.nio.file.Files;

import java.nio.file.Path;

import java.nio.charset.StandardCharsets;

import java.util.\*;



public class FileUtils {



&nbsp;   // 1️⃣ Feldarabolás tetszőleges separátor alapján

&nbsp;   public static String\[] darabol(String sor, String separator) {

&nbsp;       return sor.split(separator);

&nbsp;   }



&nbsp;   // 2️⃣ Fölösleges szóközök eltávolítása

&nbsp;   public static String tisztit(String szoveg) {

&nbsp;       return szoveg.trim().replaceAll("\\\\s+", " ");

&nbsp;   }



&nbsp;   // 3️⃣ Egy soros fájl beolvasása Stringbe

&nbsp;   public static String beolvasEgySor(Path path) throws IOException {

&nbsp;       return Files.readString(path, StandardCharsets.UTF\_8);

&nbsp;   }



&nbsp;   // 4️⃣ Több soros fájl beolvasása String listába

&nbsp;   public static List<String> beolvasSorok(Path path) throws IOException {

&nbsp;       return Files.readAllLines(path, StandardCharsets.UTF\_8);

&nbsp;   }



&nbsp;   // 5️⃣ Több soros fájl beolvasása Ember objektumok listájába

&nbsp;   public static List<Ember> beolvasEmberek(Path path) throws IOException {

&nbsp;       List<String> sorok = beolvasSorok(path);

&nbsp;       List<Ember> emberek = new ArrayList<>();



&nbsp;       // első sor fejléc → átugorjuk

&nbsp;       for (int i = 1; i < sorok.size(); i++) {

&nbsp;           String\[] darabok = sorok.get(i).split(",");

&nbsp;           String nev = darabok\[0].trim();

&nbsp;           int kor = Integer.parseInt(darabok\[1].trim());

&nbsp;           String cim = darabok\[2].trim();

&nbsp;           emberek.add(new Ember(nev, kor, cim));

&nbsp;       }

&nbsp;       return emberek;

&nbsp;   }

}



**Main – programozási tételek alkalmazása**

import java.nio.file.Path;

import java.util.\*;



public class Main {

&nbsp;   public static void main(String\[] args) throws Exception {



&nbsp;       Path egySorPath = Path.of("resources/egy\_soros.txt");

&nbsp;       Path sorokPath = Path.of("resources/tobb\_soros.txt");

&nbsp;       Path emberekPath = Path.of("resources/emberek.txt");



&nbsp;       System.out.println("===== Egysoros beolvasása =====");

&nbsp;       String egySor = FileUtils.beolvasEgySor(egySorPath);

&nbsp;       System.out.println(egySor);



&nbsp;       System.out.println("\\n===== Többsoros beolvasása String listába =====");

&nbsp;       List<String> sorok = FileUtils.beolvasSorok(sorokPath);

&nbsp;       sorok.forEach(System.out::println);



&nbsp;       System.out.println("\\n===== Emberek beolvasása =====");

&nbsp;       List<Ember> emberek = FileUtils.beolvasEmberek(emberekPath);

&nbsp;       emberek.forEach(System.out::println);



&nbsp;       // 1️⃣ Programozási tétel: legidősebb ember

&nbsp;       Ember legidosebb = emberek.get(0);

&nbsp;       for (Ember e : emberek) {

&nbsp;           if (e.getKor() > legidosebb.getKor()) {

&nbsp;               legidosebb = e;

&nbsp;           }

&nbsp;       }

&nbsp;       System.out.println("\\nLegidősebb ember: " + legidosebb.getNev());



&nbsp;       // 2️⃣ Eldöntés tétele: mindenki elmúlt 18?

&nbsp;       boolean mindenFelnott = true;

&nbsp;       for (Ember e : emberek) {

&nbsp;           if (e.getKor() < 18) {

&nbsp;               mindenFelnott = false;

&nbsp;               break;

&nbsp;           }

&nbsp;       }

&nbsp;       System.out.println("\\nMindenki elmúlt 18? " + mindenFelnott);



&nbsp;       // 3️⃣ Hány féle hely? (HashSet)

&nbsp;       Set<String> helyek = new HashSet<>();

&nbsp;       for (Ember e : emberek) {

&nbsp;           helyek.add(e.getCim());

&nbsp;       }

&nbsp;       System.out.println("\\nHány féle helyen laknak? " + helyek.size());



&nbsp;       // 4️⃣ Hol hányan laknak? (Map statisztika)

&nbsp;       Map<String, Integer> stat = new HashMap<>();

&nbsp;       for (Ember e : emberek) {

&nbsp;           String hely = e.getCim();

&nbsp;           if (stat.containsKey(hely)) {

&nbsp;               stat.put(hely, stat.get(hely) + 1);

&nbsp;           } else {

&nbsp;               stat.put(hely, 1);

&nbsp;           }

&nbsp;       }



&nbsp;       System.out.println("\\nHol, mennyien laknak?");

&nbsp;       for (var entry : stat.entrySet()) {

&nbsp;           System.out.println(entry.getKey() + ": " + entry.getValue() + " fő");

&nbsp;       }



&nbsp;   }

}







emberek.txt

név,kor,cím

Béla,22,Budapest

Anna,11,Érd

Géza,44,Pécs

Edit,19,Budapest



egy\_soros.txt

Szia, ez egy egysoros fájl ékezetekkel.





tobb\_soros.txt

első sor

második sor

harmadik sor





feladat:

adjuk vissza a neveket, embereket





jegyzet:



kif

public class Ember implements Comparable<Ember>	//generikus kell
public int compareTo(Ember masik)
Collections.sort(emberek)
for(Ember ember:emberek)
comparator tipus





keszits egy kor alapjan rendezo oszt
eloszor oszt, ami megval a rendezési logikat, aztan fel kell haszn

import java.text.Collator;
import java.util.Comparator;

public class EmberCimRendezo implements Comparator























































































































extras:

import java.io.File;  // Import the File class



public class GetFileInfo { 

&nbsp; public static void main(String\[] args) {

&nbsp;   File myObj = new File("filename.txt");

&nbsp;   if (myObj.exists()) {

&nbsp;     System.out.println("File name: " + myObj.getName());

&nbsp;     System.out.println("Absolute path: " + myObj.getAbsolutePath());

&nbsp;     System.out.println("Writeable: " + myObj.canWrite());

&nbsp;     System.out.println("Readable " + myObj.canRead());

&nbsp;     System.out.println("File size in bytes " + myObj.length());

&nbsp;   } else {

&nbsp;     System.out.println("The file does not exist.");

&nbsp;   }

&nbsp; }

}

