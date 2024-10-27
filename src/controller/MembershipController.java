package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.InputException;
import model.Membership;
import model.Memberships;
import java.io.IOException;
import java.lang.reflect.Member;

import javax.swing.Action;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.TableColumnBase;

public class MembershipController extends Controller<Membership> {

    @FXML
    private GridPane memberGP;
    @FXML
    private GridPane personalGP;
    @FXML
    private TextField nameTf;
    @FXML
    private TextField emailTf;
    @FXML
    private TextField phoneTf;
    @FXML
    private TextField addressTf;
    @FXML
    private TextField idTf;
    @FXML
    private TextField expenseTf;
    @FXML
    private Text typeText;
    @FXML
    private Button updateBtn;
    @FXML
    private Button addBtn;

    @FXML
    public void initialize() {

        stage.getIcons().setAll(new Image("view/edit.png"));

        if (getMembership().getID() == null) {
            updateBtn.setDisable(true);
            getMembership().updateDetails("", "", "", "", "", 0);
        } else {
            addBtn.setDisable(true);
        }
        // nameTf.textProperty().bind(getMembership().nameProperty());
        nameTf.setText(getMembership().getName());
        emailTf.setText(getMembership().getEmail());
        phoneTf.setText(getMembership().getPhone());
        addressTf.setText(getMembership().getAddress());
        idTf.setText(getMembership().getID());
        expenseTf.setText("" + getMembership().getexpense());
        // expenseTf.textProperty().bind(getMembership().expenseProperty().asString());
        typeText.setText(getMembership().getType());

        // memberGP.setGridLinesVisible(true);
        memberGP.setAlignment(Pos.CENTER);
        memberGP.setHgap(125);
        personalGP.setAlignment(Pos.CENTER);
        personalGP.setHgap(125);

        // updateBtn.setDisable(true);

        expenseTf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                expenseTf.setText(newValue.replaceAll("[^\\d||.]", ""));
            }
        });

    }

    public Membership getMembership() {
        return model;
    }

    private String getText(TextField t) {
        return t.getText();
    }

    public void closeTab() {
        stage.close();
    }

    public void updateMember(ActionEvent e) throws IOException {
        // if (expenseEmpty()) {
        // expenseTf.setText("" + getMembership().getexpense());
        // }
        if (checkError()) {

            getMembership().updateDetails(getText(nameTf), getText(emailTf), getText(phoneTf), getText(addressTf),
                    getText(idTf), Double.parseDouble(getText(expenseTf)));
            stage.close();
        } else {
            printErrors();
        }

    }

    public void addMember(ActionEvent e) throws IOException {
        // if (expenseEmpty()) {
        // expenseTf.setText("0.0");
        // }
        if (checkError()) {
            getMembership().updateDetails(getText(nameTf), getText(emailTf), getText(phoneTf), getText(addressTf),
                    getText(idTf), Double.parseDouble(getText(expenseTf)));
            getMembership().getSuperMarket().addMembership(getMembership());
            stage.close();

        } else {
            printErrors();
        }
    }

    public boolean checkError() {
        Validator v = new Validator();
        return (v.isValid(getText(nameTf), getText(emailTf), getText(phoneTf), getText(addressTf), getText(idTf),
                Double.parseDouble(getText(expenseTf))));

    }

    public boolean expenseEmpty() {
        return (expenseTf.getText().equals(""));
    }
    // public boolean expenseEmpty() {
    // return (getText(expenseTf).equals(""));
    // }

    // public boolean empty() {
    // Validator v = new Validator();
    // for(int i = 0; i < v.errors().size(); i++ ) {
    // if (v.errors().get(i) == null) {
    // return true;
    // }
    // }
    // return false;
    // }

    public void printErrors() throws IOException {
        Validator v = new Validator();
        v.generateErrors(getText(nameTf), getText(emailTf), getText(phoneTf), getText(addressTf), getText(idTf),
                Double.parseDouble(getText(expenseTf)));
        ViewLoader.showStage(v, "/view/error.fxml", "Input Exceptions", new Stage());

    }

    // public void addMember(ActionEvent e) {
    // if ()

    // }

}
