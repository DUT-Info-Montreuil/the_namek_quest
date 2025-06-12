package universite_paris8.iut.fan.the_namek_quest.modele;

import universite_paris8.iut.fan.the_namek_quest.Constante;

public class PersonnageEnnemis extends Personnage{

    public PersonnageEnnemis(Environnement env) {
        super(250, 400, env);
        this.setVitesse(1);
        this.setDirection(0);
    }


    public void dectecteDirection(int xTrunks, int yTrunks) {
        if (this.getX() < xTrunks) {
            this.setDirection(1);
        } else if (this.getX() > xTrunks) {
            this.setDirection(-1);
        } else {
            this.setDirection(0);
        }
    }
    /*public void deplacement(){
        int vitesse = getVitesse();
        Terrain terrain = this.getEnv().getTerrain();
        int x = this.getX();
        int y = this.getY();
        int newX = this.getX()+(getVitesse()*this.getDirection());
        int newY = this.getY()+(getVitesse()*this.getDirection());

        System.out.println("la direction ennemis : " + this.getDirection());
        this.setDirection(this.detecteTrunks(this.getEnv().getTrunks().getX(), this.getEnv().getTrunks().getY()));

            if(this.getDirection() == 1){
                if (terrain.dansTerrain(newX, y) && !getEnv().collisionDroite(newX, y)){
                    if(newX != getEnv().getTrunks().getX()-Constante.LARGEUR_PERSO && newY != getEnv().getTrunks().getY()){
                        this.setX(newX);
                    }

                }
            }if (this.getDirection() == -1){
                if(terrain.dansTerrain(newX, y) && !getEnv().collisionGauche(newX , y)){
                    if (newX != getEnv().getTrunks().getX()+Constante.LARGEUR_PERSO && newY != getEnv().getTrunks().getY()) {
                        this.setX(newX);
                    }

                }
            }

        }*/

        /*public void deplacement(int x, int y) {
            if (getEnv().getTerrain().dansTerrainModel(x, y)) {
                dectecteDirection(x, y);
                if(this.getDirection() == 1){
                    if (!getEnv().collisionDroite(x, y)){
                        if(x != getEnv().getTrunks().getY()){
                            this.setX((x * Constante.TAILLE_TUILE) + getVitesse());
                        }
                    }
                }if (this.getDirection() == -1){
                    if(!getEnv().collisionGauche(x , y)){
                        if (x != getEnv().getTrunks().getY()) {
                            this.setX((x * Constante.TAILLE_TUILE) + getVitesse());
                        }
                    }
                }
            }

            System.out.println("Position ennemis : " + this.getX() + " - " + this.getY());
        }*/



    public void deplacement(int x, int y) {
        dectecteDirection(x * Constante.TAILLE_TUILE, this.getY());

        if (this.getDirection() == 1) {
            int newX = this.getX() + this.getVitesse();
            if (newX > x * Constante.TAILLE_TUILE) newX = x * Constante.TAILLE_TUILE;
            if (!getEnv().collisionDroite(newX, this.getY()) && !getEnv().collisionDroite(newX, this.getY())) {
                this.setX(newX);
            }
        } else if (this.getDirection() == -1) {
            int newX = this.getX() - this.getVitesse();
            if (newX < x * Constante.TAILLE_TUILE) newX = x * Constante.TAILLE_TUILE;
            if (!getEnv().collisionGauche(newX, this.getY()) && !getEnv().collisionGauche(newX, this.getY())) {
                this.setX(newX);
            }
        }

        /*if (this.getY() < y * Constante.TAILLE_TUILE) {
            int newY = this.getY() + this.getVitesse();
            if (newY > y * Constante.TAILLE_TUILE) newY = y * Constante.TAILLE_TUILE;
            this.setY(newY);
        } else if (this.getY() > y * Constante.TAILLE_TUILE) {
            int newY = this.getY() - this.getVitesse();
            if (newY < y * Constante.TAILLE_TUILE) newY = y * Constante.TAILLE_TUILE;
            this.setY(newY);
        }
        System.out.println("Position ennemis : " + this.getX() + " - " + this.getY());
    }*/
    }
}


//this.setX((x * Constante.TAILLE_TUILE) + getVitesse());
    //this.setY((y * Constante.TAILLE_TUILE) + getVitesse());




