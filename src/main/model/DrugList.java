package main.model;

import java.util.ArrayList;
import java.util.List;

public class DrugList {

    private List<Drug> drugs;

    public DrugList() {
        this.drugs = new ArrayList<>();
    }

    public DrugList(List<Drug> drugs) {
        this.drugs = drugs;
        System.out.println("drugs in the list: " + drugs.size());
    }

    public List<Drug> getDrugList() {
        return drugs;
    }

    public void addDrug(Drug drug) {
        drugs.add(drug);
        System.out.println("drugs in the list: " + drugs.size());
    }

//    public String saveDrug(int idx) {
//        String value = drugs.get(idx).save();
//        return value;
//    }

    public void printdrugs() {
        System.out.println("drugs:");
        for (int i = 0; i < drugs.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + drugs.get(i));
        }
    }

//    public void getDrug

    public int getSize() {
        return drugs.size();
    }
}
