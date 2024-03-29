/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Previous_Record_Details.java
 *
 * Created on 8 Aug, 2010, 9:11:58 PM
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
public class Income_Per_Patient extends javax.swing.JFrame {

    /** Creates new form Previous_Record_Details */
    public Connection c;
    public Statement st;
    public ResultSet rs1;
     PreparedStatement pre;
     public ResultSet rs2;
     PreparedStatement pre1;
     public Statement st1;
    String case_no_last;
    String suggetion_list[];
    String Case_No_arg_Store,Patient_Name_arg_Store;
    

    public Income_Per_Patient() {
        initComponents();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {{null, null, "", ""}},new String [] {"Number","Date Of Visit", "Charge","Remark"}));        
        try
        {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                c=DriverManager.getConnection("jdbc:odbc:Clinic_Management");
                System.out.println("Connection established.");
                st=(Statement) c.createStatement();

                rs1 = st.executeQuery("Select Case_No from New_Patient_Details order by Case_No");
                System.out.println("nt n=st.executeUpdate(sql);");

                while(rs1.next())
                {
                    //System.out.print(rs1.getString(2));
                   case_no_last=rs1.getString("Case_No");
                }
               // System.out.print(case_no_last+"System.+1900)");
                int i=0,icase_no_last=(Integer.parseInt(case_no_last)+1);
                suggetion_list=new String[icase_no_last-1];
                rs1 = st.executeQuery("Select * from New_Patient_Details order by Patient_Name");
                while(rs1.next())
                {
                    suggetion_list[i++]=rs1.getString(2);
                   //case_no_last=rs1.getString("Case_No");
                }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Case_No = new javax.swing.JTextField();
        Patient_Name = new javax.swing.JTextField();
        Find = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Ok = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Suggetion_jList = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Clinic_Management");

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36));
        jLabel1.setText("-::- Enter the Case Number Or Patient Name for history -::-");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel2.setText("Case Number");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel3.setText("OR Patient Name");

        Case_No.setFont(new java.awt.Font("Tahoma", 1, 18));
        Case_No.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Case_NoKeyReleased(evt);
            }
        });

        Patient_Name.setFont(new java.awt.Font("Tahoma", 1, 18));
        Patient_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Patient_NameKeyReleased(evt);
            }
        });

        Find.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Find.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinic_management/buttons/Find.jpg"))); // NOI18N
        Find.setBorderPainted(false);
        Find.setContentAreaFilled(false);
        Find.setDefaultCapable(false);
        Find.setFocusPainted(false);
        Find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(102, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(102, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel4.setText("Income by this Patient is following:-");

        jTable1.setBackground(new java.awt.Color(255, 255, 204));
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setGridColor(new java.awt.Color(51, 51, 51));
        jTable1.setMaximumSize(new java.awt.Dimension(2147483647, 80));
        jTable1.setRowHeight(20);
        jScrollPane1.setViewportView(jTable1);

        Ok.setBackground(new java.awt.Color(51, 255, 255));
        Ok.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinic_management/buttons/OK.jpg"))); // NOI18N
        Ok.setBorderPainted(false);
        Ok.setContentAreaFilled(false);
        Ok.setFocusPainted(false);
        Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkActionPerformed(evt);
            }
        });

        Suggetion_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Suggetion_jList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Suggetion_jListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Suggetion_jList);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("Suggetion for Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1238, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(Case_No, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(Patient_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(397, 397, 397)
                                .addComponent(Find)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel4)
                .addGap(793, 793, 793))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(595, 595, 595)
                .addComponent(Ok)
                .addContainerGap(583, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1277, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1259, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Case_No, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(Patient_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Find, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Ok)
                .addContainerGap(24, Short.MAX_VALUE))
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

    private void Case_NoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Case_NoKeyReleased
        // TODO add your handling code here:
        Patient_Name.setText("");
    }//GEN-LAST:event_Case_NoKeyReleased

    private void Patient_NameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Patient_NameKeyReleased
        // TODO add your handling code here:
        Case_No.setText("");
        String start_with=Patient_Name.getText();
       final String[] list;
       int j=0;
        for (int i = 0; i < suggetion_list.length; i++) {
            if(suggetion_list[i].startsWith(start_with))
            {
                j++;
               // list[j]=suggetion_list[i];
            }
             //System.out.println();
        }
        list=new String[j];
        j=0;
        for (int i = 0; i < suggetion_list.length; i++) {
            if(suggetion_list[i].startsWith(start_with))
            {
                list[j++]=suggetion_list[i];
            }
             //System.out.println();
        }
        Suggetion_jList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = list;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });

    }//GEN-LAST:event_Patient_NameKeyReleased

    private void Suggetion_jListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Suggetion_jListMouseClicked
        // TODO add your handling code here:
        String old_patient_name=(String) Suggetion_jList.getSelectedValue();
        Patient_Name.setText(old_patient_name);
        try {
            rs1 = st.executeQuery("Select Case_No from New_Patient_Details where Patient_Name='"+old_patient_name+"'");
            if(rs1.next())
            {
                Case_No.setText(rs1.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Previous_Record_Details.class.getName()).log(Level.SEVERE, null, ex);
        }
               Case_No_arg_Store=Case_No.getText();
        Patient_Name_arg_Store=Patient_Name.getText();
        Fill_History_Table();
    }//GEN-LAST:event_Suggetion_jListMouseClicked

    private void OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkActionPerformed
        // TODO add your handling code here:
        
                Welcome_Page n = new Welcome_Page();
                n.setSize(clinic_management.Main.d.width,clinic_management.Main.d.height-40);
                n.setVisible(true);
                dispose();
        
    }//GEN-LAST:event_OkActionPerformed

    private void FindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindActionPerformed
        // TODO add your handling code here:
        Case_No_arg_Store=Case_No.getText();
        Patient_Name_arg_Store=Patient_Name.getText();
        Fill_History_Table();

    }//GEN-LAST:event_FindActionPerformed
    void Fill_History_Table()
    {
        
        Object[][] data=null;
       
        int count=0,Seq_No=0;

        if(!(Case_No_arg_Store.equals("")&&Patient_Name_arg_Store.equals("")))
        {
            try {
                    if(Case_No_arg_Store.equals(""))
                    {
                       pre = c.prepareStatement("Select Case_No from New_Patient_Details where Patient_Name=?");
                       pre.setString(1, Patient_Name_arg_Store);
                       rs1=pre.executeQuery();
                       if(rs1.next())
                       {
                       Case_No_arg_Store=rs1.getString("Case_No");
                       Case_No.setText(Case_No_arg_Store);
                       }
                    }
                    else
                    {
                        int cs=Integer.parseInt(Case_No_arg_Store);
                       pre = c.prepareStatement("Select Patient_Name from New_Patient_Details where Case_No=?");
                       pre.setInt(1, cs);
                       rs1=pre.executeQuery();
                       if(rs1.next())
                       {
                       Patient_Name.setText(rs1.getString("Patient_Name"));
                       }
                    }
                   int cs=Integer.parseInt(Case_No_arg_Store);
                   pre = c.prepareStatement("Select * from Diagosis_Details_All_Patient where Case_No=?");
                   pre.setInt(1, cs);
                   rs1=pre.executeQuery();
                   while(rs1.next())
                   {
                        count++;
                   }
                   count+=2;
                   //System.out.print("max" +count);
                   data=new Object[count][4];
                   for (int i = 0; i < count; i++) {
                    for(int j=0;j<4;j++)
                    {
                        data[i][j]=null;
                    }
                   }
                   count=0;
                   pre=c.prepareStatement("Select * from Diagosis_Details_All_Patient where Case_No=? order by Date_Of_Entry");
                   pre.setInt(1, cs);
                   rs1=pre.executeQuery();
                   int total_income=0;
                   while(rs1.next())
                   {
                        Seq_No++;
                        data[count][0]=(Object)Seq_No;
                        java.sql.Date date_temp=rs1.getDate("Date_Of_Entry");
                        String temp=date_temp.getDate()+"-"+(date_temp.getMonth()+1)+"-"+(date_temp.getYear()+1900);
                        data[count][1]=(Object)temp;
                        int nn=rs1.getInt("Charge");
                        total_income+=nn;
                        data[count][2]=(Object)nn;
                        //data[count][3]=(Object)rs1.getInt("Cash");
                        data[count][3]=(Object)rs1.getString("Remark");
                        count++;
                   }
                   data[count+1][1]="Total";
                   data[count+1][2]=total_income;
                   jTable1.setModel(new javax.swing.table.DefaultTableModel(data, new String [] {
                "Number","Date Of Visit", "Charge","Remark"
            }
        ));
                   TableColumn tb=jTable1.getColumn("Number");
                   tb.setMaxWidth(65);
                   tb.setMinWidth(65);
 

            } catch (SQLException ex) {
                Logger.getLogger(Previous_Record_Details.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else
        {
            Dialog_class.Old_Patient_Dialog n=new Dialog_class.Old_Patient_Dialog(this, true,"Please enter patient case_no Or name first...");
               n.setVisible(true);
        }
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Previous_Record_Details("","",1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Case_No;
    private javax.swing.JButton Find;
    private javax.swing.JButton Ok;
    private javax.swing.JTextField Patient_Name;
    private javax.swing.JList Suggetion_jList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
