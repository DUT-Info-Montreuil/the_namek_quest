module universite_paris8.iut.fan.the_namek_quest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens universite_paris8.iut.fan.the_namek_quest to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest;
    exports universite_paris8.iut.fan.the_namek_quest.modele;
    opens universite_paris8.iut.fan.the_namek_quest.modele to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.controlleur;
    opens universite_paris8.iut.fan.the_namek_quest.controlleur to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.modele.inventaire;
    opens universite_paris8.iut.fan.the_namek_quest.modele.inventaire to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme;
    opens universite_paris8.iut.fan.the_namek_quest.modele.inventaire.arme to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux;
    opens universite_paris8.iut.fan.the_namek_quest.modele.inventaire.materiaux to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.modele.inventaire.outils;
    opens universite_paris8.iut.fan.the_namek_quest.modele.inventaire.outils to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.modele.personnage;
    opens universite_paris8.iut.fan.the_namek_quest.modele.personnage to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.vue;
    opens universite_paris8.iut.fan.the_namek_quest.vue to javafx.fxml;
}