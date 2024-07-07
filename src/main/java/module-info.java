module com.example.foodwastemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    exports com.example.foodwastemanagementsystem.Controller;
    opens com.example.foodwastemanagementsystem.Controller to javafx.fxml;
    exports com.example.foodwastemanagementsystem.main;
    opens com.example.foodwastemanagementsystem.main to javafx.fxml;
}