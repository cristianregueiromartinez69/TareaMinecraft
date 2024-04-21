package com.dam.programacion.minecraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal {

    private JPanel centerPanel;
    private JButton selectMaterialsButton;
    private JButton createProyectButton;
    private JButton viewProyectButton;
    private JButton modifyMaterialsButton;
    private Image minecratImage;
    private ImageIcon minecraftIcon;


    public MenuPrincipal() {
        JFrame frame = new JFrame("Minecraft");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);


        JPanel centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon backgroundImage = new ImageIcon("imagen_minecraft_menu.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };


        centerPanel.setLayout(new OverlayLayout(centerPanel));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setBackground(Color.GREEN);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        selectMaterialsButton = new JButton("seleccion de materiales");
        selectMaterialsButton.setBackground(Color.CYAN);
        selectMaterialsButton.setForeground(Color.WHITE);
        createProyectButton = new JButton("Crear proyecto");
        createProyectButton.setBackground(Color.ORANGE);
        createProyectButton.setForeground(Color.WHITE);
        viewProyectButton = new JButton("Ver proyectos");
        viewProyectButton.setBackground(Color.DARK_GRAY);
        viewProyectButton.setForeground(Color.WHITE);
        modifyMaterialsButton = new JButton("modificar mis materiales");
        modifyMaterialsButton.setBackground(Color.PINK);
        modifyMaterialsButton.setForeground(Color.WHITE);
        buttonPanel.add(selectMaterialsButton);
        buttonPanel.add(createProyectButton);
        buttonPanel.add(viewProyectButton);
        buttonPanel.add(modifyMaterialsButton);


        Dimension buttonSize = createProyectButton.getPreferredSize();
        selectMaterialsButton.setBounds(200, 500, buttonSize.width, buttonSize.height);
        createProyectButton.setBounds(150, 100, buttonSize.width, buttonSize.height);
        viewProyectButton.setBounds(200, 150, buttonSize.width, buttonSize.height);
        frame.add(centerPanel);
        frame.setVisible(true);
        selectMaterialsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SeleccionDeMateriales();
            }
        });
        createProyectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuProyecto();
            }
        });
        viewProyectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerProyecto();
            }
        });
        modifyMaterialsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModificarMateriales();
            }
        });
    }

}
