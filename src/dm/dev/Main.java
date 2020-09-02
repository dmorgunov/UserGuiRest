package dm.dev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/main.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 828, 434));
        primaryStage.setTitle("Users");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
