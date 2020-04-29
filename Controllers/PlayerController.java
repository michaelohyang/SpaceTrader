package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import classes.*;

public class PlayerController implements Initializable {

    @FXML
    private TextField strength;
    @FXML
    private TextField agility;
    @FXML
    private TextField intelligence;
    @FXML
    private TextField charistma;
    @FXML
    private TextField engineering;
    @FXML
    private Label nameLabel;
    @FXML
    private Label point;
    @FXML
    private Label difficulty;
    private static Stage stage;
    private static Stage newStage;
    @FXML
    private Button back;

    private int diffPoint;
    private int credits;

    public void initData(String text, int difficulty) {
        nameLabel.setText(text);
        this.difficulty.setText("The difficulty you chose is: " + difficulty);
        point.setText("You have " + 25 / difficulty  + " points");
        diffPoint = 25 / difficulty;
        credits = 1000 / difficulty;
    }

    /**
     * Change to Character page
     * @param event cause
     */
    @FXML
    void showStat(ActionEvent event) {
        try {
            if (diffPoint == 0) {
                FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../fxml/SetupSummary.fxml"));
                Parent root = (Parent) loader.load();
                SetupSummaryController charController = loader.getController();
                charController.initData(nameLabel.getText(), strength.getText(),
                            agility.getText(), intelligence.getText(),
                        charistma.getText(), engineering.getText(), credits);
                SkillConfigurationController.hidStage();
                newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass()
                        .getResource("../fxml/SkillConfigurationError.fxml"));
                Parent root = (Parent) loader.load();
                stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeNewStage() {
        newStage.close();
        SkillConfigurationController.showNewStage();
    }

    public static void hideNewStage() {
        newStage.hide();
    }
    @FXML
    void returnToConfig(ActionEvent event) {
        stage.close();
    }

    @FXML
    private void selectAgain() {
        SkillConfigurationController.closeStage();
        SpaceTradermain.primaryshow();
    }



    /**
     * Adds a skill point to Strength attribute
     */
    @FXML
    private void addPoints() {
        if (diffPoint > 0) {
            int f1 = Integer.parseInt(strength.getText());
            int f2 = f1 + 1;
            strength.setText(String.valueOf(f2));
            diffPoint--;
            point.setText("You have " + diffPoint + " points");
        }
    }

    /**
     * Take off a skill point to Strength attribute
     */
    @FXML
    private void deduPoints() {
        int f1 = Integer.parseInt(strength.getText());
        if (f1 > 0) {
            int f2 = f1 - 1;
            strength.setText(String.valueOf(f2));
            diffPoint++;
            point.setText("You have " + diffPoint + " points");
        }
    }

    /**
     * Adds a skill point to Agility attribute
     */
    @FXML
    private void addPointsAG() {
        if (diffPoint > 0) {
            int f1 = Integer.parseInt(agility.getText());
            int f2 = f1 + 1;
            agility.setText(String.valueOf(f2));
            diffPoint--;
            point.setText("You have " + diffPoint + " points");
        }
    }
    /**
     * Take off a skill point from Agility attribute
     */
    @FXML
    private void deduPointsAG() {
        int f1 = Integer.parseInt(agility.getText());
        if (f1 > 0) {
            int f2 = f1 - 1;
            agility.setText(String.valueOf(f2));
            diffPoint++;
            point.setText("You have " + diffPoint + " points");
        }
    }
    /**
     * Adds a skill point to Intelligence attribute
     */
    @FXML
    private void addPointsIn() {
        if (diffPoint > 0) {
            int f1 = Integer.parseInt(intelligence.getText());
            int f2 = f1 + 1;
            intelligence.setText(String.valueOf(f2));
            diffPoint--;
            point.setText("You have " + diffPoint + " points");
        }
    }
    /**
     * Take off a skill point from Intelligence attribute
     */
    @FXML
    private void deduPointsIn() {
        int f1 = Integer.parseInt(intelligence.getText());
        if (f1 > 0) {
            int f2 = f1 - 1;
            intelligence.setText(String.valueOf(f2));
            diffPoint++;
            point.setText("You have " + diffPoint + " points");
        }
    }
    /**
     * Adds a skill point to Charistma attribute
     */
    @FXML
    private void addPointsCh() {
        if (diffPoint > 0) {
            int f1 = Integer.parseInt(charistma.getText());
            int f2 = f1 + 1;
            charistma.setText(String.valueOf(f2));
            diffPoint--;
            point.setText("You have " + diffPoint + " points");
        }
    }
    /**
     * Take off a skill point from Christma attribute
     */
    @FXML
    private void deduPointsCh() {
        int f1 = Integer.parseInt(charistma.getText());
        if (f1 > 0) {
            int f2 = f1 - 1;
            charistma.setText(String.valueOf(f2));
            diffPoint++;
            point.setText("You have " + diffPoint + " points");
        }
    }
    /**
     * Adds a skill point to Engineering  attribute
     */
    @FXML
    private void addPointsEn() {
        if (diffPoint > 0) {
            int f1 = Integer.parseInt(engineering.getText());
            int f2 = f1 + 1;
            engineering.setText(String.valueOf(f2));
            diffPoint--;
            point.setText("You have " + diffPoint + " points");
        }
    }
    /**
     * Take off a skill point from Engineering attribute
     */
    @FXML
    private void deduPointsEn() {
        int f1 = Integer.parseInt(engineering.getText());
        if (f1 > 0) {
            int f2 = f1 - 1;
            engineering.setText(String.valueOf(f2));
            diffPoint++;
            point.setText("You have " + diffPoint + " points");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
