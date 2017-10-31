package io.github.kschworkflows.ui;

import com.experimental.openmrs.Patient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatientProperty {

    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty displayName = new SimpleStringProperty();

    public PatientProperty(Patient patient) {
        setId(patient.getUuid());
        setDisplayName(patient.getDisplay());
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
