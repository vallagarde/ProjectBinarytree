package fr.isika.cda13.projet1.projet1.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Noeud {
	
	
	// ajouter parent doublon

	public Stagiaire donnee;

	public Noeud gauche;

	public Noeud droit;

	public Noeud doublon;

	public String str;

	public int indiceParentD;
	public int indiceParentG;
	public int indiceGauche;
	public int indiceDroite;
	public int indiceDoublon;

	// COnstructeur

	public Noeud(Stagiaire t) {
		this.donnee = t;
		this.gauche = null;
		this.droit = null;

	}

	public Noeud(Stagiaire t, Noeud gauche, Noeud droit) {
		this.donnee = t;
		this.gauche = gauche;
		this.droit = droit;

	}
	



	public int getIndiceParentD() {
		return indiceParentD;
	}

	public void setIndiceParentD(int indiceParentD) {
		this.indiceParentD = indiceParentD;
	}

	public int getIndiceParentG() {
		return indiceParentG;
	}

	public void setIndiceParentG(int indiceParentG) {
		this.indiceParentG = indiceParentG;
	}

	public Noeud getGauche() {
		return gauche;
	}

	public void setGauche(Noeud gauche) {
		this.gauche = gauche;
	}

	public Noeud getDroit() {
		return droit;
	}

	public void setDroit(Noeud droit) {
		this.droit = droit;
	}

	public void afficherNoeudPrefixe() {
		System.out.println(this.donnee.toString());
		if (this.gauche != null)
			this.gauche.afficherNoeudPrefixe();
		if (this.droit != null)
			this.droit.afficherNoeudPrefixe();
	}

	public void afficherNoeudInfixe() {
		
		if (this.gauche != null)
			this.gauche.afficherNoeudInfixe();
		if (this.doublon != null)
			this.doublon.afficherNoeudInfixe();
		//str += this.donnee.toString()+"\n";
		System.out.println(this.toString());
		if (this.droit != null)
			this.droit.afficherNoeudInfixe();
	}



//	public Noeud rechercheIndice(int i) {
//
//		return rechercheIndice2(i, this);
//
//	}
//
//	private Noeud rechercheIndice(int i, Noeud racineCourante) {
//
//		if (racineCourante.donnee.getIndice() == i) { /// ou racineCourante.donnee.compareTo(valeur) ==0
//			return racineCourante;
//
//		} else if (racineCourante.donnee.getIndice() != i) {
//			if (racineCourante.gauche != null) {
//				racineCourante = racineCourante.gauche;
//				racineCourante.rechercheIndice(i);
//
//			}
//			if (racineCourante.droit != null) {
//				racineCourante = racineCourante.droit;
//				racineCourante.rechercheIndice(i);
//			}
//
//		}
//		return racineCourante;
//
//	}

//	private Noeud rechercheIndice2(int i, Noeud racineCourante) {
//
//		
//		if (racineCourante.donnee.getIndice() == i) { /// ou racineCourante.donnee.compareTo(valeur) ==0
//			return racineCourante;
//		} else {
//			if (racineCourante.gauche != null && racineCourante.droit != null) {
//				racGauche = racineCourante.gauche;
//				racDroite = racineCourante.droit;
//				racGauche.rechercheIndice(i);
//				racDroite.rechercheIndice(i);
//			} else if (racineCourante.gauche != null &&racineCourante.droit == null) {
//				racGauche = racineCourante.gauche;
//				racGauche.rechercheIndice(i);
//
//			} else if (racineCourante.droit != null && racineCourante.gauche == null) {
//				racDroite = racineCourante.droit;
//				racDroite.rechercheIndice(i);
//
//			}
//		}
//		
//		if (racineCourante ==null) {
//			return null;
//		}
//	
//
//	}

	public int hauteur() {

		if ((this.getGauche() == null && this.getDroit() == null)) {
			return 1;
		} else if (this.getGauche() != null) {
			return 1 + this.getGauche().hauteur();
		} else {
			return 1 + this.getDroit().hauteur();
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Noeud [donnee=");
		builder.append(donnee);
		builder.append(", gauche=");
		builder.append(indiceGauche);
		builder.append(", droit=");
		builder.append(indiceDroite);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(donnee);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Noeud other = (Noeud) obj;
		return Objects.equals(donnee, other.donnee);
	}

	public void ajouterIndice() {
		
		
		
		if (this.gauche != null) {
			this.indiceGauche = this.gauche.donnee.getIndice();
			this.gauche.setIndiceParentG(this.donnee.getIndice());
			this.gauche.ajouterIndice();
		}
		if (this.doublon != null) {
			this.indiceDoublon = this.doublon.donnee.getIndice();
			this.doublon.ajouterIndice();

		}
		if (this.droit != null) {
			this.indiceDroite = this.droit.donnee.getIndice();
			this.droit.setIndiceParentD(this.donnee.getIndice());
			this.droit.ajouterIndice();

	}
	}
	
}
