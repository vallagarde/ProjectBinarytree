package fr.isika.cda13.projet1.projet1.view;

package fr.isika.cda13.projet1.projet1.view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.text.TabableView;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import fr.isika.cda13.projet1.projet1.model.Departement;
import fr.isika.cda13.projet1.projet1.model.DepartementDao;
import fr.isika.cda13.projet1.projet1.model.FichierBinaire;
import fr.isika.cda13.projet1.projet1.model.FirstPdf;
import fr.isika.cda13.projet1.projet1.model.Stagiaire;

/**
 * JavaFX App
 */
public class Login extends Application {

	FichierBinaire file = new FichierBinaire("Documents/STAGIAIRES.DON", "Documents/stagiaires2.bin");

	private BorderPane root = new BorderPane();
	private Scene scene = new Scene(root, 1200, 900);
	private BorderPane root2 = new BorderPane();
	private Scene scene2 = new Scene(root2, 1200, 900);

	@Override
	public void start(Stage mainStage) throws Exception {

// ********************************************SCENE************************************************

		// int indicesupp;
//Background color scene 
		root.setStyle("-fx-background-color:Azure");

//Instantiation d'une vBox1
		VBox vBox1 = new VBox();

//Creation labels & bouttons
		Label connexion = new Label("---CONNEXION---");
		connexion.setStyle("-fx-font-weight: bold");
		Button utilisateur = new Button("Utilisateur");
		Button admin = new Button("Administrateur");

//Ajout des elements dans la vBox1
		vBox1.getChildren().addAll(connexion, utilisateur, admin);

//Mise en page vBox1
		vBox1.setAlignment(Pos.CENTER);
		vBox1.setSpacing(20);
		vBox1.setPadding(new Insets(50, 10, 10, 10));

//Ajout de la vBox1 dans le borderpane
		root.setTop(vBox1);

		mainStage.setScene(scene);
		mainStage.sizeToScene();
		mainStage.setTitle("Annuaire Isika - Bienvenue !");
		mainStage.show();

// ********************************************SCENE2********************************************

////// *******************************Definition du tableview******************************\\\\\\\\\

		List<Stagiaire> liste = new ArrayList<Stagiaire>();

		// Impression de l'annuaire
		file.creationListe2(liste);

		// Creation d'un AnchorPane pour le tableview
		AnchorPane tableau = new AnchorPane();

		// Instanciation d'un stagiaire Dao pour pouvoir acceder aux données de
		// stagiaires

		// Instanciation d'une liste observable avec la methode observableArrayList() de
		// l'objet FXCollections
		// avec pour parametre getStagiaire()
		ObservableList<Stagiaire> observableStagiaires = FXCollections.observableArrayList(liste);

		// Creation d'une tableview
		TableView<Stagiaire> tableview = new TableView<Stagiaire>(observableStagiaires);
		// tableview.setPrefHeight(850);
		tableview.setPrefSize(600, 800);

		// Instanciation des colonnes
		TableColumn<Stagiaire, String> colNom = new TableColumn<>("Nom");
		colNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		colNom.setMinWidth(90);

		TableColumn<Stagiaire, String> colPrenom = new TableColumn<>("Prenom");
		colPrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		colNom.setMinWidth(70);

		TableColumn<Stagiaire, String> colDept = new TableColumn<>("Departement");
		colDept.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));

		TableColumn<Stagiaire, String> colPromo = new TableColumn<>("Promotion");
		colPromo.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promotion"));

		TableColumn<Stagiaire, String> colAnnee = new TableColumn<>("Annee");
		colAnnee.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("annee"));

		// Transmission des données aux differentes colonnes:

		// Affectation des colomnes au tableview
		tableview.getColumns().addAll(colNom, colPrenom, colDept, colPromo, colAnnee);

		// Mise en page des la tableview
		tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// Ajout de la Tableview a l'anchorPane
		tableau.getChildren().add(tableview);

/////////// *******************************Fin de definition du tableview******************************\\\\\\\\\\

/// Label de stockage de l'indice du stagiaire selectionnée:
		Label stockage1 = new Label("stockage");
		stockage1.setVisible(false);

		Label stockage2 = new Label("");
		stockage2.setVisible(false);

