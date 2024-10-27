package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import model.MMS;
import model.MMSreport;
import model.Membership;

public class MMSlipController extends Controller<MMSreport> {

    @FXML
    private Label creditsLb;
    @FXML
    private Label expenseLb;
    @FXML
    private Label gasLb;
    @FXML
    private Label payperLb;
    @FXML
    private Label dollarLb;
    @FXML
    private Label deductLb;

    @FXML
    public void initialize() {
        stage.getIcons().setAll(new Image("view/edit.png"));

        creditsLb.textProperty().bind(getMms().totalCreditsProperty().asString("%.2f"));
        expenseLb.textProperty().bind(getMms().expenseProperty().asString("$%.2f"));
        gasLb.textProperty().bind(getMms().GasdeductionRateProperty().asString("%.2f"));
        payperLb.textProperty().bind(getMms().PayPerCreditProperty().asString("%.2f"));
        dollarLb.textProperty().bind(getMms().DollarAvailableProperty().asString("$%.2f"));
        deductLb.textProperty().bind(getMms().deductionRateProperty().asString("%.2f"));
        // System.out.println(getMms().gettype());
        // System.out.println("HEllo world");
        // creditsLb.setText(getMms().gettotalCredits().asString());
        // test.setGridLinesVisible(true);

    }

    public MMSreport getMms() {
        return model;

    }

    public void closeWindow() {
        stage.close();
    }

}
