/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * View_all_Patient_Details.java
 *
 * Created on 25 Jul, 2010, 8:48:53 AM
 */

package clinic_management;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 *
 * @author mayur
 */
public class View_all_Patient_Details extends javax.swing.JFrame implements ActionListener{

    JPanel panel3;
    JLabel l0;
    JButton ok;

    Connection c;
    Statement st;
    ResultSet rs1;
    /** Creates new form View_all_Patient_Details */
    public View_all_Patient_Details() {
  //      initComponents();
                setTitle("Clinic_Management");
                ImageIcon ic1=new ImageIcon("mayur.jpg");
		Image img=ic1.getImage();
                this.setIconImage(img);
                setLayout(new BorderLayout());
		panel3=new JPanel();

		JPanel p0=new JPanel();
                l0=new JLabel("-::- This is my all Patient Details  -::-");

		l0.setFont(new Font("Arial",Font.BOLD,40));
		l0.setForeground(Color.orange);
		p0.add(l0,FlowLayout.LEFT);
		add(p0,BorderLayout.NORTH);


		JPanel p2=new JPanel();
		p2.setLayout(new FlowLayout());
		ok=new JButton("OK");
		ok.setBackground(new Color(35,180,216));
		ok.setForeground(Color.black);
		ok.addActionListener(this);
		p2.add(ok);
		add(p2,BorderLayout.SOUTH);

		final String[] colHeads = { "Case_No.","Patient_Name","Address","Gender","Age","Height","Weight","Blood_Presure","Pulse","Blood_Group","Mobile_No","Entry_Date"};
		Object[][] data=new Object[2][12];
		ArrayList al = new ArrayList();
		int count=0;
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			c=DriverManager.getConnection("jdbc:odbc:Clinic_Management");
			System.out.println("Connection established.");
			st=c.createStatement();
			System.out.println("Statement st=c.createStatement()");
                        rs1 = st.executeQuery("Select * from New_Patient_Details order by Case_No");


			System.out.println("Select * from DrawingModel ");
			int countofrecord=1;
                        //String date_temp = null;
			while (rs1.next())
			{
				String str[]=new String[12];
				
				countofrecord++;
				str[0]=rs1.getString(1);
				str[1]=rs1.getString(2);
				str[2]=rs1.getString(3);
				str[3]=rs1.getString(4);
				str[4]=rs1.getString(5);
				str[5]=rs1.getString(6);
				str[6]=rs1.getString(7);
				str[7]=rs1.getString(8);
				str[8]=rs1.getString(9);
				str[9]=rs1.getString(10);
				str[10]=rs1.getString(11);

				java.sql.Date date_temp=rs1.getDate(12);//getString(12);
                               str[11]=date_temp.getDate()+"-"+(date_temp.getMonth()+1)+"-"+(date_temp.getYear()+1900);
                             //System.out.println(date_temp.getMonth()+ "  "+date_temp.getYear());
                                count++;
				al.add(str);
			}
                       // System.out.print(date_temp);//.substring(9,10)+date_temp.substring(6,7)+date_temp.substring(0, 3));
			c.close();
			int n=0;
			n=al.size();
			data=new Object[count][12];
			int cnt=0;
			Iterator itr = al.iterator();
			while(itr.hasNext())
			{

			Object o= itr.next();
			String element[]=(String[])o;
			System.out.print(element + " ");
				for(int i=0;i<12;i++)
				{
					data[cnt][i]=element[i];

				}
				cnt++;
			}
		}
		catch(Exception ee)
		{
			System.out.println("Connection not established."+ee);
                        Dialog_class.Old_Patient_Dialog n=new Dialog_class.Old_Patient_Dialog(this, true,"Error in DataBase..So try Again...");
            n.setVisible(true);
		}

		JTable table = new JTable(data, colHeads);
		
                // Add table to a scroll pane
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane jsp = new JScrollPane(table, v, h);
		jsp.setFont(new Font("Arial",Font.ITALIC,30));
		jsp.setForeground(Color.yellow);
		// Add scroll pane to the content pane
		add(jsp, BorderLayout.CENTER);


               // for closing windows
		addWindowListener(new WindowAdapter(){
                    public void windowClosing(WindowEvent we){
                               setVisible(false);
                               System.exit(0);
                   }
		});
    }
    


public void actionPerformed(ActionEvent ew) {
        //throw new UnsupportedOperationException("Not supported yet.");
        if("OK".equals(ew.getActionCommand()))
        {
            Welcome_Page cr2 = new Welcome_Page();
            cr2.setSize(clinic_management.Main.d.width,clinic_management.Main.d.height-40);
            cr2.setVisible(true);
            dispose();
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
   // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Clinic_Management");
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View_all_Patient_Details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
