/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package matodolist;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author LGC
 */


public class Matodolist extends JFrame {



    private JTextField taskField;
    private JTextField dateField;
    private JTextField heureField;
    private JComboBox<String> prioriteComboBox;
    private JComboBox<String> statutComboBox;
    private DefaultTableModel tableModel;
    private JTable table;

    private static final String FILE_NAME = "taches.csv";

    public TodoList() {
        super("Liste de tâches - Version améliorée");

        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        // ---- PANEL SAISIE ----
        JPanel panelSaisie = new JPanel(new GridLayout(5, 2, 10, 10));
        panelSaisie.setBorder(BorderFactory.createTitledBorder("Informations"));

        panelSaisie.add(new JLabel("Tâche :"));
        taskField = new JTextField();
        panelSaisie.add(taskField);

        panelSaisie.add(new JLabel("Date :"));
        dateField = new JTextField();
        panelSaisie.add(dateField);

        panelSaisie.add(new JLabel("Heure :"));
        heureField = new JTextField();
        panelSaisie.add(heureField);

        panelSaisie.add(new JLabel("Priorité :"));
        String[] priorites = {"Haute", "Moyenne", "Basse"};
        prioriteComboBox = new JComboBox<>(priorites);
        panelSaisie.add(prioriteComboBox);

        panelSaisie.add(new JLabel("Statut :"));
        String[] statuts = {"À faire", "En cours", "Terminée"};
        statutComboBox = new JComboBox<>(statuts);
        panelSaisie.add(statutComboBox);

        add(panelSaisie, BorderLayout.NORTH);

        // ---- TABLE ----
        String[] colonnes = {"Tâche", "Date", "Heure", "Priorité", "Statut"};
        tableModel = new DefaultTableModel(colonnes, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Liste des tâches"));
        add(scrollPane, BorderLayout.CENTER);

        // ---- BOUTONS ----
        JPanel panelBoutons = new JPanel(new FlowLayout());

        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.addActionListener(new AjouterActionListener());
        panelBoutons.add(ajouterButton);

        JButton modifierButton = new JButton("Modifier");
        modifierButton.addActionListener(new ModifierActionListener());
        panelBoutons.add(modifierButton);

        JButton supprimerButton = new JButton("Supprimer");
        supprimerButton.addActionListener(new SupprimerActionListener());
        panelBoutons.add(supprimerButton);

        add(panelBoutons, BorderLayout.SOUTH);

        // Charger les données
        chargerTaches();

        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Sauvegarde automatique quand la fenêtre se ferme
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                sauvegarderTaches();
            }
        });

        setVisible(true);
    }

    // ----- AJOUTER -----
    private class AjouterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.addRow(new Object[]{
                    taskField.getText(),
                    dateField.getText(),
                    heureField.getText(),
                    prioriteComboBox.getSelectedItem(),
                    statutComboBox.getSelectedItem()
            });

            taskField.setText("");
            dateField.setText("");
            heureField.setText("");

            sauvegarderTaches();
        }
    }

    // ----- MODIFIER DIRECTEMENT -----
    private class ModifierActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = table.getSelectedRow();

            if (row != -1) {
                tableModel.setValueAt(taskField.getText(), row, 0);
                tableModel.setValueAt(dateField.getText(), row, 1);
                tableModel.setValueAt(heureField.getText(), row, 2);
                tableModel.setValueAt(prioriteComboBox.getSelectedItem(), row, 3);
                tableModel.setValueAt(statutComboBox.getSelectedItem(), row, 4);

                sauvegarderTaches();
            }
        }
    }

    // ----- SUPPRIMER -----
    private class SupprimerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = table.getSelectedRow();
            if (row != -1) {
                tableModel.removeRow(row);
                sauvegarderTaches();
            }
        }
    }

    // ----- SAUVEGARDE CSV -----
    private void sauvegarderTaches() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                writer.println(
                        tableModel.getValueAt(i, 0) + ";" +
                        tableModel.getValueAt(i, 1) + ";" +
                        tableModel.getValueAt(i, 2) + ";" +
                        tableModel.getValueAt(i, 3) + ";" +
                        tableModel.getValueAt(i, 4)
                );
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde !");
        }
    }

    // ----- CHARGEMENT CSV -----
    private void chargerTaches() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(";");
                if (data.length == 5) {
                    tableModel.addRow(data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement !");
        }
    }

    
       



    /**
     * @param args the command line arguments
     */
      public static void main(String[] args) {
        SwingUtilities.invokeLater(Matodolist::new);
        }
}

