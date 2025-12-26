/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tache;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author LGC
 */



public class TACHE extends JFrame {

    private JTextField titreField;
    private JTextArea descriptionArea;
    private JTextField dateDebutField;
    private JTextField dateFinField;
    private JComboBox<String> statutComboBox;
    private DefaultTableModel tableModel;
    private JTable table;

    public TACHE() {
        setLayout(new BorderLayout());

        // Panel de saisie
        JPanel panelSaisie = new JPanel();
        panelSaisie.setLayout(new GridLayout(5, 2));

        panelSaisie.add(new JLabel("Titre :"));
        titreField = new JTextField();
        panelSaisie.add(titreField);

        panelSaisie.add(new JLabel("Description :"));
        descriptionArea = new JTextArea(5, 20);
        panelSaisie.add(new JScrollPane(descriptionArea));

        panelSaisie.add(new JLabel("Date de début (jj/mm/aaaa) :"));
        dateDebutField = new JTextField();
        panelSaisie.add(dateDebutField);

        panelSaisie.add(new JLabel("Date de fin (jj/mm/aaaa) :"));
        dateFinField = new JTextField();
        panelSaisie.add(dateFinField);

        panelSaisie.add(new JLabel("Statut :"));
        String[] statuts = {"À faire", "En cours", "Terminée"};
        statutComboBox = new JComboBox<>(statuts);
        panelSaisie.add(statutComboBox);

        add(panelSaisie, BorderLayout.NORTH);

        // Table de tâches
        String[] colonnes = {"Titre", "Description", "Date de début", "Date de fin", "Statut"};
        tableModel = new DefaultTableModel(colonnes, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Boutons
        JPanel panelBoutons = new JPanel();
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

        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class AjouterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String titre = titreField.getText();
            String description = descriptionArea.getText();
            String dateDebut = dateDebutField.getText();
            String dateFin = dateFinField.getText();
            String statut = (String) statutComboBox.getSelectedItem();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date debut = format.parse(dateDebut);
                Date fin = format.parse(dateFin);

                tableModel.addRow(new Object[]{titre, description, debut, fin, statut});
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Format de date incorrect");
            }
        }
    }

    private class ModifierActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = table.getSelectedRow();
            if (row != -1) {
                String titre = titreField.getText();
                String description = descriptionArea.getText();
                String dateDebut = dateDebutField.getText();
                String dateFin = dateFinField.getText();
                String statut = (String) statutComboBox.getSelectedItem();

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date debut = format.parse(dateDebut);
                    Date fin = format.parse(dateFin);

                    tableModel.setValueAt(titre, row, 0);
                    tableModel.setValueAt(description, row, 1);
                    tableModel.setValueAt(debut, row, 2);
                    tableModel.setValueAt(fin, row, 3);
                    tableModel.setValueAt(statut, row, 4);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Format de date incorrect");
                }
            }
        }
    }

    private class SupprimerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = table.getSelectedRow();
            if (row != -1) {
                tableModel.removeRow(row);
            }
        }
    }

   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new TACHE().setVisible(true));
        // TODO code application logic here
    }
    
}
