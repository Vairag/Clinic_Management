/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Delete_Medicine_name_form_table.java
 *
 * Created on 25 Aug, 2010, 6:49:15 PM
 */

package Dialog_class;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author mayur
 */
public class Delete_Medicine_name_form_table extends javax.swing.JDialog {

    /** Creates new form Delete_Medicine_name_form_table */
    public Delete_Medicine_name_form_table(java.awt.Frame parent, boolean modal,String[] args,String sms) {
        super(parent, modal);
        initComponents();
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension d=kit.getScreenSize();
        int w=d.width;
        int h=d.height;
        this.setLocation(w-w/2-w/4,h-h/2-h/4);
        setSize(550,180);
        if(sms.equals("medicine"))
        {
              Hedear_jLabel1.setText("-::- Select Medicine for Delete -::-");
              title_jLabel2.setText("Medicine Name");
        }
        else
        {
            Hedear_jLabel1.setText("-::- Select Lab Test for Delete -::-");
              title_jLabel2.setText("Lab Test Name");
        }
         Medicine_name_jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(args));
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
        Hedear_jLabel1 = new javax.swing.JLabel();
        title_jLabel2 = new javax.swing.JLabel();
        Medicine_name_jComboBox1 = new javax.swing.JComboBox();
        Delete_jButton1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Delete_Medicine");

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        Hedear_jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Hedear_jLabel1.setText("-::- Select Medicine for Delete -::- ");

        title_jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        title_jLabel2.setText("Medicine Name");

        Medicine_name_jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Delete_jButton1.setFont(new java.awt.Font("Tahoma", 1, 12));
        Delete_jButton1.setText("Delete");
        Delete_jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_jButton1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jButton1.setText("Cancle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(title_jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Delete_jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE))
                            .addComponent(Medicine_name_jComboBox1, 0, 202, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(Hedear_jLabel1)))
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Hedear_jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title_jLabel2)
                    .addComponent(Medicine_name_jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Delete_jButton1)
                    .addComponent(jButton1))
                .addContainerGap(45, Short.MAX_VALUE))
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

    private void Delete_jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_jButton1ActionPerformed
        // TODO add your handling code here:
        clinic_management.Diagonsis_Patient_Entry.deleted_Medicine_Name=(String) Medicine_name_jComboBox1.getSelectedItem();
        dispose();
    }//GEN-LAST:event_Delete_jButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        clinic_management.Diagonsis_Patient_Entry.deleted_Medicine_Name=null;
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Delete_Medicine_name_form_table dialog = new Delete_Medicine_name_form_table(new javax.swing.JFrame(), true,new String[]{},"medicine");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete_jButton1;
    private javax.swing.JLabel Hedear_jLabel1;
    private javax.swing.JComboBox Medicine_name_jComboBox1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel title_jLabel2;
    // End of variables declaration//GEN-END:variables

}
