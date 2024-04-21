package com.dam.programacion.proyectosMinecraft;



import com.dam.programacion.minecraft.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Granja extends JFrame {

    private JPanel centerPanel;
    private JPanel imagePanel;
    private JPanel constructionsRequirimentsPanel;
    private JPanel confirmPanel;
    private Image imageCastle;
    private ImageIcon imageIconCastle;
    private JLabel requirimentsMaterialsIndicator;
    private JLabel getRequirimentsMaterialsText;
    private JButton insertMaterialsButton;
    private JTextArea confirmArea;
    private static final int WOOD_REQUIREMENTS = 12;
    private static final int STONE_REQUIRIMENTS = 300;
    private static final int IRON_REQUIRIMENTS = 150;
    private static final int DIAMOND_REQUIRIMENTS = 40;
    private static final int GOLD_REQUIRIMENTS = 27;
    private int woodValue = 0;
    private int stoneValue = 0;
    private int ironValue = 0;
    private int diamondValue = 0;
    private int goldValue = 0;

    public Granja() {

        setTitle("Proyecto Granja");
        setBounds(600, 600, 600, 600);
        setVisible(true);
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        add(centerPanel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets.set(0, 10, 0, 0);
        imagePanel = new JPanel();
        imagePanel.setLayout(new GridBagLayout());
        centerPanel.add(imagePanel, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        constructionsRequirimentsPanel = new JPanel();
        constructionsRequirimentsPanel.setLayout(new GridBagLayout());
        centerPanel.add(constructionsRequirimentsPanel, gbc);
        gbc.gridy = 4;
        gbc.gridx = 0;
        confirmPanel = new JPanel();
        confirmPanel.setLayout(new GridBagLayout());
        centerPanel.add(confirmPanel, gbc);
        imagePanel.add(putImage());
        requirimentsMaterialsIndicator = new JLabel("MATERIALES NECESARIOS: ");
        constructionsRequirimentsPanel.add(requirimentsMaterialsIndicator);
        constructionsRequirimentsPanel.add(putRequirements());
        insertMaterialsButton = new JButton("Hacer Proyecto");
        insertMaterialsButton.setBackground(Color.GREEN);
        insertMaterialsButton.setForeground(Color.WHITE);
        confirmPanel.add(insertMaterialsButton);
        confirmArea = new JTextArea();
        confirmArea.setPreferredSize(new Dimension(300, 20));
        confirmPanel.add(new JScrollPane(confirmArea));
        insertMaterialsButton.addActionListener(this::doConstruction);

    }

    private JLabel putImage() {
        String sourceImageCastle = "Granja.jpg";
        imageIconCastle = new ImageIcon();
        imageCastle = imageIconCastle.getImage();
        JLabel putImage = new JLabel();
        putImage.setBorder(new EmptyBorder(0, 50, 0, 0));
        imageIconCastle = new ImageIcon(sourceImageCastle);
        imageCastle = imageIconCastle.getImage();
        Image scaledImage = imageCastle.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        putImage.setIcon(scaledIcon);
        return putImage;
    }

    private JLabel putRequirements() {
        getRequirimentsMaterialsText = new JLabel();
        getRequirimentsMaterialsText.setText(" Madera: " + WOOD_REQUIREMENTS + " Piedra: " + STONE_REQUIRIMENTS
                + " Acero: " + IRON_REQUIRIMENTS + " Diamante: " + DIAMOND_REQUIRIMENTS + " Oro: " + GOLD_REQUIRIMENTS);
        return getRequirimentsMaterialsText;
    }

    public void doConstruction(ActionEvent e) {

        if (checkNumberMaterials(SeleccionDeMateriales.getMyMaterials())) {
            insertMaterialsButton.setEnabled(true);
            String wood = "Madera";
            String stone = "Piedra";
            String iron = "Acero";
            String diamond = "Diamante";
            String gold = "Gold";
            int newWoodValue = woodValue - WOOD_REQUIREMENTS;
            int newStoneValue = stoneValue - STONE_REQUIRIMENTS;
            int newIronValue = ironValue - IRON_REQUIRIMENTS;
            int newDiamonsValue = diamondValue - DIAMOND_REQUIRIMENTS;
            int newGoldValue = goldValue - GOLD_REQUIRIMENTS;

            newWoodValue(newWoodValue);
            newStoneValue(newStoneValue);
            newIronValue(newIronValue);
            newDiamonValue(newDiamonsValue);
            newGoldValue(newGoldValue);

            try {
                int auxFilePath = 1; //variable necesaria por si creamos mas de 1 proyecto del mismo nombre
                String filePath = "Granja" + auxFilePath + ".txt";
                FileWriter fileWriter = new FileWriter(filePath);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writer.write("Nombre del proyecto: " + "Granja" + auxFilePath);
                writer.write("\nMateriales requeridos: ");
                writer.write("\nMadera: " + WOOD_REQUIREMENTS);
                writer.write("\nPiedra: " + STONE_REQUIRIMENTS);
                writer.write("\nHierro: " + IRON_REQUIRIMENTS);
                writer.write("\nDiamante: " + DIAMOND_REQUIRIMENTS);
                writer.write("\nOro: " + GOLD_REQUIRIMENTS);
                writer.write("\nMateriales Usados: ");
                writer.write("\nMadera: " + woodValue);
                writer.write("\nPiedra: " + stoneValue);
                writer.write("\nHierro: " + ironValue);
                writer.write("\nDiamante: " + diamondValue);
                writer.write("\nOro: " + goldValue);
                writer.write("\nMateriales actualizados después del proyecto: ");
                writer.write("\nMadera: " + newWoodValue);
                writer.write("\nPiedra: " + newStoneValue);
                writer.write("\nHierro: " + newIronValue);
                writer.write("\nDiamante: " + newDiamonsValue);
                writer.write("\nOro: " + newGoldValue);
                writer.close();
                confirmArea.setText("Proyecto Granja" + auxFilePath + " creado correctamente");
                MenuProyecto.addElementProyectName(filePath);
                auxFilePath++;

            } catch (IOException ex) {
                confirmArea.setText("No se ha podido guardar el proyecto, vuelve a intentarlo");
            }


        } else {
            confirmArea.setText("Faltan materiales, vuelve a la seleccion de materiales");
        }
    }

    private boolean checkNumberMaterials(HashMap<String, Integer> materialsMap) {
        String wood = "Madera";
        String stone = "Piedra";
        String iron = "Hierro";
        String diamond = "Diamante";
        String gold = "Oro";


        woodValue = materialsMap.getOrDefault(wood, 0);
        stoneValue = materialsMap.getOrDefault(stone, 0);
        ironValue = materialsMap.getOrDefault(iron, 0);
        diamondValue = materialsMap.getOrDefault(diamond, 0);
        goldValue = materialsMap.getOrDefault(gold, 0);


        return woodValue >= WOOD_REQUIREMENTS && stoneValue >= STONE_REQUIRIMENTS &&
                ironValue >= IRON_REQUIRIMENTS && diamondValue >= DIAMOND_REQUIRIMENTS &&
                goldValue >= GOLD_REQUIRIMENTS;
    }


    public void newWoodValue(int value) {
        if (value > 0) {
            SeleccionDeMateriales.addOrEditItemMap("Madera", value);
        } else {
            SeleccionDeMateriales.removeItemMap("Madera");
        }

    }


    public void newStoneValue(int value) {
        if (value > 0) {
            SeleccionDeMateriales.addOrEditItemMap("Piedra", value);
        } else {
            SeleccionDeMateriales.removeItemMap("Piedra");
        }

    }

    public void newIronValue(int value) {
        if (value > 0) {
            SeleccionDeMateriales.addOrEditItemMap("Hierro", value);
        } else {
            SeleccionDeMateriales.removeItemMap("Hierro");
        }

    }

    public void newDiamonValue(int value) {
        if (value > 0) {
            SeleccionDeMateriales.addOrEditItemMap("Diamante", value);
        } else {
            SeleccionDeMateriales.removeItemMap("Diamante");
        }

    }

    public void newGoldValue(int value) {
        if (value > 0) {
            SeleccionDeMateriales.addOrEditItemMap("Oro", value);
        } else {
            SeleccionDeMateriales.removeItemMap("Oro");
        }

    }
}