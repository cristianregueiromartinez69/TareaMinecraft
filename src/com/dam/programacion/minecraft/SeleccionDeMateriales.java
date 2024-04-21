package com.dam.programacion.minecraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

/**
 * madera, piedra, hierro, diamante, oro
 */
public class SeleccionDeMateriales extends JFrame {

    private JPanel centerPanel;
    private JPanel informationPanel;
    private JPanel comboBoxPanel;
    private JPanel confirmPanel;
    private JLabel quantityIndicator;
    private JTextField quantityText;
    private JComboBox<String> materialsBox;
    private JTextArea confirmArea;
    private JButton confirmButton;
    private static HashMap<String, Integer> myMaterials = new HashMap<>();

    public SeleccionDeMateriales() {
        setTitle("Seleccion de materiales");
        setVisible(true);
        setBounds(600, 600, 600, 600);
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        add(centerPanel);
        informationPanel = new JPanel();
        informationPanel.setLayout(new BorderLayout());
        centerPanel.add(informationPanel, BorderLayout.CENTER);
        comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new BorderLayout());
        centerPanel.add(comboBoxPanel, BorderLayout.WEST);
        confirmPanel = new JPanel();
        confirmPanel.setLayout(new BorderLayout());
        centerPanel.add(confirmPanel, BorderLayout.SOUTH);
        quantityIndicator = new JLabel("Cantidad de material");
        informationPanel.add(quantityIndicator, BorderLayout.NORTH);
        quantityText = new JTextField();
        informationPanel.add(quantityText);
        DefaultComboBoxModel<String> materiasList = new DefaultComboBoxModel<>();
        materialsBox = new JComboBox<>(materiasList);
        materialsBox.setBackground(Color.MAGENTA);
        materialsBox.setForeground(Color.WHITE);
        materiasList.addElement("Madera");
        materiasList.addElement("Piedra");
        materiasList.addElement("Hierro");
        materiasList.addElement("Diamante");
        materiasList.addElement("Oro");
        comboBoxPanel.add(materialsBox);
        confirmArea = new JTextArea();
        confirmArea.setEditable(false);
        confirmPanel.add(new JScrollPane(confirmArea), BorderLayout.SOUTH);
        confirmButton = new JButton("confirmar");
        confirmButton.setBackground(Color.GREEN);
        confirmButton.setForeground(Color.WHITE);
        confirmPanel.add(confirmButton);
        confirmButton.addActionListener(this::insertMaterials);


    }

    public void insertMaterials(ActionEvent e) {
        if (checkEmptyContent()) {
            confirmButton.setEnabled(true);
            String materialKey = (String) materialsBox.getSelectedItem();
            String quantityNumberMaterialsText = quantityText.getText();
            try {
                Integer quantityNumberMaterials = Integer.parseInt(quantityNumberMaterialsText);
                confirmArea.setText("Cantidad  de " + materialKey + " seleccionada y con cantidad de " + quantityNumberMaterials + " añadida correctamente");
                quantityText.setText("");
                myMaterials.put(materialKey, quantityNumberMaterials);
            } catch (NumberFormatException ex) {
                confirmArea.setText("No se ha podido añadir los materiales, vuelve a intentarlo");
            }

        } else {
            confirmArea.setText("Rellene los campos que faltan");
        }

    }

    private boolean checkEmptyContent() {
        int selectIndex = materialsBox.getSelectedIndex();
        return !quantityText.getText().isEmpty() && selectIndex != -1;
    }


    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public JPanel getInformationPanel() {
        return informationPanel;
    }

    public void setInformationPanel(JPanel informationPanel) {
        this.informationPanel = informationPanel;
    }

    public JPanel getComboBoxPanel() {
        return comboBoxPanel;
    }

    public void setComboBoxPanel(JPanel comboBoxPanel) {
        this.comboBoxPanel = comboBoxPanel;
    }

    public JPanel getConfirmPanel() {
        return confirmPanel;
    }

    public void setConfirmPanel(JPanel confirmPanel) {
        this.confirmPanel = confirmPanel;
    }

    public JLabel getQuantityIndicator() {
        return quantityIndicator;
    }

    public void setQuantityIndicator(JLabel quantityIndicator) {
        this.quantityIndicator = quantityIndicator;
    }

    public JTextField getQuantityText() {
        return quantityText;
    }

    public void setQuantityText(JTextField quantityText) {
        this.quantityText = quantityText;
    }

    public JComboBox<String> getMaterialsBox() {
        return materialsBox;
    }

    public void setMaterialsBox(JComboBox<String> materialsBox) {
        this.materialsBox = materialsBox;
    }

    public JTextArea getConfirmArea() {
        return confirmArea;
    }

    public void setConfirmArea(JTextArea confirmArea) {
        this.confirmArea = confirmArea;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public void setConfirmButton(JButton confirmButton) {
        this.confirmButton = confirmButton;
    }

    public static HashMap<String, Integer> getMyMaterials() {
        return myMaterials;
    }

    public static void setMyMaterials(HashMap<String, Integer> myMaterials) {
        SeleccionDeMateriales.myMaterials = myMaterials;
    }
    public static void addOrEditItemMap(String key, int value){
        myMaterials.put(key, value);
    }
    public static void removeItemMap(String key){

            myMaterials.remove(key);

    }
}
