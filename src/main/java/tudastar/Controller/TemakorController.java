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
import tudastar.Model.Temakor;

import java.util.ArrayList;

public class TemakorController {
    @FXML
    private TableView tv_temakor;
    @FXML
    private TextField insert_megnevezes;
    @FXML
    private ComboBox insert_fotemakor;
    @FXML
    private ComboBox update_id;
    @FXML
    private TextField update_megnevezes;
    @FXML
    private ComboBox update_fotemakor;
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
        TableColumn fotemakorCol = new TableColumn("foTemakorID");
        fotemakorCol.setCellValueFactory(new PropertyValueFactory<>("foTemakorID"));
        tv_temakor.getColumns().addAll(IDCol, megnevezesCol, fotemakorCol);
    }

    public void pressRead(ActionEvent actionEvent) {
        ArrayList<Temakor> temakorok = doh.readTemakor();
        tv_temakor.getItems().clear();
        tv_temakor.getItems().addAll(temakorok);
    }

    public void pressInsert(ActionEvent actionEvent) {
        Temakor fotemakor_selected = (Temakor) insert_fotemakor.getValue();
        Temakor temakor = new Temakor(
                doh.maxID("temakor") + 1,
                insert_megnevezes.getText(),
                fotemakor_selected !=null? fotemakor_selected.getID() : null
        );
        doh.insert(temakor);
        insert_megnevezes.setText("");
        insert_fotemakor.setValue(null);
    }

    public void pressUpdate(ActionEvent actionEvent) {
        Temakor temakor_selected = (Temakor) update_id.getValue();
        Temakor fotemakor_selected = (Temakor) update_fotemakor.getValue();
        Temakor temakor = new Temakor(
                temakor_selected.getID(),
                update_megnevezes.getText(),
                fotemakor_selected !=null? temakor_selected.getID() : null
        );
        doh.update(temakor);
        update_id.setValue(null);
        update_fotemakor.setValue(null);
        update_megnevezes.setText("");
    }

    public void pressDelete(ActionEvent actionEvent) {
        Temakor temakor_selected = (Temakor) delete_combobox.getValue();
        doh.delete(temakor_selected);
        onSelectDelete(null);
    }

    public void onSelectInsert(Event event) {
        ArrayList<Temakor> temakork = doh.readTemakor();
        insert_fotemakor.getItems().clear();
        insert_fotemakor.getItems().addAll(temakork);
    }

    public void onSelectUpdate(Event event) {
        ArrayList<Temakor> temakork = doh.readTemakor();
        update_id.getItems().clear();
        update_id.getItems().addAll(temakork);
        update_fotemakor.getItems().clear();
        update_fotemakor.getItems().addAll(temakork);
    }

    public void onSelectDelete(Event event) {
        ArrayList<Temakor> temakork = doh.readTemakor();
        delete_combobox.getItems().clear();
        delete_combobox.getItems().addAll(temakork);
    }

    public void onUpdateIdSelection(ActionEvent actionEvent) {
        Temakor temakor_selected = (Temakor) update_id.getValue();
        if (temakor_selected != null) {
            update_megnevezes.setText(temakor_selected.getMegnevezes());
            //TODO: set fotemakor
        }
    }
}
