package fr.isika.cda13.projet1.projet1.model;

import java.util.List;

public class Arbre {

	// Attribut de la classe : c'est un noeud qui est la racine de l'arbre binaire
	// de recherche
	public Noeud racine;

	// Constructeur vide de l'abre binaire, on initialise juste la racine à null

	public Arbre() {
		this.racine = null;
	}

	public void ajouterValeur(Stagiaire valeur) {

		this.racine = ajouterValeur(valeur, racine);

	}

	public Noeud ajouterValeur(Stagiaire valeur, Noeud racineCourante) {

		if (racineCourante == null) {
			racineCourante = new Noeud(valeur);
			return racineCourante;
		} else if (racineCourante.donnee.compareTo(valeur) > 0) {
			racineCourante.gauche = ajouterValeur(valeur, racineCourante.gauche);
			return racineCourante;
		}else if (racineCourante.donnee.compareTo(valeur) == 0){
			racineCourante.doublon =ajouterValeur(valeur, racineCourante.doublon);
				return racineCourante;
		
				
		} else {racineCourante.droit = ajouterValeur(valeur, racineCourante.droit);
		return racineCourante;
			

		}

	}
	

		
	public void majPrenomValeur(Stagiaire valeur, String e) {

		this.racine = majPrenomValeur(valeur, racine, e);

	}

	public Noeud majPrenomValeur(Stagiaire valeur, Noeud racineCourante, String e) {

		if (racineCourante == null) {
			return racineCourante;
		} else if (racineCourante.donnee.compareTo(valeur) > 0) {
			racineCourante.gauche = majPrenomValeur(valeur, racineCourante.gauche,e);
			return racineCourante;
		}else if (racineCourante.donnee.compareTo(valeur) == 0){
			if (racineCourante.donnee.equals(valeur)) {
				racineCourante.donnee.setPrenom(e);
				return racineCourante;
				
			}else racineCourante.droit = majPrenomValeur(valeur, racineCourante.droit,e);
			return racineCourante;
				
		} else {racineCourante.droit = majPrenomValeur(valeur, racineCourante.droit,e);
		return racineCourante;
			

		}

	}
	public void majPromotionValeur(Stagiaire valeur, String e) {

		this.racine = majPromotionValeur(valeur, racine, e);

	}
	public Noeud majPromotionValeur(Stagiaire valeur, Noeud racineCourante, String e) {

		if (racineCourante == null) {
			return racineCourante;
		} else if (racineCourante.donnee.compareTo(valeur) > 0) {
			racineCourante.gauche = majPromotionValeur(valeur, racineCourante.gauche,e);
			return racineCourante;
		}else if (racineCourante.donnee.compareTo(valeur) == 0){
			if (racineCourante.donnee.equals(valeur)) {
				racineCourante.donnee.setPromotion(e);
				return racineCourante;
				
			}else racineCourante.droit = majPromotionValeur(valeur, racineCourante.droit,e);
			return racineCourante;
				
		} else {racineCourante.droit = majPromotionValeur(valeur, racineCourante.droit,e);
		return racineCourante;
			

		}

	}
	public void majAnneeValeur(Stagiaire valeur, String e) {

		this.racine = majAnneeValeur(valeur, racine, e);

	}

	public Noeud majAnneeValeur(Stagiaire valeur, Noeud racineCourante, String e) {

		if (racineCourante == null) {
			return racineCourante;
		} else if (racineCourante.donnee.compareTo(valeur) > 0) {
			racineCourante.gauche = majAnneeValeur(valeur, racineCourante.gauche,e);
			return racineCourante;
		}else if (racineCourante.donnee.compareTo(valeur) == 0){
			if (racineCourante.donnee.equals(valeur)) {
				racineCourante.donnee.setAnnee(e);
				return racineCourante;
				
			}else racineCourante.droit = majAnneeValeur(valeur, racineCourante.droit,e);
			return racineCourante;
				
		} else {racineCourante.droit = majAnneeValeur(valeur, racineCourante.droit,e);
		return racineCourante;
			

		}

	}
	public void majDepartementValeur(Noeud a, String e) {

		try {
			this.racine = majDepartementValeur(a, racine, e);
		} catch (Exception e2) {
			System.out.println("il 'y a pas de personne recherchée avec ce nom la");
		}
		

	}
	public Noeud majDepartementValeur(Noeud a, Noeud racineCourante, String e) {

		if (racineCourante == null) {
			return racineCourante;
		} else if (racineCourante.donnee.compareTo(a.donnee) > 0) {
			racineCourante.gauche = majDepartementValeur(a, racineCourante.gauche,e);
			return racineCourante;
		}else if (racineCourante.donnee.compareTo(a.donnee) == 0){
			if (racineCourante.donnee.equals(a.donnee)) {
				racineCourante.donnee.setDepartement(e);
				return racineCourante;
				
			}else racineCourante.droit = majDepartementValeur(a, racineCourante.droit,e);
			return racineCourante;
				
		} else {racineCourante.droit = majDepartementValeur(a, racineCourante.droit,e);
		return racineCourante;
			

		}

	}
	public int hauteurArbre() {

		return hauteurSousArbre(this.racine);

	}

	private int hauteurSousArbre(Noeud racineCourante) {
		if (racineCourante == null) {
			return 0;
		} else {
			return 1 + Math.max(hauteurSousArbre(racineCourante.gauche), hauteurSousArbre(racineCourante.droit));
		}
	}

	public boolean presence(Stagiaire valeur) {

		return presence(valeur, this.racine);

	}