//Background color scene 
		root2.setStyle("-fx-background-color:Aliceblue");
		root2.setPadding(new Insets(10, 10, 10, 10));

		VBox vBox2 = new VBox();
		GridPane gridPane2 = new GridPane();

// Creation d'un titre Utilisateur
		Label titre1 = new Label("Administrateur");
		titre1.setStyle("-fx-font-weight: bold");
		VBox vBox7 = new VBox();

//Centrage du titre
		vBox7.getChildren().addAll(titre1);
		root2.setTop(vBox7);

		vBox7.setAlignment(Pos.CENTER);

//Creation d'un boutton retour en arriere
		Button retour = new Button("Retour à la page d'accueil");

		retour.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.setScene(scene);
			}
		});

//Mise en page boutton retour
		gridPane2.setPadding(new Insets(25, 10, 10, 30));
//		gridPane2.setGridLinesVisible(true);

//Creation du libelle Recherche
		Label recherche = new Label("Recherche de stagiaires");
		gridPane2.add(recherche, 1, 0);

//Creation du libl et champ Nom
		Label nom = new Label("Nom   ");
		gridPane2.add(nom, 2, 1);
		TextField nom1 = new TextField();
		gridPane2.add(nom1, 3, 1);

//Creation du lbl et champ Prenom
		Label prenom = new Label("Prenom   ");
		gridPane2.add(prenom, 2, 2);
		TextField prenom1 = new TextField();
		gridPane2.add(prenom1, 3, 2);

//Creation du lbl et champ dept
		Label dept = new Label("Departement   ");
		gridPane2.add(dept, 2, 3);
		TextField dept1 = new TextField();
		// gridPane2.add(dept1, 3, 3);

		/// Initialisation de la liste choiceBox:
		DepartementDao depdao = new DepartementDao();
		ObservableList<Departement> observableDpt = FXCollections.observableArrayList(depdao.departementDao());
		ChoiceBox<Departement> cb2 = new ChoiceBox<>(observableDpt);

		cb2.setConverter(new StringConverter<Departement>() {

			@Override
			public String toString(Departement perso) {
				return String.format("%s %s", perso.numero, perso.nom);
			}

			public Departement fromString(String string) {
				return null;
			}
		});
		ChangeListener<Departement> changeListener = new ChangeListener<Departement>() {

			@Override
			public void changed(ObservableValue<? extends Departement> observable, //
					Departement oldValue, Departement newValue) {
				if (newValue != null) {
					stockage2.setText(newValue.numero);
				}
			}
		};
		cb2.getSelectionModel().selectedItemProperty().addListener(changeListener);

		gridPane2.add(cb2, 3, 3);
		// List<Departement> dep = DepartementDao.departementDao();

//Creation du lbl et champ promotion
		Label promotion = new Label("Formation   ");
		gridPane2.add(promotion, 2, 4);
		TextField promotion1 = new TextField();
		gridPane2.add(promotion1, 3, 4);

//Creation du lbl et champ annee
		Label annee = new Label("Annee   ");
		gridPane2.add(annee, 2, 5);
		TextField annee1 = new TextField();
		gridPane2.add(annee1, 3, 5);

//Creation du boutton recherche stagiaire
		Button Recherche_stagiaire = new Button("Rechercher");
		Button Clear = new Button("Annuaire");
		HBox recherche_s = new HBox();
		recherche_s.getChildren().addAll(Recherche_stagiaire, Clear);
		recherche_s.setAlignment(Pos.CENTER);
		recherche_s.setSpacing(50);

//creation boutons admin
		Button maj = new Button("Mise a jour");
		Button sup = new Button("Supprimer");
		Button ajout = new Button("Ajout");
		Button print = new Button("Imprimer");
		Label test = new Label();

//Encapsulage des bouttons
		VBox vBox3 = new VBox();
		vBox3.getChildren().addAll(maj, sup, ajout, print);

//Alignement boutons
		vBox3.setAlignment(Pos.CENTER);

//marge entre le gridpane et les boutons
		vBox3.setPadding(new Insets(50, 10, 10, 10));

