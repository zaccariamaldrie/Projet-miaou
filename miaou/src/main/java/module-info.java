module fr.miaou {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens fr.miaou to javafx.fxml;
    exports fr.miaou;
}