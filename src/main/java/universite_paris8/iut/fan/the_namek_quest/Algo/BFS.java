package universite_paris8.iut.fan.the_namek_quest.Algo;

import universite_paris8.iut.fan.the_namek_quest.Constante;
import universite_paris8.iut.fan.the_namek_quest.modele.Environnement;

import java.util.*;
import java.util.Map;

public class BFS {

    private Environnement env;
    private Position positionTrunks;
    private Grille g;
    
    /**
     * Chaque sommet (clé) est associé à son prédécesseur (valeur) du parcours en largeur
     */
    private Map<Position, Position> predecesseurs;
    

    public BFS(Environnement env) {
        this.env = env;
        predecesseurs = new HashMap<Position, Position>();
        positionTrunks = new Position(env.getTrunks().getX()/(Constante.TAILLE_TUILE-1),env.getTrunks().getY()/ (Constante.TAILLE_TUILE-1));
        this.g = new Grille(this.env,env.getTerrain().hauteurTerrain(),env.getTerrain().largeurTerrain());
        algoBFS();
    }

    public void algoBFS() {
        LinkedList<Position> fifo = new LinkedList<Position>();

        Position positionUtilisé = positionTrunks;
        predecesseurs.put(positionUtilisé,null);
        fifo.addFirst(positionUtilisé);

        while (!fifo.isEmpty()) {
            positionUtilisé = fifo.pollFirst();
            if(g.adjacents(positionUtilisé)!=null){
                for(Position  voisin : g.adjacents(positionUtilisé)) {
                    if(!predecesseurs.containsKey(voisin) /*&& env.getTerrain().codeTuile(voisin.getX(), voisin.getY())==1*/) {
                        predecesseurs.put(voisin, positionUtilisé);
                        fifo.addLast(voisin);
                        //visited.add(voisin);
                    }
                }
            }
        }
    }

    public Position getNextMove(Position cible){
        ArrayList<Position> chemin = new ArrayList<>();
        Position positionUtilisé = cible;

        while(predecesseurs.get(positionUtilisé) != null) {

            chemin.add(positionUtilisé);
            positionUtilisé = predecesseurs.get(positionUtilisé);

        }

       if(chemin.isEmpty()){
           return null;
       }if(chemin.size()==1){
           return chemin.get(0);
       }else {
           return chemin.get(1);
       }
    }


}