//marge entre les boutons
		vBox3.setSpacing(10);

//Ajout de la gridPane et de la vBox3 dans la vBox2
		vBox2.getChildren().addAll(retour, gridPane2, recherche_s, vBox3, stockage1, stockage2);

//Assignation vBox2 a ma scene2 
		root2.setLeft(vBox2);

//Creation d'une vBox pour mettre dans le coté droit du bordepane le tableview
		VBox Vbox9 = new VBox();
		Vbox9.getChildren().add(tableview);

//Mise en page du tableau dans Vbox root2
		root2.setRight(Vbox9);
		// root2.setRight(tableau);

// On desactive le bouton maj et suppression qui ont beoin d'un stagiaire selectionné pour fonctionner:

		maj.setDisable(true);
		sup.setDisable(true);

		// selection d'un Stagiaire dans la liste

		tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Stagiaire>() {
			@Override
			public void changed(ObservableValue<? extends Stagiaire> observableValue, Stagiaire oldValue,
					Stagiaire newValue) {
				// On affiche les attributs du personnage sélectionné dans le label:

				// observableStagiaires.clear();
				test.setText(newValue.toString());

				stockage1.setText(String.valueOf(newValue.getIndice()));

				// on active les boutons pour qu'ils soient cliquables
				maj.setDisable(false);
				sup.setDisable(false);

			}
		});

/////////////////////////////////////////CONFIGURATION DES BOUTONS//////////////////////////////////////////////////////////////
//--------------------------------------------BOUTON MISE A JOUR------------------------------------------------------------------

		maj.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent Event) {

				final Stage stageMaj = new Stage();

				stageMaj.setTitle("Mettre à jour un stagiaire");
				stageMaj.initOwner(mainStage);

				VBox modifVbox = new VBox(); // anciennement "dialogBox"
				modifVbox.setPadding(new Insets(50, 50, 100, 50)); // Haut - droit - bas - gauche
				modifVbox.setSpacing(10);

				Label linfoModif = new Label("Vous modifiez les informations du stagiaire suivant :");
				linfoModif.setStyle("-fx-font-weight: bold");

				VBox selection = new VBox();
				selection.getChildren().addAll(test);

				Label lnewModif = new Label("Nouvelles informations : ");
				lnewModif.setStyle("-fx-font-weight: bold");

				Label lnomModif = new Label("Nom   ");
				TextField tnomModif = new TextField();

				Label lprenomModif = new Label("Prenom   ");
				TextField tprenomModif = new TextField();

				Label ldptModif = new Label("Departement   ");
				TextField tdptModif = new TextField();

				Label lpromoModif = new Label("Promotion   ");
				TextField tpromoModif = new TextField();

				Label lanModif = new Label("Année   ");
				TextField tanModif = new TextField();

				Button bValid = new Button("Valider les modifications");

				modifVbox.getChildren().addAll(linfoModif, selection, lnewModif, lnomModif, tnomModif, lprenomModif,
						tprenomModif, ldptModif, tdptModif, lpromoModif, tpromoModif, lanModif, tanModif, bValid);

				Scene majScene = new Scene(modifVbox, 500, 650);
				stageMaj.setScene(majScene);
				stageMaj.show();

				bValid.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						int st = Integer.parseInt(stockage1.getText());

						if (tnomModif.getText().equals("")) {

							if (!tprenomModif.getText().equals("")) {
								file.setPrenom(Integer.parseInt(stockage1.getText()), tprenomModif.getText());
							}

							if (!tdptModif.getText().equals("")) {
								file.setDepartement(Integer.parseInt(stockage1.getText()), tdptModif.getText());
							}

							if (!tpromoModif.getText().equals("")) {
								file.setPromotion(Integer.parseInt(stockage1.getText()), tpromoModif.getText());
							}

							if (!tanModif.getText().equals("")) {
								file.setAnnee(Integer.parseInt(stockage1.getText()), tanModif.getText());
							}

						} else {
							String nom = tnomModif.getText();
							String prenom = file.getPrenom(st);
							String dpt = file.getDepartement(st);
							String promo = file.getPromotion(st);
							String an = file.getAnnee(st);

							if (!tprenomModif.getText().equals("")) {
								prenom = tprenomModif.getText();
								for (int j = prenom.length(); j < 30; j++) { // 60
									prenom += " ";
								}

							}
							if (!tdptModif.getText().equals("")) {
								dpt = tdptModif.getText();
								for (int j = dpt.length(); j < 4; j++) { // 60
									dpt += " ";
								}

							}
							if (!tpromoModif.getText().equals("")) {
								promo = tpromoModif.getText();
								for (int j = promo.length(); j < 10; j++) { // 60
									promo += " ";
								}

							}
							if (!tanModif.getText().equals("")) {
								an = tanModif.getText();
								for (int j = an.length(); j < 10; j++) { // 60
									an += " ";
								}

							}
							file.majNom(nom, prenom, dpt, promo, an, Integer.parseInt(stockage1.getText()));

						}

						maj.setDisable(false);
						sup.setDisable(false);
						stageMaj.close();

						final Stage popup_maj = new Stage();
						popup_maj.setTitle("Mise à jour");
						popup_maj.initOwner(mainStage);

						VBox popup_majVbox = new VBox();
						popup_majVbox.setPadding(new Insets(30, 5, 30, 5));
						popup_majVbox.setAlignment(Pos.TOP_CENTER);

						Label conf_maj = new Label("La modification a été effectuée.");
						Button InHere2 = new Button("Ok");

						VBox popup_Vbox3 = new VBox();
						popup_Vbox3.getChildren().addAll(InHere2);
						popup_Vbox3.setAlignment(Pos.TOP_CENTER);
						popup_Vbox3.setPadding(new Insets(30, 5, 30, 5));

						stageMaj.close();
						InHere2.setOnAction(new EventHandler<ActionEvent>() {

							public void handle(ActionEvent Event) {
								liste.clear();
								file.creationListe2(liste);
								observableStagiaires.clear();
								observableStagiaires.addAll(liste);
								maj.setDisable(true);
								sup.setDisable(true);

								popup_maj.close();
							}
						});
						popup_majVbox.getChildren().addAll(conf_maj, popup_Vbox3);

						Scene conf_miseaj = new Scene(popup_majVbox, 300, 150);
						popup_maj.setScene(conf_miseaj);
						popup_maj.show();

					}
				});
			}
		});

