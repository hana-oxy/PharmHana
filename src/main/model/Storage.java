package main.model;

import view.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private Ui ui = new Ui();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Drug> load() throws DrugManagerException {
        List<Drug> loadedDrugs = new ArrayList<>();
        try {
            List<String> lines = getLines(filePath);
            if (lines.isEmpty()) {
                throw new DrugManagerException("");
            } else {
                for (String line : lines) {
                    if (line.trim().isEmpty()) { //ignore empty lines
                        continue;
                    }
                    loadedDrugs.add(createDrug(line)); //convert the line to a task and add to the list
                }
            }
        } catch (FileNotFoundException e) {
            ui.printError("problem encountered while loading data: " + e.getMessage());
        }
        return loadedDrugs;
    }

    public List<String> getLines(String filePath) throws FileNotFoundException, DrugManagerException {
        List<String> drugs = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        if (!s.hasNext()) {
            throw new DrugManagerException("");
        }
        while (s.hasNext()) {
            drugs.add(s.nextLine());
        }
        return drugs;
    }

    public Drug createDrug(String line) {
        Drug drug = null;
        String[] s = line.split("\\|");
        String typeName = s[0];
        String typeDescription = s[1];
        String drugName = s[2];
        String drugClass = s[3];
        String sideEffect = s[4];
        drug = new Drug(typeName, typeDescription, drugName, drugClass, sideEffect);
        return drug;
    }

//    public void save(DrugList drugs) throws IOException {
//        FileWriter fw = null;
//        try {
//            fw = new FileWriter(filePath);
//            for (int i = 0; i < drugs.getSize(); i++) {
//                fw.write(drugs.saveDrug(i));
//            }
//            fw.close();
//        } catch (ArrayIndexOutOfBoundsException e) {
//            e.getMessage();
//        }
//    }

}
