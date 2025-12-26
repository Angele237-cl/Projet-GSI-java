/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;


/**
 *
 * @author LGC
 */
public class CRUD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {     
            e.printStackTrace();
    }
    
        JFrame frame = new JFrame("Gestion des étudiants - CRUD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setContentPane(new crudPanel());
        
        frame.pack();
        frame.setLocationRelativeTo(null); // centre la fenêtre
        frame.setVisible(true);
    }
}