//--------------------------------------------BOUTON SUPPRIMER---------------------------------------------------------------
		sup.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent Event) {

				final Stage dialog = new Stage();

				dialog.setTitle("Attention");
				dialog.initOwner(mainStage);
				VBox dialogVbox = new VBox(20);
				dialogVbox.setPadding(new Insets(30, 5, 5, 15));
				dialogVbox.setAlignment(Pos.TOP_CENTER);
				Label finished = new Label("Etes-vous sûr de vouloir supprimer ?");
				Button finish_Him = new Button("Oui");
				Button Wait = new Button("Non");

//creation d'une hbox de confirmation	
				HBox theFinisher = new HBox();
				VBox Finish_Who = new VBox();
				Finish_Who.getChildren().addAll(test);
				Finish_Who.setAlignment(Pos.CENTER);
				theFinisher.setAlignment(Pos.CENTER);
				theFinisher.getChildren().addAll(finish_Him, Wait);
				theFinisher.setSpacing(10);
				dialogVbox.getChildren().addAll(finished, Finish_Who, theFinisher);
				Scene dialogScene = new Scene(dialogVbox, 300, 250);

				dialog.setScene(dialogScene);
				dialog.show();
				Wait.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent Event) {

						dialog.close();
					}
				});

				finish_Him.setOnAction(new EventHandler<ActionEvent>() {

					public void handle(ActionEvent Event) {
						file.suppression(Integer.parseInt(stockage1.getText()));
						liste.clear();
						file.creationListe2(liste);
						observableStagiaires.clear();
						observableStagiaires.addAll(liste);

						final Stage popup_sup = new Stage();
						popup_sup.setTitle("Supression");
						popup_sup.initOwner(mainStage);

						VBox popup_supVbox = new VBox();
						popup_supVbox.setPadding(new Insets(30, 5, 30, 5));
						popup_supVbox.setAlignment(Pos.TOP_CENTER);

						Label conf_sup = new Label("Le stagiaire a été supprimé.");
						Button ThatsIt = new Button("Ok");

						VBox popup_Vbox1 = new VBox();
						popup_Vbox1.getChildren().addAll(ThatsIt);
						popup_Vbox1.setAlignment(Pos.TOP_CENTER);
						popup_Vbox1.setPadding(new Insets(30, 5, 30, 5));

						dialog.close();
						ThatsIt.setOnAction(new EventHandler<ActionEvent>() {

							public void handle(ActionEvent Event) {
								maj.setDisable(false);
								sup.setDisable(false);
								popup_sup.close();
							}
						});
						popup_supVbox.getChildren().addAll(conf_sup, popup_Vbox1);

						Scene conf_suppression = new Scene(popup_supVbox, 300, 150);
						popup_sup.setScene(conf_suppression);
						popup_sup.show();

					}
				});
			}
		});

