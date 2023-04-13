package lk.ijse.hostel.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane furniturePane;
    public static void navigate(Routes routes,AnchorPane pane) throws IOException {
        Navigation.furniturePane=pane;
        Navigation.furniturePane.getChildren().clear();
        Stage window=(Stage) Navigation.furniturePane.getScene().getWindow();

        switch (routes){
            case furniture:
                window.setTitle("Furniture Site");
                initUi("FurnitureForm.fxml");
                break;
            case selling:
                window.setTitle("Selling Details");
                initUi("SellingForm.fxml");
                break;

        }
    }



    public static void initUi(String location) throws IOException {
        Navigation.furniturePane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("../view/"+location)));

    }

}


