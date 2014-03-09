/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * View_All_Extra_Expence.java
 *
 * Created on 14 Aug, 2010, 7:59:31 AM
 */

package clinic_management;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableColumn;

/**
 *
 * @author mayur
 */
public class View_All_Extra_Expence extends javax.swing.JFrame {

    /** Creates new form View_All_Extra_Expence */
     Connection c;
    Statement st;
    ResultSet rs1;
    int count=0;
    Object alldata[][];
    long total=0;
    String header[];
    public View_All_Extra_Expence() {
        initComponents();
        header=new String [] {"Sequence No", "Item Name", "Shop Name", "Person Name","Date of Payment","Cost","Remark"};

        try
        {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                c=DriverManager.getConnection("jdbc:odbc:Clinic_Management");
                System.out.println("Connection established.");
                st=(Statement) c.createStatement();
                 rs1 = st.executeQuery("Select Sequence_No from Extra_Expence order by Sequence_No");
                 while(rs1.next())
                 {
                     count++;
                 }
                 count+=2;
                 alldata=new Object[count][7];
                 count=0;
                 rs1 = st.executeQuery("Select * from Extra_Expence order by Date_Payment");
                 while(rs1.next())
                 {
                     alldata[count][0]="  "+(count+1);
                     alldata[count][1]=rs1.getString(2);
                     alldata[count][2]=rs1.getString(3);
                     alldata[count][3]=rs1.getString(4);
                     
                     java.sql.Date date_temp=rs1.getDate(5);//getString(12);
                     alldata[count][4]=date_temp.getDate()+"-"+(date_temp.getMonth()+1)+"-"+(date_temp.getYear()+1900);
                     int cost=rs1.getInt(6);
                     total+=cost;
                     alldata[count][5]=" "+cost;
                     alldata[count][6]=rs1.getString(7);
                     count++;
                 }
                alldata[count+1][4]="Total";
                alldata[count+1][5]=" "+total;
                Expence_table.setModel(new javax.swing.table.DefaultTableModel(alldata,header));
                TableColumn tb=Expence_table.getColumn("Sequence No");
                tb.setMaxWidth(85);
                tb.setMinWidth(85);
          }
         catch(Exception e){
              Dialog_class.Old_Patient_Dialog n=new Dialog_class.Old_Patient_Dialog(this, true,"Error in DataBase..So try Again...");
            n.setVisible(true);

         }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Expence_table = new javax.swing.JTable();
        OK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Clinic_MAnagement");

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36));
        jLabel1.setText("-::- List of all Extra Expence -::-");

        Expence_table.setBackground(new java.awt.Color(255, 255, 204));
        Expence_table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        Expence_table.setFont(new java.awt.Font("Tahoma", 1, 11));
        Expence_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Expence_table.setIntercellSpacing(new java.awt.Dimension(3, 3));
        Expence_table.setRowHeight(20);
        jScrollPane1.setViewportView(Expence_table);

        OK.setBackground(new java.awt.Color(0, 255, 255));
        OK.setFont(new java.awt.Font("Tahoma", 1, 18));
        OK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinic_management/buttons/OK.jpg"))); // NOI18N
        OK.setBorderPainted(false);
        OK.setContentAreaFilled(false);
        OK.setDefaultCapable(false);
        OK.setFocusPainted(false);
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1183, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(495, 495, 495)
                .addComponent(OK)
                .addContainerGap(589, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(OK)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        try {
            // TODO add your handling code here:
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(View_All_Extra_Expence.class.getName()).log(Level.SEVERE, null, ex);
        }
        Welcome_Page n=new Welcome_Page();
        n.setSize(clinic_management.Main.d.width,clinic_management.Main.d.height-40);
        n.setVisible(true);
        dispose();
    }//GEN-LAST:event_OKActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View_All_Extra_Expence().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Expence_table;
    private javax.swing.JButton OK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}