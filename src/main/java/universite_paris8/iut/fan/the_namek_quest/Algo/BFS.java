package universite_paris8.iut.fan.the_namek_quest.Algo;

    import universite_paris8.iut.fan.the_namek_quest.Constante;
    import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;

    import java.util.*;

    public class BFS {

        private Environnement env;
        private Position positionTrunks;
        private Grille grille;
        private Map<Position, Position> predecesseurs;

        public BFS(Environnement env) {
            this.env = env;
            this.predecesseurs = new HashMap<>();
            this.positionTrunks = calculerPositionTrunks();
            this.grille = creerGrilleAutourDeTrunks();
            algoBFS();
        }

        private Position calculerPositionTrunks() {
            return new Position(
                env.getTrunks().getX() / Constante.TAILLE_TUILE,
                env.getTrunks().getY() / Constante.TAILLE_TUILE
            );
        }

        private Grille creerGrilleAutourDeTrunks() {
            int x = env.getTrunks().getX();
            int y = env.getTrunks().getY();
            int taille = 10*Constante.TAILLE_TUILE;

            return new Grille(
                env,
                (y + taille) / Constante.TAILLE_TUILE,
                (x + taille) / Constante.TAILLE_TUILE,
                (x - taille) / Constante.TAILLE_TUILE,
                (y - taille) / Constante.TAILLE_TUILE
            );
        }

        public void algoBFS() {
            LinkedList<Position> fifo = new LinkedList<>();
            predecesseurs.clear();

            predecesseurs.put(positionTrunks, null);
            fifo.add(positionTrunks);

            while (!fifo.isEmpty()) {
                Position positionCourante = fifo.pollFirst();
                for (Position voisin : grille.adjacents(positionCourante)) {
                    if (!predecesseurs.containsKey(voisin)) {
                        predecesseurs.put(voisin, positionCourante);
                        fifo.addLast(voisin);
                    }
                }
            }
        }

        public Position getNextMove(Position cible) {
            if (!predecesseurs.containsKey(cible)) {
                return null; // Cible inatteignable
            }

            List<Position> chemin = new ArrayList<>();
            Position positionCourante = cible;

            while (positionCourante != null) {
                chemin.add(positionCourante);
                positionCourante = predecesseurs.get(positionCourante);
            }

            //Collections.reverse(chemin);
            return chemin.size() > 1 ? chemin.get(1) : chemin.get(0);
        }

        public void setEnv(Environnement env) {
            this.env = env;
            this.positionTrunks = calculerPositionTrunks();
            this.grille.refaireMap();
            algoBFS();
        }
    }