package controller;

import java.io.IOException;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.SuperMarket;
import model.Membership;
import model.MMS;
import model.MMSreport;

public class SuperMarketController extends Controller<SuperMarket> {

    @FXML
    private Label headerLabel;
    @FXML
    private TextField emailFilterTf;
    @FXML
    private TextField nameFilterTf;
    @FXML
    private TableView<Membership> membershipsTv;
    @FXML
    private TableColumn<Membership, String> nameCol;
    @FXML
    private TableColumn<Membership, String> emailCol;
    @FXML
    private TableColumn<Membership, String> phoneCol;
    @FXML
    private Button deleteMemberBtn;
    @FXML
    private Button selectMemberBtn;
    @FXML
    private Button slipMemberBtn;

    public SuperMarket getSuperMarket() {
        return model;
    }

    @FXML
    private void initialize() {
        stage.getIcons().set(0, new Image("view/SuperMarket.png"));
        emailFilterTf.setPromptText("Filter by email");
        nameFilterTf.setPromptText("Filter by name");
        nameCol.setMinWidth(700 / 3);
        emailCol.setMinWidth(700 / 3);
        phoneCol.setMinWidth(700 / 3);
        // membershipsTv.setMinWidth(300);
        membershipsTv.setItems(getSuperMarket().getMemberships());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        membershipsTv.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldMemebership, newMembership) -> {
                    deleteMemberBtn.setDisable(newMembership == null);
                    slipMemberBtn.setDisable(newMembership == null);
                    selectMemberBtn.setDisable(newMembership == null);
                });

        nameFilterTf.textProperty().addListener((Observable, oldValue, newValue) -> {
            getSuperMarket().filterList(newValue, "*");
        });

        emailFilterTf.textProperty().addListener((Observable, oldValue, newValue) -> {
            getSuperMarket().filterList("*", newValue);
        });

    }

    public void addMember(ActionEvent e) throws IOException {
        System.out.println("ADD MEMBER........");
        Membership m = new Membership(null, null, null, null, null, 0);
        // getSuperMarket().getMemberships().add(m);
        m.setSuperMarket(getSuperMarket());
        ViewLoader.showStage(m, "/view/Membership.fxml",
                "Adding New Membership", new Stage());
    }

    public void deleteMember() {
        Membership m = membershipsTv.getSelectionModel().getSelectedItem();
        getSuperMarket().getMemberships().remove(m);
        // System.out.println("d");
    }

    public void selectMember() throws IOException {
        ViewLoader.showStage(getSelectedMembership(), "/view/Membership.fxml",
                "Accessing File: " + getSelectedMembership().getName(), new Stage());
        // System.out.println("HELLOO");
    }

    public void closeTab() {
        stage.close();
    }

    public void slipMember() throws IOException {
        Membership m = getSelectedMembership();
        MMSreport mmsreport = new MMSreport(m);
        // MMS mms = new MMS(getSuperMarket());
        ViewLoader.showStage(mmsreport, "/view/slip.fxml", mmsreport.getName() + " SLIP Report", new Stage());
        // System.out.println("sfsd");
    }

    public void report() throws IOException {
        MMS mms = new MMS(getSuperMarket());
        ViewLoader.showStage(mms, "/view/mms.fxml", "MMS Report", new Stage());
    }

    private Membership getSelectedMembership() {
        return membershipsTv.getSelectionModel().getSelectedItem();
    }

}
