package tudastar.Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tudastar.DB.DbObjectHandler;
import tudastar.Model.Felhasznalo;
import tudastar.Model.Lektor;

import java.util.ArrayList;

public class LektorController {
    @FXML
    private TableView tv_lektor;
    @FXML
    private ComboBox insert_felhasznalo;
    @FXML
    private TextField insert_tudfok;
    @FXML
    private TextField insert_intezet;
    @FXML
    private TextField insert_szakterulet;
    @FXML
    private ComboBox update_id;
    @FXML
    private TextField update_tudfok;
    @FXML
    private TextField update_intezet;
    @FXML
    private TextField update_szakterulet;
    @FXML
    private ComboBox update_felhasznalo;
    @FXML
    private ComboBox delete_combobox;
    DbObjectHandler doh;

    @FXML
    public void initialize() {
        doh = new DbObjectHandler();
        TableColumn IDCol = new TableColumn("ID");
        IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn felhasznaloNevCol = new TableColumn("felhasznaloNev");
        felhasznaloNevCol.setCellValueFactory(new PropertyValueFactory<>("felhasznaloNev"));
        TableColumn tudfokCol = new TableColumn("tudomanyos_fokozat");
        tudfokCol.setCellValueFactory(new PropertyValueFactory<>("tudomanyos_fokozat"));
        TableColumn intezetCol = new TableColumn("intezet");
        intezetCol.setCellValueFactory(new PropertyValueFactory<>("intezet"));
        TableColumn szakteruletCol = new TableColumn("szakterulet");
        szakteruletCol.setCellValueFactory(new PropertyValueFactory<>("szakterulet"));
        tv_lektor.getColumns().addAll(IDCol, felhasznaloNevCol, tudfokCol, intezetCol, szakteruletCol);
    }

    public void pressRead(ActionEvent actionEvent) {
        ArrayList<Lektor> lektorok = doh.readLektor();
        tv_lektor.getItems().clear();
        tv_lektor.getItems().addAll(lektorok);
    }

    public void pressInsert(ActionEvent actionEvent) {
        Felhasznalo felhasznalo_selected = (Felhasznalo) insert_felhasznalo.getValue();
        Lektor lektor = new Lektor(
                doh.maxID("lektor")+1,
                insert_tudfok.getText(),
                insert_intezet.getText(),
                insert_szakterulet.getText(),
                felhasznalo_selected.getID()
        );
        doh.insert(lektor);
        insert_felhasznalo.setValue(null);
        insert_intezet.setText("");
        insert_szakterulet.setText("");
        insert_tudfok.setText("");
    }

    public void pressUpdate(ActionEvent actionEvent) {
        Felhasznalo felhasznalo_selected = (Felhasznalo) update_felhasznalo.getValue();
        Lektor lektor_selected = (Lektor) update_id.getValue();
        Lektor lektor = new Lektor(
                lektor_selected.getID(),
                update_tudfok.getText(),
                update_intezet.getText(),
                update_szakterulet.getText(),
                felhasznalo_selected.getID()
        );
        doh.update(lektor);
        update_id.setValue(null);
        update_intezet.setText("");
        update_tudfok.setText("");
        update_szakterulet.setText("");
        update_felhasznalo.setValue(null);
    }

    public void pressDelete(ActionEvent actionEvent) {
        Lektor lektor = (Lektor) delete_combobox.getValue();
        doh.delete(lektor);
        onSelectDelete(null);
    }

    public void onSelectUpdate(Event event) {
        ArrayList<Lektor> lektorok =  doh.readLektor();
        update_id.getItems().clear();
        update_id.getItems().addAll(lektorok);
        ArrayList<Felhasznalo> felhasznalok = doh.readFelhasznalo();
        update_felhasznalo.getItems().clear();
        update_felhasznalo.getItems().addAll(felhasznalok);
    }

    public void onSelectDelete(Event event) {
        ArrayList<Lektor> lektorok =  doh.readLektor();
        delete_combobox.getItems().clear();
        delete_combobox.getItems().addAll(lektorok);
    }

    public void onSelectInsert(Event event) {
        ArrayList<Felhasznalo> felhasznalok = doh.readFelhasznalo();
        insert_felhasznalo.getItems().clear();
        insert_felhasznalo.getItems().addAll(felhasznalok);
    }

    public void onUpdateIdSelection(ActionEvent actionEvent) {
        Lektor lektor_selected = (Lektor) update_id.getValue();
        if(lektor_selected != null) {
            update_szakterulet.setText(lektor_selected.getSzakterulet());
            update_tudfok.setText(lektor_selected.getTudomanyos_fokozat());
            update_intezet.setText(lektor_selected.getIntezet());
            //TODO: A felhasználói adatok mezőben legyen feltöltöt érték.
        }
    }
}
