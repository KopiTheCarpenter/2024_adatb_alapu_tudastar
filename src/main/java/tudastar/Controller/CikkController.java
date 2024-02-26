package tudastar.Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tudastar.DB.DbObjectHandler;
import tudastar.Model.Cikk;
import tudastar.Model.CikkKategoriaTemakor;

import java.util.ArrayList;

public class CikkController {

    @FXML
    private ComboBox dCikkCombo;
    @FXML
    private TextField ucim;
    @FXML
    private TextField utartalom;
    @FXML
    private CheckBox ulektoralte;
    @FXML
    private TextField unyelv;
    @FXML
    private ComboBox uCikkCombo;
    @FXML
    private TextField icim;
    @FXML
    private TextField itartalom;
    @FXML
    private CheckBox ilektoralte;
    @FXML
    private TextField inyelv;
    @FXML
    private TableView tv_cikk;
    @FXML
    private TableView tv_ckt;
    DbObjectHandler doh;

    @FXML
    public void initialize() {
        doh = new DbObjectHandler();
        TableColumn IDCol = new TableColumn("ID");
        IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn cimCol = new TableColumn("cim");
        cimCol.setCellValueFactory(new PropertyValueFactory<>("cim"));
        TableColumn tartalomCol = new TableColumn("tartalom");
        tartalomCol.setCellValueFactory(new PropertyValueFactory<>("tartalom"));
        TableColumn feltoltesIdejeCol = new TableColumn("feltoltesIdeje");
        feltoltesIdejeCol.setCellValueFactory(new PropertyValueFactory<>("feltoltesIdeje"));
        TableColumn utolsoModositasCol = new TableColumn("utolsoModositas");
        utolsoModositasCol.setCellValueFactory(new PropertyValueFactory<>("utolsoModositas"));
        TableColumn modositasokSzamakCol = new TableColumn("modositasokSzama");
        modositasokSzamakCol.setCellValueFactory(new PropertyValueFactory<>("modositasokSzama"));
        TableColumn lektoralteCol = new TableColumn("lektoralte");
        lektoralteCol.setCellValueFactory(new PropertyValueFactory<>("lektoralte"));
        TableColumn nyelvCol = new TableColumn("nyelv");
        nyelvCol.setCellValueFactory(new PropertyValueFactory<>("nyelv"));
        TableColumn szerzoIDCol = new TableColumn("szerzoID");
        szerzoIDCol.setCellValueFactory(new PropertyValueFactory<>("szerzoID"));
        TableColumn utolsoModositoIDCol = new TableColumn("utolsoModositoID");
        utolsoModositoIDCol.setCellValueFactory(new PropertyValueFactory<>("utolsoModositoID"));
        tv_cikk.getColumns().addAll(IDCol, cimCol, tartalomCol, feltoltesIdejeCol, utolsoModositasCol, modositasokSzamakCol, lektoralteCol, nyelvCol, szerzoIDCol, utolsoModositoIDCol);

        TableColumn CimCol = new TableColumn("cim");
        CimCol.setCellValueFactory(new PropertyValueFactory<>("cim"));
        TableColumn kategoriaCol = new TableColumn("kategoria");
        kategoriaCol.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        TableColumn temakorCol = new TableColumn("temakor");
        temakorCol.setCellValueFactory(new PropertyValueFactory<>("temakor"));
        tv_ckt.getColumns().addAll(cimCol,kategoriaCol,temakorCol);
    }

    public void pressRead(ActionEvent e) {
        ArrayList<Cikk> cikkek = doh.readCikk();
        tv_cikk.getItems().clear();
        tv_cikk.getItems().addAll(cikkek);
    }

    public void pressInsert(ActionEvent e) {
        Cikk newcikk = new Cikk(
                doh.maxID("cikk")+1,
                icim.getText(),
                itartalom.getText(),
                ilektoralte.isSelected()?1:0,
                inyelv.getText(),
                DbObjectHandler.loggedUserID
        );
        doh.insert(newcikk);
        icim.setText("");
        itartalom.setText("");
        ilektoralte.setSelected(false);
        inyelv.setText("");
    }

    public void pressUpdate(ActionEvent e) {
        Cikk cikk_selected = (Cikk) uCikkCombo.getValue();
        Cikk newcikk = new Cikk(
                cikk_selected.getID(),
                ucim.getText(),
                utartalom.getText(),
                ulektoralte.isSelected()?1:0,
                unyelv.getText(),
                DbObjectHandler.loggedUserID
        );
        doh.update(newcikk);
        uCikkCombo.setValue(null);
        ucim.setText("");
        utartalom.setText("");
        ulektoralte.setSelected(false);
        unyelv.setText("");
    }

    public void pressDelete(ActionEvent e) {
        Cikk cikk = (Cikk)dCikkCombo.getValue();
        doh.delete(cikk);
        onSelectDelete(null);
    }

    public void onUpdateIdSelection(ActionEvent actionEvent) {
        Cikk cikk = (Cikk)uCikkCombo.getValue();
        if(cikk != null) {
            this.ucim.setText(cikk.getCim());
            this.utartalom.setText(cikk.getTartalom());
            this.ulektoralte.setSelected(cikk.getLektoralte()==1?true:false);
            this.unyelv.setText(cikk.getNyelv());
        }
    }

    public void onSelectUpdate(Event event) {
        ArrayList<Cikk> cikkek = doh.readCikk();
        uCikkCombo.getItems().clear();
        uCikkCombo.getItems().addAll(cikkek);
    }

    public void onSelectDelete(Event event) {
        ArrayList<Cikk> cikkek = doh.readCikk();
        dCikkCombo.getItems().clear();
        dCikkCombo.getItems().addAll(cikkek);
    }

    public void pressReadCKT(ActionEvent actionEvent) {
        ArrayList<CikkKategoriaTemakor> cikkek = doh.readCKT();
        tv_ckt.getItems().clear();
        tv_ckt.getItems().addAll(cikkek);
    }
}

