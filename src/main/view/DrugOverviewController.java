package main.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.DrugManagerMain;
import main.model.Drug;
import main.model.DrugList;
import main.model.Storage;

public class DrugOverviewController {

    @FXML
    private TableView<Drug> drugTableView;
    @FXML
    private TableColumn<Drug, String> drugNameTableColumn;
//    @FXML
//    private TableColumn<Drug, String> lastNameColumn;

    //    @FXML
//    private
//
//    @FXML
//    private Accordion drugMenuAccordion = new Accordion();

    @FXML
    private Label drugNameLabel;
    @FXML
    private Label drugClassLabel;
    @FXML
    private Label typeNameLabel;
    @FXML
    private Label typeDescriptionLabel;
    @FXML
    private Label sideEffect;

    // Reference to the main application.
    private DrugManagerMain mainApp;
    private Storage storage;
    private DrugList drugs;

    /**
     * The constructor. It is called before the initialize() method.
     */
    public DrugOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        //Initialize the drug menu accordion
//        TitledPane insulin = new TitledPane("Insulin", );
//        drugMenuAccordion.setExpandedPane();

        // Initialize the drug accordion with the drug values
//        for (Drug drug : drugs) {
            drugNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().drugNameProperty());
//        }

//        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Clear drug details.

        showDrugDetails(null);

        // Create TitledPane for the accordion
//        TitledPane insulin = new TitledPane("Insulin", new Button("insulin"));
//        drugMenuAccordion.setExpandedPane();


        // Listen for selection changes and show the drug details when changed.
        drugTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDrugDetails(newValue));

        // Listen for selection changes and show the person details when changed.
//        personTable.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     */
    public void setMainApp(DrugManagerMain mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        drugTableView.setItems(mainApp.getDrugData());
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param drug the person or null
     */
    private void showDrugDetails(Drug drug) {
        if (drug != null) {
            // Fill the labels with info from the drug object.
            drugNameLabel.setText(drug.getDrugName());
            drugClassLabel.setText(drug.getDrugClass());
            typeNameLabel.setText(drug.getTypeName());
            typeDescriptionLabel.setText(drug.getTypeDescription());
            sideEffect.setText(drug.getSideEffect());
        } else {
            // Drug is null, remove all the text.
            drugNameLabel.setText("");
            drugClassLabel.setText("");
            typeNameLabel.setText("");
            typeDescriptionLabel.setText("");
            sideEffect.setText("");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = drugTableView.getSelectionModel().getSelectedIndex();
        drugTableView.getItems().remove(selectedIndex);
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewDrug() {
        Drug tempDrug = new Drug();
        boolean okClicked = mainApp.showDrugEditDialog(tempDrug);
        if (okClicked) {
            mainApp.getDrugData().add(tempDrug);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditDrug() {
        Drug selectedDrug = drugTableView.getSelectionModel().getSelectedItem();
        if (selectedDrug != null) {
            boolean okClicked = mainApp.showDrugEditDialog(selectedDrug);
            if (okClicked) {
                showDrugDetails(selectedDrug);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Drug Selected");
            alert.setContentText("Please select a drug in the table.");

            alert.showAndWait();
        }
    }

}
