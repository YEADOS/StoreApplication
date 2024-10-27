package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class ErrorController extends Controller<Validator> {

    @FXML
    private Label errorsLb;

    @FXML
    private Button okayBtn;

    public Validator getModel() {
        return model;
    }

    @FXML
    private void initialize() {
        // System.out.println(getModel().errors());
        stage.getIcons().setAll(new Image("view/error.png"));

        String errors = "";
        if (getModel().errors().size() >= 1) {
            for (int i = 0; i < getModel().errors().size(); i++) {
                errors += getModel().errors().get(i);
                // System.out.println(errors);
            }
        }
        errorsLb.setText(errors);

        // errorsLb.textProperty().bind(getModel().errors().get(1));

    }

    public void handleOkay() {
        getModel().clear();
        stage.close();
    }

}
