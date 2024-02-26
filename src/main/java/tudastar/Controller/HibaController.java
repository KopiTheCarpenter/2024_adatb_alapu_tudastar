package tudastar.Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import tudastar.DB.DbObjectHandler;
import tudastar.Model.Hiba;

import java.util.ArrayList;

public class HibaController {
    @FXML
    private TableView tv_hiba;
    @FXML
    private TextArea insert_hiba_leiras;
    @FXML
    private ComboBox archive_combobox;
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
        TableColumn bejelentes_idejeCol = new TableColumn("bejelentesIdeje");
        bejelentes_idejeCol.setCellValueFactory(new PropertyValueFactory<>("bejelentesIdeje"));
        TableColumn hiba_leirasCol = new TableColumn("hibaLeiras");
        hiba_leirasCol.setCellValueFactory(new PropertyValueFactory<>("hibaLeiras"));
        TableColumn archiv_eCol = new TableColumn("archiv_e");
        archiv_eCol.setCellValueFactory(new PropertyValueFactory<>("archiv_e"));
        tv_hiba.getColumns().addAll(IDCol, felhasznaloNevCol, bejelentes_idejeCol, hiba_leirasCol, archiv_eCol);
    }

    public void pressRead(ActionEvent actionEvent) {
        ArrayList<Hiba> hibak = doh.readHiba();
        tv_hiba.getItems().clear();
        tv_hiba.getItems().addAll(hibak);
    }

    public void pressInsert(ActionEvent actionEvent) {
        Hiba hiba = new Hiba(
                doh.maxID("hiba") + 1,
                insert_hiba_leiras.getText(),
                doh.loggedUserID

        );
        doh.insert(hiba);
        insert_hiba_leiras.setText("");
    }

    public void pressArchive(ActionEvent actionEvent) {
        //hiba nem modosítható csak admin által archiválható
        Hiba hiba = (Hiba)archive_combobox.getValue();
        doh.update(hiba);
        archive_combobox.setValue(null);
    }

    public void pressDelete(ActionEvent actionEvent) {
        Hiba hiba = (Hiba) delete_combobox.getValue();
        doh.delete(hiba);
        onSelectDelete(null);
    }

    public void onSelectUpdate(Event event) {
        ArrayList<Hiba> hibak = doh.readHiba();
        archive_combobox.getItems().clear();
        archive_combobox.getItems().addAll(hibak);
    }

    public void onSelectDelete(Event event) {
        ArrayList<Hiba> hibak = doh.readHiba();
        delete_combobox.getItems().clear();
        delete_combobox.getItems().addAll(hibak);
    }
}
