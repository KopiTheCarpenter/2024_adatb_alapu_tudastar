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
import tudastar.Model.Lektor;
import tudastar.Model.Nyelvtudas;

import java.util.ArrayList;

public class NyelvtudasController {
    @FXML
    private TableView tv_nyelvtudas;
    @FXML
    private TextField insert_nyelv;
    @FXML
    private TextField insert_szint;
    @FXML
    private ComboBox insert_lektor;
    @FXML
    private TextField update_nyelv;
    @FXML
    private TextField update_szint;
    @FXML
    private ComboBox update_lektor;
    @FXML
    private ComboBox update_id;
    @FXML
    private ComboBox delete_combobox;
    DbObjectHandler doh;

    @FXML
    public void initialize() {
        doh = new DbObjectHandler();
        TableColumn IDCol = new TableColumn("ID");
        IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn felhasznaloNevCol= new TableColumn("felhasznaloNev");
        felhasznaloNevCol.setCellValueFactory(new PropertyValueFactory<>("felhasznaloNev"));
        TableColumn nyelvCol = new TableColumn("nyelv");
        nyelvCol.setCellValueFactory(new PropertyValueFactory<>("nyelv"));
        TableColumn szintCol = new TableColumn("szint");
        szintCol.setCellValueFactory(new PropertyValueFactory<>("szint"));

        tv_nyelvtudas.getColumns().addAll(IDCol,felhasznaloNevCol, nyelvCol, szintCol);

    }

    public void pressRead(ActionEvent actionEvent) {
        ArrayList<Nyelvtudas> nyelvtudasok = doh.readNyelvtudas();
        tv_nyelvtudas.getItems().clear();
        tv_nyelvtudas.getItems().addAll(nyelvtudasok);
    }

    public void pressInsert(ActionEvent actionEvent) {
        Lektor lektor_selected = (Lektor) insert_lektor.getValue();
        Nyelvtudas nyelvtudas = new Nyelvtudas(
                doh.maxID("nyelvtudas") + 1,
                insert_nyelv.getText(),
                insert_szint.getText(),
                lektor_selected.getID()
        );
        doh.insert(nyelvtudas);
        insert_lektor.setValue(null);
        insert_nyelv.setText("");
        insert_szint.setText("");
    }

    public void pressUpdate(ActionEvent actionEvent) {
        Nyelvtudas nyelvtudas_selected = (Nyelvtudas) update_id.getValue();
        Lektor lektor_selected = (Lektor) update_lektor.getValue();
        Nyelvtudas nyelvtudas = new Nyelvtudas(
                nyelvtudas_selected.getID(),
                update_nyelv.getText(),
                update_szint.getText(),
                lektor_selected.getID()
        );
        doh.update(nyelvtudas);
        update_id.setValue(null);
        update_lektor.setValue(null);
        update_szint.setText("");
        update_nyelv.setText("");
    }

    public void pressDelete(ActionEvent actionEvent) {
        Nyelvtudas nyelvtudas = (Nyelvtudas) delete_combobox.getValue();
        doh.delete(nyelvtudas);
        onSelectDelete(null);
    }

    public void onSelectUpdate(Event event) {
        ArrayList<Nyelvtudas> nyelvtudasok = doh.readNyelvtudas();
        update_id.getItems().clear();
        update_id.getItems().addAll(nyelvtudasok);
        ArrayList<Lektor> lektorok = doh.readLektor();
        update_lektor.getItems().clear();
        update_lektor.getItems().addAll(lektorok);
    }

    public void onSelectDelete(Event event) {
        ArrayList<Nyelvtudas> nyelvtudasok = doh.readNyelvtudas();
        delete_combobox.getItems().clear();
        delete_combobox.getItems().addAll(nyelvtudasok);
    }

    public void onUpdateIdSelection(ActionEvent actionEvent) {
        Nyelvtudas nyelvtudas_selected = (Nyelvtudas) update_id.getValue();
        if (nyelvtudas_selected != null) {
            update_nyelv.setText(nyelvtudas_selected.getNyelv());
            update_szint.setText(nyelvtudas_selected.getSzint());
            //TODO: hozzá tartozó lektor mező automatikus kitöltése.
        }
    }


    public void onSelectInsert(Event event) {
        ArrayList<Lektor> lektorok = doh.readLektor();
        insert_lektor.getItems().clear();
        insert_lektor.getItems().addAll(lektorok);
    }
}
