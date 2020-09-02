package dm.dev.ui.userInfo;

import dm.dev.data.User;
import dm.dev.helper.PhotoHelper;
import dm.dev.ui.Controller;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class UserInfoController implements Controller {

    @FXML
    private AnchorPane mainPanel2;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Button backButton;

    private User user;

    private Font arial = new Font("Arial", 18);

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        backButton.setOnMouseClicked(e -> goBackButtonClick(e));

        Platform.runLater(() -> {
            int rowIndex = 0;
            GridPane gridPane = new GridPane();
            AnchorPane photoPanel = new AnchorPane();
            GridPane userInfoPanel = new GridPane();

            ImageView imageView = PhotoHelper.getImageView(user.getPicture().getLarge());
            photoPanel.getChildren().add(imageView);

            userInfoPanel.add(getFormatedData("Login:"), 0, rowIndex);
            userInfoPanel.add(getFormatedData(user.getLogin().getUsername()), 1, rowIndex++);

            userInfoPanel.add(getFormatedData("Gender:"), 0, rowIndex);
            userInfoPanel.add(getFormatedData(user.getGender()), 1, rowIndex++);

            userInfoPanel.add(getFormatedData("Email:"), 0, rowIndex);
            userInfoPanel.add(getFormatedData(user.getEmail()), 1, rowIndex++);

            userInfoPanel.add(getFormatedData("Nat:"), 0, rowIndex);
            userInfoPanel.add(getFormatedData(user.getNat()), 1, rowIndex++);

            userInfoPanel.add(getFormatedData("Cell:"), 0, rowIndex);
            userInfoPanel.add(getFormatedData(user.getCell()), 1, rowIndex++);

            userInfoPanel.add(getFormatedData("City:"), 0, rowIndex);
            userInfoPanel.add(getFormatedData(user.getLocation().getCity()), 1, rowIndex++);

            gridPane.add(photoPanel, 0, 0);
            gridPane.add(userInfoPanel, 1, 0);
            contentPane.getChildren().add(gridPane);
        });
    }

    private Label getFormatedData(String text) {
        Label label = new Label(text);
        label.setFont(arial);
        return label;
    }

    private void goBackButtonClick(Event event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../main.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();

        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
    }
}
