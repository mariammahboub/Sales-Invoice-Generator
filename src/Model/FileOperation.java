package Model;

import Controller.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Scanner;

public class FileOperation extends JFrame{

    private int invoiceNum;

    public int getNextInvoiceNum() {
        int invoiceNum = 0;

        for (InvoiceHeader invoiceHeader : getInvoiceHeaders()) {
            if (invoiceHeader.getInvoiceNum() > invoiceNum)
                invoiceNum = invoiceHeader.getInvoiceNum();
        }

        return ++invoiceNum;
    }

    private InvoiceHeader invoiceHeader;
    private Test test;
    private InvoiceHeader invoiceViewField;
    private InvoiceLine lineViewField;
    private InvoiceLine invoiceLine;
    private ArrayList<InvoiceHeader> invoiceHeaders;

    public void setInvoiceHeaders(ArrayList<InvoiceHeader> invoiceHeaders) {
        this.invoiceHeaders = invoiceHeaders;
    }


    public ArrayList<InvoiceHeader> getInvoiceHeaders() {
        return invoiceHeaders;
    }

    public Controller.Test getTest() {
        return test;
    }

    public void setTest(Controller.Test test) {
        this.test = test;
    }

    public InvoiceHeader getInvoiceViewField() {
        return invoiceViewField;
    }

    public void setInvoiceViewField(InvoiceHeader invoiceViewField) {
        this.invoiceViewField = invoiceViewField;
    }

    public InvoiceLine getLineViewField() {
        return lineViewField;
    }

    public void setLineViewField(InvoiceLine lineViewField) {
        this.lineViewField = lineViewField;
    }

    public InvoiceLine getInvoiceLine() {
        return invoiceLine;
    }

    public void setInvoiceLine(InvoiceLine invoiceLine) {
        this.invoiceLine = invoiceLine;
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }


