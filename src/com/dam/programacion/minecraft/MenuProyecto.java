package com.dam.programacion.minecraft;

import com.dam.programacion.proyectosMinecraft.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuProyecto {
    private JPanel centerPanel;
    private JButton createCastleButton;
    private JButton createPirateShipButton;
    private JButton createAutomaticCastingButton;
    private JButton createFarmButton;
    private Image minecratImage;
    private ImageIcon minecraftIcon;
    private static ArrayList <String> proyectsNames = new ArrayList<>();


    public MenuProyecto() {
        JFrame frame = new JFrame("Minecraft");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);


        JPanel centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon backgroundImage = new ImageIcon("crearproyecto.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };


        centerPanel.setLayout(new OverlayLayout(centerPanel));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setBackground(Color.GREEN);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        createCastleButton = new JButton("Crear Proyecto de un castillo");
        createCastleButton.setBackground(Color.CYAN);
        createCastleButton.setForeground(Color.WHITE);
        createPirateShipButton = new JButton("Crear Proyecto de un barco pirata");
        createPirateShipButton.setBackground(Color.ORANGE);
        createPirateShipButton.setForeground(Color.WHITE);
        createAutomaticCastingButton = new JButton("Crear proyecto de una fundicion automática");
        createAutomaticCastingButton.setBackground(Color.DARK_GRAY);
        createAutomaticCastingButton.setForeground(Color.WHITE);
        createFarmButton = new JButton("Crear Proyecto de una granja");
        createFarmButton.setBackground(Color.PINK);
        createFarmButton.setForeground(Color.WHITE);
        buttonPanel.add(createCastleButton);
        buttonPanel.add(createPirateShipButton);
        buttonPanel.add(createAutomaticCastingButton);
        buttonPanel.add(createFarmButton);


        Dimension buttonSize = createPirateShipButton.getPreferredSize();
        createCastleButton.setBounds(200, 500, buttonSize.width, buttonSize.height);
        createPirateShipButton.setBounds(150, 100, buttonSize.width, buttonSize.height);
        createAutomaticCastingButton.setBounds(200, 150, buttonSize.width, buttonSize.height);
        createFarmButton.setBounds(250, 200, buttonSize.width, buttonSize.height);
        frame.add(centerPanel);
        frame.setVisible(true);
        createCastleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Castillo();
            }
        });
        createPirateShipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BarcoPirata();
            }
        });
        createAutomaticCastingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FundicionAutomatica();
            }
        });
        createFarmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Granja();
            }
        });
    }

    public static ArrayList<String> getProyectsNames() {
        return proyectsNames;
    }

   public static void addElementProyectName(String element){
        proyectsNames.add(element);
   }
}
