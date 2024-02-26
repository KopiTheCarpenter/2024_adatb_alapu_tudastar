package tudastar.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import tudastar.DB.DbObjectHandler;

public class LoginController {
    @FXML
    private TextField input_email;
    @FXML
    private PasswordField input_password;
    @FXML
    private Label output_text;

    private DbObjectHandler doh;
    private Runnable onAuthenticated;

    @FXML
    public void initialize() {
        doh = new DbObjectHandler();
    }

    public void pressLogin(ActionEvent actionEvent) {
        if (doh.authenticateUser(input_email.getText(), input_password.getText())) {
           onAuthenticated.run();
        }
        else{
            output_text.setVisible(true);
            output_text.setTextFill(Color.RED);
            output_text.setText("Helytelen email vagy jelszó!");
        }
    }

    public void setOnAuthenticated(Runnable onAuthenticated) {
        this.onAuthenticated = onAuthenticated;
    }
}
