package universite_paris8.iut.fan.the_namek_quest.jUnit;

import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;
import universite_paris8.iut.fan.the_namek_quest.Algo.BFS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.fan.the_namek_quest.Constante;
import universite_paris8.iut.fan.the_namek_quest.modele.personnage.Trunks;
import universite_paris8.iut.fan.the_namek_quest.Algo.Position;

import static org.junit.jupiter.api.Assertions.*;

public class BFSTest {

    private Environnement env;
    private BFS bfs;

    @BeforeEach
    public void setup() {
        env = new Environnement();
        bfs = new BFS(env);
    }

    @Test
    public void instancieBFS() {
        assertNotNull(bfs);
    }

    @Test
    public void nextMoveTrunks() {
        Trunks trunks = env.getTrunks();
        Position trunksPos = new Position(trunks.getX() / Constante.TAILLE_TUILE, trunks.getY() / Constante.TAILLE_TUILE);
        Position result = bfs.getNextMove(trunksPos);
        assertEquals(trunksPos, result);
    }

    @Test
    public void nextMoveNull() {
        Position inaccessible = new Position(999, 999);
        assertNull(bfs.getNextMove(inaccessible));
    }

    @Test
    public void setEnvMajTrunks() {
        Environnement env2 = new Environnement();
        Trunks trunks2 = env2.getTrunks();
        trunks2.setX(42);
        trunks2.setY(84);
        bfs.setEnv(env2);

        Position trunksPos2 = new Position(trunks2.getX() / Constante.TAILLE_TUILE, trunks2.getY() / Constante.TAILLE_TUILE);
        assertEquals(trunksPos2, bfs.getNextMove(trunksPos2));
    }


}