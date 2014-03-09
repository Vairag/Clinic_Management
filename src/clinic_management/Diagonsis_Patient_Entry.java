/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Diagonsis_Patient_Entry.java
 *
 * Created on 7 Aug, 2010, 8:16:38 AM
 */

package clinic_management;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableColumn;

/**
 *
 * @author mayur
 */
public class Diagonsis_Patient_Entry extends javax.swing.JFrame {

    public Connection c;
    public Statement st;
    public ResultSet rs1;
    PreparedStatement pre;
    String Case_No,Patient_Name,Header_Medicine_Name[],Header_Lab_Test_Name[];
    public static String deleted_Medicine_Name=null,Print_Variable="cancle",Print_Variable_Lab_Test="no";
    Object MedicineName_Object[][],Lab_Test_Object[][];
    ArrayList arraylist_data,arraylist_LabTest;
    int Lab_Test_Key=0,flag_lab_test_visiable=0;

    /** Creates new form Diagonsis_Patient_Entry */
    public Diagonsis_Patient_Entry(String cn,String pn) {
        initComponents();
        arraylist_data= new ArrayList();
        arraylist_LabTest= new ArrayList();
        MedicineName_Object=null;
        Lab_Test_Object=null;
        Header_Medicine_Name=new String [] {"No.","In/Out","Medicine Name","Dose","Qty"};
        Header_Lab_Test_Name=new String[]{"No.","Lab Test Name","Count","Normal count"};
        Medicine_Table.setModel(new javax.swing.table.DefaultTableModel(MedicineName_Object,Header_Medicine_Name));
        TableColumn tb=Medicine_Table.getColumn("Qty");
        tb.setMaxWidth(60);
        tb.setMinWidth(60);
        tb=Medicine_Table.getColumn("Dose");
        tb.setMaxWidth(70);
        tb.setMinWidth(70);
        tb=Medicine_Table.getColumn("No.");
        tb.setMaxWidth(30);
        tb.setMinWidth(30);
        tb=Medicine_Table.getColumn("In/Out");
        tb.setMaxWidth(45);
        tb.setMinWidth(45);


        Lab_Test_jTable2.setModel(new javax.swing.table.DefaultTableModel(Lab_Test_Object,Header_Lab_Test_Name));
        tb=Lab_Test_jTable2.getColumn("No.");
        tb.setMaxWidth(30);
        tb.setMinWidth(30);
        tb=Lab_Test_jTable2.getColumn("Count");
        tb.setMaxWidth(70);
        tb.setMinWidth(70);
        LabTest_Panel.setVisible(false);


        Case_No=cn;
        Patient_Name=pn;
        Case_No_Textbox.setText(Case_No);
        Patient_Name_Textbox.setText(Patient_Name);

         java.util.Date current=new java.util.Date();
        int day=current.getDate();
        Date_Combo.setSelectedIndex(day);
        int m=current.getMonth();
        Month_Combo.setSelectedIndex(m+1);
        int y=current.getYear();
        //System.out.print((y+1900)+ "   ");
        Year_Combo.setSelectedIndex(y-106);
         try
        {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                c=DriverManager.getConnection("jdbc:odbc:Clinic_Management");
                System.out.println("Connection established.");
                st=(Statement) c.createStatement();
                System.out.println("Statement st=c.createStatement()");
                Update_list();
                //for counting of debit amount
                 pre=c.prepareStatement("Select Debit from Diagosis_Details_All_Patient where Case_No=?");
                   pre.setInt(1, Integer.parseInt(Case_No));
                   rs1=pre.executeQuery();
                int Debit_Amount=0,visite_count=0;;
                System.out.print("Debit_Amount");
                while(rs1.next())
                {
                    visite_count++;
                    Debit_Amount+=rs1.getInt("Debit");
                    System.out.print(Debit_Amount);
                }
                Debit_text.setText(Debit_Amount+"");
                Visite_Count.setText(" "+visite_count);

        }
         catch(Exception e)
         {
            Dialog_class.Old_Patient_Dialog n=new Dialog_class.Old_Patient_Dialog(this, true,"Error in DataBase,So try again...");
            n.setVisible(true);
         }
    }
    // for updating and adding item in list
    void Update_list()
    {
         try
         {
                rs1 = st.executeQuery("Select Disease_Name from Disease_Name_Details order by Disease_Name");
                System.out.println("nt n=st.executeUpdate(sql);");
                int count=0;
                while(rs1.next())
                {
                    count++;
                }
                final String Disease_Name_list[]=new String[count];
                rs1 = st.executeQuery("Select Disease_Name from Disease_Name_Details order by Disease_Name");
                System.out.println("nt n=st.executeUpdate(sql);");
                count=0;
                while(rs1.next())
                {
                    Disease_Name_list[count++]=rs1.getString("Disease_Name");
                }
                Diagonsis_list.setModel(new javax.swing.AbstractListModel() {
                    String[] strings = Disease_Name_list;
                    public int getSize() { return strings.length; }
                    public Object getElementAt(int i) { return strings[i]; }
                });

                rs1 = st.executeQuery("Select Symptom_Name from Symptom_Name_Details order by Symptom_Name");
                System.out.println("nt n=st.executeUpdate(sql);");
                count=0;
                while(rs1.next())
                {
                    count++;
                }
                final String Symptom_Name_list[]=new String[count];
                rs1 = st.executeQuery("Select Symptom_Name from Symptom_Name_Details order by Symptom_Name");
                System.out.println("nt n=st.executeUpdate(sql);");
                count=0;
                while(rs1.next())
                {
                    Symptom_Name_list[count++]=rs1.getString("Symptom_Name");
                }
                 Symptom_List.setModel(new javax.swing.AbstractListModel() {
                    String[] strings = Symptom_Name_list;
                    public int getSize() { return strings.length; }
                    public Object getElementAt(int i) { return strings[i]; }
                });
                rs1 = st.executeQuery("Select Medicine_Name from Medicine_Name_Details order by Medicine_Name");
            //    System.out.println("nt n=st.executeUpdate(sql);");
                count=0;
                while(rs1.next())
                {
                    count++;
                }
                final String Medicine_Name_list[]=new String[count+1];
                rs1 = st.executeQuery("Select Medicine_Name from Medicine_Name_Details order by Medicine_Name");
                System.out.println("nt n=st.executeUpdate(sql);");
                count=0;
                Medicine_Name_list[count++]=" ";
                while(rs1.next())
                {
                    Medicine_Name_list[count++]=rs1.getString("Medicine_Name");
                }
                Medicine_name_combobox.setModel(new javax.swing.DefaultComboBoxModel(Medicine_Name_list));              
                update_lab_Test_list();
         }
         catch(SQLException e)
         {
             
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Case_No_Textbox = new javax.swing.JTextField();
        Patient_Name_Textbox = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Date_Combo = new javax.swing.JComboBox();
        Month_Combo = new javax.swing.JComboBox();
        Year_Combo = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        Previous_Disease = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Medicine_Table = new javax.swing.JTable();
        Medicine_name_combobox = new javax.swing.JComboBox();
        Dose_ComboBox = new javax.swing.JComboBox();
        Add_Medicine_Button = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        Day_ComboBox = new javax.swing.JComboBox();
        In_Out_jComboBox1 = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Delete_Medicine_name_jButton1 = new javax.swing.JButton();
        Medicine_Update_Button = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        Visite_Count = new javax.swing.JTextField();
        Lab_Test_Button = new javax.swing.JButton();
        Lab_Panel = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        Lab_Test_Name_ComboBox = new javax.swing.JComboBox();
        Normal_Count_TextBox = new javax.swing.JTextField();
        Count_Test_TextBox = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        Suggetion_for_Lab_ComoBox = new javax.swing.JComboBox();
        Send_email_panel = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        Yes_RadioButton = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Diagonsis_list = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Symptom_List = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Diseases_Update_Button = new javax.swing.JButton();
        Symptop_Update_Button = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Ok = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Cash_Text = new javax.swing.JTextField();
        Charge_Text = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        rupees = new javax.swing.JLabel();
        Remark_TextBox = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Debit_text = new javax.swing.JTextField();
        Cancle = new javax.swing.JButton();
        Print_and_ok_Buttons = new javax.swing.JButton();
        LabTest_Panel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Lab_Test_jTable2 = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        Lab_Test_jComboBox2 = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        Count_Lab_TEst_jTextField1 = new javax.swing.JTextField();
        Lab_Test_Add_jButton3 = new javax.swing.JButton();
        Lab_test_delete_jButton2 = new javax.swing.JButton();
        LAb_Test_Button2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Clinic_Management");

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setText("-::- Patient Case number and Name are following -::- ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("Case Number");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("And Patient Name");

        Case_No_Textbox.setFont(new java.awt.Font("Tahoma", 1, 12));
        Case_No_Textbox.setEnabled(false);

        Patient_Name_Textbox.setFont(new java.awt.Font("Tahoma", 1, 12));
        Patient_Name_Textbox.setEnabled(false);

        jSeparator1.setBackground(new java.awt.Color(153, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(153, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel4.setText("-::- Enter Disease details for this Patient -::-");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("Date Of Entry");

        Date_Combo.setFont(new java.awt.Font("Tahoma", 1, 12));
        Date_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        Month_Combo.setFont(new java.awt.Font("Tahoma", 1, 12));
        Month_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        Year_Combo.setFont(new java.awt.Font("Tahoma", 1, 12));
        Year_Combo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018" }));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel16.setText("Previous Disease Details of this patient ");

        Previous_Disease.setFont(new java.awt.Font("Tahoma", 1, 14));
        Previous_Disease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinic_management/buttons/History.jpg"))); // NOI18N
        Previous_Disease.setBorderPainted(false);
        Previous_Disease.setContentAreaFilled(false);
        Previous_Disease.setFocusPainted(false);
        Previous_Disease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Previous_DiseaseActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        Medicine_Table.setBackground(new java.awt.Color(255, 255, 204));
        Medicine_Table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Medicine_Table.setFont(new java.awt.Font("Tahoma", 1, 11));
        Medicine_Table.setModel(new javax.swing.table.DefaultTableModel(
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
        Medicine_Table.setRowHeight(20);
        jScrollPane4.setViewportView(Medicine_Table);

        Medicine_name_combobox.setBackground(new java.awt.Color(255, 255, 204));
        Medicine_name_combobox.setFont(new java.awt.Font("Tahoma", 1, 11));
        Medicine_name_combobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Dose_ComboBox.setBackground(new java.awt.Color(255, 255, 204));
        Dose_ComboBox.setFont(new java.awt.Font("Tahoma", 1, 11));
        Dose_ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1-0-1", "1-1-1", "1-0-0", "0-0-1", "1-1-0", "0-1-1", "0-1-0", "1-1-1-1" }));

        Add_Medicine_Button.setFont(new java.awt.Font("Tahoma", 1, 11));
        Add_Medicine_Button.setText("ADD");
        Add_Medicine_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_Medicine_ButtonActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Medicine Name");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel18.setText("Days");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel19.setText("Dose");

        Day_ComboBox.setBackground(new java.awt.Color(255, 255, 204));
        Day_ComboBox.setFont(new java.awt.Font("Tahoma", 1, 11));
        Day_ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));

        In_Out_jComboBox1.setBackground(new java.awt.Color(255, 255, 204));
        In_Out_jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11));
        In_Out_jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1" }));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel26.setText("In/Out");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("Medicine");

        Delete_Medicine_name_jButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        Delete_Medicine_name_jButton1.setText("Delete");
        Delete_Medicine_name_jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_Medicine_name_jButton1ActionPerformed(evt);
            }
        });

        Medicine_Update_Button.setFont(new java.awt.Font("Tahoma", 1, 12));
        Medicine_Update_Button.setText("ADD");
        Medicine_Update_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Medicine_Update_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Medicine_Update_Button)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Medicine_name_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Dose_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(Day_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Delete_Medicine_name_jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(In_Out_jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Add_Medicine_Button))))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jLabel18)
                        .addComponent(jLabel26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Medicine_name_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Dose_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Day_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(In_Out_jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Add_Medicine_Button))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Medicine_Update_Button)
                    .addComponent(Delete_Medicine_name_jButton1))
                .addContainerGap())
        );

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel20.setText("Visite count");

        Visite_Count.setFont(new java.awt.Font("Tahoma", 1, 12));
        Visite_Count.setEnabled(false);

        Lab_Test_Button.setText("Lab Test");
        Lab_Test_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Lab_Test_ButtonActionPerformed(evt);
            }
        });

        Lab_Panel.setBackground(new java.awt.Color(153, 255, 204));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel21.setText("Lab Test Name");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel22.setText("Normal Range of Count");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel23.setText("Count");

        Lab_Test_Name_ComboBox.setBackground(new java.awt.Color(255, 255, 204));
        Lab_Test_Name_ComboBox.setFont(new java.awt.Font("Tahoma", 1, 12));
        Lab_Test_Name_ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Normal_Count_TextBox.setFont(new java.awt.Font("Tahoma", 1, 13));

        Count_Test_TextBox.setFont(new java.awt.Font("Tahoma", 1, 13));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel24.setText("Suggetion for Lab");

        Suggetion_for_Lab_ComoBox.setBackground(new java.awt.Color(255, 255, 204));
        Suggetion_for_Lab_ComoBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Send_email_panel.setBackground(new java.awt.Color(153, 255, 204));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel25.setText("Send e-mail");

        Yes_RadioButton.setBackground(new java.awt.Color(255, 204, 204));
        buttonGroup1.add(Yes_RadioButton);
        Yes_RadioButton.setFont(new java.awt.Font("Tahoma", 1, 12));
        Yes_RadioButton.setText("Yes");

        jRadioButton2.setBackground(new java.awt.Color(255, 204, 204));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("No");

        javax.swing.GroupLayout Send_email_panelLayout = new javax.swing.GroupLayout(Send_email_panel);
        Send_email_panel.setLayout(Send_email_panelLayout);
        Send_email_panelLayout.setHorizontalGroup(
            Send_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Send_email_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addGap(81, 81, 81)
                .addComponent(Yes_RadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addContainerGap(105, Short.MAX_VALUE))
        );
        Send_email_panelLayout.setVerticalGroup(
            Send_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Send_email_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Send_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(Yes_RadioButton)
                    .addComponent(jRadioButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Lab_PanelLayout = new javax.swing.GroupLayout(Lab_Panel);
        Lab_Panel.setLayout(Lab_PanelLayout);
        Lab_PanelLayout.setHorizontalGroup(
            Lab_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Lab_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Lab_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24))
                .addGap(10, 10, 10)
                .addGroup(Lab_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Suggetion_for_Lab_ComoBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Count_Test_TextBox)
                    .addComponent(Lab_Test_Name_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Normal_Count_TextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
            .addComponent(Send_email_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Lab_PanelLayout.setVerticalGroup(
            Lab_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Lab_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Lab_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(Lab_Test_Name_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Lab_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Normal_Count_TextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Lab_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Count_Test_TextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Lab_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(Suggetion_for_Lab_ComoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Send_email_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        Diagonsis_list.setFont(new java.awt.Font("Tahoma", 1, 12));
        Diagonsis_list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(Diagonsis_list);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("Disease");

        Symptom_List.setFont(new java.awt.Font("Tahoma", 1, 12));
        Symptom_List.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(Symptom_List);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setText("Symptom");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setText("If its not in list press add");

        Diseases_Update_Button.setFont(new java.awt.Font("Tahoma", 1, 10));
        Diseases_Update_Button.setText("ADD");
        Diseases_Update_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Diseases_Update_ButtonActionPerformed(evt);
            }
        });

        Symptop_Update_Button.setFont(new java.awt.Font("Tahoma", 1, 10));
        Symptop_Update_Button.setText("ADD");
        Symptop_Update_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Symptop_Update_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Symptop_Update_Button))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(Diseases_Update_Button))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Diseases_Update_Button)
                        .addComponent(Symptop_Update_Button))))
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        Ok.setFont(new java.awt.Font("Tahoma", 1, 18));
        Ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinic_management/buttons/OK.jpg"))); // NOI18N
        Ok.setBorderPainted(false);
        Ok.setContentAreaFilled(false);
        Ok.setDefaultCapable(false);
        Ok.setFocusPainted(false);
        Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel10.setText("Charge");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel11.setText("Cash");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel15.setText("Remark");

        Cash_Text.setFont(new java.awt.Font("Tahoma", 1, 13));

        Charge_Text.setFont(new java.awt.Font("Tahoma", 1, 13));
        Charge_Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Charge_TextKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinic_management/buttons/rupee-Symbol.jpg"))); // NOI18N

        rupees.setFont(new java.awt.Font("Tahoma", 1, 12));
        rupees.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinic_management/buttons/rupee-Symbol.jpg"))); // NOI18N

        Remark_TextBox.setFont(new java.awt.Font("Tahoma", 1, 13));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel14.setText("Previous(TOTAL) Debit");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinic_management/buttons/rupee-Symbol.jpg"))); // NOI18N

        Debit_text.setFont(new java.awt.Font("Tahoma", 1, 18));
        Debit_text.setText("0");
        Debit_text.setEnabled(false);

        Cancle.setFont(new java.awt.Font("Tahoma", 1, 18));
        Cancle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinic_management/buttons/Cancle.jpg"))); // NOI18N
        Cancle.setBorderPainted(false);
        Cancle.setContentAreaFilled(false);
        Cancle.setFocusPainted(false);
        Cancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancleActionPerformed(evt);
            }
        });

        Print_and_ok_Buttons.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinic_management/buttons/print and ok.jpg"))); // NOI18N
        Print_and_ok_Buttons.setBorderPainted(false);
        Print_and_ok_Buttons.setContentAreaFilled(false);
        Print_and_ok_Buttons.setDefaultCapable(false);
        Print_and_ok_Buttons.setFocusPainted(false);
        Print_and_ok_Buttons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Print_and_ok_ButtonsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rupees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel15))
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(Ok, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Print_and_ok_Buttons)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Remark_TextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Cash_Text, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Charge_Text, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Debit_text, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Charge_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Cash_Text, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(Debit_text, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rupees, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(Remark_TextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Print_and_ok_Buttons, javax.swing.GroupLayout.PREFERRED_SIZE, 54, Short.MAX_VALUE)
                                    .addComponent(Ok, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Cancle, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
        );

        LabTest_Panel.setBackground(new java.awt.Color(255, 204, 204));

        Lab_Test_jTable2.setBackground(new java.awt.Color(255, 255, 204));
        Lab_Test_jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Lab_Test_jTable2.setFont(new java.awt.Font("Tahoma", 1, 11));
        Lab_Test_jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        Lab_Test_jTable2.setRowHeight(20);
        jScrollPane3.setViewportView(Lab_Test_jTable2);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel27.setText("Lab Test Name");

        Lab_Test_jComboBox2.setBackground(new java.awt.Color(255, 255, 204));
        Lab_Test_jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11));
        Lab_Test_jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel28.setText("Count");

        Count_Lab_TEst_jTextField1.setFont(new java.awt.Font("Tahoma", 1, 11));

        Lab_Test_Add_jButton3.setFont(new java.awt.Font("Tahoma", 1, 11));
        Lab_Test_Add_jButton3.setText("ADD");
        Lab_Test_Add_jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Lab_Test_Add_jButton3ActionPerformed(evt);
            }
        });

        Lab_test_delete_jButton2.setFont(new java.awt.Font("Tahoma", 1, 11));
        Lab_test_delete_jButton2.setText("Delete");
        Lab_test_delete_jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Lab_test_delete_jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LabTest_PanelLayout = new javax.swing.GroupLayout(LabTest_Panel);
        LabTest_Panel.setLayout(LabTest_PanelLayout);
        LabTest_PanelLayout.setHorizontalGroup(
            LabTest_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
            .addGroup(LabTest_PanelLayout.createSequentialGroup()
                .addGroup(LabTest_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(Lab_Test_jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LabTest_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addGroup(LabTest_PanelLayout.createSequentialGroup()
                        .addComponent(Count_Lab_TEst_jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Lab_Test_Add_jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Lab_test_delete_jButton2))))
        );
        LabTest_PanelLayout.setVerticalGroup(
            LabTest_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabTest_PanelLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(LabTest_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LabTest_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lab_Test_jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Count_Lab_TEst_jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lab_Test_Add_jButton3)
                    .addComponent(Lab_test_delete_jButton2)))
        );

        LAb_Test_Button2.setFont(new java.awt.Font("Tahoma", 1, 11));
        LAb_Test_Button2.setText("Lab Test");
        LAb_Test_Button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LAb_Test_Button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(772, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Case_No_Textbox, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Patient_Name_Textbox, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(Visite_Count, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Previous_Disease)))
                .addContainerGap(746, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 6851, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Date_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(Month_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Year_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LAb_Test_Button2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(LabTest_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(938, 938, 938)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lab_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lab_Test_Button)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4277, 4277, 4277))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(Patient_Name_Textbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Case_No_Textbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Visite_Count, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addComponent(Previous_Disease, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Date_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Month_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Year_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(Lab_Test_Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Lab_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LAb_Test_Button2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LabTest_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1418, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void update_lab_Test_list()
    {
        try {
            rs1 = st.executeQuery("Select Lab_Test_Name from Lab_Test_Details order by Lab_Test_Name");
            //    System.out.println("nt n=st.executeUpdate(sql);");
            int count = 0;
            while (rs1.next()) {
                count++;
            }
            final String[] Lab_Test_Name_list = new String[count + 1];
            rs1 = st.executeQuery("Select Lab_Test_Name from Lab_Test_Details order by Lab_Test_Name");
            System.out.println("nt n=st.executeUpdate(sql);");
            count = 0;
            Lab_Test_Name_list[count++] = " ";
            while (rs1.next()) {
                Lab_Test_Name_list[count++] = rs1.getString("Lab_Test_Name");
            }
            Lab_Test_jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(Lab_Test_Name_list));
        } catch (SQLException ex) {
            Logger.getLogger(Diagonsis_Patient_Entry.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void CancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancleActionPerformed
        // TODO add your handling code here:
        Welcome_Page n=new Welcome_Page();
        n.setSize(clinic_management.Main.d.width,clinic_management.Main.d.height-40);
        n.setVisible(true);
        dispose();
    }//GEN-LAST:event_CancleActionPerformed

    private void OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkActionPerformed
        // TODO add your handling code here:
    add_in_Database();
    Welcome_Page n = new Welcome_Page();
    n.setSize(clinic_management.Main.d.width,clinic_management.Main.d.height-40);
    n.setVisible(true);
    dispose();
    }//GEN-LAST:event_OkActionPerformed
    public void add_in_Database()
        {
        int Diseases_Key=0,Symptom_Key=0,Medicine_Key=0;
        if(!Charge_Text.getText().equals(""))
        {
            try {
                rs1 = st.executeQuery("Select Disease_Key from Diagosis_Details_All_Patient order by Disease_Key");

                while(rs1.next())
                {
                    int dk=rs1.getInt("Disease_Key");
                    System.out.println("int dk=rs1.getInt(Disease_Key);"+dk);
                    if(Diseases_Key<=dk)
                    {
                        Diseases_Key=dk;
                    }
                }
                Diseases_Key+=1;
                System.out.println(Diseases_Key);
                 rs1 = st.executeQuery("Select Symptom_Key from Diagosis_Details_All_Patient order by Symptom_Key");
                while(rs1.next())
                {
                    int sk=rs1.getInt("Symptom_Key");
                    //System.out.println("int dk=rs1.getInt(Disease_Key);"+dk);
                    if(Symptom_Key<=sk)
                    {
                        Symptom_Key=sk;
                    }
                }
                Symptom_Key+=1;
                System.out.println(Symptom_Key);
                rs1 = st.executeQuery("Select Medicine_Key from Diagosis_Details_All_Patient order by Medicine_Key");
                while(rs1.next())
                {
                    int sk=rs1.getInt("Medicine_Key");

                    if(Medicine_Key<=sk)
                    {
                        Medicine_Key=sk;
                    }
                }
                Medicine_Key+=1;

                String sYear_Combo=(String) Year_Combo.getSelectedItem();
                String sMonth_Combo=(String) Month_Combo.getSelectedItem();
                String sDate_Combo=(String) Date_Combo.getSelectedItem();
                java.sql.Date entry_date=new java.sql.Date(Integer.parseInt(sYear_Combo)-1900,Integer.parseInt(sMonth_Combo)-1,Integer.parseInt(sDate_Combo));
                int Charge=Integer.parseInt(Charge_Text.getText());
                int Cash=Integer.parseInt(Cash_Text.getText());
                int Debit=Charge-Cash;
                String Remark=Remark_TextBox.getText();
                st.executeUpdate("INSERT INTO Diagosis_Details_All_Patient VALUES ('"+Case_No+"','"+entry_date+"','"+Diseases_Key+"','"+Symptom_Key+"','"+Medicine_Key+"','"+Charge+"','"+Cash+"','"+Debit+"','"+Remark+"','"+Lab_Test_Key+"')");

                Object[] Dis= Diagonsis_list.getSelectedValues();
                int n=Dis.length;
                String Disease;
                for (int i = 0; i < Dis.length; i++) {
                    Disease=(String) Dis[i];
                    st.executeUpdate("INSERT INTO Disease_Key_Entry VALUES ('"+Diseases_Key+"','"+Disease+"')");
                }
                Dis=Symptom_List.getSelectedValues();
                for (int i = 0; i < Dis.length; i++) {
                    Disease=(String) Dis[i];
                    st.executeUpdate("INSERT INTO Symptom_Key_Entry VALUES ('"+Symptom_Key+"','"+Disease+"')");
                }
                //insert medicine
            Iterator itr = arraylist_data.iterator();
            int count=0;
            while(itr.hasNext())
            {

                Object temp1= itr.next();
                String element[]=(String[])temp1;
                int qty_temp=Integer.parseInt(element[3]);

                st.executeUpdate("INSERT INTO Medicine_Key1_Entry VALUES ('"+Medicine_Key+"','"+element[0]+"','"+element[1]+"','"+element[2]+"','"+qty_temp+"')");
                count++;
            }
            itr = arraylist_LabTest.iterator();
            while(itr.hasNext())
            {

                Object temp1= itr.next();
                String element[]=(String[])temp1;
                st.executeUpdate("INSERT INTO Lab_Test_Key_Entry VALUES ('"+Lab_Test_Key+"','"+element[0]+"','"+element[1]+"','"+element[2]+"')");
            }


                c.close();
            } catch (SQLException ex) {
                System.out.print(ex);
            }

        }else
        {
            Dialog_class.Old_Patient_Dialog n=new Dialog_class.Old_Patient_Dialog(this, true,"Please,Enter all Details properly...");
            n.setVisible(true);
        }
    }
    private void Charge_TextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Charge_TextKeyReleased
        // TODO add your handling code here:
        Cash_Text.setText(Charge_Text.getText());
    }//GEN-LAST:event_Charge_TextKeyReleased

    private void Previous_DiseaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Previous_DiseaseActionPerformed
        // TODO add your handling code here:
            Previous_Record_Details n = new Previous_Record_Details(Case_No_Textbox.getText(),Patient_Name_Textbox.getText(),1);
            n.setSize(clinic_management.Main.d.width,clinic_management.Main.d.height-40);
            n.setVisible(true);
            dispose();
    }//GEN-LAST:event_Previous_DiseaseActionPerformed

    private void Diseases_Update_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Diseases_Update_ButtonActionPerformed
        // TODO add your handling code here:
        Dialog_class.Update_D_S_M_Table n=new Dialog_class.Update_D_S_M_Table(this, true,1);
        n.setVisible(true);
        Update_list();
    }//GEN-LAST:event_Diseases_Update_ButtonActionPerformed

    private void Symptop_Update_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Symptop_Update_ButtonActionPerformed
        // TODO add your handling code here:
         Dialog_class.Update_D_S_M_Table n=new Dialog_class.Update_D_S_M_Table(this, true,2);
        n.setVisible(true);
        Update_list();
    }//GEN-LAST:event_Symptop_Update_ButtonActionPerformed

    private void Medicine_Update_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Medicine_Update_ButtonActionPerformed
        // TODO add your handling code here:
          Dialog_class.Update_D_S_M_Table n=new Dialog_class.Update_D_S_M_Table(this, true,3);
        n.setVisible(true);
        Update_list();
    }//GEN-LAST:event_Medicine_Update_ButtonActionPerformed

    private void Lab_Test_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Lab_Test_ButtonActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_Lab_Test_ButtonActionPerformed

    private void LAb_Test_Button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LAb_Test_Button2ActionPerformed
        // TODO add your handling code here:
        update_lab_Test_list();
        if(flag_lab_test_visiable==0)
        {
            LabTest_Panel.setVisible(true);
            try {
                    rs1 = st.executeQuery("Select Lab_Test_Key from Diagosis_Details_All_Patient order by Lab_Test_Key");

                    while(rs1.next())
                    {
                        int dk=rs1.getInt("Lab_Test_Key");
                        if(Lab_Test_Key<=dk)
                        {
                            Lab_Test_Key=dk;
                        }
                    }
                    Lab_Test_Key+=1;
            }
            catch(SQLException ee)
            {
            }
            flag_lab_test_visiable=1;
        }
        else
        {
            LabTest_Panel.setVisible(false);
            flag_lab_test_visiable=0;
            Lab_Test_Key=0;
        }
    }//GEN-LAST:event_LAb_Test_Button2ActionPerformed

    private void Add_Medicine_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_Medicine_ButtonActionPerformed
        // TODO add your handling code here:
        String Medicine_name_Retrive=(String) Medicine_name_combobox.getSelectedItem();
        String inout=(String) In_Out_jComboBox1.getSelectedItem();
        String dose=(String) Dose_ComboBox.getSelectedItem();
        if(!Medicine_name_Retrive.equals(" "))
        {
            int qty=0;
            int day_of_dose=Integer.parseInt((String)Day_ComboBox.getSelectedItem());
            switch(Dose_ComboBox.getSelectedIndex())
            {
                case 0:
                        qty=day_of_dose*2;
                        break;
                case 1:
                        qty=day_of_dose*3;
                        break;
                case 2:
                        qty=day_of_dose*1;
                        break;
                case 3:
                        qty=day_of_dose*1;
                        break;
                case 4:
                        qty=day_of_dose*2;
                        break;
                case 5:
                        qty=day_of_dose*2;
                        break;
                case 6:
                        qty=day_of_dose*1;
                        break;
                case 7:
                        qty=day_of_dose*4;
                        break;
            }
        
            String temp[]=new String[]{inout+"",Medicine_name_Retrive,dose,""+qty};
            arraylist_data.add(temp);
            MedicineName_Object=new Object[arraylist_data.size()][5];
            Iterator itr = arraylist_data.iterator();
            int count=0;
            while(itr.hasNext())
            {
                
                Object temp1= itr.next();
                String element[]=(String[])temp1;
                MedicineName_Object[count][0]=count+1;
                for(int i=0;i<4;i++)
                {
                        MedicineName_Object[count][i+1]=element[i];
                }
                count++;
            }

            Medicine_Table.setModel(new javax.swing.table.DefaultTableModel(MedicineName_Object,Header_Medicine_Name));
            TableColumn tb=Medicine_Table.getColumn("Qty");
            tb.setMaxWidth(60);
            tb.setMinWidth(60);
            tb=Medicine_Table.getColumn("Dose");
            tb.setMaxWidth(70);
            tb.setMinWidth(70);
            tb=Medicine_Table.getColumn("No.");
            tb.setMaxWidth(30);
            tb.setMinWidth(30);
            tb=Medicine_Table.getColumn("In/Out");
            tb.setMaxWidth(45);
            tb.setMinWidth(45);

        }
        else
        {
            Dialog_class.Old_Patient_Dialog n=new Dialog_class.Old_Patient_Dialog(this, true,"Please,First select Medicine Name...");
            n.setVisible(true);
        }
        Medicine_name_combobox.setSelectedIndex(0);
        //Day_ComboBox.setSelectedIndex(0);
        //Dose_ComboBox.setSelectedIndex(0);
        In_Out_jComboBox1.setSelectedIndex(0);
    }//GEN-LAST:event_Add_Medicine_ButtonActionPerformed

    private void Delete_Medicine_name_jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_Medicine_name_jButton1ActionPerformed
        // TODO add your handling code here:
        String Name_temp[]=new String[arraylist_data.size()];
        Iterator itr = arraylist_data.iterator();
        int count=0;
        while(itr.hasNext())
        {

            Object temp1= itr.next();
            String element[]=(String[])temp1;
            Name_temp[count++]=element[1];

        }
        
        Dialog_class.Delete_Medicine_name_form_table n=new Dialog_class.Delete_Medicine_name_form_table(this, true,Name_temp,"medicine");
        n.setVisible(true);
        System.out.println("          "+deleted_Medicine_Name);
        if(!deleted_Medicine_Name.equals(null))
        {
            itr = arraylist_data.iterator();
            count=0;
            while(itr.hasNext())
            {
                count++;
                Object temp1= itr.next();
                String element[]=(String[])temp1;
                if(deleted_Medicine_Name.equals(element[1]))
                {
                    arraylist_data.remove(count-1);
                    break;
                }

            }
            MedicineName_Object=new Object[arraylist_data.size()][5];
            itr = arraylist_data.iterator();
            count=0;
            while(itr.hasNext())
            {

                Object temp1= itr.next();
                String element[]=(String[])temp1;
                MedicineName_Object[count][0]=count+1;
                for(int i=0;i<4;i++)
                {
                        MedicineName_Object[count][i+1]=element[i];
                }
                count++;
            }
            Medicine_Table.setModel(new javax.swing.table.DefaultTableModel(MedicineName_Object,Header_Medicine_Name));
            TableColumn tb=Medicine_Table.getColumn("Qty");
            tb.setMaxWidth(60);
            tb.setMinWidth(60);
            tb=Medicine_Table.getColumn("Dose");
            tb.setMaxWidth(70);
            tb.setMinWidth(70);
            tb=Medicine_Table.getColumn("No.");
            tb.setMaxWidth(30);
            tb.setMinWidth(30);
            tb=Medicine_Table.getColumn("In/Out");
            tb.setMaxWidth(45);
            tb.setMinWidth(45);
            
            deleted_Medicine_Name=null;
        }
    }//GEN-LAST:event_Delete_Medicine_name_jButton1ActionPerformed

    private void Lab_Test_Add_jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Lab_Test_Add_jButton3ActionPerformed
        // TODO add your handling code here:
        String Lab_Test_Name_Retrive=(String) Lab_Test_jComboBox2.getSelectedItem();
        String count_lab=Count_Lab_TEst_jTextField1.getText();
        String normal_range=null;
        if(!Lab_Test_Name_Retrive.equals(" "))
        {
            try {
                rs1 = st.executeQuery("Select * from Lab_Test_Details where Lab_Test_Name='" + Lab_Test_Name_Retrive + "'");
                if(rs1.next())
                {
                    normal_range=rs1.getString("Normal_Range");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Diagonsis_Patient_Entry.class.getName()).log(Level.SEVERE, null, ex);
            }
            String temp[]=new String[]{Lab_Test_Name_Retrive,count_lab,normal_range};
            arraylist_LabTest.add(temp);
            Lab_Test_Object=new Object[arraylist_LabTest.size()][4];
            Iterator itr = arraylist_LabTest.iterator();
            int count=0;
            while(itr.hasNext())
            {

                Object temp1= itr.next();
                String element[]=(String[])temp1;
                Lab_Test_Object[count][0]=count+1;
                for(int i=0;i<3;i++)
                {
                        Lab_Test_Object[count][i+1]=element[i];
                }
                count++;
            }
             Lab_Test_jTable2.setModel(new javax.swing.table.DefaultTableModel(Lab_Test_Object,Header_Lab_Test_Name));
            TableColumn tb=Lab_Test_jTable2.getColumn("No.");
            tb.setMaxWidth(30);
            tb.setMinWidth(30);
            tb=Lab_Test_jTable2.getColumn("Count");
            tb.setMaxWidth(70);
            tb.setMinWidth(70);

        }
        else
        {
            Dialog_class.Old_Patient_Dialog n=new Dialog_class.Old_Patient_Dialog(this, true,"Please,First select Lab Test Name...");
            n.setVisible(true);
        }
        Lab_Test_jComboBox2.setSelectedIndex(0);
    }//GEN-LAST:event_Lab_Test_Add_jButton3ActionPerformed

    private void Lab_test_delete_jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Lab_test_delete_jButton2ActionPerformed
        // TODO add your handling code here:
        String Name_temp[]=new String[arraylist_LabTest.size()];
        Iterator itr = arraylist_LabTest.iterator();
        int count=0;
        while(itr.hasNext())
        {

            Object temp1= itr.next();
            String element[]=(String[])temp1;
            Name_temp[count++]=element[0];

        }

        Dialog_class.Delete_Medicine_name_form_table n=new Dialog_class.Delete_Medicine_name_form_table(this, true,Name_temp,"dfdficine");
        n.setVisible(true);
        System.out.println("          "+deleted_Medicine_Name);
        if(!deleted_Medicine_Name.equals(null))
        {
            itr = arraylist_LabTest.iterator();
            count=0;
            while(itr.hasNext())
            {
                count++;
                Object temp1= itr.next();
                String element[]=(String[])temp1;
                if(deleted_Medicine_Name.equals(element[0]))
                {
                    arraylist_LabTest.remove(count-1);
                    break;
                }

            }
            Lab_Test_Object=new Object[arraylist_LabTest.size()][4];
            itr = arraylist_LabTest.iterator();
            count=0;
            while(itr.hasNext())
            {

                Object temp1= itr.next();
                String element[]=(String[])temp1;
                Lab_Test_Object[count][0]=count+1;
                for(int i=0;i<3;i++)
                {
                        Lab_Test_Object[count][i+1]=element[i];
                }
                count++;
            }
             Lab_Test_jTable2.setModel(new javax.swing.table.DefaultTableModel(Lab_Test_Object,Header_Lab_Test_Name));
            TableColumn tb=Lab_Test_jTable2.getColumn("No.");
            tb.setMaxWidth(30);
            tb.setMinWidth(30);
            tb=Lab_Test_jTable2.getColumn("Count");
            tb.setMaxWidth(70);
            tb.setMinWidth(70);

            deleted_Medicine_Name=null;
        }
    }//GEN-LAST:event_Lab_test_delete_jButton2ActionPerformed

    private void Print_and_ok_ButtonsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Print_and_ok_ButtonsActionPerformed
        // TODO add your handling code here:
        add_in_Database();
        Dialog_class.Print_Oftion_All_or_Out tt=new Dialog_class.Print_Oftion_All_or_Out(this, true,Lab_Test_Key);
        tt.setVisible(true);
        try {
            PrintMedicines prientsm = new PrintMedicines(arraylist_data,arraylist_LabTest,Case_No_Textbox.getText());
        } catch (Exception ex) {
            Logger.getLogger(Diagonsis_Patient_Entry.class.getName()).log(Level.SEVERE, null, ex);
        }
        Welcome_Page n = new Welcome_Page();
        n.setSize(clinic_management.Main.d.width,clinic_management.Main.d.height-40);
        n.setVisible(true);
        dispose();
    }//GEN-LAST:event_Print_and_ok_ButtonsActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Diagonsis_Patient_Entry("","").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_Medicine_Button;
    private javax.swing.JButton Cancle;
    private javax.swing.JTextField Case_No_Textbox;
    private javax.swing.JTextField Cash_Text;
    private javax.swing.JTextField Charge_Text;
    private javax.swing.JTextField Count_Lab_TEst_jTextField1;
    private javax.swing.JTextField Count_Test_TextBox;
    private javax.swing.JComboBox Date_Combo;
    private javax.swing.JComboBox Day_ComboBox;
    private javax.swing.JTextField Debit_text;
    private javax.swing.JButton Delete_Medicine_name_jButton1;
    private javax.swing.JList Diagonsis_list;
    private javax.swing.JButton Diseases_Update_Button;
    private javax.swing.JComboBox Dose_ComboBox;
    private javax.swing.JComboBox In_Out_jComboBox1;
    private javax.swing.JButton LAb_Test_Button2;
    private javax.swing.JPanel LabTest_Panel;
    private javax.swing.JPanel Lab_Panel;
    private javax.swing.JButton Lab_Test_Add_jButton3;
    private javax.swing.JButton Lab_Test_Button;
    private javax.swing.JComboBox Lab_Test_Name_ComboBox;
    private javax.swing.JComboBox Lab_Test_jComboBox2;
    private javax.swing.JTable Lab_Test_jTable2;
    private javax.swing.JButton Lab_test_delete_jButton2;
    private javax.swing.JTable Medicine_Table;
    private javax.swing.JButton Medicine_Update_Button;
    private javax.swing.JComboBox Medicine_name_combobox;
    private javax.swing.JComboBox Month_Combo;
    private javax.swing.JTextField Normal_Count_TextBox;
    private javax.swing.JButton Ok;
    private javax.swing.JTextField Patient_Name_Textbox;
    private javax.swing.JButton Previous_Disease;
    private javax.swing.JButton Print_and_ok_Buttons;
    private javax.swing.JTextField Remark_TextBox;
    private javax.swing.JPanel Send_email_panel;
    private javax.swing.JComboBox Suggetion_for_Lab_ComoBox;
    private javax.swing.JList Symptom_List;
    private javax.swing.JButton Symptop_Update_Button;
    private javax.swing.JTextField Visite_Count;
    private javax.swing.JComboBox Year_Combo;
    private javax.swing.JRadioButton Yes_RadioButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel rupees;
    // End of variables declaration//GEN-END:variables

}
