package io.github.kschworkflows.ui;

import javafx.scene.layout.Pane;

public abstract class Page {

    protected final ClinicalServiceApp application;

    public Page(ClinicalServiceApp application) {
        if (application == null) {
            throw new IllegalArgumentException("Application parameter must not be null.");
        }

        this.application = application;
    }

    public ClinicalServiceApp getApplication() {
        return application;
    }

    public abstract Pane getPageContents();
}
