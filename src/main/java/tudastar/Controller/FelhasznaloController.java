package tudastar.Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tudastar.DB.DbObjectHandler;
import tudastar.Model.Felhasznalo;

import java.util.ArrayList;

public class FelhasznaloController {
    @FXML
    private TableView tv_felhasznalo;
    @FXML
    private TextField insert_id;
    @FXML
    private TextField insert_nev;
    @FXML
    private PasswordField insert_jelszo;
    @FXML
    private TextField insert_email;
    @FXML
    private CheckBox insert_admin;

    @FXML
    private ComboBox update_id;
    @FXML
    private TextField update_nev;
    @FXML
    private PasswordField update_jelszo;
    @FXML
    private TextField update_email;
    @FXML
    private CheckBox update_admin;
    @FXML
    private ComboBox delete_combobox;
    private DbObjectHandler doh;

    @FXML
    public void initialize() {
        doh = new DbObjectHandler();
        TableColumn IDCol = new TableColumn("ID");
        IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn nevCol = new TableColumn("nev");
        nevCol.setCellValueFactory(new PropertyValueFactory<>("nev"));
        TableColumn jelszoCol = new TableColumn("jelszo");
        jelszoCol.setCellValueFactory(new PropertyValueFactory<>("jelszo"));
        TableColumn emailCol = new TableColumn("email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn adminisztrator_eCol = new TableColumn("adminisztrator_e");
        adminisztrator_eCol.setCellValueFactory(new PropertyValueFactory<>("adminisztrator_e"));
        tv_felhasznalo.getColumns().addAll(IDCol, nevCol, jelszoCol, emailCol, adminisztrator_eCol);

    }

    public void pressRead(ActionEvent actionEvent) {
        ArrayList<Felhasznalo> felhasznalok = doh.readFelhasznalo();
        tv_felhasznalo.getItems().clear();
        tv_felhasznalo.getItems().addAll(felhasznalok);
    }


    public void pressInsert(ActionEvent actionEvent) {
        Felhasznalo felhasznalo = new Felhasznalo(
                doh.maxID("felhasznalo")+1,
                insert_nev.getText(),
                insert_jelszo.getText(),
                insert_email.getText(),
                insert_admin.isSelected() ? 1 : 0
                );
        doh.insert(felhasznalo);
        insert_nev.setText("");
        insert_email.setText("");
        insert_jelszo.setText("");
        insert_admin.setSelected(false);
    }

    public void pressUpdate(ActionEvent actionEvent) {
        Felhasznalo felhasznalo_selected = (Felhasznalo) update_id.getValue();
        Felhasznalo felhasznalo = new Felhasznalo(
                felhasznalo_selected.getID(),
                update_nev.getText(),
                update_jelszo.getText(),
                update_email.getText(),
                update_admin.isSelected() ?1:0
        );
        doh.update(felhasznalo);
        update_id.setValue(null);
        update_nev.setText("");
        update_email.setText("");
        update_jelszo.setText("");
        update_admin.setSelected(false);
    }

    public void pressDelete(ActionEvent actionEvent) {
        Felhasznalo felhasznalo = (Felhasznalo) delete_combobox.getValue();
        doh.delete(felhasznalo);
        onSelectDelete(null);
    }

    public void onSelectUpdate(Event event) {
        ArrayList<Felhasznalo> felhasznalok = doh.readFelhasznalo();
        update_id.getItems().clear();
        update_id.getItems().addAll(felhasznalok);
//        update_nev.setText("");
//        update_jelszo.setText("");
//        update_email.setText("");
//        update_admin.setSelected(false);
    }

    public void onSelectDelete(Event event) {
        ArrayList<Felhasznalo> felhasznalok = doh.readFelhasznalo();
        delete_combobox.getItems().clear();
        delete_combobox.getItems().addAll(felhasznalok);
    }

    public void onUpdateIdSelection(ActionEvent actionEvent) {
        Felhasznalo felhasznalo_selected = (Felhasznalo) update_id.getValue();
        if(felhasznalo_selected!=null) {
            update_nev.setText(felhasznalo_selected.getNev());
            update_email.setText(felhasznalo_selected.getEmail());
            update_jelszo.setText(felhasznalo_selected.getJelszo());
            update_admin.setSelected(felhasznalo_selected.isAdminisztrator_e() == 1 ? true : false);
        }
    }
}
