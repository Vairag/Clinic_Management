/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Income_Per_All_Patents.java
 *
 * Created on 18 Aug, 2010, 6:27:29 PM
 */

package clinic_management;

import java.sql.*;
import javax.swing.table.TableColumn;

/**
 *
 * @author mayur
 */
public class Income_Per_All_Patents extends javax.swing.JFrame {

    /** Creates new form Income_Per_All_Patents */
    public Connection c;
    public Statement st;
    public ResultSet rs1;
    PreparedStatement pre;
    public ResultSet rs2;
    PreparedStatement pre1;
    public Statement st1;
    Object[][] data=null;
    long final_income=0;
    public Income_Per_All_Patents() {
        initComponents();
        String[] header=new String [] {"Case No","Name","Address","Gender","Age","Mobile no","Date", "Charge"};
        
        try
        {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                c=DriverManager.getConnection("jdbc:odbc:Clinic_Management");
                System.out.println("Connection established.");
                st=(Statement) c.createStatement();

                rs1 = st.executeQuery("Select Case_No from New_Patient_Details order by Case_No");
                int count=0;
                while(rs1.next())
                {
                    count++;
                }
                count+=2;
                data=new Object[count][8];
                for (int i = 0; i < count; i++) {
                    for(int j=0;j<8;j++)
                    {
                        data[i][j]=null;
                    }
                   }
                count=0;
                rs1 = st.executeQuery("Select * from New_Patient_Details order by Case_No");
                while(rs1.next())
                {
                    int cn=rs1.getInt(1);
                    data[count][0]=(Object)("   "+cn);
                    data[count][1]=(Object)rs1.getString(2);
                    data[count][2]=(Object)rs1.getString(3);
                    data[count][3]=(Object)rs1.getString(4);
                    data[count][4]=(Object)rs1.getString(5);
                    data[count][5]=(Object)rs1.getString(11);
                    java.sql.Date date_temp=rs1.getDate(12);
                    data[count][6]=date_temp.getDate()+"-"+(date_temp.getMonth()+1)+"-"+(date_temp.getYear()+1900);
                    pre=c.prepareStatement("Select * from Diagosis_Details_All_Patient where Case_No=?");
                   pre.setInt(1, cn);
                   rs2=pre.executeQuery();
                   int total_income=0;
                   while(rs2.next())
                   {
                        int nn=rs2.getInt("Charge");
                        total_income+=nn;
                   }
                   final_income+=total_income;
                   data[count][7]=(Object)total_income;
                   count++;
                }
                data[count+1][6]="Total";
                data[count+1][7]=final_income;
                allpatient_income_table.setModel(new javax.swing.table.DefaultTableModel(data,header));
                TableColumn tb=allpatient_income_table.getColumn("Case No");
                tb.setMaxWidth(55);
                tb.setMinWidth(55);
                tb=allpatient_income_table.getColumn("Name");
                tb.setMaxWidth(300);
                tb.setMinWidth(300);
                tb=allpatient_income_table.getColumn("Address");
                tb.setMaxWidth(250);
                tb.setMinWidth(250);
                tb=allpatient_income_table.getColumn("Gender");
                tb.setMaxWidth(75);
                tb.setMinWidth(75);
                tb=allpatient_income_table.getColumn("Age");
                tb.setMaxWidth(65);
                tb.setMinWidth(65);
        }
        catch(Exception e)
        {
            System.out.print("error ");
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
        allpatient_income_table = new javax.swing.JTable();
        OK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Clinic_Management");

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36));
        jLabel1.setText("-::- This is a list of income of per Patient and total income of ur Clinic -::-");

        allpatient_income_table.setBackground(new java.awt.Color(255, 255, 204));
        allpatient_income_table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        allpatient_income_table.setFont(new java.awt.Font("Tahoma", 1, 11));
        allpatient_income_table.setModel(new javax.swing.table.DefaultTableModel(
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
        allpatient_income_table.setAutoscrolls(false);
        allpatient_income_table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        allpatient_income_table.setRowHeight(22);
        jScrollPane1.setViewportView(allpatient_income_table);

        OK.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1335, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(594, 594, 594)
                .addComponent(OK)
                .addContainerGap(642, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(OK, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        // TODO add your handling code here:
        Welcome_Page n = new Welcome_Page();
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
                new Income_Per_All_Patents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OK;
    private javax.swing.JTable allpatient_income_table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