	private boolean presence(Stagiaire valeur, Noeud racineCourante) {

		if (racineCourante == null) {
			return false;
		}

		else if (racineCourante.donnee.equals(valeur)) { /// ou racineCourante.donnee.compareTo(valeur) ==0
			return true;
		} else if (racineCourante.donnee.compareTo(valeur) > 0) {
			return presence(valeur, racineCourante.gauche);

		} else {
			return presence(valeur, racineCourante.droit);
		}

	}
	public Noeud recherche(String nom) {

		return recherche(nom, this.racine);

	}

	private Noeud recherche(String nom, Noeud racineCourante) {

		if (racineCourante == null) {
			return null;
		}

		else if (racineCourante.donnee.getNom().compareTo(nom)==0) { /// ou racineCourante.donnee.compareTo(valeur) ==0
			return racineCourante;
		} else if (racineCourante.donnee.getNom().compareTo(nom) > 0) {
			return recherche(nom, racineCourante.gauche);

		} else {
			return recherche(nom, racineCourante.droit);
		}

	}
//	public Noeud rechercheIndice(int i) {
//
//		return rechercheIndice(i, this.racine);
//
//	}
//
//	private Noeud rechercheIndice(int i, Noeud racineCourante) {
//
//		if (racineCourante.donnee.getIndice()==i) { /// ou racineCourante.donnee.compareTo(valeur) ==0
//			return racineCourante;
//		
//		}else{
//			if(racineCourante.gauche != null) {
//			racineCourante.gauche = rechercheIndice(i, racineCourante.gauche);
//			
//			}
//			if(racineCourante.droit != null) {
//				racineCourante.droit.rechercheIndice(i);
//			}
//
//		}
//		return null;
//
//
////	}
//	public Noeud rechercheIndice(int i) {
//		return this.racine.rechercheIndice(i);
//	}
//	

	public Noeud successeur(Noeud racineCourante) {

		if (racineCourante.droit != null) {
			racineCourante = racineCourante.droit;
			while (racineCourante.gauche != null) {
				racineCourante = racineCourante.gauche;
			}
			return racineCourante;
		} else
			return null;

	}

	private Noeud supprimerValeur(Stagiaire valeur, Noeud racineCourante) {

		if (racineCourante == null) {
			return null;
		}

		else if (racineCourante.donnee.equals(valeur)) { /// ou racineCourante.donnee.compareTo(valeur) ==0
			return suppressionRacine(racineCourante);

		} else if (racineCourante.donnee.compareTo(valeur) > 0) {
			racineCourante.gauche = supprimerValeur(valeur, racineCourante.gauche);
			return racineCourante;

		} else {
			racineCourante.droit = supprimerValeur(valeur, racineCourante.droit);
			return racineCourante;
		}

	}

	public Noeud suppressionRacine(Stagiaire valeur) {

		return suppressionRacine(this.racine);

	}

	public Noeud suppressionRacine(Noeud racineCourante) {

		if (racineCourante.gauche == null && racineCourante.droit == null) { // si la valeur est une feuille on
			// la supprime juste
			racineCourante = null;
			return racineCourante;
		} else if (racineCourante.gauche != null && racineCourante.droit == null) { // si il n'a q'un fils gauche

			racineCourante = racineCourante.gauche;
			return racineCourante;

		} else if (racineCourante.gauche == null && racineCourante.droit != null) { // si il n'y a qu'une seule racine
																					// droite
			racineCourante = racineCourante.droit;
			return racineCourante;

		} else {// Si la valeur et qu'il a un successeur n'est pas une feuille on va la
				// remplacer par son sucesseur
			if (this.successeur(racineCourante).droit == null) {
				racineCourante.donnee = successeur(racineCourante).donnee; // on remplace la valeur du noeud
																			// courant par la valeur du
																			// successeur
				racineCourante = racineCourante.droit;
				while (racineCourante.gauche != null) {
				}
				racineCourante = null;

				return racineCourante;

			} else {
				racineCourante.donnee = successeur(racineCourante).donnee; // on remplace la valeur du noeud
				// courant par la valeur du
				// successeur
				racineCourante = racineCourante.droit;
				
				Noeud a = racineCourante.droit;
				racineCourante =a;
				return racineCourante;
			}
		}

	}

	public Noeud suppression(Stagiaire valeur) {

		return suppression(valeur, this.racine);

	}

	public Noeud suppression(Stagiaire valeur, Noeud racineCourante) {

		if (this.presence(valeur)) { // on vérifie d'abord que la valeur a supprimer existe
			if (racineCourante == null) {
				return null;

			} else if ((racineCourante.donnee.compareTo(valeur) == 0)) { // si la valeur vaut la valeur a supprimer :

				this.suppressionRacine(valeur);

			} else if (racineCourante.donnee.compareTo(valeur) > 0) {
				racineCourante.gauche = suppression(valeur, racineCourante.gauche);
				return racineCourante;
			} else {
				racineCourante.droit = suppression(valeur, racineCourante.droit);
				return racineCourante;

			}

		}
		return racineCourante;
	}
	public void ajouterIndice() {
		this.racine.ajouterIndice();
	}

	public void afficherArbre() {
		this.racine.afficherNoeudInfixe();
		//System.out.println(this.racine.str);
	}
	
	public void treeItemsToList(List<Noeud> list ){
		treeItemsToList(this.racine,list );
	}
	
	public void treeItemsToList(Noeud node, List<Noeud> list ){
        if(node == null){
            return;
        }
        treeItemsToList(node.gauche, list);
        treeItemsToList(node.doublon, list);
        list.add(node);
        treeItemsToList(node.droit, list);
    }

//	@Override
//	public String toString() {
//	return this.racine.afficherNoeudInfixe1();
//	}


}

