module com.example.javafx_practice {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    opens com.example.javafx_practice to javafx.fxml;
    exports com.example.javafx_practice;
}