package com.dam.programacion.minecraft;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModificarMateriales extends JFrame {

    private JPanel centerPanel;
    private JPanel informationMaterialsPanel;
    private JPanel modifyMateriaslPanel;
    private JPanel comboBoxPanel;
    private JComboBox<String> materialListBox;
    private JTable materialsTable;
    private DefaultTableModel modelTable;
    private JLabel modifyMaterialNameIndicator;
    private JTextField modifyMaterialNameText;
    private JTextArea confirmArea;
    private JButton confirm;
    private DefaultComboBoxModel <String> materialList;

    public ModificarMateriales() {
        setTitle("Modificar Materiales");
        setVisible(true);
        setBounds(600, 600, 600, 600);
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(Color.DARK_GRAY);
        add(centerPanel, BorderLayout.CENTER);
        informationMaterialsPanel = new JPanel();
        informationMaterialsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        centerPanel.add(informationMaterialsPanel, gbc);
        modifyMateriaslPanel = new JPanel();
        modifyMateriaslPanel.setLayout(new GridBagLayout());
        gbc.gridx = 3;
        gbc.gridy = 1;
        centerPanel.add(modifyMateriaslPanel, gbc);
        comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new GridBagLayout());
        gbc.gridx = 3;
        gbc.gridy = 0;
        centerPanel.add(comboBoxPanel, gbc);
        materialList = new DefaultComboBoxModel<>();
        materialListBox = new JComboBox<>(materialList);
        ArrayList<String> auxMaterials = materialsName(SeleccionDeMateriales.getMyMaterials());
        for (String element : auxMaterials) {
            materialList.addElement(element);
        }
        comboBoxPanel.setBackground(Color.MAGENTA);
        comboBoxPanel.add(materialListBox);
        modelTable = new DefaultTableModel(
                new Object[][]{
                        {""},

                },
                new Object[]{"Materiales"}
        );
        materialsTable = new JTable(modelTable);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(Color.RED);

        for (int i = 0; i < materialsTable.getColumnCount(); i++) {
            materialsTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(materialsTable);
        scrollPane.setBackground(Color.BLACK);
        informationMaterialsPanel.add(scrollPane);
        modifyMaterialNameIndicator = new JLabel(getSelectItem());
        modifyMateriaslPanel.add(modifyMaterialNameIndicator);
        modifyMaterialNameText = new JTextField(10);
        modifyMateriaslPanel.add(modifyMaterialNameText);
        confirmArea = new JTextArea();
        confirmArea.setPreferredSize(new Dimension(200, 20));
        confirmArea.setEditable(false);
        modifyMateriaslPanel.add(new JScrollPane(confirmArea));
        confirm = new JButton("Confirmar");
        confirm.setBackground(Color.GREEN);
        confirm.setForeground(Color.WHITE);
        modifyMateriaslPanel.add(confirm);

        materialListBox.addActionListener(this::showMaterials);
        confirm.addActionListener(this::modifyMyMaterials);
    }

    public void showMaterials(ActionEvent e) {

        String itemForLook = (String) materialListBox.getSelectedItem();
        modifyMaterialNameIndicator.setText(getSelectItem());

        switch (itemForLook) {
            case "Madera":
                Object[] row1 = {getItemKey(SeleccionDeMateriales.getMyMaterials())};
                modelTable.addRow(row1);
                Object[] row2 = {getItemValue(SeleccionDeMateriales.getMyMaterials())};
                modelTable.addRow(row2);
                break;
            case "Piedra":
                Object[] row3 = {getItemKey(SeleccionDeMateriales.getMyMaterials())};
                modelTable.addRow(row3);
                Object[] row4 = {getItemValue(SeleccionDeMateriales.getMyMaterials())};
                modelTable.addRow(row4);
                break;
            case "Hierro":
                Object[] row5 = {getItemKey(SeleccionDeMateriales.getMyMaterials())};
                modelTable.addRow(row5);
                Object[] row6 = {getItemValue(SeleccionDeMateriales.getMyMaterials())};
                modelTable.addRow(row6);
                break;
            case "Diamante":
                Object[] row7 = {getItemKey(SeleccionDeMateriales.getMyMaterials())};
                modelTable.addRow(row7);
                Object[] row8 = {getItemValue(SeleccionDeMateriales.getMyMaterials())};
                modelTable.addRow(row8);
                break;
            case "Oro":
                Object[] row9 = {getItemKey(SeleccionDeMateriales.getMyMaterials())};
                modelTable.addRow(row9);
                Object[] row10 = {getItemValue(SeleccionDeMateriales.getMyMaterials())};
                modelTable.addRow(row10);
                break;
            default:
                confirmArea.setText("Sin materiales para modificar");
                break;


        }
    }

    public void modifyMyMaterials(ActionEvent e) {
        if (checkEmptyContent()) {
            confirm.setEnabled(true);
            String itemNameForLook = modifyMaterialNameIndicator.getText();
            String auxItemNumberForLook = modifyMaterialNameText.getText();
            try {
                int itemNumberForLook = Integer.parseInt(auxItemNumberForLook);
                if (itemNumberForLook > 0) {
                    SeleccionDeMateriales.addOrEditItemMap(itemNameForLook, itemNumberForLook);
                    confirmArea.setText(itemNameForLook + ": " + itemNumberForLook + " modificado correctamente");
                } else {
                    SeleccionDeMateriales.removeItemMap(itemNameForLook);
                    confirmArea.setText(itemNameForLook + " borrado correctamente");
                    materialList.removeElement(itemNameForLook);

                }

            } catch (NumberFormatException ex) {
                confirmArea.setText("No se ha podido modificar el numero");

            }
        } else {
            confirmArea.setText("Rellene el nuevo valor del material escogido");
        }


    }

    public ArrayList<String> materialsName(HashMap<String, Integer> materialsMap) {
        ArrayList<String> elements = new ArrayList<>();
        for (String entry : materialsMap.keySet()) {
            elements.add(entry);
        }
        return elements;
    }

    public String getSelectItem() {
        String item = (String) materialListBox.getSelectedItem();

        return item;
    }

    private boolean checkEmptyContent() {

        return !modifyMaterialNameText.getText().isEmpty();
    }

    private String getItemKey(HashMap<String, Integer> materialsMap) {
        String itemForLook = (String) materialListBox.getSelectedItem();
        for (Map.Entry<String, Integer> entry : materialsMap.entrySet()) {
            if (entry.getKey().equals(itemForLook)) {
                return entry.getKey();
            }
        }
        return "";
    }


    private int getItemValue(HashMap<String, Integer> materialsMap) {
        String itemForLook = (String) materialListBox.getSelectedItem();
        return materialsMap.getOrDefault(itemForLook, 0);
    }


}
