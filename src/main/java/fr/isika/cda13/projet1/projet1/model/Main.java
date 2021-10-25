package fr.isika.cda13.projet1.projet1.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws MalformedURLException, IOException {

		// Je crée un fichier binaire que je range dans un objet fichierbinaire
		FichierBinaire file = new FichierBinaire("Documents/STAGIAIRES.DON", "Documents/stagiaires2.bin");

//		RandomAccessFile raf = new RandomAccessFile("Documents/stagiaires2.bin", "r");
//
//		Arbre stagiaires = new Arbre();
//
//		raf.length();
//
//		for (int j = 0; j < 1378; j++) {
//
//			Stagiaire a = new Stagiaire();
//
//			String nom = "";
//			String prenom = "";
//			String departement = "";
//			String promotion = "";
//			String annee = "";
//			int indice = 0;
//			int indiceG = 0;
//			int indiceD = 0;
//
//			raf.seek(92 * j * 2);
//			for (int i = 0; i < 30; i++) {
//
//				nom += raf.readChar();
//				a.addNom(nom);
//			}
//			raf.seek(92 * j * 2 + 30 * 2);
//
//			for (int i = 0; i < 30; i++) {
//
//				prenom += raf.readChar();
//				a.addPrenom(prenom);
//			}
//			raf.seek(92 * j * 2 + 60 * 2);
//			for (int i = 0; i < 4; i++) {
//
//				departement += raf.readChar();
//				a.addDepartement(departement);
//			}
//			raf.seek(92 * j * 2 + 64 * 2);
//			for (int i = 0; i < 10; i++) {
//
//				promotion += raf.readChar();
//				a.addPromotion(promotion);
//			}
//			raf.seek(92 * j * 2 + 74 * 2);
//			for (int i = 0; i < 10; i++) {
//
//				annee += raf.readChar();
//				a.addAnnee(annee);
//			}
//			raf.seek(92 * j * 2 + 84 * 2);
//
//			indice = raf.readInt();
//			a.setIndice(indice);
//			a.setIndex(indice);
//
//			raf.seek(92 * j * 2 + 86 * 2);
//
//			indiceG = raf.readInt();
//
//			raf.seek(92 * j * 2 + 88 * 2);
//
//			indiceD = raf.readInt();
//
//			stagiaires.ajouterValeur(a);
//
//		}
//		raf.close();

		//stagiaires.afficherArbre();
		List<StringBuilder> listeStagiaire =new ArrayList<StringBuilder>();
		System.out.println(file.affichagefBinaire("ZRELL" ));
		file.majPrenomValeur(1189, "sous les stromboscope") ;
		System.out.println(file.affichagefBinaire("ZRELLI"));
		file.suppression(file.rechercheIndice("ZOUAOUI"));
		file.affichageInfixe(0, listeStagiaire);
		System.out.println(listeStagiaire);
	
	
	}
}