//--------------------------------------------BOUTON AJOUTER----------------------------------------------------------------
		ajout.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent Event) {

				final Stage stageAjout = new Stage();
				stageAjout.setTitle("Ajouter un stagiaire");
				stageAjout.initOwner(mainStage);

				Label ajout_s = new Label("Veuillez s'il-vous-plait remplir les differents champs :");
				ajout_s.setStyle("-fx-font-weight: bold");

				VBox vbox_ajout = new VBox();
				vbox_ajout.setPadding(new Insets(50, 50, 100, 50));
				vbox_ajout.setSpacing(10);

				VBox add = new VBox();
				add.setPadding(new Insets(50, 50, 100, 50));
				add.setSpacing(10);

				Label add_s = new Label("Nouvelles informations : ");
				add_s.setStyle("-fx-font-weight: bold");

				// Lebel warning:
				Label warning = new Label("");

				Label add_nom = new Label("Nom   ");
				TextField add_nom_text = new TextField();

				Label add_prenom = new Label("Prenom   ");
				TextField add_prenom_text = new TextField();

				Label add_dept = new Label("Departement   ");
				TextField add_dept_text = new TextField();

				Label add_promo = new Label("Promotion   ");
				TextField add_promo_text = new TextField();

				Label add_annee = new Label("Année   ");
				TextField add_annee_text = new TextField();

				Button bAjout = new Button("Ajouter un nouveau stagiaire");

				vbox_ajout.getChildren().addAll(add_s, add_nom, add_nom_text, add_prenom, add_prenom_text, add_dept,
						add_dept_text, add_promo, add_promo_text, add_annee, add_annee_text, bAjout, warning);

				Scene addScene = new Scene(vbox_ajout, 500, 650);

				stageAjout.setScene(addScene);
				stageAjout.show();

				bAjout.setOnAction(new EventHandler<ActionEvent>() {
//			
//					@Override
					public void handle(ActionEvent event) {

						boolean fusible = true;

						if (add_nom_text.getText().length() > 30) {
							warning.setText("Le Nom ne doit pas dépasser 30 caractères");
							fusible = false;
						}
						if (add_prenom_text.getText().length() > 30) {
							warning.setText("Le Prenom ne doit pas dépasser 30 caractères");
							fusible = false;
						}
						if (add_dept_text.getText().length() > 4) {
							warning.setText("Le Departement ne doit pas dépasser 4 caractères");
							fusible = false;
						}
						if (add_promo_text.getText().length() > 10) {
							warning.setText("La formation ne doit pas dépasser &0 caractères");
							fusible = false;
						}
						if (add_annee_text.getText().length() > 10) {
							warning.setText("L'année ne doit pas dépasser 10 caractères");
							fusible = false;
						}

						if (fusible == true) {
							file.ajouterStagiaire(add_nom_text.getText().toUpperCase(), add_prenom_text.getText(),
									add_dept_text.getText(), add_promo_text.getText().toUpperCase(),
									add_annee_text.getText());

						}
						liste.clear();

						file.creationListe2(liste);
						observableStagiaires.clear();
						observableStagiaires.addAll(liste);

						stageAjout.close();

						final Stage popup_ajout = new Stage();
						popup_ajout.setTitle("Nouvelle entrée");
						popup_ajout.initOwner(mainStage);

						VBox popup_ajoutVbox = new VBox();
						popup_ajoutVbox.setPadding(new Insets(30, 5, 30, 5));
						popup_ajoutVbox.setAlignment(Pos.TOP_CENTER);

						Button InHere = new Button("Ok");
						Label conf_ajout = new Label("Un nouveau stagiaire a été ajouté.");

						///
						if (!fusible) {

							conf_ajout = warning;
						}

						VBox popup_Vbox2 = new VBox();
						popup_Vbox2.getChildren().addAll(InHere);
						popup_Vbox2.setAlignment(Pos.TOP_CENTER);
						popup_Vbox2.setPadding(new Insets(30, 5, 30, 5));

						stageAjout.close();
						InHere.setOnAction(new EventHandler<ActionEvent>() {

							public void handle(ActionEvent Event) {
								popup_ajout.close();
							}
						});
						popup_ajoutVbox.getChildren().addAll(conf_ajout, popup_Vbox2);

						Scene conf_ajouté = new Scene(popup_ajoutVbox, 300, 150);
						popup_ajout.setScene(conf_ajouté);
						popup_ajout.show();
					}
				});

			}

		});

