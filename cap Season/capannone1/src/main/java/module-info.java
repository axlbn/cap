module com.mycompany.capannone1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.capannone1 to javafx.fxml;
    exports com.mycompany.capannone1;
}
