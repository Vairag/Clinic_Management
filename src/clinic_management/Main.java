/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clinic_management;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author mayur
 */
public class Main {
    static  File inpFile=null;
    public static Dimension d;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException   {
        // TODO code application logic here
        
        try{

        inpFile = new File("C:\\c_m_s","cmsmansifile.cms");

        FileReader in = new FileReader(inpFile);
	BufferedReader inputStream = new BufferedReader(in);
	String l=null;
        if ((l = inputStream.readLine()) != null)
        {
            //if(l.equals("cy12-ry2s-89ta-569l"))
            if(l.equals("aaaa-bbbb-cccc-dddd"))
            {
                //Welcome_Page n=new Welcome_Page();
                Login n=new Login();
                Toolkit kit=Toolkit.getDefaultToolkit();
                d=kit.getScreenSize();
                n.setSize(d.width,d.height-40);
                n.setVisible(true);
            }
            else
            {   //Welcome_Page
                Dialog_class.Old_Patient_Dialog n=new Dialog_class.Old_Patient_Dialog(new java.awt.Frame(), true,"Your Serial no do not match...");
                n.setVisible(true);
                Dialog_class.Serial_No_Entry nn=new Dialog_class.Serial_No_Entry();
                nn.setVisible(true);
                //System.exit(0);
            }
	}
        }
        catch(FileNotFoundException ee)
        {
            if((new File("C:\\c_m_s")).mkdir())
            {
                System.out.println("Directory Created");
                if((new File("C:\\c_m_s","cmsmansifile.cms")).createNewFile())
                {
                     System.out.println("file Created");
                }
            }
            else
                   System.out.println("Directory is not created");

            Dialog_class.Serial_No_Entry n=new Dialog_class.Serial_No_Entry();
            n.setVisible(true);

        
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
    }

}
