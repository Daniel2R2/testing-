package com.daniel.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReporteUI extends JFrame {
    public ReporteUI(List<String[]> resultados) {
        setTitle("Reporte de Pruebas de API");
        String[] columnas = {"ID", "Tipo", "Código", "Estado"};

        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        for (String[] fila : resultados) model.addRow(fila);

        JTable tabla = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
