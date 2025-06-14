package universite_paris8.iut.fan.the_namek_quest.vue;

/**
 * Classe TerrainVue
 * -------------------
 * Gère l'affichage graphique du terrain du jeu "The Namek Quest" à partir d'un modèle de terrain.
 * Cette classe utilise un TilePane pour afficher chaque tuile (image) du terrain,
 * en fonction de son type (ciel, sol, herbe), à partir des codes fournis par la classe `Terrain`.
 **/


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.fan.the_namek_quest.modele.Terrain;

public class TerrainVue {

    private TilePane tilePane;
    private Terrain terrain;
    private final int tailleTuile = 32;

    private ImageView[][] tuiles;

    public TerrainVue(TilePane tilePane, Terrain terrain) {
        this.tilePane = tilePane;
        this.terrain = terrain;
        this.afficherTerrain();
    }

    public void afficherTerrain () {
        tilePane.setPrefColumns(terrain.largeurTerrain());
        tilePane.setPrefSize(terrain.largeurTerrain()*tailleTuile,terrain.hauteurTerrain()*tailleTuile);
        tilePane.setFocusTraversable(true);

        Image imageCiel = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/affichageGeneral/ciel.png") );
        Image imageSol = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/sol.png"));
        Image imageHerbe = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/herbe.png"));
        Image imageCristal =new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/bouleDansTerre.png"));
        Image imageHaricot =new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/haricotDansTerre.png"));
        Image imageRoche = new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/roche.png"));
        this.tuiles = new ImageView[terrain.largeurTerrain()][terrain.hauteurTerrain()];


        for(int y = 0; y< terrain.hauteurTerrain(); y++) {
            for(int x = 0; x< terrain.largeurTerrain(); x++) {
                if(this.terrain.codeTuile(x,y)==1){
                    tuiles[x][y] = new ImageView(imageCiel);
                }
                else if(this.terrain.codeTuile(x,y)==2){
                    tuiles[x][y] = new ImageView(imageSol);
                }
                else if(this.terrain.codeTuile(x,y)==3){
                    tuiles[x][y] = new ImageView(imageHerbe);
                }
                else if(this.terrain.codeTuile(x,y)==8){
                    tuiles[x][y] = new ImageView(imageCristal);
                }
                else if(this.terrain.codeTuile(x,y)==6){
                    tuiles[x][y] = new ImageView(imageHaricot);
                }
                else if(this.terrain.codeTuile(x,y)==9){
                    tuiles[x][y] = new ImageView(imageRoche);
                }

                tilePane.getChildren().add(tuiles[x][y]);
            }
        }
    }

    
    public void changerTuileCiel(int x, int y) {
        System.out.println("changerTuile");

        this.tuiles[x/32][y/32].setImage(new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/affichageGeneral/ciel.png")));

    }
    /// TODO changer cette fonction avec un switch pour changer en fonction fu typ de bloc posé
    public void changerTuileSol(int x, int y) {
        System.out.println("changerTuile");
        this.tuiles[x/32][y/32].setImage(new Image(getClass().getResourceAsStream("/universite_paris8/iut/fan/the_namek_quest/images/materieau/sol.png")));
    }
}
