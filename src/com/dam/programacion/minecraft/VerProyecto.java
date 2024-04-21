package com.dam.programacion.minecraft;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VerProyecto extends JFrame {
    private JPanel centerPanel;
    private JPanel informationProyectsPanel;
    private JPanel comboBoxPanel;
    private JComboBox<String> proyectNaneListBox;
    private JTable proyectsNameTable;
    private DefaultTableModel modelTable;
    private JTextArea confirmArea;
    private JButton confirm;
    private DefaultComboBoxModel<String> proyectNameList;

    public VerProyecto() {
        setTitle("Ver Proyectos");
        setVisible(true);
        setBounds(600, 600, 600, 600);
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(Color.DARK_GRAY);
        add(centerPanel, BorderLayout.CENTER);
        informationProyectsPanel = new JPanel();
        informationProyectsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        centerPanel.add(informationProyectsPanel, gbc);
        comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new GridBagLayout());
        gbc.gridx = 3;
        gbc.gridy = 0;
        centerPanel.add(comboBoxPanel, gbc);
        proyectNameList = new DefaultComboBoxModel<>();
        proyectNaneListBox = new JComboBox<>(proyectNameList);

        for (String element : MenuProyecto.getProyectsNames()) {
            proyectNameList.addElement(element);
        }
        comboBoxPanel.setBackground(Color.BLUE);
        comboBoxPanel.add(proyectNaneListBox);
        modelTable = new DefaultTableModel(
                new Object[][]{
                        {""},

                },
                new Object[]{"Proyectos"}
        );
        proyectsNameTable = new JTable(modelTable);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(Color.CYAN);

        for (int i = 0; i < proyectsNameTable.getColumnCount(); i++) {
            proyectsNameTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(proyectsNameTable);
        scrollPane.setBackground(Color.DARK_GRAY);
        informationProyectsPanel.add(scrollPane);
        confirmArea = new JTextArea();
        confirmArea.setPreferredSize(new Dimension(100, 20));
        confirmArea.setEditable(false);
        informationProyectsPanel.add(new JScrollPane(confirmArea));
        confirm = new JButton("Confirmar");
        confirm.setBackground(Color.GREEN);
        confirm.setForeground(Color.WHITE);
        informationProyectsPanel.add(confirm);
        confirm.addActionListener(this::showProyects);
    }

    public void showProyects(ActionEvent e) {
        String line;
        String proyectForLook = (String) proyectNaneListBox.getSelectedItem();
        try (FileReader fileReader = new FileReader(proyectForLook);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {

                Object[] row = {line};
                modelTable.addRow(row);


            }
            String space = "";
            Object[] spacerow = {space};
            modelTable.addRow(spacerow);

        } catch (FileNotFoundException ex) {
            confirmArea.setText("Archivo no encontrado");
        } catch (IOException ex) {
            confirmArea.setText("No se ha podido leer el proyecto");
        }


    }


}
