module tudastar {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires com.oracle.database.jdbc;
    requires java.naming;


    exports tudastar;
    opens tudastar to javafx.fxml;
    exports tudastar.Controller;
    opens tudastar.Controller to javafx.fxml;
    opens tudastar.Model to javafx.base;
}