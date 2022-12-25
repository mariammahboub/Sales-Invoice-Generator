package Model;
import java.util.ArrayList;
public class InvoiceLine {
    private int invoiceNumber;
private double total;
    public InvoiceLine(String itemName, double itemPrice, int count, double total, int invoiceNumber) {
        this.itemName=itemName;
        this.itemPrice=itemPrice;
        this.count=count;
        this.total=total;
        this.invoiceNumber=invoiceNumber;
    }


    public InvoiceLine getItemNameField() {
        return itemNameField;
    }

    public InvoiceLine getItemCountField() {
        return itemCountField;
    }

    public InvoiceLine getItemPriceField() {
        return itemPriceField;
    }

    InvoiceLine itemNameField;
    InvoiceLine itemCountField;
    InvoiceLine itemPriceField;
    public InvoiceLine(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    private String itemName;
    private ArrayList<InvoiceLine>invoiceLines;

    public InvoiceLine() {

    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    private double itemPrice;
    private int count;
    private InvoiceHeader invoiceHeader;
    public InvoiceLine(String itemName, double itemPrice, int count, ArrayList<InvoiceHeader> invoiceHeader){

    }
    public InvoiceLine(String itemName, double itemPrice, int count, InvoiceHeader invoiceHeader) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.invoiceHeader = invoiceHeader;
    }

    public InvoiceLine(int i, String s, double v, int i1) {
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }
    public int getCount() {
        return count;
    }
    public double getLineTotal() {
        return itemPrice * count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getItemName() {
        return itemName;
    }
    public double getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    @Override
    public String toString() {
        return "\nInvoice Line { " + " \nInvoice Num : " + invoiceHeader.getInvoiceNum() + "\nitem Name : " + itemName + " \n item Price=" + itemPrice + " \ncount : " + count + '}';
    }
    public String getAsCSV() {
        return invoiceHeader.getInvoiceNum() + "," + itemName + "," + itemPrice + "," + count;
    }


    public InvoiceLine(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }
    public ArrayList<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine invoiceLine=invoiceLines.get(rowIndex);
        switch(columnIndex){
            case 0:
                return invoiceLine.getInvoiceHeader().getInvoiceNum();
            case 1:
                return invoiceLine.getItemName();
            case 2:
                return invoiceLine.getItemPrice();
            case 3:
                return invoiceLine.getCount();
            case 4:
                return invoiceLine.getLineTotal();
            default:
                return "return";
        }
    }
}
