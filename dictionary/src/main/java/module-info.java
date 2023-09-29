module dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens org.openjfx.dictionary to javafx.fxml;

    exports org.openjfx.dictionary;
    exports org.openjfx.dictionary.cmd;
}