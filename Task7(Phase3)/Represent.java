import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Represent extends JFrame {

    private String[][] data;

    public Represent(String[][] data) {
        this.data = data;

        setTitle("Proposed Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JTable and set its data from the 2D array
        JTable table = new JTable(new DefaultTableModel(data, new String[data[0].length]));
        table.setPreferredSize(new Dimension(1000, 300));
        // Set the table as the content pane
        setContentPane(table);

        pack();
        setLocationRelativeTo(null);
    }

   
}

