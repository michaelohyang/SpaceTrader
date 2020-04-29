package classes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpaceTradermain extends Application {
    private static Scene mainStage;
    private static Stage myStage;
    private static Scene configStage;
    private static Scene configStage2;
    private static Scene configStage3;
    private static SpaceTradermain trader = new SpaceTradermain();

    public void start(Stage stage) throws Exception {


        //welcome screen
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("../fxml/WelcomeScreen.fxml"));

        //set-up screen
        FXMLLoader loader2 = new FXMLLoader();
        Parent conf = loader2.load(getClass().getResource("../fxml/GameSetupScreen.fxml"));

        //character configurations screen
        FXMLLoader loader3 = new FXMLLoader();
        Parent conf2 = loader3.load(getClass().getResource("../fxml/SkillConfiguration.fxml"));

        //character overview screen
        FXMLLoader loader4 = new FXMLLoader();
        Parent conf3 = loader4.load(getClass().getResource("../fxml/SetupSummary.fxml"));

        mainStage = new Scene(root);
        configStage = new Scene(conf);
        configStage2 = new Scene(conf2);
        configStage3 = new Scene(conf3);

        myStage = stage;

        myStage.setScene(mainStage);

        myStage.show();
    }

    public static void changeToConfiguration() {
        myStage.setScene(configStage);
    } // goes to set up screen

    public static void changeToConfiguration2() {
        myStage.setScene(configStage2);
    } // goes to character config screen

    public static void changeToConfiguration3() {
        myStage.setScene(configStage3);
    } // goes to character overview screen

    public static void changeTomainStage() {
        myStage.setScene(mainStage);
    }

    public static void primaryClose() {
        myStage.hide();
    }

    public static void primaryshow() {
        myStage.show();
    }

    public static void restartGame() throws Exception {
        trader.start(myStage);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
