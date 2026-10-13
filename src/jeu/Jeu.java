package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
    private Sabot sabot;

    public Jeu() {
        JeuDeCartes jeuDeCartes = new JeuDeCartes();

        List<Carte> listeCartes = new ArrayList<>();
        Collections.addAll(listeCartes, jeuDeCartes.donnerCartes());

        listeCartes = GestionCartes.melanger(listeCartes);

        Carte[] cartes = listeCartes.toArray(new Carte[0]);
        sabot = new Sabot(cartes);
    }
}