    public FileOperation() {


        System.out.println("If you need read data click 1 \n" +
                "If you add data invoice header click 2 \n" +
                "If you add data invoice Line click 3 ");
System.out.print("Choose what you need : ");

            Scanner input = new Scanner(System.in);
            int x = input.nextInt();
            if ( x== 1 ) {
                JOptionPane.showMessageDialog(null, "Choose Invoice Header excel before InvoiceLine excel");
                JFileChooser fileChooser = new JFileChooser();
                try {
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File headerFile = fileChooser.getSelectedFile();
                        Path headerPath = Paths.get(headerFile.getAbsolutePath());
                        List<String> headerLines = Files.readAllLines(headerPath);
                        //---------- check read date -----------------
                        ArrayList<InvoiceHeader> invoiceHeaderArray = new ArrayList<>();
                        for (String headerLine : headerLines) {
                            try {
                                String[] partsInvoice = headerLine.split("\n");
                                int invoiceNum = Integer.parseInt(partsInvoice[0]);
                                String invoiceDate = partsInvoice[1];
                                String customerName = partsInvoice[2];
                                InvoiceHeader invoiceHeader = new InvoiceHeader(invoiceNum, invoiceDate, customerName);
                                invoiceHeaderArray.add(invoiceHeader);

                            } catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Folder/File path is not found",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        }
                        System.out.println("excel file chooser in invoice header: " + invoiceHeaderArray);

                        result = fileChooser.showOpenDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File lineFile = fileChooser.getSelectedFile();
                            Path linePath = Paths.get(lineFile.getAbsolutePath());
                            List<String> lineLines = Files.readAllLines(linePath);
                            //---------read file Invoice Line(invoiceNumber,itemName,itemPrice,count)
                            System.out.println("Invoice Line check read");
                            for (String lineLine : lineLines) {
                                Scanner myObj = new Scanner(System.in);


                                try {


                                    String[] partsLine = lineLine.split("\n");
                                    int invoiceNum = Integer.parseInt(partsLine[0]);

                                    String itemName = partsLine[1];
                                    double itemPrice = Double.parseDouble(partsLine[2]);

                                    int count = Integer.parseInt(partsLine[3]);
                                    InvoiceHeader invoiceLoops = null;
                                    for (InvoiceHeader invoiceHeader : invoiceHeaderArray) {
                                        if (invoiceHeader.getInvoiceNum() == invoiceNum) {
                                            invoiceLoops = invoiceHeader;

                                            break;

                                        }
                                    }
                                    InvoiceLine invoiceLine = new InvoiceLine(itemName, itemPrice, count, invoiceLoops);
                                    invoiceLoops.getInvoiceLines().add(invoiceLine);
                                    System.out.println("excel file chooser in invoice line: " + invoiceLoops + invoiceLine);
                                    break;

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    JOptionPane.showMessageDialog(null, "Folder/File path is not found", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } else if (x == 2) {
                Scanner sc = new Scanner(System.in);
                JOptionPane.showMessageDialog(null, "Add Data From Console Please ");
                System.out.println("Input Date of Invoice Header 'dd/MM/YYYY' : ");
                String invDateField = sc.next();
                System.out.println("Input Customer Name of Invoice Header  : ");
                String custNameField = sc.next();
                System.out.println("Input Id invoice Number of Invoice Header : ");
                int invoiceNum = sc.nextInt();
                String invoiceDate = invDateField;
                String customerName = custNameField;
                String newInvoice = "\n Invoice Header new : [{ invoiceNum = " + invoiceNum + " - " + " , customerName = " + customerName +
                        " , " + "invoicedate = " + invoiceDate + " }] .";
                try {
                    String[] dateParts = invoiceDate.split("/");
                    if (dateParts.length < 3) {
                        JOptionPane.showMessageDialog(null, "Wrong date  format", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int day = Integer.parseInt(dateParts[0]);
                        int month = Integer.parseInt(dateParts[1]);
                        int year = Integer.parseInt(dateParts[2]);
                        if (day > 31 || month > 12) {
                            JOptionPane.showMessageDialog(null, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            InvoiceHeader invoiceHeader = new InvoiceHeader(invoiceNum, invoiceDate, customerName);
                            JOptionPane.showMessageDialog(null, "Add Data Successfully ..! ! ");
                            System.out.println(newInvoice);
                            String invoice = invoiceDate + " " + " " + customerName + " " + invoiceNum;
                            try {
                                String COMMA = " , ";
                                String NEWLINE = "\n";
                                String FILE_HEADER = "Invoice Date , Customer Name , invoice Number";
                                List<InvoiceHeader> invoices = new ArrayList<InvoiceHeader>();
                                invoices.add(new InvoiceHeader(invoiceDate, customerName, invoiceNum));
                                FileWriter myObj = new FileWriter("src\\ExcelForWrite\\InvoiceHeader.csv");

                                myObj.append(FILE_HEADER);
                                for (InvoiceHeader inv : invoices) {
                                    myObj.append(NEWLINE);
                                    myObj.append(invoiceDate);
                                    myObj.append(COMMA);
                                    myObj.append(customerName);
                                    myObj.append(COMMA);

                                    myObj.append(String.valueOf(invoiceNum));
                                }
                                myObj.flush();
                                myObj.close();
                                JOptionPane.showMessageDialog(null, "Add Data Successfully \n save this excel (InvoiceHeader.csv)  ..! ! ");

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, " Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);

                            }


                        }
                    }


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Wrong file format ", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } else if (x == 3) {
                JOptionPane.showMessageDialog(null, "Add Data From Console Please ");
                Scanner sc = new Scanner(System.in);
                System.out.println("Input Item Name of Invoice Line : ");

                String itemNameField = sc.next();
                System.out.println("Input Item Count of Invoice Line : ");

                String itemCountField = sc.next();

                System.out.println("Input Item Price of Invoice Line : ");

                int itemPriceField = sc.nextInt();
                System.out.println("Input Invoice Num of Invoice Line : ");
                int invoiceNumber = sc.nextInt();
                String itemName = itemNameField;
                String countShow = itemCountField;
                String priceShow = String.valueOf(itemPriceField);
                int count = Integer.parseInt(countShow);
                double itemPrice = Double.parseDouble(priceShow);
                double total = itemPrice * count;
                InvoiceLine invoiceLine = new InvoiceLine(itemName, itemPrice, count, total, invoiceNumber);

                JOptionPane.showMessageDialog(null, "Add Data Successfully ..! ! ");

                System.out.println("New Invoice Item : { Item name = " + itemName + " , Item Price = " + itemPrice + " , Count = " + count + " , Total = , "
                        + itemPrice * count + "  , Invoice Number = " + invoiceNumber + " }");
                String invoice = itemName + " " + " " + itemPrice + " " + count + " " + total + " " + invoiceNumber;
                try {
                    String COMMA = " , ";
                    String NEWLINE = "\n";
                    String FILE_HEADER = "Item name  , Item Price , Item Price , Count , Total ,  Invoice Number  ";
                    List<InvoiceLine> invoices = new ArrayList<InvoiceLine>();
                    invoices.add(new InvoiceLine(itemName, itemPrice, count, total, invoiceNumber));
                    FileWriter myObj = new FileWriter("src\\ExcelForWrite\\InvoiceLines.csv");

                    myObj.append(FILE_HEADER);
                    for (InvoiceLine inv : invoices) {
                        myObj.append(NEWLINE);
                        myObj.append(itemName);
                        myObj.append(COMMA);
                        myObj.append(String.valueOf(itemPrice));
                        myObj.append(COMMA);
                        myObj.append(String.valueOf(count));
                        myObj.append(COMMA);
                        myObj.append(String.valueOf(total));
                        myObj.append(COMMA);
                        myObj.append(String.valueOf(invoiceNumber));
                        myObj.append(COMMA);

                        myObj.append(String.valueOf(invoiceNum));
                    }
                    myObj.flush();
                    myObj.close();
                    JOptionPane.showMessageDialog(null, "Add Data Successfully \n save this excel (InvoiceLines.csv) ..! ! ");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, " Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(null, " Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);

            }


        }


    }

