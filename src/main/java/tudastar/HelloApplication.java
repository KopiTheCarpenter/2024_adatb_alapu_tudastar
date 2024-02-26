package tudastar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import tudastar.Controller.*;

import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane root = new TabPane();
        ArrayList<Tab> tabs = new ArrayList<>();

        // Load Login.fxml
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent login = loginLoader.load();
        LoginController loginController = loginLoader.getController();
        Tab loginTab = new Tab("Login", login);

        // Load Cikk.fxml
        FXMLLoader cikkLoader = new FXMLLoader(getClass().getResource("Cikk.fxml"));
        Parent cikk = cikkLoader.load();
        CikkController cikkController = cikkLoader.getController();
        Tab cikkTab = new Tab("Cikk", cikk);
        tabs.add(cikkTab);

        // Load Felhasznalo.fxml
        FXMLLoader felhasznaloLoader = new FXMLLoader(getClass().getResource("Felhasznalo.fxml"));
        Parent felhasznalo = felhasznaloLoader.load();
        FelhasznaloController felhasznaloController = felhasznaloLoader.getController();
        Tab felhasznaloTab = new Tab("Felhasználó", felhasznalo);
        tabs.add(felhasznaloTab);

        // Load Lektor.fxml
        FXMLLoader lektorLoader = new FXMLLoader(getClass().getResource("Lektor.fxml"));
        Parent lektor = lektorLoader.load();
        LektorController lektorController = lektorLoader.getController();
        Tab lektorTab = new Tab("Lektor", lektor);
        tabs.add(lektorTab);

        // Load Nyelvtudas.fxml
        FXMLLoader nyelvtudasLoader = new FXMLLoader(getClass().getResource("Nyelvtudas.fxml"));
        Parent nyelvtudas = nyelvtudasLoader.load();
        NyelvtudasController nyelvtudasController = nyelvtudasLoader.getController();
        Tab nyelvtudasTab = new Tab("Nyelvtudás", nyelvtudas);
        tabs.add(nyelvtudasTab);

        FXMLLoader hibaLoader = new FXMLLoader(getClass().getResource("Hiba.fxml"));
        Parent hiba = hibaLoader.load();
        HibaController hibaController = hibaLoader.getController();
        Tab hibaTab = new Tab("Hiba", hiba);
        tabs.add(hibaTab);


        FXMLLoader temakorLoader = new FXMLLoader(getClass().getResource("Temakor.fxml"));
        Parent temakor = temakorLoader.load();
        TemakorController temakorController = temakorLoader.getController();
        Tab temakorTab = new Tab("Temakor", temakor);
        tabs.add(temakorTab);

        FXMLLoader kategoriaLoader = new FXMLLoader(getClass().getResource("Kategoria.fxml"));
        Parent kategoria = kategoriaLoader.load();
        KategoriaController kategoriaController = kategoriaLoader.getController();
        Tab kategoriaTab = new Tab("Kategoria", kategoria);
        tabs.add(kategoriaTab);

        FXMLLoader SpeckoLoader = new FXMLLoader(getClass().getResource("Specko.fxml"));
        Parent specko = SpeckoLoader.load();
        SpeckoController speckoController = SpeckoLoader.getController();
        Tab SpeckoTab = new Tab("Specko", specko);
        tabs.add(SpeckoTab);

        root.getTabs().add(loginTab);

        for (Tab tab : tabs) {
            tab.setClosable(false);
        }
        // Switch to the menu tab when the user is authenticated
        loginController.setOnAuthenticated(() -> {
            root.getTabs().remove(loginTab);
            root.getTabs().addAll(tabs);
            root.getSelectionModel().select(cikkTab);
            root.setTabDragPolicy(TabPane.TabDragPolicy.REORDER);
        });

        primaryStage.setTitle("Tudastar");
        primaryStage.setScene(new Scene(root, 1000, 430));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}