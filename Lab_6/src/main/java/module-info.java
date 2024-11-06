module org.example.lab_6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lab_6 to javafx.fxml;
    exports org.example.lab_6;
}