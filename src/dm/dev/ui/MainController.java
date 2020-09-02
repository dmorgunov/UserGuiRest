package dm.dev.ui;

import dm.dev.data.RandomUserResponse;
import dm.dev.data.User;
import dm.dev.helper.NavigationHelper;
import dm.dev.helper.PhotoHelper;
import dm.dev.ui.userInfo.UserInfoController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private AnchorPane contentPane;

    private final String randomuserUrl = "https://randomuser.me/api/?results=20";

    private final int MAX_ATTEMPTS = 10;

    @FXML
    public void initialize() {

        RandomUserResponse randomUserResponse = initRandomUserResponse();
        GridPane gridpane = new GridPane();

        List<User> users = randomUserResponse.getResults();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            createRow(gridpane, user, i);
        }

        ScrollPane scrollPane = new ScrollPane(gridpane);
        scrollPane.setPrefViewportHeight(370);
        scrollPane.setPrefViewportWidth(785);
        scrollPane.setFitToHeight(true);
        scrollPane.pannableProperty().set(true);
        contentPane.getChildren().add(scrollPane);

    }

    private RandomUserResponse initRandomUserResponse() {
        RandomUserResponse randomUserResponse = null;
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            try {
                randomUserResponse = getRandomUserResponse();
                break;
            } catch (Exception e) {}
        }

        if (randomUserResponse == null) {
            throw new RuntimeException("Error connecting randomuser.me");
        }
        return randomUserResponse;
    }

    private void createRow(GridPane gridpane, User user, int rowIndex) {

        GridPane gridPaneRow = new GridPane();
        ImageView avatar = PhotoHelper.getImageView(user.getPicture().getThumbnail());
        avatar.setFitHeight(50);
        avatar.setFitWidth(50);

        Label nameLabel = new Label(user.getLogin().getUsername());
        nameLabel.setFont(new Font("Arial", 24));
        gridPaneRow.add(avatar, 0, rowIndex);
        gridPaneRow.add(nameLabel, 1, rowIndex);

        gridPaneRow.setPrefWidth(785);
        gridPaneRow.setStyle("-fx-background-color:#fff;");
        gridPaneRow.setOnMouseEntered(e-> gridPaneRow.setStyle("-fx-background-color:#f9f3c5;"));
        gridPaneRow.setOnMouseExited(e-> gridPaneRow.setStyle("-fx-background-color:#fff;"));
        gridPaneRow.setOnMouseClicked(e-> showUserInfoStage(e, user));
        gridpane.add(gridPaneRow, 1, rowIndex);
    }

    private void showUserInfoStage(Event e, User user) {
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userInfo/userInfo.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            UserInfoController controller = fxmlLoader.getController();
            controller.setUser(user);
            stage.show();

        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
//        проблема с многопоточностью, нужно подождать пока выполнится setterConsumer
//        NavigationHelper.goTo(e, "ui/userInfo/userInfo.fxml", controller -> ((UserInfoController) controller).setUser(user));
    }

    public RandomUserResponse getRandomUserResponse() throws RuntimeException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget
                = client.target(randomuserUrl);
        Invocation.Builder invocationBuilder
                = webTarget.request(MediaType.APPLICATION_JSON);
        return invocationBuilder.get(RandomUserResponse.class);
    }
}
