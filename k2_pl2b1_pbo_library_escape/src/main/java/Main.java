package main.java;

import javax.swing.SwingUtilities;
import main.java.core.Engine;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Engine::new); // Aman untuk GUI
    }
}
