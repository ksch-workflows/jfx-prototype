package io.github.kschworkflows.common.ui;

import io.github.kschworkflows.ClinicalServiceApplication;
import javafx.scene.layout.Pane;

public abstract class Page {

    protected final ClinicalServiceApplication application;

    public Page(ClinicalServiceApplication application) {
        if (application == null) {
            throw new IllegalArgumentException("Application parameter must not be null.");
        }

        this.application = application;
    }

    public ClinicalServiceApplication getApplication() {
        return application;
    }

    public abstract Pane getContents();
}
