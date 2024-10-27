package controller;

import java.io.File;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Session;
import javafx.scene.control.Button;
import javafx.scene.text.*;
import javafx.scene.control.Label;
import java.io.*;

public class SessionController extends Controller<Session> {

    @FXML
    private Button loginBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Label textMMS;
    @FXML
    private Image imageLogo;

    public Session getSession() {
        return model;
    }

    public void handleLogin(ActionEvent event) throws IOException {
        // MMSLoginController loginController = new MMSLoginController();

        // stage.getIcons().add(new Image("view/book.png"));
        ViewLoader.showStage(getSession(), "/view/MMSlogin.fxml", "Sign In", new Stage());

        // ViewLoader.y
        // System.out.println("Hello");
    }

    public void handleExit(ActionEvent event) {
        stage.close();
        // System.out.println("Get Good");
    }

    @FXML
    private void initialize() {
        // labelMMS.textAlignmentProperty().set(TextAlignment.RIGHT);
        // labelMMS.setTextAlignment(TextAlignment.RIGHT);

        // imageView.setImage(image);

    }

}
