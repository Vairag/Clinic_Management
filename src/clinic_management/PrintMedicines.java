
package clinic_management;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author mayur
 */
public class PrintMedicines {
    
    public PrintMedicines(ArrayList array_list_medicines,ArrayList array_list_Lab_test,String case_no) throws Exception {
    if(!clinic_management.Diagonsis_Patient_Entry.Print_Variable.equals("cancle"))
    {
        PrinterJob pj = PrinterJob.getPrinterJob();

        Book book = new Book();
        PageFormat pf = pj.defaultPage();
        Paper paper = new Paper();
        double margin =20; // half inch
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight()- margin * 2);
        pf.setPaper(paper);
        book.append(new MyPrintable(array_list_medicines,case_no), pf);
        if(clinic_management.Diagonsis_Patient_Entry.Print_Variable_Lab_Test.equals("yes"))
        {
            book.append(new Document(array_list_Lab_test,case_no), pf);
        }
        pj.setPageable(book);
        if (pj.printDialog()) {
          try {
            pj.print();
          } catch (PrinterException e) {
            System.out.println(e);
          }
          }
      }
    }
}

class MyPrintable implements Printable {

    public Connection c;
    public Statement st;
    public ResultSet rs1;
    PreparedStatement pre;
    ArrayList array_list_medicines=null;
    String Case_No=null;
    MyPrintable(ArrayList array_list_medicines,String Case_No) {
        this.array_list_medicines=array_list_medicines;
        this.Case_No=Case_No;
    }
  public int print(Graphics g, PageFormat pf, int pageIndex)
  {
    if (pageIndex != 0)
      return NO_SUCH_PAGE;
    String pn=null,add=null,mn=null;
    Graphics2D g2 = (Graphics2D) g;
    
    g2.setPaint(Color.black);
    int count_line_number=60;
    try
    {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        c=DriverManager.getConnection("jdbc:odbc:Clinic_Management");
        System.out.println("Connection established.");
        st=(Statement) c.createStatement();
        int cs=Integer.parseInt(Case_No);
        pre=c.prepareStatement("Select * from New_Patient_Details where Case_No=?");
        pre.setInt(1, cs);
        rs1=pre.executeQuery();
        if(rs1.next())
        {
            java.util.Date current=new java.util.Date();
            pn="Patient Name:- "+rs1.getString("Patient_Name")+"                                      Date:- "+current.getDate()+"/"+(current.getMonth()+1)+"/"+(current.getYear()+1900);
            add="Address:- "+rs1.getString("Address");
            mn="Mobile No:- "+rs1.getString("Mobile_No");
        }
    }
    catch(Exception e)
    {}

    g2.setFont(new Font("Serif", Font.PLAIN, 35));
    g2.drawString("MANSI CLINIC",190,count_line_number);
    count_line_number+=40;
    g2.setFont(new Font("Serif", Font.PLAIN, 20));
    g2.drawString("Dr.Manoj R. Isotiya",25,count_line_number);
    count_line_number+=30;
    g2.drawString("Add: Madhav-Park,Rajkot                      Mobile No: 9427733186",25,count_line_number);
    count_line_number+=30;
    g2.drawLine(20,count_line_number-10, (int)pf.getImageableWidth()+10,count_line_number-10);
    count_line_number+=20;
    g2.drawString(pn,25,count_line_number);
    count_line_number+=30;
    g2.drawString(add,25,count_line_number);
    count_line_number+=30;
    g2.drawString(mn,25,count_line_number);
    count_line_number+=50;
    
    String line1=null;
    g2.drawLine(20,count_line_number-20, (int)pf.getImageableWidth()+10,count_line_number-20);
    Iterator itr = array_list_medicines.iterator();
    g2.setFont(new Font("Serif", Font.PLAIN, 15));
    int count=0;
    count_line_number+=10;
    if(clinic_management.Diagonsis_Patient_Entry.Print_Variable.equals("all"))
    {
        while(itr.hasNext())
        {

            Object temp1= itr.next();
            String element[]=(String[])temp1;
            line1=(count+1)+".  "+element[1];
            g2.drawString(line1, 50,count_line_number);
            count_line_number+=25;
            line1="Dose: "+element[2]+"   Qty: "+element[3];
            g2.drawString(line1, 100,count_line_number);
            count_line_number+=25;
            count++;
        }
    }
    else
    {
        while(itr.hasNext())
        {

            Object temp1= itr.next();
            String element[]=(String[])temp1;
            if(element[0].equals("1"))
            {
                line1=(count+1)+".  "+element[1];
                g2.drawString(line1, 50,count_line_number);
                count_line_number+=25;
                line1="Dose: "+element[2]+"   Qty: "+element[3];
                g2.drawString(line1, 100,count_line_number);
                count_line_number+=25;
                count++;
            }
        }
    }
    Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf.getImageableWidth(), pf.getImageableHeight());
    g2.draw(outline);
    
