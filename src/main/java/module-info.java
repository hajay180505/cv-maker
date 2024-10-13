module com.cvmaker.cvmaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires kernel;
    requires layout;
    requires java.sql;
    requires io;

    opens com.cvmaker.cvmaker to javafx.fxml;
    exports com.cvmaker.cvmaker;
}