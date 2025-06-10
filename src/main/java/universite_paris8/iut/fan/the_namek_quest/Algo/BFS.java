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
        positionTrunks = new Position(env.getTrunks().getX()/Constante.TAILLE_TUILE,env.getTrunks().getY()/ Constante.TAILLE_TUILE);
        this.g = new Grille(this.env,env.getTerrain().hauteurTerrain(),env.getTerrain().largeurTerrain());
        algoBFS();
    }

    public void algoBFS() {
        LinkedList<Position> fifo = new LinkedList<Position>();
        Set<Position> visited = new HashSet<>();

        predecesseurs.put(positionTrunks,null);
        fifo.add(positionTrunks);
        visited.add(positionTrunks);

        Position positionUtilisé = positionTrunks;

        while (!fifo.isEmpty()) {
            positionUtilisé = fifo.pollFirst();
            for(Position  voisin : g.adjacents(positionUtilisé)) {
                if(!predecesseurs.containsKey(voisin) /*&& env.getTerrain().codeTuile(voisin.getX(), voisin.getY())==1*/) {
                    predecesseurs.put(voisin, positionUtilisé);
                    fifo.addLast(voisin);
                    visited.add(voisin);
                }
            }
        }
    }

    /*public Position getNextMove(Position from) {
        if (!predecesseurs.containsKey(from)) {
            return null;
        }

        Position pos = from;

        while (predecesseurs.containsKey(pos) && !predecesseurs.get(pos).equals(positionTrunks)) {
            System.out.println("next mouvement");
            pos = predecesseurs.get(pos);
        }

        return pos;
    }*/

    public Position getNextMove(Position cible){
        ArrayList<Position> chemin = new ArrayList<>();
        Position positionUtilisé = cible;
        while(predecesseurs.get(positionUtilisé) != null) {
            System.out.println(positionUtilisé.toString());
            chemin.add(positionUtilisé);
            positionUtilisé = predecesseurs.get(positionUtilisé);
        }

       if(chemin.isEmpty()){
           return null;
       }if(chemin.size()==1){
           return chemin.get(0);
       }else {
           return chemin.get(chemin.size()-1);
       }
    }

    private ArrayList<Position> getVoisins(Position p) {
        ArrayList<Position> voisins = new ArrayList<>();
        int x = p.getX();
        int y = p.getY();

        
        if(env.getTerrain().dansTerrainModel(x + 1, y) ){
            if(env.getTerrain().codeTuile(x+1,y)==1){
                voisins.add(new Position(x + 1, y));
            }

        }if(env.getTerrain().dansTerrainModel(x - 1, y) && env.getTerrain().codeTuile(x-1,y)==1){
            if(env.getTerrain().codeTuile(x-1,y)==1){
                voisins.add(new Position(x-1, y));
            }
        }if(env.getTerrain().dansTerrainModel(x, y+1)){
            if(env.getTerrain().codeTuile(x,y+1)==1){
                voisins.add(new Position(x,y+1));
            }
        }if(env.getTerrain().dansTerrainModel(x, y-1)){
            if(env.getTerrain().codeTuile(x,y-1)==1){
                voisins.add(new Position(x,y-1));
            }

        }

        return voisins;
    }






}
