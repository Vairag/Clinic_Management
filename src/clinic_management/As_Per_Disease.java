/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Search_Expence_Between_to_Date.java
 *
 * Created on 14 Aug, 2010, 10:31:12 AM
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
public class As_Per_Disease extends javax.swing.JFrame {

    /** Creates new form Search_Expence_Between_to_Date */
    public Connection c;
    public Statement st;
    public ResultSet rs1,rs2,rs3;
     PreparedStatement pre,pre1,pre2;
    int count=0,total=0;
    String Item_List_String[],header[];
    Object alldata[][];
    public As_Per_Disease() {
        initComponents();
        header=new String [] {"Number","Case_No.","Patient_Name","Address","Gender","Age","Height","Weight","Blood_Presure","Pulse","Blood_Group","Mobile_No","Entry_Date"};
        java.util.Date current=new java.util.Date();
        int d=current.getDate();
        Date_Start.setSelectedIndex(d);
        Date_End.setSelectedIndex(d);
        int m=current.getMonth();
        Month_Start.setSelectedIndex(m+1);
        Month_End.setSelectedIndex(m+1);
        int y=current.getYear();
        Year_Start.setSelectedIndex(y-106);
        Year_End.setSelectedIndex(y-106);
        
        try
        {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                c=DriverManager.getConnection("jdbc:odbc:Clinic_Management");
                System.out.println("Connection established.");
                st=(Statement) c.createStatement();
                count=0;
                rs1 = st.executeQuery("Select * from Disease_Name_Details order by Disease_Name");
                 while(rs1.next())
                 {
                     count++;
                 }
                 count++;
                 Item_List_String=new String[count];
                 count=1;
                 Item_List_String[0]=" ";
                 rs1 = st.executeQuery("Select * from Disease_Name_Details order by Disease_Name");
                 while(rs1.next())
                 {
                     Item_List_String[count++]=rs1.getString(1);
                 }

                 Disease_nmae_jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(Item_List_String));
        }
        catch(Exception e){
                Dialog_class.Old_Patient_Dialog n=new Dialog_class.Old_Patient_Dialog(this, true,"Error in DataBase..So try Again...");
                n.setVisible(true);
        }
        alldata=null;
        Expence_table.setModel(new javax.swing.table.DefaultTableModel(alldata,header));
          
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
        Date_Start = new javax.swing.JComboBox();
        Month_Start = new javax.swing.JComboBox();
        Year_Start = new javax.swing.JComboBox();
        Find = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        Expence_table = new javax.swing.JTable();
        OK = new javax.swing.JButton();
        Second_Panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Date_End = new javax.swing.JComboBox();
        Year_End = new javax.swing.JComboBox();
        Month_End = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        Disease_nmae_jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Clinic_Management");

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36));
        jLabel1.setText("-::- Select Date  -::- ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel2.setText("Start Date");

        Date_Start.setFont(new java.awt.Font("Tahoma", 1, 18));
        Date_Start.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        Month_Start.setFont(new java.awt.Font("Tahoma", 1, 18));
        Month_Start.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        Year_Start.setFont(new java.awt.Font("Tahoma", 1, 18));
        Year_Start.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));

        Find.setBackground(new java.awt.Color(51, 255, 255));
        Find.setFont(new java.awt.Font("Tahoma", 1, 18));
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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel5.setText("Your search result are following....");

        jSeparator1.setBackground(new java.awt.Color(51, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(51, 255, 255));

        Expence_table.setBackground(new java.awt.Color(255, 255, 204));
        Expence_table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)));
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
        Expence_table.setRowHeight(22);
        jScrollPane1.setViewportView(Expence_table);

        OK.setBackground(new java.awt.Color(51, 255, 255));
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

        Second_Panel.setBackground(new java.awt.Color(255, 204, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel3.setText("End Date");

        Date_End.setFont(new java.awt.Font("Tahoma", 1, 18));
        Date_End.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        Year_End.setFont(new java.awt.Font("Tahoma", 1, 18));
        Year_End.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));

        Month_End.setFont(new java.awt.Font("Tahoma", 1, 18));
        Month_End.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        javax.swing.GroupLayout Second_PanelLayout = new javax.swing.GroupLayout(Second_Panel);
        Second_Panel.setLayout(Second_PanelLayout);
        Second_PanelLayout.setHorizontalGroup(
            Second_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Second_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Date_End, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Month_End, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Year_End, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        Second_PanelLayout.setVerticalGroup(
            Second_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Second_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Second_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Date_End, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Year_End, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Month_End, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Disease Name");

        Disease_nmae_jComboBox1.setBackground(new java.awt.Color(255, 255, 204));
        Disease_nmae_jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Date_Start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Month_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Year_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Disease_nmae_jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Second_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Find))
                .addContainerGap(322, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1183, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1183, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(535, 535, 535)
                .addComponent(OK)
                .addContainerGap(549, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Month_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Year_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Date_Start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(Second_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(Disease_nmae_jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Find, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(OK, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
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
        try {
            // TODO add your handling code here:
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(Search_Expence_Between_to_Date.class.getName()).log(Level.SEVERE, null, ex);
        }
        Welcome_Page n = new Welcome_Page();
        n.setSize(clinic_management.Main.d.width,clinic_management.Main.d.height-40);
        n.setVisible(true);
        dispose();
    }//GEN-LAST:event_OKActionPerformed

    private void FindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindActionPerformed
        // TODO add your handling code here:
        alldata=null;
        java.sql.Date EndDate = null;
        java.sql.Date StartDate=null;
        String disease_name_retrive=(String) Disease_nmae_jComboBox1.getSelectedItem();
        if(disease_name_retrive.equals(" "))
        {
            Dialog_class.Old_Patient_Dialog n=new Dialog_class.Old_Patient_Dialog(this, true,"Please,First select Disease Name....");
            n.setVisible(true);
        }else
        {
            try{
                int sd=Integer.parseInt((String)Date_Start.getSelectedItem());
                int sm=Integer.parseInt((String)Month_Start.getSelectedItem());
                int sy=Integer.parseInt((String)Year_Start.getSelectedItem());
                StartDate=new java.sql.Date(sy-1900,sm-1,sd);

                int ed = Integer.parseInt((String) Date_End.getSelectedItem());
                int em=Integer.parseInt((String)Month_End.getSelectedItem());
                int ey=Integer.parseInt((String)Year_End.getSelectedItem());
                EndDate=new java.sql.Date(ey-1900,em-1,ed);
                if(0<(StartDate.compareTo(EndDate)))
                {
                    throw new ArithmeticException();
                }

               pre = c.prepareStatement("Select * from Diagosis_Details_All_Patient where Date_Of_Entry>=? and Date_Of_Entry<=?");
               pre.setDate(1, StartDate);
               pre.setDate(2, EndDate);
               rs1=pre.executeQuery();
               count=0;
               while(rs1.next())
               {
                   int d_k=rs1.getInt("Disease_Key");
                   pre2 = c.prepareStatement("Select * from Disease_Key_Entry where Disease_Key=?");
                    pre2.setInt(1,d_k);
                    rs3=pre2.executeQuery();
                    while(rs3.next())
                    {
                        if(rs3.getString("Disease").equals(disease_name_retrive))
                        {
                            count++;
                            break;
                        }
                    }
               }
               //count++;
               alldata=new Object[count][13];
               for(int i=0;i<count;i++)
                   for(int j=0;j<13;j++)
                       alldata[i][j]=null;
               count=0;

               rs1=pre.executeQuery();
               while(rs1.next())
               {
                   int cn=rs1.getInt("Case_No");
                    int d_k=rs1.getInt("Disease_Key");
                   pre2 = c.prepareStatement("Select * from Disease_Key_Entry where Disease_Key=?");
                    pre2.setInt(1,d_k);
                    rs3=pre2.executeQuery();
                    while(rs3.next())
                    {
                        if(rs3.getString("Disease").equals(disease_name_retrive))
                        {

                            
                            pre1 = c.prepareStatement("Select * from New_Patient_Details where Case_No=?");
                            pre1.setInt(1,cn);
                            rs2=pre1.executeQuery();
                            if(rs2.next())
                            {
                                alldata[count][0]="  "+(count+1);
                                alldata[count][1]=rs2.getString(1);
                                alldata[count][2]=rs2.getString(2);
                                alldata[count][3]=rs2.getString(3);
                                alldata[count][4]=rs2.getString(4);
                                alldata[count][5]=rs2.getString(5);
                                alldata[count][6]=rs2.getString(6);
                                alldata[count][7]=rs2.getString(7);
                                alldata[count][8]=rs2.getString(8);
                                alldata[count][9]=rs2.getString(9);
                                alldata[count][10]=rs2.getString(10);
                                alldata[count][11]=rs2.getString(11);

                                java.sql.Date date_temp=rs2.getDate(12);//getString(12);
                                alldata[count][12]=date_temp.getDate()+"-"+(date_temp.getMonth()+1)+"-"+(date_temp.getYear()+1900);

                                count++;
                            }
                            break;
                        }
                   }
               }
            Expence_table.setModel(new javax.swing.table.DefaultTableModel(alldata,header));
            TableColumn tb=Expence_table.getColumn("Number");
            tb.setMaxWidth(55);
            tb.setMinWidth(55);
            tb=Expence_table.getColumn("Case_No.");
            tb.setMaxWidth(60);
            tb.setMinWidth(60);
            tb=Expence_table.getColumn("Patient_Name");
            tb.setMaxWidth(250);
            tb.setMinWidth(250);
            tb=Expence_table.getColumn("Address");
            tb.setMaxWidth(150);
            tb.setMinWidth(150);
            }
            catch(NumberFormatException ee)
            {
                Expence_table.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {
                  {null, null, null, null,null,null,null,null, null, null, null,null,null,}},header));
                Dialog_class.Old_Patient_Dialog n=new Dialog_class.Old_Patient_Dialog(this, true,"Please,Select your date....");
                n.setVisible(true);
            }
            catch(ArithmeticException ee)
            {
                Expence_table.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {
                  {null, null, null, null,null,null,null,null, null, null, null,null,null,}},header));
                Dialog_class.Old_Patient_Dialog n=new Dialog_class.Old_Patient_Dialog(this, true,"Please,Select your date proper way....");
                n.setVisible(true);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                Expence_table.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {
                    {null, null, null, null,null,null,null,null, null, null, null,null,null,}},header));
               Dialog_class.Old_Patient_Dialog n=new Dialog_class.Old_Patient_Dialog(this, true,"Error in DataBase..So try Again...");
                n.setVisible(true);
            }
        }
    }//GEN-LAST:event_FindActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new As_Per_Disease().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Date_End;
    private javax.swing.JComboBox Date_Start;
    private javax.swing.JComboBox Disease_nmae_jComboBox1;
    private javax.swing.JTable Expence_table;
    private javax.swing.JButton Find;
    private javax.swing.JComboBox Month_End;
    private javax.swing.JComboBox Month_Start;
    private javax.swing.JButton OK;
    private javax.swing.JPanel Second_Panel;
    private javax.swing.JComboBox Year_End;
    private javax.swing.JComboBox Year_Start;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

}
