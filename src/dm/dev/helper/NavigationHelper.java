package dm.dev.helper;

import dm.dev.Main;
import dm.dev.ui.Controller;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;


public class NavigationHelper {

    /**
     * Дает возможность переходить между экранами, загружает FXML и сеттит данные, необходимые для его работы
     *
     * @param actionEvent    - текущий ивент
     * @param relativePath   - путь к fxml
     * @param setterConsumer - позволяет устанавливать данные, необходимые для корректной работы контроллера
     */
    // TODO ошибка с многопоточностью
    public static void goTo(Event actionEvent, String relativePath, Consumer<Controller> setterConsumer) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(relativePath));
            stage.setScene(new Scene(fxmlLoader.load()));
            Controller controller = fxmlLoader.getController();
            setterConsumer.accept(controller);
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
