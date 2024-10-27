package controller;

import java.io.IOException;

import javax.swing.text.View;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Session;
import model.SuperMarket;
import model.SuperMarkets;

public class MMSLoginController extends Controller<Session> {

    @FXML
    private PasswordField passwordTf;
    @FXML
    private TextField emailTf;
    @FXML
    private Label errorLabel;
    @FXML
    private GridPane test;

    public Session getSession() {
        return model;
    }

    private String getPassword() {
        return passwordTf.getText();
    }

    private String getEmail() {
        return emailTf.getText();
    }

    public SuperMarket getSuperMarket() {
        return getSession().getSuperMarket(getEmail(), getPassword());
    }

    @FXML
    private void initialize() {
        stage.getIcons().add(new Image("view/book.png"));
        // test.setGridLinesVisible(true);
    }

    public void handleOK(ActionEvent event) throws IOException {
        // System.out.println(getEmail() + getPassword());
        SuperMarket s = getSuperMarket();
        if (s != null) {
            ViewLoader.showStage(getSuperMarket(), "/view/SuperMarket.fxml",
                    "Session Admin: " + getSuperMarket().getName(), stage);

            // System.out.println("Hello " + s.getName());
        } else {
            emailTf.setText("");
            passwordTf.setText("");
            errorLabel.setText("Incorrect Login Details");
        }
    }

    public void handleCancel(ActionEvent event) throws IOException {
        stage.close();
        // ViewLoader.showStage(new Session(), "/view/session.fxml", "MMS - SuperMarket
        // Mode", stage);
    }

}
