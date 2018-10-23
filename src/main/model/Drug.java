package main.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Drug extends Type {

    private StringProperty drugName;
    private StringProperty drugClass;
    private StringProperty sideEffect;

    public Drug() {  }

    public Drug(String typeName, String typeDescription, String drugName, String drugClass, String sideEffect) {
        super(typeName, typeDescription);
        this.drugName = new SimpleStringProperty(drugName);
        this.drugClass = new SimpleStringProperty(drugClass);
        this.sideEffect = new SimpleStringProperty(sideEffect);
    }

    public String getDrugName() {
        return drugNameProperty().get();
    }

    public void setDrugName(String drugName) {
        drugNameProperty().set(drugName);
    }

    public String getDrugClass() {
        return drugClassProperty().get();
    }

    public void setDrugClass(String drugClass) {
        drugClassProperty().set(drugClass);
    }

    public String getSideEffect() {
        return sideEffectProperty().get();
    }

    public void setSideEffect(String sideEffect) {
        sideEffectProperty().set(sideEffect);
    }

    public StringProperty drugNameProperty() {
        return drugName;
    }

    public StringProperty drugClassProperty() {
        return drugClass;
    }

    public StringProperty sideEffectProperty() {
        return sideEffect;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drugName='" + drugName + '\'' +
                ", drugClass='" + drugClass + '\'' +
                ", sideEffect='" + sideEffect + '\'' +
                '}';
    }

//    public String save() {
//        return this.drugClass;
//    }

}
