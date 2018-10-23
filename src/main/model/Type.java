package main.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Type {

    private StringProperty typeName;
    private StringProperty  typeDescription;

    public Type() {  }

    public Type(String typeName, String typeDescription) {
        this.typeName = new SimpleStringProperty(typeName);
        this.typeDescription = new SimpleStringProperty(typeDescription);
    }

    public String getTypeName() {
        return typeName.get();
    }

    public void setTypeName(StringProperty typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescription() {
        return typeDescription.get();
    }

    public void setTypeDescription(StringProperty typeDescription) {
        this.typeDescription = typeDescription;
    }

    public StringProperty typeNameProperty() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName.set(typeName);
    }

    public StringProperty typeDescriptionProperty() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription.set(typeDescription);
    }
}
