package universite_paris8.iut.fan.the_namek_quest.model;

public class Trunks extends Personnage {

    public Trunks(Environnement env) {
        //super(200, 250, 352, env);
        super(200, 400, 352, env);
        this.setVitesse(16);
    }

    public void seDeplacer(int d) {
        int newX;
        if (d == 0) {
            System.out.println("tente de Deplacer des trunks a droite");
            newX = this.getX()+1;
            if (this.getEnv().dansTerrain(newX, this.getY()) /*&& this.collisionHorizontale(newX)*/) {
                System.out.println("C1");
                /*if(this.collisionHorizontale(newX)) {
                    System.out.println("Deplacer des trunks a droite vraiment");
                    setX((newX - 1) + getVitesse());
                }
                else if (!this.collisionVerticale(newX)) {
                    System.out.println("C3");
                }*/
                if (this.getEnv().getTerrain().codeTuile(this.getX()*16, this.getY())*16 != 2) {
                    System.out.println("C2");
                    setX((newX - 1));
                }
            }

        }

        else if (d == 1) {
            System.out.println("Deplacer des trunks a gauche");
            newX = this.getX() - 1;
            if (this.getEnv().dansTerrain(newX, this.getY()) /*&& this.collisionHorizontale(newX)*/) {
                System.out.println("Deplacer des trunks a gauche");
                setX((newX -1) - getVitesse());
            }
        }
    }

    /*public void sauter() {
        Timeline timeline = new Timeline();
        int sautHauteur = 20;

        // Monter
        for (int i = 0; i < sautHauteur; i++) {
            int delta = -1;
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(i * 10), e -> {
                        if (env.dansTerrain(getX(), getY() + delta)) {
                            setY(getY() + delta);
                        }
                    })
            );
        }

        // Redescendre
        for (int i = 0; i < sautHauteur; i++) {
            int delta = 1;
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis((sautHauteur + i) * 10), e -> {
                        if (env.dansTerrain(getX(), getY() + delta)) {
                            setY(getY() + delta);
                        }
                    })
            );
        }

        timeline.play();
    }
    */

}
