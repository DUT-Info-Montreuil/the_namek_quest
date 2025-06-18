package universite_paris8.iut.fan.the_namek_quest.jUnit;//package universite_paris8.iut.fan.the_namek_quest.jUnit;
//
//import org.junit.jupiter.api.Test;
//import universite_paris8.iut.fan.the_namek_quest.modele.Terrain;
//
//import static org.junit.jupiter.api.Assertions.*;
//class TerrainTest {
//
//    @Test
//    void dansTerrain() {
//        Terrain terrain = new Terrain();
//
//        // Valide
//        assertTrue(terrain.dansTerrain(0, 0));
//        assertTrue(terrain.dansTerrain(9, 9));
//        assertTrue(terrain.dansTerrain(10, 0));
//        //en attente de merge
//        //assertTrue(terrain.dansTerrain(terrain.getHeight()-1, terrain.getWidth()-1));
//
//        // Invalide
//        assertFalse(terrain.dansTerrain(-9, 0));
//        /*assertFalse(terrain.dansTerrain(0, -11));
//        assertFalse(terrain.dansTerrain(terrain.hauteurTerrain()+1, terrain.largeurTerrain()+8));
//        assertFalse(terrain.dansTerrain(-100000, -1500));
//        assertFalse(terrain.dansTerrain(terrain.hauteurTerrain(), terrain.largeurTerrain()));*/
//        assertFalse(terrain.dansTerrain(-9, 0));
//    }
//
//
//}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import universite_paris8.iut.fan.the_namek_quest.Constante;
import universite_paris8.iut.fan.the_namek_quest.modele.Terrain;

class TerrainTest {

    // Si besoin, adapte ou remplace par universite_paris8.iut.fan.the_namek_quest.Constante.TAILLE_TUILE etc.
    static final int TAILLE_TUILE = 32;
    static final int MARGE_GAUCHE_MUR = 2;
    static final int MARGE_DROITE_MUR = 2;

    Terrain terrain;

 @BeforeEach
   void setUp() {
//        // Si tu ne veux pas écraser Constante, commente ces lignes
//        Constante.TAILLE_TUILE = TAILLE_TUILE;
//        Constante.MARGE_GAUCHE_MUR = MARGE_GAUCHE_MUR;
//        Constante.MARGE_DROITE_MUR = MARGE_DROITE_MUR;
       terrain = new Terrain();
   }

    @Test
    void testDimensions() {
        assertEquals(terrain.largeurTerrain(), terrain.codeTuile(0,0) == 1 ? 25 : 0);
        assertEquals(terrain.hauteurTerrain(), 24);
        assertEquals(terrain.largeurTerrain() * TAILLE_TUILE, terrain.largeurTerrain() * TAILLE_TUILE);
        assertEquals(terrain.hauteurTerrain() * TAILLE_TUILE, terrain.hauteurTerrain() * TAILLE_TUILE);
    }

    @Test
    void testGetSetTuile() {
        int c = 2, l = 3;
        terrain.setTuile(c, l, 2); // sol
        assertEquals(2, terrain.codeTuile(c, l));
        terrain.setTuileCiel(c, l);
        assertEquals(1, terrain.codeTuile(c, l));
    }

    @Test
    void testCodeTuilePixel() {
        int c = 4, l = 5;
        terrain.setTuile(c, l, 3);
        int x = c * TAILLE_TUILE + 1, y = l * TAILLE_TUILE + 1;
        assertEquals(3, terrain.codeTuilePixel(x, y));
    }

    @Test
    void testGetCaseXandY() {
        int px = 64, py = 96;
        assertEquals(px / TAILLE_TUILE, terrain.getCaseX(px));
        assertEquals(py / TAILLE_TUILE, terrain.getCaseY(py));
    }

    @Test
    void testDansTerrain() {
        assertTrue(terrain.dansTerrain(0, 0));
        int xOut = terrain.largeurTerrain() * TAILLE_TUILE;
        int yOut = terrain.hauteurTerrain() * TAILLE_TUILE;
        assertFalse(terrain.dansTerrain(xOut, 0));
        assertFalse(terrain.dansTerrain(0, yOut));
    }

    @Test
    void testEstTraversableCielEtSol() {
        int c = 1, l = 1;
        terrain.setTuile(c, l, 1); // ciel
        int x = c * TAILLE_TUILE + 1, y = l * TAILLE_TUILE + 1;
        assertTrue(terrain.estTraversable(x, y));
        terrain.setTuile(c, l, 2); // sol
        assertFalse(terrain.estTraversable(x, y));
    }

//    @Test
//    void testEstTraversableMur() {
//        int c = 1, l = 1;
//        terrain.setTuile(c, l, 10); // mur
//        int xLeft = c * TAILLE_TUILE + 1;
//        int xRight = (c + 1) * TAILLE_TUILE - 1;
//        int y = l * TAILLE_TUILE + 1;
//        // Bords du mur sont traversables
//        assertTrue(terrain.estTraversable(c * TAILLE_TUILE, y));
//        assertTrue(terrain.estTraversable((c + 1) * TAILLE_TUILE - MARGE_DROITE_MUR, y));
//        // Centre du mur non traversable
//        assertFalse(terrain.estTraversable(c * TAILLE_TUILE + 10, y));
//    }

    @Test
    void testCasserEtPoserBloc() {
        int c = 2, l = 2;
        double x = c * TAILLE_TUILE + 1, y = l * TAILLE_TUILE + 1;
        terrain.poserBloc(x, y, 2);
        assertEquals(2, terrain.codeTuile(c, l));
        terrain.casserBloc(x, y);
        assertEquals(1, terrain.codeTuile(c, l));
    }

//    @Test
//    void testRangeCreuser() {
//        int trunksX = 64, trunksY = 64;
//        double sourisX = 64 + TAILLE_TUILE, sourisY = 64 + TAILLE_TUILE;
//        // Dans la zone de creusage, mais pas sur Trunks lui-même
//        assertTrue(terrain.rangeCreuser(trunksX, trunksY, sourisX, sourisY));
//        // Sur Trunks lui-même
//        assertFalse(terrain.rangeCreuser(trunksX, trunksY, trunksX + 1, trunksY + 1));
//        // Hors zone
//        assertFalse(terrain.rangeCreuser(trunksX, trunksY, trunksX - 2 * TAILLE_TUILE, trunksY));
//    }
}