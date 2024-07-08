module nodos_sd {
    requires javafx.controls;
    requires javafx.fxml;

    opens nodos_sd.controlador to javafx.fxml;
    exports nodos_sd;
}