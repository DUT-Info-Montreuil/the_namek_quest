package universite_paris8.iut.fan.the_namek_quest.model.Inventaire.ressource;

import universite_paris8.iut.fan.the_namek_quest.model.Inventaire.Object;

public abstract class Materieau extends Object {
    private int Quantite ;
    public Materieau(int id, String name) {
        super(id, name);
        this.Quantite = 0;
    }

    public void incrementerRessource(){
        this.Quantite++;
    }

    public void decrementerRessource(){
        this.Quantite--;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }


}
