package io.github.kschworkflows.registration;

import io.github.kschworkflows.patient.Patient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class PatientProperty {

    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty displayName = new SimpleStringProperty();

    public PatientProperty(Patient patient) {
        setId(patient.getId());
        setDisplayName(String.format("%s %s", patient.getFirstName(), patient.getLastName()));
    }

    public StringProperty idProperty() {
        return id;
    }

    public final void setId(String id) {
        this.id.setValue(id);
    }

    public final String getId() {
        return id.get();
    }

    public StringProperty displayNameProperty() {
        return displayName;
    }

    public final void setDisplayName(String id) {
        this.displayName.setValue(id);
    }

    public final String getDisplayName() {
        return displayName.get();
    }
}
