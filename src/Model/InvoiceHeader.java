package Model;
import View.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
public class InvoiceHeader   {

    private ArrayList<InvoiceHeader> invoiceHeaders;

    InvoiceHeader  invoiceViewField ;
    InvoiceHeader invDateField;

    InvoiceHeader custNameField;

    public InvoiceHeader(String invoiceDate, String customerName, int invoiceNum) {
    this.invoiceDate=invoiceDate;
    this.customerName=customerName;
    this.invoiceNum=invoiceNum;
    }

    public ArrayList<InvoiceHeader> getInvoiceHeaders() {
        return invoiceHeaders;
    }

    public InvoiceHeader getInvoiceViewField() {
        return invoiceViewField;
    }

    public InvoiceHeader getInvDateField() {
        return invDateField;
    }

    public InvoiceHeader getCustNameField() {
        return custNameField;
    }

    public InvoiceHeader(ArrayList<InvoiceHeader> invoiceHeaders) {
        this.invoiceHeaders = invoiceHeaders;
    }

    private int invoiceNum;
    private String invoiceDate;
    private String customerName;

    private double invoiceTotal;
    private ArrayList<InvoiceLine> invoiceLines;
    public InvoiceHeader(int invoiceNum, String invoiceDate, String customerName) {
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }

    public InvoiceHeader() {

    }

    public InvoiceHeader(InvoiceHeader invoiceNum, String invoiceDate, String customerName) {
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }


    public ArrayList<InvoiceLine> getInvoiceLines() {
        if (invoiceLines == null) {
            invoiceLines = new ArrayList<>();
        }
        return invoiceLines;
    }
    public double getInvoiceTotal() {
        double total = 0.0;
        for (InvoiceLine invoiceLine : getInvoiceLines()) {
            total = total + invoiceLine.getLineTotal();
        }
        return total;
    }
    public int getInvoiceNum() {
        return invoiceNum;
    }
    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }
    public String getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    @Override
    public String toString() {
        return " \n InvoiceHeader{ \n " + " invoiceNum : " + invoiceNum + " \n , invoiceDate= " + invoiceDate + " \n, customerName= " + customerName + '}';
    }
    public String getAsCSV() {
        return invoiceNum + "," + invoiceDate + "," + customerName;
    }




    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoiceHeader = invoiceHeaders.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return invoiceHeader.getInvoiceNum();
            case 1:
                return invoiceHeader.getInvoiceDate();
            case 2:
                return invoiceHeader.getCustomerName();
            case 3:
                return invoiceHeader.getInvoiceTotal();
            case 4:
                return invoiceHeader.getInvoiceLines();
            default:
                return "return ..";
        }
    }


}
