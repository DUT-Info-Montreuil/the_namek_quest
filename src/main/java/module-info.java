module universite_paris8.iut.fan.the_namek_quest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens universite_paris8.iut.fan.the_namek_quest to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest;
    exports universite_paris8.iut.fan.the_namek_quest.modele;
    opens universite_paris8.iut.fan.the_namek_quest.modele to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.controller;
    opens universite_paris8.iut.fan.the_namek_quest.controller to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.modele.Inventaire;
    opens universite_paris8.iut.fan.the_namek_quest.modele.Inventaire to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.modele.Inventaire.arme;
    opens universite_paris8.iut.fan.the_namek_quest.modele.Inventaire.arme to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.modele.Inventaire.ressource;
    opens universite_paris8.iut.fan.the_namek_quest.modele.Inventaire.ressource to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.modele.Inventaire.outils;
    opens universite_paris8.iut.fan.the_namek_quest.modele.Inventaire.outils to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.modele.bloc;
    opens universite_paris8.iut.fan.the_namek_quest.modele.bloc to javafx.fxml;
}