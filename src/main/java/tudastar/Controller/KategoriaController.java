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
import tudastar.Model.Kategoria;

import java.util.ArrayList;


public class KategoriaController {
    @FXML
    private TableView tv_kategoria;
    @FXML
    private TextField insert_megnevezes;
    @FXML
    private ComboBox insert_fokategoria;
    @FXML
    private ComboBox update_id;
    @FXML
    private TextField update_megnevezes;
    @FXML
    private ComboBox update_fokategoria;
    @FXML
    private ComboBox delete_combobox;
    DbObjectHandler doh;

    @FXML
    public void initialize() {
        doh = new DbObjectHandler();
        TableColumn IDCol = new TableColumn("ID");
        IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn megnevezesCol = new TableColumn("megnevezes");
        megnevezesCol.setCellValueFactory(new PropertyValueFactory<>("megnevezes"));
        TableColumn fokategoriaCol = new TableColumn("foKategoriaID");
        fokategoriaCol.setCellValueFactory(new PropertyValueFactory<>("foKategoriaID"));
        tv_kategoria.getColumns().addAll(IDCol, megnevezesCol, fokategoriaCol);
    }

    public void pressRead(ActionEvent actionEvent) {
        ArrayList<Kategoria> kategoriak = doh.readKategoria();
        tv_kategoria.getItems().clear();
        tv_kategoria.getItems().addAll(kategoriak);
    }

    public void pressInsert(ActionEvent actionEvent) {
        Kategoria fokategoria_selected = (Kategoria) insert_fokategoria.getValue();
        Kategoria kategoria = new Kategoria(
                doh.maxID("kategoria") + 1,
                insert_megnevezes.getText(),
                fokategoria_selected != null? fokategoria_selected.getID() : null
        );
        doh.insert(kategoria);
        insert_megnevezes.setText("");
        insert_fokategoria.setValue(null);
    }

    public void pressUpdate(ActionEvent actionEvent) {
        Kategoria kategoria_selected = (Kategoria) update_id.getValue();
        Kategoria fokategoria_selected = (Kategoria) update_fokategoria.getValue();
        Kategoria kategoria = new Kategoria(
                kategoria_selected.getID(),
                update_megnevezes.getText(),
                fokategoria_selected != null? fokategoria_selected.getID() : null
        );
        doh.update(kategoria);
        update_id.setValue(null);
        update_fokategoria.setValue(null);
        update_megnevezes.setText("");
    }

    public void pressDelete(ActionEvent actionEvent) {
        Kategoria kategoria_selected = (Kategoria) delete_combobox.getValue();
        doh.delete(kategoria_selected);
        onSelectDelete(null);
    }

    public void onSelectInsert(Event event) {
        ArrayList<Kategoria> kategoriak = doh.readKategoria();
        insert_fokategoria.getItems().clear();
        insert_fokategoria.getItems().addAll(kategoriak);
    }

    public void onSelectUpdate(Event event) {
        ArrayList<Kategoria> kategoriak = doh.readKategoria();
        update_id.getItems().clear();
        update_id.getItems().addAll(kategoriak);
        update_fokategoria.getItems().clear();
        update_fokategoria.getItems().addAll(kategoriak);
    }

    public void onSelectDelete(Event event) {
        ArrayList<Kategoria> kategoriak = doh.readKategoria();
        delete_combobox.getItems().clear();
        delete_combobox.getItems().addAll(kategoriak);
    }

    public void onUpdateIdSelection(ActionEvent actionEvent) {
        Kategoria kategoria_selected = (Kategoria) update_id.getValue();
        if (kategoria_selected != null) {
            update_megnevezes.setText(kategoria_selected.getMegnevezes());
            //TODO: set fokategoria
        }
    }
}
