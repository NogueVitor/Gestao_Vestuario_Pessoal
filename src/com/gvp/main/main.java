package com.gvp.main;
import com.gvp.view.GestorVestuarioGUI;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new GestorVestuarioGUI().setVisible(true);
        });
    }
}