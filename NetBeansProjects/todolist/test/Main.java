/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LGC
 */
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
 public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ma TodoList");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new TodoListpanel());  // remplace par le nom exact de ta classe JPanel
            frame.pack();
            frame.setLocationRelativeTo(null); // pour centrer la fenêtre
            frame.setVisible(true);
        });
    }
}
   

