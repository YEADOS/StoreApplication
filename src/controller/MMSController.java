package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;
import model.MMS;
import model.MMSreport;

public class MMSController extends Controller<MMS> {

    @FXML
    private TableView<MMSreport> reportTv;

    @FXML
    private TableColumn<MMSreport, String> nameCol;
    @FXML
    private TableColumn<MMSreport, String> typeCol;
    @FXML
    private TableColumn<MMSreport, String> expenseCol;
    @FXML
    private TableColumn<MMSreport, String> creditsCol;
    @FXML
    private TableColumn<MMSreport, Double> gasCol;
    @FXML
    private TableColumn<MMSreport, Double> deductCol;
    @FXML
    private TableColumn<MMSreport, String> payPayCreditCol;
    @FXML
    private TableColumn<MMSreport, String> dollarAvaCol;
    @FXML
    private Button closeBtn;
    @FXML
    private Label totalE;
    @FXML
    private Label AvgGDR;
    @FXML
    private Label AvgPPC;
    @FXML
    private Label totalC;
    @FXML
    private Label AvgDR;
    @FXML
    private Label totalDA;

    public MMS getMMS() {
        return model;
    }

    @FXML
    private void initialize() {
        reportTv.setItems(getMMS().reports());
        stage.getIcons().setAll(new Image(("view/uts.jpeg")));

        expenseCol.setCellValueFactory(cellData -> cellData.getValue().expenseProperty().asString("$%.2f"));
        creditsCol.setCellValueFactory(cellData -> cellData.getValue().totalCreditsProperty().asString("%.2f"));
        dollarAvaCol.setCellValueFactory(cellData -> cellData.getValue().DollarAvailableProperty().asString("$%.2f"));

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        gasCol.setCellValueFactory(new PropertyValueFactory<>("GasdeductionRate"));
        deductCol.setCellValueFactory(new PropertyValueFactory<>("deductionRate"));
        payPayCreditCol.setCellValueFactory(new PropertyValueFactory<>("PayPerCredit"));

        totalE.textProperty().bind(getMMS().ExpenseProperty().asString("$%.2f"));
        AvgGDR.textProperty().bind(getMMS().GasdeductionRateProperty().asString("%.2f"));
        AvgPPC.textProperty().bind(getMMS().PayPerCreditProperty().asString("%.2f"));
        totalC.textProperty().bind(getMMS().totalCreditsProperty().asString("%.2f"));
        AvgDR.textProperty().bind(getMMS().deductionRateProperty().asString("%.2f"));
        totalDA.textProperty().bind(getMMS().DollarAvailableProperty().asString("$%.2f"));

    }

    public void closeTab() {
        stage.close();
    }

}
