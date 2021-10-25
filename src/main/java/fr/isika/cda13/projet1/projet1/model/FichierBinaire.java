package fr.isika.cda13.projet1.projet1.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FichierBinaire {

	File fichierBinaire;
	String nomDuFichierAImporter;
	String nomDuFichierAExporter;

	public FichierBinaire(String nomDuFichierAImporter, String nomDuFichierAExporter) {
		// ici j'ai crée le dossier

		// File rep = new File("Documents");
		// rep.mkdir();

		File file = new File(nomDuFichierAImporter);

		Scanner sr;
		try {
			sr = new Scanner(new BufferedReader(new FileReader(file)));
		} catch (Exception e) {
			sr = null;
		}

		Arbre stagiaires = new Arbre();

		for (int j = 0; j < 1378; j++) {
			Stagiaire a = new Stagiaire();

			for (int i = 0; i < 6; i++) {
				if (i == 0) {
					a.addNom(sr.nextLine().toUpperCase());
				} else if (i == 1) {
					a.addPrenom(sr.nextLine());
				} else if (i == 2) {
					a.addDepartement(sr.nextLine());
				} else if (i == 3) {
					a.addPromotion(sr.nextLine());
				} else if (i == 4) {
					a.addAnnee(sr.nextLine());
				} else if (i == 5) {
					sr.nextLine();
				}
			}
			stagiaires.ajouterValeur(a);

		}
		// Noeud ababa = stagiaires.recherche("ZRELLI");
		// stagiaires.majDepartementValeur(ababa, "Yaka");
		stagiaires.ajouterIndice();
		stagiaires.afficherArbre();

		// On crée une liste de stagiaire pour rechercher à l'intérieur les données:
		List<Noeud> listeStag = new ArrayList<Noeud>();
		stagiaires.treeItemsToList(listeStag);
		// System.out.println(listeStag);
		listeStag.size();

		// on crée une liste de stagiaire qui est rangée dans le bon ordre (ordre
		// d'indice):
		List<Noeud> listeStagRang = new ArrayList<Noeud>();
		for (int i = 0; i < listeStag.size(); i++) {
			for (int j = 0; j < listeStag.size(); j++) {
				if (listeStag.get(j).donnee.getIndice() == i) {
					Noeud interm = listeStag.get(j);
					listeStagRang.add(interm);
				}

			}

		}

		// System.out.println(listeStagRang);
		// System.out.println(listeStag);

		fichierBinaire = new File(nomDuFichierAExporter);

		// chaque stagiaire fait 96 charactères
		// Si on veut update le prenom : 96*indice+30
		// si on veut update le departement : 96*indice +60
		// si on veut update la promotion : 96*indice +64
		// si on veut update l'annee : 96*indice +74
		// Si on veut update l'indice : 96*indice +84
		// Si on veut update l'indice gauche : 96*indice +86
		// Si on veut update l'indice droit : 96*indice +88
		// Si on veut update l'indice doublon : 96*indice +90
		// SI on veut update l'indice ParentG : 96*indice +92
		// SI on veut update l'indice ParentG : 96*indice +94

		try {
			fichierBinaire.createNewFile();
			RandomAccessFile raf = new RandomAccessFile(nomDuFichierAExporter, "rw");

			for (int i = 0; i < 1378; i++) {

				Noeud item = listeStagRang.get(i);
				String chaineNom = item.donnee.getNom();
				// On ecrit le nom du stagiaire:
				for (int j = item.donnee.getNom().length(); j < 30; j++) {
					chaineNom += " ";
				}
				raf.writeChars(chaineNom);

				String chainePrenom = item.donnee.getPrenom();
				// On ecrit le nom du stagiaire:
				for (int j = item.donnee.getPrenom().length(); j < 30; j++) {
					chainePrenom += " ";
				}
				raf.writeChars(chainePrenom);

				String chaineDepartement = item.donnee.getDepartement();
				// On ecrit le nom du stagiaire:
				for (int j = item.donnee.getDepartement().length(); j < 4; j++) {
					chaineDepartement += " ";
				}
				raf.writeChars(chaineDepartement);

				String chainePromotion = item.donnee.getPromotion();
				// On ecrit le nom du stagiaire:
				for (int j = item.donnee.getPromotion().length(); j < 10; j++) {
					chainePromotion += " ";
				}
				raf.writeChars(chainePromotion);

				String chaineAnnee = item.donnee.getAnnee();
				// On ecrit le nom du stagiaire:
				for (int j = item.donnee.getAnnee().length(); j < 10; j++) {
					chaineAnnee += " ";
				}
				raf.writeChars(chaineAnnee);

				int chaineIndex = item.donnee.getIndice();

				raf.writeInt(chaineIndex);

				int chaineIndexG = item.indiceGauche;
				raf.writeInt(chaineIndexG);

				int chaineIndexD = item.indiceDroite;
				raf.writeInt(chaineIndexD);

				int chaineIndexDouble = item.indiceDoublon;
				raf.writeInt(chaineIndexDouble);

				int chaineIndexParentG = item.indiceParentG;
				raf.writeInt(chaineIndexParentG);

				int chaineIndexParentD = item.indiceParentD;
				raf.writeInt(chaineIndexParentD);

			}
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// La methode affichage permet d'afficher les informations d'un noeud à un
	// indice donné sous forme de Stringbuilder
	// Elle est utilisée par les autres méthodes qui nécessitent un affichage
	// Elle prend en entrée un indice de noeud
	// Elle donne en sortie un Stringbuilder construit a partir des information de
	// ce noeud

	public StringBuilder affichage(int a) {

		try {
			RandomAccessFile raf = new RandomAccessFile(fichierBinaire, "r");

			// On rentre toutes les infos du stagiaire a chercher
			String nom = "";
			String prenom = "";
			String departement = "";
			String promotion = "";
			String annee = "";

			StringBuilder stagiaireCourant = new StringBuilder();

			if (a == -1) {
				raf.close();
				stagiaireCourant.append("Ce nom n'est pas dans la liste");
				return stagiaireCourant;

			}
			if (a == -2) {
				raf.close();
				stagiaireCourant.append("Le fichier n'a pas été lu dans successeur");
				return stagiaireCourant;
			}
			if (a == -3) {
				raf.close();
				stagiaireCourant.append("Le fichier n'a pas été lu dans successeur1");
				return stagiaireCourant;
			}

			raf.seek(96 * a * 2);
			for (int i = 0; i < 30; i++) {

				nom += raf.readChar();
			}
			raf.seek(96 * a * 2 + 30 * 2);

			for (int i = 0; i < 30; i++) {

				prenom += raf.readChar();
			}
			raf.seek(96 * a * 2 + 60 * 2);
			for (int i = 0; i < 4; i++) {

				departement += raf.readChar();
			}
			raf.seek(96 * a * 2 + 64 * 2);
			for (int i = 0; i < 10; i++) {

				promotion += raf.readChar();

			}
			raf.seek(96 * a * 2 + 74 * 2);
			for (int i = 0; i < 10; i++) {

				annee += raf.readChar();
			}
			raf.seek(96 * a * 2 + 86 * 2);

			raf.close();

			// On construit le stagiaire courant si on l'a trouvé
			stagiaireCourant.append(nom + "\n");
			stagiaireCourant.append(prenom + "\n");
			stagiaireCourant.append(departement + "\n");
			stagiaireCourant.append(promotion + "\n");
			stagiaireCourant.append(annee + "\n");/// ou racineCourante.donnee.compareTo(valeur) ==0
			return stagiaireCourant;
		} catch (Exception e) {

			StringBuilder stagiaireCourant = new StringBuilder();
			stagiaireCourant.append("Le fichier n'est pas accessible dans affichage");
			return stagiaireCourant;

		}

	}

	// recherche générique par le nom qui renvoie son indice dans la liste
	// Cette recherche permet uniquement de trouver l'indice du noeud recherché par
	// le nom de ce dernier
	// C'est la recherche normale du nom de l'arbre binaire.
	// elle ne permet dans aucun cas d'afficher directement un noeud
	// Il faut pour ca l'associer à la méthode afficher(int)

	public int rechercheIndice(String nomachercher) {
		return rechercheIndice(nomachercher, 0);
	}

	public int rechercheIndice(String nomachercher, int a) {

		try {
			RandomAccessFile raf = new RandomAccessFile(fichierBinaire, "r");

			// On rentre toutes les infos du stagiaire a chercher
			String nom = "";

			int indice = 0;
			int filsG = 0;
			int filsD = 0;

			try {
				raf.seek(96 * a * 2);
				for (int i = 0; i < 30; i++) {

					nom += raf.readChar();
				}

				raf.seek(96 * a * 2 + 84 * 2);

				indice = raf.readInt();

				raf.seek(96 * a * 2 + 86 * 2);

				filsG = raf.readInt();

				raf.seek(96 * a * 2 + 88 * 2);

				filsD = raf.readInt();

				raf.seek(96 * a * 2 + 90 * 2);

				raf.close();

				// On adapte le nom a charcher au format du nom dans la liste binaire
				String chaineNom = nomachercher;
				for (int j = nomachercher.length(); j < 30; j++) {
					chaineNom += " ";
				}

				if (nom.compareTo(chaineNom) == 0) {

					return indice;

				} else if (filsG == 0 && filsD == 0) {
					return -1;

				} else if (nom.compareTo(chaineNom) > 0) {
					return rechercheIndice(chaineNom, filsG);

				} else {
					return rechercheIndice(chaineNom, filsD);
				}

			} catch (Exception e) {
				StringBuilder stagiaireCourant = new StringBuilder();
				stagiaireCourant.append("La personne cherchée n'existe pas");
				return -3;

			}
		} catch (Exception e) {
			StringBuilder stagiaireCourant = new StringBuilder();
			stagiaireCourant.append("Le fichier dans lequel nous devons chercher n'existe pas");
			return -2;

		}

	}

	// Recherche binaire utilise le resultat de la recherche générique pour
	// récupérer les informations du stagiaire
	// Elle permet notamment de récupérer tous les doublons

	public StringBuilder affichagefBinaire(String nomachercher) {
		return this.affichagefBinaire(nomachercher, rechercheIndice(nomachercher));
	}

	public StringBuilder affichagefBinaire(String nomachercher, int a) {

		try {
			RandomAccessFile raf = new RandomAccessFile(fichierBinaire, "r");

			int filsDouble = 0;
			raf.seek(96 * a * 2 + 90 * 2);

			filsDouble = raf.readInt();
			raf.close();

			if (filsDouble != 0) { // Si le stagiaire a un doublon on le rajoute a la liste d'impression
				return affichage(a).append(affichagefBinaire(nomachercher, filsDouble));

			} else { // Si le stagiaire n'a pas de doublons on l'imprime directement
				return affichage(a);
			}
		} catch (Exception e) {
			StringBuilder stagiaireCourant = new StringBuilder();
			stagiaireCourant.append("Le fichier n'est pas accessible dans recherchefBinaire");
			return stagiaireCourant;
		}

	}

	public void majPrenomValeur(int a, String e) {

		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(fichierBinaire, "rw");

			raf.seek(96 * a * 2 + 30 * 2);

			String chainePrenom = e;
			for (int j = e.length(); j < 30; j++) {
				chainePrenom += " ";
			}
			raf.writeChars(chainePrenom);

		} catch (Exception e1) {

		}
	}

	// successeur et successeur1 permettent de trouver le successeur
	// successeur s'assure que le noeud que l'on regarde n'a pas de doublon et a
	// bien un fils droit
	// sucesseur deux va alors rechercher le dernier fils gauche de la lignée du
	// premier fils droit.
	// On retourne alors l'indice du noeud successeur qui peut etre lu par toutes
	// les fonctions prenant en entrée un indice

	public int successeur(int a) { // Si le fichier n'est pas lu dans ce successeur renvoie erreur -2 pour
									// affichage

		try {
			RandomAccessFile raf = new RandomAccessFile(fichierBinaire, "r");

			// On rentre toutes les infos du stagiaire a chercher

			int filsD = 0;
			int filsDouble = 0;

			raf.seek(96 * a * 2 + 88 * 2);

			filsD = raf.readInt();

			raf.seek(96 * a * 2 + 90 * 2);

			filsDouble = raf.readInt();

			raf.close();

			if (filsDouble != 0) {
				return filsDouble;

			}

			if (filsD != 0) {
				return this.successeur1(filsD);

			} else
				return -1;

		} catch (Exception e) {
			return -2;
		}
	}

	public int successeur1(int a) { // Si le fichier n'est pas lu dans ce successeur renvoie erreur -3 pour
									// affichage

		try {
			RandomAccessFile raf = new RandomAccessFile(fichierBinaire, "r");

			// On rentre toutes les infos du stagiaire a chercher

			int indice = 0;
			int filsG = 0;

			raf.seek(96 * a * 2 + 84 * 2);

			indice = raf.readInt();

			raf.seek(96 * a * 2 + 86 * 2);

			filsG = raf.readInt();

			raf.close();

			if (filsG != 0) {
				return this.successeur1(filsG);

			} else {
				return indice;
			}

		} catch (Exception e) {
			return -3;
		}
	}

	//

	public void affichageInfixe(int a, List<StringBuilder> list) {

		try {
			RandomAccessFile raf = new RandomAccessFile(fichierBinaire, "r");

			// On rentre toutes les infos du stagiaire a chercher

			String nom = "";
			int filsD = 0;
			int filsG = 0;
			// int indice =0;

			raf.seek(96 * a * 2);
			for (int i = 0; i < 30; i++) {

				nom += raf.readChar();
			}

			raf.seek(96 * a * 2 + 84 * 2);

			// indice = raf.readInt();

			raf.seek(96 * a * 2 + 86 * 2);

			filsG = raf.readInt();

			raf.seek(96 * a * 2 + 88 * 2);

			filsD = raf.readInt();

			if (filsG != 0) {
				affichageInfixe(filsG, list);
			}
			list.add(affichagefBinaire(nom));
			if (filsD != 0) {
				affichageInfixe(filsD, list);
			}

			raf.close();
		} catch (Exception e) {

		}

	}

	public void suppression(int a) {

		RandomAccessFile raf;
		RandomAccessFile raf2;
		try {
			raf = new RandomAccessFile(fichierBinaire, "r");
			raf2 = new RandomAccessFile(fichierBinaire, "rw");

			// On rentre toutes les infos du stagiaire a chercher

			String nom = "";
			int filsD = 0;
			int filsG = 0;
			int filsDoublon;
			int parentG = 0;
			int parentD = 0;
			// int indice =0;

			raf.seek(96 * a * 2);
			for (int i = 0; i < 30; i++) {

				nom += raf.readChar();
			}

			raf.seek(96 * a * 2 + 84 * 2);

			// indice = raf.readInt();

			raf.seek(96 * a * 2 + 86 * 2);

			filsG = raf.readInt();

			raf.seek(96 * a * 2 + 88 * 2);

			filsD = raf.readInt();

			raf.seek(96 * a * 2 + 90 * 2);

			filsDoublon = raf.readInt();

			raf.seek(96 * a * 2 + 92 * 2);

			parentG = raf.readInt();

			raf.seek(96 * a * 2 + 94 * 2);

			parentD = raf.readInt();

			if (filsDoublon != 0) {
				if (parentG != 0) {
					raf2.seek(96 * parentG * 2 + 86 * 2);
					raf2.writeInt(filsDoublon);
				} else {
					raf2.seek(96 * parentD * 2 + 88 * 2);
					raf.writeInt(filsDoublon);
				}

			} else if (filsD == 0 && filsG == 0) { // Si le noeud a supprimer est une feuille, on supprime son
													// réferencement
				// dans son noeud parent
				if (parentG != 0) {
					raf2.seek(96 * parentG * 2 + 86 * 2);
					raf2.writeInt(0);
				} else {
					raf2.seek(96 * parentD * 2 + 88 * 2);
					raf.writeInt(0);
				}
			} else if (filsD != 0 && filsG == 0) { // Si le noued a supprimer n'aqu'un fils droit:
				if (parentG != 0) {
					raf2.seek(96 * parentG * 2 + 86 * 2);
					raf2.writeInt(filsD);
				} else {
					raf2.seek(96 * parentD * 2 + 88 * 2);
					raf2.writeInt(filsD);
				}
			} else if (filsG != 0 && filsD == 0) { // Si le noued a supprimer n'aqu'un fils gauche:
				if (parentG != 0) {
					raf2.seek(96 * parentG * 2 + 86 * 2);
					raf2.writeInt(filsG);
				} else {
					raf2.seek(96 * parentD * 2 + 88 * 2);
					raf2.writeInt(filsG);
				}
			} else if (filsG != 0 && filsD != 0) { // Si le noued a supprimer n'aqu'un fils gauche:
				if (parentG != 0) {
					raf2.seek(96 * parentG * 2 + 86 * 2);
					raf2.writeInt(successeur(a));
					suppression(successeur(a));
				} else {
					raf2.seek(96 * parentD * 2 + 88 * 2);
					raf2.writeInt(successeur(a));
					suppression(successeur(a));
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getIndiceMax() {

		return (int) ((fichierBinaire.length()) / 192);

	}

	public void ajouterStagiaire(String nom, String prenom, String departement, String promotion, String annee, int a) {

		try {
			RandomAccessFile raf = new RandomAccessFile(nomDuFichierAExporter, "rw");
			int futureParentG =0;
			int futureParentD =0;
			int futureParentDouble =0;
			try {
				RandomAccessFile pere = new RandomAccessFile(nomDuFichierAExporter, "r");

				String nom1 = "";

				int indice = 0;
				int filsG = 0;
				int filsD = 0;
				int doublon =0;
				
				

				pere.seek(96 * a * 2);
				for (int i = 0; i < 30; i++) {

					nom1 += pere.readChar();
				}

				pere.seek(96 * a * 2 + 84 * 2);

				indice = pere.readInt();

				pere.seek(96 * a * 2 + 86 * 2);

				filsG = pere.readInt();

				pere.seek(96 * a * 2 + 88 * 2);

				filsD = pere.readInt();

				pere.seek(96 * a * 2 + 90 * 2);
				
				doublon = pere.readInt();

				pere.close();

				// On adapte le nom a charcher au format du nom dans la liste binaire
				String chaineNom = nom;
				for (int j = nom.length(); j < 30; j++) {
					chaineNom += " ";
				}

				if (nom1.compareTo(chaineNom) == 0) {
					
					if (doublon!=0) {
						ajouterStagiaire( nom, prenom, departement, promotion, annee, doublon);
						
					}else {
						raf.seek(96 * a * 2 + 90 * 2);
						raf.writeInt((this.getIndiceMax())+1);
						futureParentDouble =indice;

					}

				

				} else if (filsG == 0 && filsD == 0) {
					if(nom1.compareTo(chaineNom) > 0) {
						raf.seek(96 * a * 2 + 88 * 2);
						raf.writeInt((this.getIndiceMax())+1);
						futureParentG = indice;
						
					}else{
						raf.seek(96 * a * 2 + 90 * 2);
						raf.writeInt((this.getIndiceMax())+1);
						futureParentD =indice;
					}

				} else if (nom1.compareTo(chaineNom) > 0) {
					ajouterStagiaire( nom, prenom, departement, promotion, annee,filsG);

				} else {
					ajouterStagiaire( nom, prenom, departement, promotion, annee,filsD);
				}

			} catch (Exception e) {

			}

			raf.seek(this.getIndiceMax() + 1 * 192);

			String chaineNom = nom;
			// On ecrit le nom du stagiaire:
			for (int j = nom.length(); j < 30; j++) {
				chaineNom += " ";
			}
			raf.writeChars(chaineNom);

			String chainePrenom = prenom;
			// On ecrit le nom du stagiaire:
			for (int j = prenom.length(); j < 30; j++) {
				chainePrenom += " ";
			}
			raf.writeChars(chainePrenom);

			String chaineDepartement = departement;
			// On ecrit le nom du stagiaire:
			for (int j = departement.length(); j < 4; j++) {
				chaineDepartement += " ";
			}
			raf.writeChars(chaineDepartement);

			String chainePromotion = promotion;
			// On ecrit le nom du stagiaire:
			for (int j = promotion.length(); j < 10; j++) {
				chainePromotion += " ";
			}
			raf.writeChars(chainePromotion);

			String chaineAnnee = annee;
			// On ecrit le nom du stagiaire:
			for (int j = annee.length(); j < 10; j++) {
				chaineAnnee += " ";
			}
			raf.writeChars(chaineAnnee);

			int chaineIndex = (this.getIndiceMax()) + 1;

			raf.writeInt(chaineIndex);

			int chaineIndexG = 0;
			raf.writeInt(chaineIndexG);

			int chaineIndexD = 0;
			raf.writeInt(chaineIndexD);

			int chaineIndexDouble = 0;
			raf.writeInt(chaineIndexDouble);
			
			if (futureParentG==0 && futureParentD!=0 ) {
				
				int chaineIndexParentG = 0;
				raf.writeInt(chaineIndexParentG);

				int chaineIndexParentD = futureParentD;
				raf.writeInt(chaineIndexParentD);
				
				
			}else if (futureParentD==0 && futureParentG!=0){
				
				int chaineIndexParentG = futureParentG;
				raf.writeInt(chaineIndexParentG);

				int chaineIndexParentD = 0;
				raf.writeInt(chaineIndexParentD);
			}else {
				int chaineIndexParentG =  0;
				raf.writeInt(chaineIndexParentG);

				int chaineIndexParentD = 0;
				raf.writeInt(chaineIndexParentD);
			}
				
			

			

		} catch (Exception e) {

		}

	}
}