//--------------------------------------------BOUTON ANNUAIRE----------------------------------------------------------------
		Clear.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent Event) {

				observableStagiaires.clear();
				observableStagiaires.addAll(liste);

			}
		});

//--------------------------------------------BOUTON RECHERCHE----------------------------------------------------------------

		Recherche_stagiaire.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent Event) {

				liste.clear();
				file.creationListe2(liste); // liste binaire QUESTION : utile ?
				List<Stagiaire> liste0 = liste; // conteneur du résultat de la recherche binaire créé en dehors de
												// toutes {}

				observableStagiaires.clear();
				observableStagiaires.addAll(liste);

				if (!nom1.getText().equals("")) { // si le critère nom est utilisé

					observableStagiaires.clear();
					liste0 = file.rechercheNom(nom1.getText().toUpperCase());
					observableStagiaires.addAll(liste0); // je mets tout dans une liste que je peux utiliser pour les
															// autres filtres
				}
				boolean flag = false; // Condition No Go
				if (prenom1.getText() != "" || // Critères multiples et variables ...
				dept1.getText() != "" || promotion1.getText() != "" || annee1.getText() != "")
					flag = true; // ...du Go

				if (flag) { //
					observableStagiaires.clear(); // QUESTION : est ce que m'efface le nom ?
					List<Stagiaire> liste1 = liste0.stream() // je reprends les résultat de la recherche par nom qui
																// sont dans liste0
							.filter(e -> (e.getPrenom().toLowerCase().contains(prenom1.getText().toLowerCase()))) // entrées
																													// des
																													// utilisateur
																													// +
																													// filtres
							.filter(e -> (e.getDepartement().toLowerCase().contains(stockage2.getText().toLowerCase())))
							.filter(e -> (e.getPromotion().toUpperCase()
									.startsWith(promotion1.getText().toUpperCase())))
							.filter(e -> (e.getAnnee().contains(annee1.getText()))).collect(Collectors.toList()); // ici
																													// :
																													// liste1
					observableStagiaires.addAll(liste1); // affichage dans le tableView
				}
			}
		});

