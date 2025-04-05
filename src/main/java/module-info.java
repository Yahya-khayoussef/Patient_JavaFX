module org.example.product_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.product_javafx to javafx.fxml;
    exports org.example.product_javafx;
}