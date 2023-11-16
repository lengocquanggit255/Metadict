module dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires transitive javafx.graphics;
    requires org.jsoup;
    requires java.desktop;
    requires voicerss.tts;
    requires java.net.http;
    requires langdetect;
    requires javafx.media;

    opens org.openjfx.dictionary to javafx.fxml;

    exports org.openjfx.dictionary;
    exports org.openjfx.dictionary.cmd;
}