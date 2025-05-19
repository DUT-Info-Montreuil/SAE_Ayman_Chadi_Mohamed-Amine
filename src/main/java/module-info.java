module fr.mbouklikha.dev.sae_glacium {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens fr.mbouklikha.dev.sae_glacium to javafx.fxml;
    exports fr.mbouklikha.dev.sae_glacium;

    exports fr.mbouklikha.dev.sae_glacium.modeles.controller;
    opens fr.mbouklikha.dev.sae_glacium.modeles.controller to javafx.fxml;
}