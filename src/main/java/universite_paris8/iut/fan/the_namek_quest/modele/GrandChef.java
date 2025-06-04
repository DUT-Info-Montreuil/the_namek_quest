package universite_paris8.iut.fan.the_namek_quest.modele;

import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Inventaire;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.Object;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme.Arme;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Energie;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.Materieau;
import universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux.RocheDeNamek;

/**
 * Classe PersonnageNonJoueur
 * ---------------------------
 *
 **/

public class GrandChef  extends Personnage {
    private Trunks trunks;
    private Object object;
    private Materieau materieau;
    private Inventaire inventaire;

    public GrandChef(int x, int y, Environnement env, Trunks trunks) {
        super(x, y, env);
        this.trunks = trunks;
        this.inventaire = new Inventaire();
    }

    public void seDeplacer(){}

    public void ameliorerEpee(){
        System.out.println("demande d'amelioration de l'epee du grand chef");
        if(trunks.getX() == this.getX()-1 && trunks.getY() == this.getY()){

            Materieau rocheNamek = (Materieau) inventaire.getListObjects().get(inventaire.getIndexObject(new RocheDeNamek()));
            Materieau energie = (Materieau) inventaire.getListObjects().get(inventaire.getIndexObject(new Energie()));
            if(inventaire.ressourceDansInventaire(7) && inventaire.ressourceDansInventaire(4) ){
                if(rocheNamek.getQuantite() >= 3 && energie.getQuantite() >= 2){
                    if((trunks.getObjectEquipe().getId()== 0)){
                        Arme epee = (Arme) trunks.getObjectEquipe();
                        epee.incrementerDegat(10);
                    }
                }
            }
        }
    }

    public boolean trunksAProximite(){
        return trunks.getX() >= this.getX()-96 && trunks.getX() <= this.getX()+32;
    }
}
