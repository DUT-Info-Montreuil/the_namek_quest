package universite_paris8.iut.fan.the_namek_quest.jUnit;

import org.junit.jupiter.api.Test;
import universite_paris8.iut.fan.the_namek_quest.modele.Terrain;

import static org.junit.jupiter.api.Assertions.*;
class TerrainTest {

    @Test
    void dansTerrain() {
        Terrain terrain = new Terrain();

        // Valide
        assertTrue(terrain.dansTerrain(0, 0));
        assertTrue(terrain.dansTerrain(9, 9));
        assertTrue(terrain.dansTerrain(10, 0));
        //en attente de merge
        //assertTrue(terrain.dansTerrain(terrain.getHeight()-1, terrain.getWidth()-1));

        // Invalide
        assertFalse(terrain.dansTerrain(-9, 0));
        /*assertFalse(terrain.dansTerrain(0, -11));
        assertFalse(terrain.dansTerrain(terrain.hauteurTerrain()+1, terrain.largeurTerrain()+8));
        assertFalse(terrain.dansTerrain(-100000, -1500));
        assertFalse(terrain.dansTerrain(terrain.hauteurTerrain(), terrain.largeurTerrain()));*/
        assertFalse(terrain.dansTerrain(-9, 0));
    }
}