module fr.miaou {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;

    opens fr.miaou to javafx.fxml;
    exports fr.miaou;
}