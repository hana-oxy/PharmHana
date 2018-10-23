package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.model.Drug;
import main.model.DrugManagerException;
import main.model.Storage;
import main.view.DrugEditDialogController;
import main.view.DrugOverviewController;
import view.Ui;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

public class DrugManagerMain extends Application {

    private Storage storage;
//    private DrugList drugs;
    private List<Drug> drugs;
    private Ui ui;
    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The data as an observable list of {@link Drug}.
     */
    private ObservableList<Drug> drugData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public DrugManagerMain() {
        // populate with data
        // load drug values from txt
        storage = new Storage("data/drugs.txt");
        try {
//            drugs = new DrugList(storage.load());
            drugs = storage.load();
        } catch (DrugManagerException e) {
//            ui.showToUser("Problem reading file. Starting with an empty task list");
//            drugs = new DrugList();

        }
        for (Drug drug : drugs) {
            drugData.add(drug);
        }
//        drugData.add(new Person("Hans", "Muster"));
//        drugData.add(new Person("Ruth", "Mueller"));
//        drugData.add(new Person("Heinz", "Kurz"));
//        drugData.add(new Person("Cornelia", "Meier"));
//        personData.add(new Person("Werner", "Meyer"));
//        personData.add(new Person("Lydia", "Kunz"));
//        personData.add(new Person("Anna", "Best"));
//        personData.add(new Person("Stefan", "Meier"));
//        personData.add(new Person("Martin", "Mueller"));
    }

//    public controller.DrugManagerMain(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        try {
//            drugs = new DrugList(storage.load());
//        } catch (DrugManagerException e) {
//            ui.showToUser("Problem reading file. Starting with an empty drug list");
//            drugs = new DrugList();
//        }
//    }

    //    public static void main(String[] args) {
//        new controller.DrugManagerMain("data/drugs.txt").launch();
//    }


    /**
     * Returns the drug file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return
     */
    public File getDrugFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(DrugManagerMain.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setDrugFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(DrugManagerMain.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("PharmHana - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("PharmHana - Your Medicine Management Application");
        }
    }

    /**
     * Returns the data as an observable list of {@link Drug}.
     */
    public ObservableList<Drug> getDrugData() {
        return drugData;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PharmHana - Your Medicine Management Application");

        // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:resources/images/pharmHana.png"));

        initRootLayout();
        showDrugOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DrugManagerMain.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showDrugOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DrugManagerMain.class.getResource("view/DrugOverview.fxml"));
            BorderPane drugOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(drugOverview);

            // Give the controller access to the main app.
            DrugOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Opens a dialog to edit details for the specified drug. If the user
     * clicks OK, the changes are saved into the provided drug object and true
     * is returned.
     *
     * @param drug the drug object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showDrugEditDialog(Drug drug) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DrugManagerMain.class.getResource("view/DrugEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Drug");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            DrugEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(drug);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
