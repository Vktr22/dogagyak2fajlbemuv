package hu.szamalk.controller;

import hu.szamalk.modell.Ember;
import java.util.Comparator;

public class EmberCimRendezo implements Comparator<Ember> {

    @Override
    public int compare(Ember e1, Ember e2) {
        return e1.getCim().compareTo(e2.getCim());
    }
}

//hany azonos ember van a file-ban?
//adjuk vissza azokat akik tobbszor szerepelnek