//--------------------------------------------BOUTON IMPRIMER----------------------------------------------------------------

		print.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent Event) {

				final Stage dialog = new Stage();

				dialog.setTitle("Attention");
				dialog.initOwner(mainStage);
				VBox dialogVbox = new VBox();

				Button finish_Him = new Button("Oui");
				Button Wait = new Button("Non");
				Label deuxiemes = new Label("Nom du fichier");
				TextField dossier = new TextField();
				TextField dossierNom = new TextField();
				HBox premier = new HBox();
				HBox deuxieme = new HBox();

				DirectoryChooser directoryChooser = new DirectoryChooser();
				directoryChooser.setInitialDirectory(new File("src"));

				Scene dialogScene = new Scene(dialogVbox, 300, 250);

				dialog.setScene(dialogScene);
				dialog.show();
				Button button = new Button("Select Directory");
				premier.getChildren().addAll(button, dossier);
				deuxieme.getChildren().addAll(deuxiemes, dossierNom);
				dialogVbox.getChildren().addAll(premier, deuxieme, Wait, finish_Him);

				button.setOnAction(e -> {
					File selectedDirectory = directoryChooser.showDialog(dialog);

					dossier.setText(selectedDirectory.getAbsolutePath());
				});
				Wait.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent Event) {

						dialog.close();
					}
				});

				finish_Him.setOnAction(new EventHandler<ActionEvent>() {

					public void handle(ActionEvent Event) {
						String FILE = dossier.getText()+ "/"+ dossierNom.getText() + ".pdf";

						try {
							Document document = new Document();
							PdfWriter.getInstance(document, new FileOutputStream(FILE));
							document.open();
							Anchor anchor2 = new Anchor("Liste de stagiaires");

							Chapter stagiaires = new Chapter(new Paragraph(anchor2), 3);
							for (Stagiaire st : observableStagiaires) {
								Paragraph para = new Paragraph(st.toString2());
								stagiaires.add(para);

							}
							document.add(stagiaires);

							document.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						dialog.close();

					}
				});
			}
		});

		utilisateur.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				maj.setVisible(false);
				sup.setVisible(false);
				print.setVisible(false);
				mainStage.setScene(scene2);
			}
		});

		// Configuration boutton admin
		admin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				final Stage pwd = new Stage();
				pwd.setTitle("Profil Administrateur");
				pwd.initOwner(mainStage);

				Label WhoAreYou = new Label("Bonjour, veuillez s'il-vous-plait entrer votre mot de passe :");
				Label SayWho = new Label("Nom d'utilisateur:");
				SayWho.setStyle("-fx-font-weight: bold");
				TextField EnterUrUsername = new TextField();

				Label WhatsYourPwd = new Label("Mot de Passe");
				WhatsYourPwd.setStyle("-fx-font-weight: bold");
				PasswordField EnterUrPwd = new PasswordField();

				Button CheckId = new Button("Ok");
				CheckId.setOnAction(new EventHandler<ActionEvent>() {

					public void handle(ActionEvent Event) {

						final Stage denied = new Stage();
						denied.setTitle("Accès refusé");

						Label YoureOut = new Label(
								"Mot de passe ou nom d'utilisateur erronés.\n" + "Veuillez réessayer s'il-vous-plait.");
						Button GetOut = new Button("Ok");

						GetOut.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								mainStage.setScene(scene);
							}
						});

						VBox TryAgain = new VBox();
						TryAgain.getChildren().addAll(YoureOut, GetOut);
						TryAgain.setPadding(new Insets(20, 25, 25, 25));
						TryAgain.setSpacing(15);
						TryAgain.setAlignment(Pos.CENTER);

						Scene TryAgain2 = new Scene(TryAgain, 500, 250);
						pwd.setScene(TryAgain2);
						pwd.show();

						if (EnterUrUsername.getText().equals("admin") && EnterUrPwd.getText().equals("12345")) {
							mainStage.setScene(scene2);
							maj.setVisible(true);
							sup.setVisible(true);
							print.setVisible(true);
						} else {
							mainStage.setScene(TryAgain2);
						}
						pwd.close();
					}
//									}
				});

				VBox ThisIsYou = new VBox();
				ThisIsYou.getChildren().addAll(WhoAreYou, SayWho, EnterUrUsername, WhatsYourPwd, EnterUrPwd, CheckId);
				ThisIsYou.setPadding(new Insets(25, 25, 25, 25));
				ThisIsYou.setSpacing(10);
				ThisIsYou.setAlignment(Pos.CENTER);

				Scene theBouncer = new Scene(ThisIsYou, 500, 250);
				pwd.setScene(theBouncer);
				pwd.show();

			}
		});

	}

	public static void main(String[] args) {
		launch(args);
	}

//---------------------------------------------------------------------------------------------------------

}
