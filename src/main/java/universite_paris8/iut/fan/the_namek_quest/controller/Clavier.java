package universite_paris8.iut.fan.the_namek_quest.controller;

import javafx.application.Platform;
import universite_paris8.iut.fan.the_namek_quest.model.Trunks;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class Clavier implements KeyListener {

    public Clavier() {
    }

    public void deplacement(Trunks trunks){
        /*Platform.runLater(() -> {
            trunks.getScene().setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case D:
                        this.trunks.setX(this.trunks.getX()+16);
                        break;
                    case Q:
                        this.trunks.setX(this.trunks.getX()-16);
                        break;
                }
                //persoImage.setTranslateX(persoImageX);
                //persoImage.setTranslateX(persoImageX);
            });
        });*/
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D){
            this.trunks.setX(this.trunks.getX()+16);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
