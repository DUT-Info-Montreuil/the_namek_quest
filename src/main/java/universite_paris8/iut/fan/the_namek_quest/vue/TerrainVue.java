package universite_paris8.iut.fan.the_namek_quest.vue;




import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.modele.Terrain;
/**
 * Classe TerrainVue
 * -------------------
 * Gère l'affichage graphique du terrain du jeu "The Namek Quest" à partir d'un modèle Terrain.
 * Utilise un TilePane pour afficher chaque tuile selon son type.
 */
public class TerrainVue {

    private TilePane tilePane;
    private Terrain terrain;
    private final int tailleTuile = 32;

    // Tableau 2D d'ImageView correspondant à chaque tuile affichée
    private ImageView[][] tuiles;

    public TerrainVue(TilePane tilePane, Terrain terrain) {
        this.tilePane = tilePane;
        this.terrain = terrain;
        afficherTerrain();
    }

    /**
     * Initialise et affiche toutes les tuiles dans le TilePane en fonction du modèle terrain.
     */
    public void afficherTerrain() {
        // Configuration du TilePane : nombre de colonnes, taille totale
        tilePane.setPrefColumns(terrain.largeurTerrain());
        tilePane.setPrefSize(terrain.largeurTerrain() * tailleTuile, terrain.hauteurTerrain() * tailleTuile);
        tilePane.setFocusTraversable(true);

        // Chargement des images des différentes tuiles
        Image imageCiel = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/affichageGeneral/ciel.png"));
        Image imageSol = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/sol.png"));
        Image imageHerbe = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/herbe.png"));
        Image imageCristal = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/bouleDansTerre.png"));
        Image imageHaricot = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/haricotDansTerre.png"));
        Image imageRoche = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/roche.png"));
        Image imageTronc = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/tronc.png"));
        Image imageFeuillage = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/feuille.png"));
        Image imageMur = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/mur.png"));
        Image imageKi = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/energieki.png"));


        tuiles = new ImageView[terrain.largeurTerrain()][terrain.hauteurTerrain()];

        // Parcours du terrain pour créer chaque ImageView selon le code de la tuile
        for (int y = 0; y < terrain.hauteurTerrain(); y++) {
            for (int x = 0; x < terrain.largeurTerrain(); x++) {
                int code = terrain.codeTuile(x, y);
                Image imgTuile;

                switch (code) {
                    case 1: imgTuile = imageCiel; break;
                    case 2: imgTuile = imageSol; break;
                    case 3: imgTuile = imageHerbe; break;
                    case 6: imgTuile = imageHaricot; break;
                    case 8: imgTuile = imageCristal; break;
                    case 9: imgTuile = imageRoche; break;
                    case 10: imgTuile = imageTronc; break;
                    case 11: imgTuile = imageFeuillage; break;
                    case 15: imgTuile = imageMur; break;
                    case 4 : imgTuile = imageKi; break;
                    default:
                        imgTuile = imageCiel; // par défaut, ciel si code inconnu
                }

                tuiles[x][y] = new ImageView(imgTuile);
                tuiles[x][y].setFitWidth(tailleTuile);
                tuiles[x][y].setFitHeight(tailleTuile);
                tilePane.getChildren().add(tuiles[x][y]);
            }
        }
    }

    /**
     * Change la tuile à la position (x,y) en tuile "ciel".
     * Note: x et y sont en pixels, divisés par tailleTuile pour obtenir indices.
     */
    public void changerTuileCiel(int x, int y) {
        int i = x / tailleTuile;
        int j = y / tailleTuile;

        if (i >= 0 && i < tuiles.length && j >= 0 && j < tuiles[0].length) {
            tuiles[i][j].setImage(new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/affichageGeneral/ciel.png")));
        }
    }

    /**
     * Change la tuile à la position (x,y) selon le type fourni.
     * Ici, seuls les types 2 (sol) et 10 (mur) sont gérés.
     */
    public void changerTuile(int x, int y, int type) {
        int i = x / tailleTuile;
        int j = y / tailleTuile;

        if (i >= 0 && i < tuiles.length && j >= 0 && j < tuiles[0].length) {
            String cheminImage = null;
            switch (type) {
                case 2:
                    cheminImage = "/universite_paris8/iut/fan/the_namek_quest/images/materieau/sol.png";
                    break;
                case 10:
                    cheminImage = "/universite_paris8/iut/fan/the_namek_quest/images/materieau/mur.png";
                    break;
                default:
                    System.out.println("Type de tuile inconnu pour changement: " + type);
            }
            if (cheminImage != null) {
                tuiles[i][j].setImage(new Image(getClass().getResourceAsStream(cheminImage)));
            }
        }
    }
}