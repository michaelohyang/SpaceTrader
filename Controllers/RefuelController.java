package controllers;
import classes.Player;
import classes.Ship;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class RefuelController {
    @FXML
    private Label cost;
    @FXML
    private Label credits;
    private int costRefuel;
    private Scene temp2;
    private Stage popStage = new Stage();
    @FXML
    private AnchorPane confirmPage;
    @FXML
    private AnchorPane success;
    @FXML
    private AnchorPane fail;
    private static GameGuiController gameGuiController;

    /**
     * confirms repair
     */
    @FXML
    private void refuel() {
        if (Ship.getGold() >= costRefuel) {
            Ship.setGold(Ship.getGold() - costRefuel);
            Player.getShip().get(0).setFuelCapacity(100);
            success.setVisible(true);
        } else {
            fail.setVisible(true);
        }
    }

    @FXML
    private void goBack() {
        cost.getScene().getWindow().hide();
    }

    @FXML
    private void backAndUpdate() {
        cost.getScene().getWindow().hide();
        gameGuiController = BanditController.getGui();
        gameGuiController.updateHealth();
    }

    @FXML
    private void yes() {
        costRefuel = 100;
        cost.setText("-$ " + costRefuel);
        credits.setText("Credit: " + Ship.getGold() + "");
        confirmPage.setVisible(true);
    }

    public void getScene(Scene scene) {
        temp2 = scene;
    }
    public void setPopBandit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("../fxml/refuel.fxml"));

        Parent popUp = (Parent) loader.load();
        popStage = new Stage();
        popStage.setScene(new Scene(popUp));
        popStage.initModality(Modality.APPLICATION_MODAL);
        popStage.initOwner(temp2.getWindow());
        popStage.show();

    }
}
