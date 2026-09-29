package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte> {
	private Carte[] cartes;
	private int nbCartes;
	private int nombreOperations = 0;

	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}
	
	public int getNbCartes() {
		return nbCartes;
	}
	
	public Carte[] getCartes() {
		return cartes;
	}
	
	public boolean estVide() {
		return nbCartes==0 ;
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes>=cartes.length) {
			throw new IndexOutOfBoundsException("Capacité maximale du sabot atteinte !");
		}
		cartes[nbCartes] = carte;
		nbCartes++;
	}

	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	private class Iterateur implements Iterator<Carte> {
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nombreOperationsReference = nombreOperations;
		
		@Override
		public boolean hasNext() {
			return indiceIterateur<nbCartes;
		}

		@Override
		public Carte next() {
			verificationConcurrence();
			if (hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carte;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			verificationConcurrence();
			if(!nextEffectue || nbCartes < 1) {
				throw new IllegalStateException();
			}
			
			for (int i = indiceIterateur - 1; i < nbCartes - 1; i++) {
				cartes[i] = cartes[i+1];
			}
			
			nextEffectue = false;
			indiceIterateur--;
			nbCartes--;
			nombreOperationsReference++;
			nombreOperations++;
		}
		
		private void verificationConcurrence() {
			if(nombreOperations!=nombreOperationsReference) {
				throw new ConcurrentModificationException();
			}
		}
		
	}
	
	public Carte piocher() {
	    Iterator<Carte> it = iterator();
	    Carte carte = it.next(); 
	    it.remove();           
	    return carte;
	}
}
