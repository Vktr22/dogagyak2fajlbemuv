package hu.szamalk.modell;

public class Ember implements Comparable{
    private String nev;
    private int kor;
    private String cim;

    public Ember(String nev, int kor, String cim) {
        this.nev = nev;
        this.kor = kor;
        this.cim = cim;
    }

    public String getNev() { return nev; }
    public int getKor() { return kor; }
    public String getCim() { return cim; }

    @Override
    public String toString() {
        return nev + " (" + kor + ") - " + cim;
    }

}