    return PAGE_EXISTS;
  }
}
 class Document implements Printable {

    public Connection c;
    public Statement st;
    public ResultSet rs1;
    PreparedStatement pre;
    ArrayList array_list_Lab_test=null;
    String Case_No=null;

    Document(ArrayList array_list_Lab_test, String case_no) {
        this.array_list_Lab_test=array_list_Lab_test;
        this.Case_No=case_no;
    }
    public int print(Graphics g, PageFormat pf, int pageIndex) {

    Graphics2D g2 = (Graphics2D) g;

    String pn=null,add=null,mn=null;

    
    g2.setPaint(Color.black);
    int count_line_number=50;
    try
    {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        c=DriverManager.getConnection("jdbc:odbc:Clinic_Management");
        System.out.println("Connection established.");
        st=(Statement) c.createStatement();
        int cs=Integer.parseInt(Case_No);
        pre=c.prepareStatement("Select * from New_Patient_Details where Case_No=?");
        pre.setInt(1, cs);
        rs1=pre.executeQuery();
        if(rs1.next())
        {
            java.util.Date current=new java.util.Date();
            pn="Patient Name:- "+rs1.getString("Patient_Name")+"                                      Date:- "+current.getDate()+"/"+(current.getMonth()+1)+"/"+(current.getYear()+1900);
            add="Address:- "+rs1.getString("Address");
            mn="Mobile No:- "+rs1.getString("Mobile_No");
        }
    }
    catch(Exception e)
    {}

    
    if (pageIndex == 1)
    {

        if(clinic_management.Diagonsis_Patient_Entry.Print_Variable_Lab_Test.equals("yes"))
        {
            g2.setFont(new Font("Serif", Font.PLAIN, 35));
            g2.drawString("MANSI CLINIC",190,count_line_number);
            count_line_number+=40;
            g2.setFont(new Font("Serif", Font.PLAIN, 20));
            g2.drawString("Dr.Manoj R. Isotiya",25,count_line_number);
            count_line_number+=30;
            g2.drawString("Add: Madhav-Park,Rajkot                      Mobile No: 9427733186",25,count_line_number);
            count_line_number+=30;
            g2.drawLine(20,count_line_number-10, (int)pf.getImageableWidth()+10,count_line_number-10);
            count_line_number+=20;
            g2.drawString(pn,25,count_line_number);
            count_line_number+=30;
            g2.drawString(add,25,count_line_number);
            count_line_number+=30;
            g2.drawString(mn,25,count_line_number);
            count_line_number+=50;

            String line1=null;
            g2.drawLine(20,count_line_number-20, (int)pf.getImageableWidth()+10,count_line_number-20);
            Iterator itr = array_list_Lab_test.iterator();
            g2.setFont(new Font("Serif", Font.PLAIN, 15));
            int count=0;
            count_line_number+=10;
            while(itr.hasNext())
            {
                Object temp1= itr.next();
                String element[]=(String[])temp1;
                line1=(count+1)+".  "+element[0];
                g2.drawString(line1, 50,count_line_number);
                count_line_number+=25;
                count++;
            }
             Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf.getImageableWidth(), pf.getImageableHeight());
             g2.draw(outline);
          }
        }
    return (PAGE_EXISTS);
    }
}
  