package Ejercicio3;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Invocamos la interfaz mediante utilidades Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CatalogoPeliculas();
            }
        });
    }
}
