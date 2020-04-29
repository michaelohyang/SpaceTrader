package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;

public class Controller {


    @FXML
    private CheckBox diff1;
    @FXML
    private CheckBox diff2;
    @FXML
    private CheckBox diff3;
    @FXML
    private CheckBox diff4;
    @FXML
    private CheckBox diff5;
    @FXML
    private TextField charName2;

    // non-FXML fields
    private boolean checked;
    private static Stage mystage;
    private static Stage newstage;

    @FXML
    public void exit() {
        System.exit(0);
    }

    @FXML
    private void toConfig() {
        classes.SpaceTradermain.changeToConfiguration();
    }

    @FXML
    void returnToConfig(ActionEvent event) {
        mystage.close();
    }

    @FXML
    void showYourName(ActionEvent event) {
        try {
            if (charName2.getLength() > 0 && checked) {
                FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/configScreen.fxml"));
                Parent root = (Parent) loader.load();
                PlayerController playerController = loader.getController();
                playerController.initData(charName2.getText(), getDifficult());
                newstage = new Stage();
                newstage.setScene(new Scene(root));
                newstage.show();
                classes.SpaceTradermain.primaryClose();
            } else {
                diff1.setSelected(false);
                diff2.setSelected(false);
                diff3.setSelected(false);
                diff4.setSelected(false);
                diff5.setSelected(false);
                charName2.clear();
                checked = false;
                mystage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(classes.SpaceTradermain.class.getResource(
                        "/fxml/errorScene.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                mystage.setTitle("Error");
                mystage.setScene(new Scene(root1));
                mystage.show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * goes to character overview page
     */
    @FXML
    private void toCharacter() {
        classes.SpaceTradermain.changeToConfiguration3();
    }


    /**
     *  goes to the main stage screen (GUI fxml)
     */
    @FXML
    private void toMainStage() {
        classes.SpaceTradermain.changeTomainStage();
    }

    /**
     *
     */
    @FXML
    private void toConfig2Normal() {
        classes.SpaceTradermain.changeToConfiguration2();
    }





    private int difficult;

    @FXML
    private void handle1box() {
        if (diff1.isSelected()) {
            diff2.setSelected(false);
            diff3.setSelected(false);
            diff4.setSelected(false);
            diff5.setSelected(false);
            checked = true;
            difficult = 1;
        }

    }
    @FXML
    private void handle2box() {
        if (diff2.isSelected()) {
            diff1.setSelected(false);
            diff3.setSelected(false);
            diff4.setSelected(false);
            diff5.setSelected(false);
            checked = true;
            difficult = 2;
        }
    }
    @FXML
    private void handle3box() {
        if (diff3.isSelected()) {
            checked = true;
            diff2.setSelected(false);
            diff1.setSelected(false);
            diff4.setSelected(false);
            diff5.setSelected(false);

            difficult = 3;
        }
    }
    @FXML
    private void handle4box() {
        if (diff4.isSelected()) {
            diff2.setSelected(false);
            diff3.setSelected(false);
            diff1.setSelected(false);
            diff5.setSelected(false);
            checked = true;
            difficult = 4;
        }
    }
    @FXML
    private void handle5box() {
        if (diff5.isSelected()) {
            diff2.setSelected(false);
            diff3.setSelected(false);
            diff4.setSelected(false);
            diff1.setSelected(false);
            checked = true;
            difficult = 5;
        }
    }

    public int getDifficult() {
        return difficult;
    }
}
