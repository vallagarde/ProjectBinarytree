package fr.isika.cda13.projet1.projet1.model;

import java.util.Objects;

public class Stagiaire implements Comparable<Stagiaire>{
	
	private String nom;
	private String prenom;
	private String departement;
	private String promotion;
	private String annee;
	private static int index=0;
	private int indice;
	
	public Stagiaire() {
		
		this.indice=index;
		index++;
		
		
	}
	public Stagiaire(String nom, String prenom, String departement, String promotion, String annee) {
		this.nom= nom;
		this.prenom=prenom;
		this.departement=departement;
		this.promotion=promotion;
		this.annee=annee;
		index++;
		this.indice=index;
		
	}
	
	public static int getIndex() {
		return index;
	}
	public static void setIndex(int index) {
		Stagiaire.index = index;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void addNom(String nom) {
		this.nom=nom;
	}
	public void addPrenom(String prenom) {
		this.prenom=prenom;
	}

	public void addDepartement(String departement) {
		this.departement=departement;
	}

	public void addPromotion(String promotion) {
		this.promotion=promotion;
	}
	public void addAnnee(String annee) {
		this.annee=annee;
	}
	public void addPromotion2(String promotion) {
		this.promotion=promotion;
	}
	public void addAnnee2(String annee) {
		this.annee=annee;
	}
	@Override
	public int compareTo(Stagiaire o) {
		return this.nom.compareTo(o.getNom());
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stagiaire [nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append(", departement=");
		builder.append(departement);
		builder.append(", promotion=");
		builder.append(promotion);
		builder.append(", annee=");
		builder.append(annee);
		builder.append(", indice=");
		builder.append(indice);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		return Objects.hash(annee, departement, nom, prenom, promotion);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stagiaire other = (Stagiaire) obj;
		return Objects.equals(annee, other.annee) && Objects.equals(departement, other.departement)
				&& Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom)
				&& Objects.equals(promotion, other.promotion);
	}
	public boolean equalsPersonne(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stagiaire other = (Stagiaire) obj;
		return  Objects.equals(departement, other.departement) && Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom);
	}
	


	

}
