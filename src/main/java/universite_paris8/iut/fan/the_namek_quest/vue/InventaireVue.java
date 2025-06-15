package universite_paris8.iut.fan.the_namek_quest.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Element;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Materieau;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Trunks;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Classe InventaireVue
 * ---------------------
 * Gère l’affichage graphique de l’inventaire dans l’interface du jeu.
 * Affiche une icône d’accès à l’inventaire, les objets collectés par le joueur,
 * et les informations associées comme la quantité et l’objet équipé.
 */

public class InventaireVue {

    private Pane pane;             // Pane principal de l'interface (affiche l'icône inventaire)
    private Inventaire inventaire; // Objet métier représentant l'inventaire
    private Trunks trunks;          // Personnage joueur
    private Pane paneInventaire;   // Pane dédié à l'affichage du contenu de l'inventaire
    private ImageView capsuleVue;  // Icône d'accès à l'inventaire
    private boolean ouvert;        // Indique si l'inventaire est ouvert

    public InventaireVue(Inventaire inventaire, Pane pane, Pane paneInventaire, Trunks trunks) {
        this.pane = pane;
        this.inventaire = inventaire;
        this.paneInventaire = paneInventaire;
        this.ouvert = false;
        this.trunks = trunks;

        // Chargement de l'icône de l'inventaire
        this.capsuleVue = new ImageView(new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/affichageGeneral/inventaire.png")));

        capsuleVue.setFitHeight(46);
        capsuleVue.setFitWidth(46);
        capsuleVue.setTranslateX(1831);
        capsuleVue.setTranslateY(108);


        afficherLogoInventaire();
    }

    public ImageView getCapsuleVue() {
        return capsuleVue;
    }
    /**
     * Affiche ou masque l'icône d'inventaire selon son état.
     */
    public void afficherLogoInventaire() {
        if (!this.estOuvert()) {
            if (!pane.getChildren().contains(capsuleVue)) {
                pane.getChildren().add(capsuleVue);
            }
        } else {
            pane.getChildren().remove(capsuleVue);
        }
    }

    /**
     * Affiche le contenu de l'inventaire dans le paneInventaire.
     * Affiche chaque objet avec son image, cadre, quantité et indication d'équipement.
     */
    public void afficherContenuInventaire() {
        // Chargement des images des objets et cadres
        Image pioche = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/outils/pioche.png"));
        Image hache = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/outils/hache.png"));
        Image epee = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/outils/epee.png"));
        Image caseInventaire = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/outils/caseInventaire.png"));
        Image equipeImage = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/CaseObjetEquipe.png"));
        Image terre = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/herbe.png"));
        Image bouleCristale1 = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/b.png"));
        Image haricot = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/haricot.png"));
        Image roche = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/roche.png"));
        Image arbre = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/bois.png"));

        int x = 753; // Position initiale X
        int y = 0;   // Position initiale Y

        if (!inventaire.getListObjects().isEmpty()) {
            this.ouvert = true;

            for (Element object : inventaire.getListObjects()) {
                // Si on dépasse la limite horizontale, passer à la ligne suivante
                if (x < 100) {
                    x = 753;
                    y += 46;
                }

                // Affiche le cadre selon que l'objet est équipé ou non
                ImageView caseInventaireVue;
                if (trunks.getObjectEquipe().equals(object)) {
                    caseInventaireVue = new ImageView(equipeImage);
                } else {
                    caseInventaireVue = new ImageView(caseInventaire);
                }
                caseInventaireVue.setFitHeight(46);
                caseInventaireVue.setFitWidth(46);
                caseInventaireVue.setTranslateX(x);
                caseInventaireVue.setTranslateY(y);
                paneInventaire.getChildren().add(caseInventaireVue);

                // Image de l'objet
                ImageView img = new ImageView();
                switch (object.getName().toLowerCase()) {  // pour éviter problème majuscules/minuscules
                    case "pioche":
                        img.setImage(pioche);
                        break;
                    case "hache":
                        img.setImage(hache);
                        break;
                    case "épée":
                        img.setImage(epee);
                        break;
                    case "terre":
                        img.setImage(terre);
                        break;
                    case "boule":
                        img.setImage(bouleCristale1);
                        break;
                    case "haricot":
                        img.setImage(haricot);
                        break;
                    case "roche":
                        img.setImage(roche);
                        break;
                    case "arbre":
                        img.setImage(arbre);
                        break;
                    default:
                        // Optionnel: gérer un cas "image inconnue"
                        break;
                }

                img.setTranslateX(x + (46 - 32) / 2);
                img.setTranslateY(y + (46 - 32) / 2);
                img.setFitHeight(32);
                img.setFitWidth(32);
                paneInventaire.getChildren().add(img);

                // Si l'objet est un matériau, afficher sa quantité
                if (object instanceof Materieau) {
                    Materieau mat = (Materieau) object;
                    Label labelQuantite = new Label();
                    labelQuantite.textProperty().bind(mat.getQuantiteProp().asString());
                    labelQuantite.setFont(new Font("Arial", 12));
                    labelQuantite.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
                    labelQuantite.setTranslateX(x + 25);
                    labelQuantite.setTranslateY(y + 28);
                    paneInventaire.getChildren().add(labelQuantite);
                }

                x -= 46; // Décalage vers la gauche pour le prochain objet
            }
        }
    }

    public boolean estOuvert() {
        return this.ouvert;
    }

    public void ouvrirInventaire() {
        this.ouvert = true;
        paneInventaire.setVisible(true);
        afficherContenuInventaire();
        pane.getChildren().remove(capsuleVue);
    }

    public void fermeInventaire() {
        this.ouvert = false;
        paneInventaire.getChildren().clear();
        paneInventaire.setVisible(false);
        if (!pane.getChildren().contains(capsuleVue)) {
            pane.getChildren().add(capsuleVue);
        }
    }
}
