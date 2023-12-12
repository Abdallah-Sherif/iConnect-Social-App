module com.example.iconnect {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;

    opens com.example.iconnect to javafx.fxml;
    exports com.example.iconnect;
    exports com.example.iconnect.FileManagement;
    opens com.example.iconnect.FileManagement to javafx.fxml;
    exports com.example.iconnect.Entities;
    opens com.example.iconnect.Entities to javafx.fxml;
}