package universite_paris8.iut.fan.the_namek_quest.modele;

import universite_paris8.iut.fan.the_namek_quest.Constante;

public class PersonnageEnnemis extends Personnage{

    public PersonnageEnnemis(Environnement env) {
        super(250, 400, env);
        this.setVitesse(1);
        this.setDirection(0);
    }


    public void dectecteDirection(int xTrunks, int yTrunks) {

        int direction = 0;
        System.out.println("distance a gauche"+ (this.getX()-3*32));
        if(this.getX() <= xTrunks ){
            direction = 1;
            this.setDirection(1);
        }else if(xTrunks <=  this.getX() ){
            direction= -1;
            this.setDirection(-1);
        }
        //System.out.println("detect trunks : " +direction);
        //return direction;
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

        public void deplacement(int x, int y) {
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
        }


//this.setX((x * Constante.TAILLE_TUILE) + getVitesse());
    //this.setY((y * Constante.TAILLE_TUILE) + getVitesse());
    }



