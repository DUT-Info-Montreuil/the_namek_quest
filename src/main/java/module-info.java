module universite_paris8.iut.fan.the_namek_quest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens universite_paris8.iut.fan.the_namek_quest to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest;
    exports universite_paris8.iut.fan.the_namek_quest.model;
    opens universite_paris8.iut.fan.the_namek_quest.model to javafx.fxml;
    exports universite_paris8.iut.fan.the_namek_quest.controller;
    opens universite_paris8.iut.fan.the_namek_quest.controller to javafx.fxml;
}