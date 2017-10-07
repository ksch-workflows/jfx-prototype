package com.experimental.pos;

import com.experimental.sales.Invoice;
import javafx.collections.ObservableList;

public interface InvoiceDao {
 
    public ObservableList<Invoice> getInvoices();
    public Invoice getInvoice(String id);
    public void saveInvoice(Invoice invoice);
    public void deleteCategory(Invoice invoice);
}
