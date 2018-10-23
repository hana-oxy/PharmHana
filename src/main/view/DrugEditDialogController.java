package main.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.model.Drug;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class DrugEditDialogController {

    @FXML
    private TextField drugNameField;
    @FXML
    private TextField drugClassField;
    @FXML
    private TextField typeNameField;
    @FXML
    private TextField typeDescriptionField;
    @FXML
    private TextField sideEffectField;


    private Stage dialogStage;
    private Drug drug;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param drug
     */
    public void setPerson(Drug drug) {
        this.drug = drug;

        drugNameField.setText(drug.getDrugName());
        drugClassField.setText(drug.getDrugClass());
        typeNameField.setText(drug.getTypeName());
        typeDescriptionField.setText(drug.getTypeDescription());
        sideEffectField.setText(drug.getSideEffect());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            drug.setDrugName(drugNameField.getText());
            drug.setDrugClass(drugClassField.getText());
            drug.setTypeName(typeNameField.getText());
            drug.setTypeDescription(typeDescriptionField.getText());
            drug.setSideEffect(sideEffectField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (drugNameField.getText() == null || drugNameField.getText().length() == 0) {
            errorMessage += "Drug name is not entered!\n";
        }
        if (drugClassField.getText() == null || drugClassField.getText().length() == 0) {
            errorMessage += "Drug class is not entered!\n";
        }
        if (typeNameField.getText() == null || typeNameField.getText().length() == 0) {
            errorMessage += "Type name is not entered!\n";
        }

        if (typeDescriptionField.getText() == null || typeDescriptionField.getText().length() == 0) {
            errorMessage += "Type description is not entered!\n";
        }

        if (sideEffectField.getText() == null || sideEffectField.getText().length() == 0) {
            errorMessage += "Side effect is not entered!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}