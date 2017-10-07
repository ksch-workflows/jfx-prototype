package com.experimental.report;

import com.experimental.sales.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface ReportInterface {
 
    public ObservableList<Invoice> REPORTLIST = FXCollections.observableArrayList();
}
