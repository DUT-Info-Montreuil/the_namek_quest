/*package universite_paris8.iut.fan.the_namek_quest.jUnit;

import org.junit.jupiter.api.Test;
import universite_paris8.iut.fan.the_namek_quest.model.Environnement;
import universite_paris8.iut.fan.the_namek_quest.model.Terrain;

import static org.junit.jupiter.api.Assertions.*;
class EnvironnementTest {

    @Test
    void dansTerrain() {
        Environnement environnement = new Environnement();

        // Valide
        assertTrue(environnement.dansTerrain(0, 0));
        assertTrue(environnement.dansTerrain(9, 9));
        assertTrue(environnement.dansTerrain(10, 0));
        //en attente de merge
        //assertTrue(environnement.dansTerrain(environnement.getHeight()-1, environnement.getWidth()-1));

        // Invalide
        assertFalse(environnement.dansTerrain(-1, 0));
        assertFalse(environnement.dansTerrain(0, -1));
        assertFalse(environnement.dansTerrain(environnement.getHeight()+1, environnement.getWidth()+1));
        assertFalse(environnement.dansTerrain(-15, -15));
        assertFalse(environnement.dansTerrain(environnement.getHeight(), environnement.getWidth()));
    }
}*/